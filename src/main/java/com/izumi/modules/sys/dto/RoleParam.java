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
@ApiModel(value = "RoleParam对象", description = "角色")
public class RoleParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="角色ID" , required = true )
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "角色ID不能为空" , groups = {Groups.Update.class} )
    private Long id;

    @ApiModelProperty(value="角色名称" , required = true )
    @NotBlank(message = "角色名称不能为空" )
    private String name;

    @ApiModelProperty(value="唯一编码" , required = true )
    @NotBlank(message = "唯一编码不能为空" )
    private String code;

    @ApiModelProperty(value="角色类型<sys_role_type>" , required = true )
    @NotNull(message = "角色类型<sys_role_type>不能为空" )
    private Integer roleType;

    @ApiModelProperty(value="是否启用" , required = true )
    @NotNull(message = "是否启用不能为空" )
    private Boolean enabled;

    @ApiModelProperty(value="备注" )
    private String remark;


}
