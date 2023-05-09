package com.izumi.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class LoginParam {
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
