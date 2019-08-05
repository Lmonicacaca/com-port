package com.example.port.processor.impl;

import com.example.port.annotation.PaymentChannel;
import com.example.port.processor.PaymentChannelProcessor;
import org.springframework.stereotype.Service;

@Service
@PaymentChannel(value = "helibao")
public class HelibaoPaymentChannelProcessor implements PaymentChannelProcessor {
    @Override
    public Object repayment(Object object) {
        return "This is helibao";
    }
}
