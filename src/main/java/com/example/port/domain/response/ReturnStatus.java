package com.example.port.domain.response;

/**
 * @author lilin
 * @date 2019-01-31
 */
public enum  ReturnStatus {
    SUCCESS("200"),
    FAILED("500");
    private String code;
    ReturnStatus(String code){
        this.code = code ;
    }

    public String getCode() {
        return code;
    }
}
