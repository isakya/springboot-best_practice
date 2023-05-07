package com.izumi.modules.sys.dto;


import com.izumi.validation.Groups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UserParam {
    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为空", groups = {Groups.Update.class})
    private Long id;
    @ApiModelProperty("用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String userName;
    @ApiModelProperty("用户密码")
    @NotBlank(message = "用户密码不能为空")
    private String password;
}
