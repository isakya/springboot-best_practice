package com.izumi.modules.sys.vo;

import com.izumi.modules.sys.entity.Role;
import io.swagger.annotations.ApiModel;
import lombok.Data;
/**
* <p>
* 角色
* </p>
*
* @author izumi
* @since 2023-05-12
*/
@Data
@ApiModel(value = "RoleVO对象", description = "角色VO")
public class RoleVO extends Role {
}
