package com.izumi.modules.sys.dto;

import com.izumi.base.PageParam;
import com.izumi.modules.sys.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserPageParam extends PageParam<User> {
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
}
