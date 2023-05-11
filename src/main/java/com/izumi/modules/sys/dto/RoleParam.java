package com.izumi.modules.sys.dto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import com.izumi.validation.Groups;
/**
 * <p>
 * 角色
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "角色")
public class RoleParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键" , required = true )
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空" , groups = {Groups.Update.class} )
    private Long id;

    @ApiModelProperty(value="角色" , required = true )
    @NotBlank(message = "角色不能为空" )
    private String name;

    @ApiModelProperty(value="唯一编码" , required = true )
    @NotBlank(message = "唯一编码不能为空" )
    private String code;

    @ApiModelProperty(value="角色类型" , required = true )
    @NotNull(message = "角色类型不能为空" )
    private Integer roleType;


}
