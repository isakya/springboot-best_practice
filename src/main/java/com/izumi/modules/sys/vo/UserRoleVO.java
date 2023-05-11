package com.izumi.modules.sys.vo;

import com.izumi.modules.sys.entity.UserRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;
/**
* <p>
* r_用户角色关系
* </p>
*
* @author izumi
* @since 2023-05-12
*/
@Data
@ApiModel(value = "UserRoleVO对象", description = "r_用户角色关系VO")
public class UserRoleVO extends UserRole {
}
