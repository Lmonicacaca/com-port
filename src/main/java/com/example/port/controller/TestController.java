package com.example.port.controller;

import com.example.port.domain.request.TestRequestModel;
import com.example.port.domain.response.ResponseModel;
import com.example.port.domain.response.TestResponseModel;
import com.example.port.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lilin
 * @date 2019-01-31
 */
@RestController
@RequestMapping("/test")
@Api(value = "测试类")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "测试方法")
    @RequestMapping(value = "/testController")
    public ResponseModel<TestResponseModel> testController(){
        ResponseModel responseModel = new ResponseModel();
        testService.testJvm();
        return responseModel;
    }

    @RequestMapping(value = "/hello")
    public String hello(){
        return "port:9999 hello";
    }
}
