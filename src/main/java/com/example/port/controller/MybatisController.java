package com.example.port.controller;

import com.example.port.domain.DO.UserDO;
import com.example.port.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getInfo")
    @ResponseBody
    public Object getInfo(){
        UserDO userDO = new UserDO();
        userDO.setUserName("admin");
        return userService.getUserDao(userDO);
    }


}
