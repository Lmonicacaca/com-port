package com.example.port.domain.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lilin
 * @date 2019-01-31
 */
@Setter
@Getter
@ToString
public class ResponseModel<T> {
    private String code = ReturnStatus.FAILED.getCode();
    private T data;
}
