package com.example.port.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.port.common.exception.NoLoginException;
import com.example.port.dao.UserDao;
import com.example.port.domain.DO.UserDO;
import com.example.port.domain.response.ReturnStatus;
import com.example.port.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lilin
 * @date 2019-01-31
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public List<UserDO> queryAllUser() {
        if(true){
            throw new NoLoginException(ReturnStatus.NOT_LOGIN);
        }
        return userDao.queryAllUser();
    }

    @Override
    public UserDO getUserDao(UserDO user) {
        UserDO userDO = new UserDO();
        userDO.setUserName("test");
        List<UserDO> select = userDao.select(userDO);
        System.out.println(JSONObject.toJSONString(select));
        Example example = new Example(UserDO.class);
        example.createCriteria().andEqualTo("userName",userDO.getUserName());
        userDao.updateByExampleSelective(userDO,example);
        return userDao.selectOne(user);
    }
}
