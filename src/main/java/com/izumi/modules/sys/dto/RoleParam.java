package com.izumi.modules.sys.dto;

import com.izumi.validation.FlagValidator;
import com.izumi.validation.Groups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@ApiModel
public class RoleParam {
    @ApiModelProperty("角色ID")
    @NotNull(message = "角色ID不能为空", groups = {Groups.Update.class})
    private Long id;
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;
    @ApiModelProperty("唯一编码")
    @NotBlank(message = "唯一编码不能为空")
    private String code;
    @ApiModelProperty("角色类型")
    @FlagValidator(values = "1,2,3", message = "角色类型只能为1,2,3")
    private Integer roleType;
}