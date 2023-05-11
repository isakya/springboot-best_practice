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
 * 用户
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("姓名")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("密码加盐")
    private String salt;

    @ApiModelProperty("手机号")
    private String mobilePhone;

    @ApiModelProperty("联系电话")
    private String tel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("管理员类型<sys_admin_type>")
    private Integer adminType;

    @ApiModelProperty("性别<sys_sex>")
    private Integer sex;

    @ApiModelProperty("是否锁定")
    private Boolean isLocked;

    @ApiModelProperty("所属部门")
    private Long deptId;

    @ApiModelProperty("所属岗位")
    private Long postId;

    @ApiModelProperty("备注")
    private String remark;

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
