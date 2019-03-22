package com.example.port.exception;

import com.example.port.common.exception.UnityException;
import com.example.port.domain.response.ResponseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlering {

    @ResponseBody
    @ExceptionHandler(value = UnityException.class)
    public ResponseModel<String> unityExceptionHandler(UnityException ex) {
        return new ResponseModel<>(ex.getCode());
    }
}
