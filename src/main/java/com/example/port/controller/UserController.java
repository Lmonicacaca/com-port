package com.example.port.controller;

/**
 * @author lilin
 * @date 2019-01-31
 */

import com.example.port.common.exception.UnityException;
import com.example.port.domain.DO.UserDO;
import com.example.port.domain.response.ResponseModel;
import com.example.port.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户详情")
    public ResponseModel<UserDO> getUser() {
        ResponseModel responseModel = new ResponseModel();
        List<UserDO> userDOS = userService.queryAllUser();
        if(userDOS != null){
            responseModel.setData(userDOS);
        }
        return responseModel;
    }
}
