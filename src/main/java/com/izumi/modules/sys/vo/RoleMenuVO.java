package com.izumi.modules.sys.vo;

import com.izumi.modules.sys.entity.RoleMenu;
import io.swagger.annotations.ApiModel;
import lombok.Data;
/**
* <p>
* r_角色菜单关系
* </p>
*
* @author izumi
* @since 2023-05-12
*/
@Data
@ApiModel(value = "RoleMenuVO对象", description = "r_角色菜单关系VO")
public class RoleMenuVO extends RoleMenu {
}
