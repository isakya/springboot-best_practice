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
 * r_角色菜单关系
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_role_menu")
@ApiModel(value = "RoleMenuParam对象", description = "r_角色菜单关系")
public class RoleMenuParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键" , required = true )
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "主键不能为空" , groups = {Groups.Update.class} )
    private Long id;

    @ApiModelProperty(value="角色ID" , required = true )
    @NotNull(message = "角色ID不能为空",groups = {ValidRoleId.class})
    private Long roleId;

    @ApiModelProperty(value="菜单ID" , required = true )
    @NotNull(message = "菜单ID不能为空" )
    private Long menuId;

    public interface ValidRoleId {}


}
