package com.izumi.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "菜单")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("应用编码")
    private String appCode;

    @ApiModelProperty("父ID")
    private Long parentId;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("唯一编码")
    private String code;

    @ApiModelProperty("父ID集合")
    private String pids;

    @ApiModelProperty("菜单类型<sys_menu_type>")
    private Integer type;

    @ApiModelProperty("排序")
    private Long sort;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件地址")
    private String component;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("是否显示")
    private Boolean isShow;

    @ApiModelProperty("是否链接")
    private Boolean isLink;

    @ApiModelProperty("外部链接地址")
    private String url;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

    @ApiModelProperty("打开方式<sys_menu_open_type>")
    private Integer openType;

    @ApiModelProperty("是否缓存")
    private Boolean isCache;

    @ApiModelProperty("是否同步")
    private Boolean isSync;

    @ApiModelProperty("额外参数JSON")
    private String variable;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建用户")
    private Long createUser;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("更新用户")
    private Long updateUser;

    @ApiModelProperty("是否删除")
    private Boolean isDeleted;


}
