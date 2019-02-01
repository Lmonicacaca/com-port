package com.example.port.common.redis.impl;

import com.example.port.common.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 类名称: RedisServiceImpl
 * 类描述: Redis缓存接口
 *
 * @author: lilin
 * @date: 2019-02-01
 */
//@Service
public abstract class AbstractRedisService<K,V> implements RedisService<K,V> {

    private final Logger logger = LoggerFactory.getLogger(AbstractRedisService.class);

    @Resource
    private RedisTemplate<K,V> redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<>();

    /**
     * 解锁lua语句
     */
    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    /**
     * 调用set后的返回值
     */
    public static final String OK = "OK";

    /**
     *
     * @param key
     * @param expire 过期单位：秒
     * @return
     */
    @Override
    public boolean lock(String key, long expire) {
        try {
            String result = redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                    String uuid = UUID.randomUUID().toString();
                    lockFlag.set(uuid);
                    String result = commands.set(key, uuid, "NX", "EX", expire);
                    return result;
                }
            });
            return OK.equalsIgnoreCase(result);
        } catch (Exception e) {
            logger.error("set redis occured an exception", e);
        }
        return false;
    }

    @Override
    public boolean unLock(String key) {

        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            List<String> keys = new ArrayList<String>();
            keys.add(key);
            List<String> args = new ArrayList<String>();
            if(null == lockFlag.get()){
                throw new NullPointerException("lockFlag不可为空，检查是否尝试解另一个线程加的锁");
            }
            args.add(lockFlag.get());

            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本

            Long result = redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    Object nativeConnection = connection.getNativeConnection();
                    // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                    // 集群模式
                    if (nativeConnection instanceof JedisCluster) {
                        return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }

                    // 单机模式
                    else if (nativeConnection instanceof Jedis) {
                        return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                    }
                    return 0L;
                }
            });

            return result != null && result > 0;
        } catch (Exception e) {
            logger.error("release lock occured an exception", e);
        }
        return false;
    }

    /**
     * 模糊删除前缀的key
     * @param prex
     */
    @Override
    public void deleteByPrex(K prex) {
        K key= (K) (prex+"*");
        Set<K> keys=redisTemplate.keys(key);
        if(keys!=null&&keys.size()>0){
            redisTemplate.delete(keys);
        }
    }

    /**
     * 模糊删除后缀的key
     * @param suffix
     */
    @Override
    public void deleteBySuffix(K suffix) {
        K key= (K) ("*"+suffix);
        Set<K> keys=redisTemplate.keys(key);
        if(keys!=null&&keys.size()>0){
            redisTemplate.delete(keys);
        }
    }

    /**
     * 保存
     * @param key
     * @param value
     */
    @Override
    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 设置key过期时间
     * @param key
     * @param timeOut
     * @return
     */
    @Override
    public boolean expire(K key, int timeOut) {
        return redisTemplate.expire(key,timeOut,TimeUnit.SECONDS);
    }

    /**
     *
     * @param key
     * @param value
     * @param timeOut 秒
     */
    @Override
    public void set(K key, V value, int timeOut) {
        redisTemplate.opsForValue().set(key, value , timeOut, TimeUnit.SECONDS);
    }

    /**
     * 获取redis 参数值
     * @param key
     * @return
     */
    @Override
    public Object get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 是否存在
     * @param key
     * @return
     */
    @Override
    public boolean hasKey(K key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除一个key
     * @param key
     */
    @Override
    public void deleteKey(K key) {
        redisTemplate.delete(key);
    }


    /**
     * 自增
     * @param key
     * @param value
     */
    @Override
    public void increment(K key, int value) {
        redisTemplate.opsForValue().increment(key,value);
    }

    /**
     * 模糊查询key
     * @param pattern
     * @return
     */
    @Override
    public Set keys(String pattern) {
        return redisTemplate.keys((K)pattern);
    }
}
