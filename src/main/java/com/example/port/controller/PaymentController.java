package com.example.port.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.port.processor.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @ResponseBody
    @RequestMapping("/payment")
    public String payment(){
        return JSONObject.toJSONString(paymentService.repayment(new Object()));
    }
}
