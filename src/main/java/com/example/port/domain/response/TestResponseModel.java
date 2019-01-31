package com.example.port.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lilin
 * @date 2019-01-31
 */
@ApiModel
@Setter
@Getter
@ToString
public class TestResponseModel {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "自我介绍")
    private String content;
}
