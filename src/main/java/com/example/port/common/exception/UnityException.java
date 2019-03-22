package com.example.port.common.exception;

import com.example.port.domain.response.ReturnStatus;

public class UnityException extends RuntimeException {
    private ReturnStatus returnStatus;
    public UnityException(ReturnStatus returnStatus){
        this.returnStatus = returnStatus;
    }

    public String getCode(){
        return returnStatus.getCode();
    }
}
