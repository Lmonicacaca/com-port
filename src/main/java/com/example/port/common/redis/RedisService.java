package com.example.port.common.redis;

import java.util.Set;

/**
 * 类名称: RedisService
 * 类描述: redis 的操作开放接口
 *
 * @author: lilin
 * @date: 2019-02-01
 */
public interface RedisService<K,V> {

    /**
     * 模糊删除前缀的key
     * @param prex
     */
    public void deleteByPrex(K prex);

    /**
     * 模糊删除后缀的key
     * @param suffix
     */
    public void deleteBySuffix(K suffix);


    /**
     * 自增
     * @param key
     * @param value
     */
    public void increment(K key, int value);

    /**
     * 设置key过期时间
     * @param key
     * @param timeOut
     * @return
     */
    public boolean expire(K key, int timeOut);

    public void set(K key, V value);

    public void set(K key, V value, int timeOut);

    public Object get(K key);

    public boolean hasKey(K key);

    public void deleteKey(K key);

    /**
     * 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false
     * @param key
     * @param timeOut
     * @return
     */
    public boolean lock(String key, long timeOut);

    /**
     * 释放锁
     */
    public boolean unLock(String key);


    /**
     * 初始化系统参数
     * @return
     */
    public boolean initSysValue();

    /**
     * 获取系统参数
     * @param key
     * @return
     */
    public String getSysValue(K key);


    String getMessageTemplate(K key);

    public boolean initMessageTemplate();

    /**
     * 模糊匹配key
     * @param pattern
     * @return
     */
    public Set keys(String pattern);

}
