package com.izumi.modules.sys.dto;

import com.izumi.base.PageParam;
import com.izumi.modules.sys.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class RolePageParam extends PageParam<Role> {
    @ApiModelProperty(value = "角色【等值查询】")
    private String m_EQ_name;
    @ApiModelProperty(value = "角色【模糊查询】")
    private String m_LIKE_name;
    @ApiModelProperty(value = "唯一编码【等值查询】")
    private String m_EQ_code;
    @ApiModelProperty(value = "唯一编码【模糊查询】")
    private String m_LIKE_code;
    @ApiModelProperty(value = "角色类型【等值查询】")
    private Integer m_EQ_roleType;
}
