package com.izumi.modules.sys.dto;

import com.izumi.base.PageParam;
import com.izumi.modules.sys.entity.RoleMenu;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class RoleMenuPageParam extends PageParam<RoleMenu> {

}
