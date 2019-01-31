package com.example.port.service.impl;

import com.example.port.dao.UserDao;
import com.example.port.domain.DO.UserDO;
import com.example.port.service.UserService;
import org.springframework.stereotype.Service;

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
        return userDao.queryAllUser();
    }
}
