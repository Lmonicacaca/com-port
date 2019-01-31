package com.example.port.domain.DO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lilin
 * @date 2019-01-31
 */
@Setter
@Getter
@ToString
@Table(name = "users")
@ApiModel
public class UserDO {
    @Id
    private Long id;
    @Column(name = "username")
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    @Column(name = "password")
    @ApiModelProperty(value = "密码")
    private String passWord;
}
