package com.example.port.processor.impl;

import com.example.port.annotation.PaymentChannel;
import com.example.port.processor.PaymentChannelProcessor;
import com.example.port.processor.PaymentService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService, InitializingBean {

    @Autowired
    private List<PaymentChannelProcessor> paymentChannelProcessors;

    private Map<String,PaymentChannelProcessor> map = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        for(PaymentChannelProcessor paymentChannelProcessor:paymentChannelProcessors){
            if(paymentChannelProcessor.getClass().isAnnotationPresent(PaymentChannel.class)){
                PaymentChannel annotation = paymentChannelProcessor.getClass().getAnnotation(PaymentChannel.class);
                map.put(annotation.value(),paymentChannelProcessor);
            }
        }
    }





    @Override
    public Object repayment(Object o) {
        String way = "helibao";
        PaymentChannelProcessor paymentChannelProcessor = map.get(way);
        return paymentChannelProcessor.repayment(o);
    }
}
