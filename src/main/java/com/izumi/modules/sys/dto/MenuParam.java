package com.izumi.modules.sys.dto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import com.izumi.validation.Groups;
/**
 * <p>
 * 菜单
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "菜单")
public class MenuParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="菜单ID" , required = true )
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "菜单ID不能为空" , groups = {Groups.Update.class} )
    private Long id;

    @ApiModelProperty(value="应用编码" )
    private String appCode;

    @ApiModelProperty(value="父ID" )
    private Long parentId;

    @ApiModelProperty(value="菜单名称" , required = true )
    @NotBlank(message = "菜单名称不能为空" )
    private String name;

    @ApiModelProperty(value="唯一编码" , required = true )
    @NotBlank(message = "唯一编码不能为空" )
    private String code;

    @ApiModelProperty(value="父ID集合" )
    private String pids;

    @ApiModelProperty(value="菜单类型<sys_menu_type>" )
    private Integer type;

    @ApiModelProperty(value="排序" )
    private Long sort;

    @ApiModelProperty(value="路由地址" )
    private String path;

    @ApiModelProperty(value="组件地址" )
    private String component;

    @ApiModelProperty(value="菜单图标" )
    private String icon;

    @ApiModelProperty(value="是否显示" )
    private Boolean isShow;

    @ApiModelProperty(value="是否链接" )
    private Boolean isLink;

    @ApiModelProperty(value="外部链接地址" )
    private String url;

    @ApiModelProperty(value="是否启用" )
    private Boolean enabled;

    @ApiModelProperty(value="打开方式<sys_menu_open_type>" )
    private Integer openType;

    @ApiModelProperty(value="是否缓存" )
    private Boolean isCache;

    @ApiModelProperty(value="是否同步" )
    private Boolean isSync;

    @ApiModelProperty(value="额外参数JSON" )
    private String variable;

    @ApiModelProperty(value="创建时间" )
    private LocalDateTime createTime;

    @ApiModelProperty(value="创建用户" )
    private Long createUser;

    @ApiModelProperty(value="更新时间" )
    private LocalDateTime updateTime;

    @ApiModelProperty(value="更新用户" )
    private Long updateUser;

    @ApiModelProperty(value="是否删除" )
    private Boolean isDeleted;


}
