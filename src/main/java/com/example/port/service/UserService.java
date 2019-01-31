package com.example.port.service;

import com.example.port.domain.DO.UserDO;

import java.util.List;

/**
 * @author lilin
 * @date 2019-01-31
 */
public interface UserService {
    public List<UserDO> queryAllUser();
}
