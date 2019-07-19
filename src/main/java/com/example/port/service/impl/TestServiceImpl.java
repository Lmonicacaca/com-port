package com.example.port.service.impl;

import com.example.port.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public void testJvm() {
        while (true){
            Object o = new Object();
        }
    }
}
