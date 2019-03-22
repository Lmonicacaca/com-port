package com.example.port.service;

import com.example.port.common.exception.UnityException;
import com.example.port.domain.DO.UserDO;

import java.util.List;

/**
 * @author lilin
 * @date 2019-01-31
 */
public interface UserService {
    List<UserDO> queryAllUser();
}
