package com.izumi.modules.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class LoginVO {
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "访问凭证")
    private String token;
    @ApiModelProperty(value = "权限标识集合")
    private List<String> perms;
    @ApiModelProperty(value = "是否是超级管理员")
    private boolean superAdmin;
}
