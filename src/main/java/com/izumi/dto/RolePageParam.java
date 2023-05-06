package com.izumi.dto;

import com.izumi.base.PageParam;
import com.izumi.entity.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class RolePageParam extends PageParam<Role> {
}
