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
 * 岗位
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_post")
@ApiModel(value = "SysPost对象", description = "岗位")
public class SysPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("岗位ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("岗位名称")
    private String name;

    @ApiModelProperty("唯一编码")
    private String code;

    @ApiModelProperty("排序")
    private Long sort;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

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
