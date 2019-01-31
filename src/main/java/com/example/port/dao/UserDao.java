package com.example.port.dao;

import com.example.port.common.dao.TkMapper;
import com.example.port.domain.DO.UserDO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lilin
 * @date 2019-01-31
 */
public interface UserDao extends TkMapper<UserDO> {
    @Select("select * from users")
    public List<UserDO> queryAllUser();
}
