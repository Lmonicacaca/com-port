package com.example.port.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.port.annotation.Oplog;
import com.example.port.domain.request.TestRequestModel;
import com.example.port.domain.response.ResponseModel;
import com.example.port.domain.response.TestResponseModel;
import com.example.port.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lilin
 * @date 2019-01-31
 */
@Controller
@RequestMapping("/test")
@Api(value = "测试类")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "测试方法")
    @ResponseBody
    @RequestMapping(value = "/testController")
    public String testController(){
        log.info("进入");
        ResponseModel responseModel = new ResponseModel();
        testService.testJvm();
        return "9999";
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    @Oplog(message = "11111")
    public String hello(){
        System.out.println("into hello");
        return "port:9999 hello";
    }


}
