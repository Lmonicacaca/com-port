package com.example.port.controller;

import com.example.port.domain.request.TestRequestModel;
import com.example.port.domain.response.ResponseModel;
import com.example.port.domain.response.TestResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilin
 * @date 2019-01-31
 */
@RestController
@RequestMapping("/test")
@Api(value = "测试类")
public class TestController {
    @ApiOperation(value = "测试方法")
    @RequestMapping(value = "/testController",method = RequestMethod.POST)
    public ResponseModel<TestResponseModel> testController(@RequestBody TestRequestModel testRequestModel){
        ResponseModel responseModel = new ResponseModel();
        TestResponseModel testResponseModel = new TestResponseModel();
        testResponseModel.setName(testRequestModel.getName());
        testResponseModel.setContent("是个好人！！");
        responseModel.setData(testResponseModel);
        return responseModel;
    }
}
