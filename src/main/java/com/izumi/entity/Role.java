package com.izumi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
@TableName("sys_role")
public class Role {
    @ApiModelProperty("角色ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("唯一编码")
    private String code;
    @ApiModelProperty("角色类型")
    private Integer roleType;
}
