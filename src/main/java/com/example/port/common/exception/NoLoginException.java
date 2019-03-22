package com.example.port.common.exception;

import com.example.port.domain.response.ReturnStatus;
import lombok.Getter;

@Getter
public class NoLoginException extends UnityException {
    public NoLoginException(ReturnStatus returnStatus){
        super(returnStatus);
    }
}
