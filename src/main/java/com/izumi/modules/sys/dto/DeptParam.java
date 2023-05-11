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
 * 部门
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Getter
@Setter
@TableName("sys_dept")
@ApiModel(value = "Dept对象", description = "部门")
public class DeptParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="部门ID" , required = true )
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(message = "部门ID不能为空" , groups = {Groups.Update.class} )
    private Long id;

    @ApiModelProperty(value="父ID" )
    private Long parentId;

    @ApiModelProperty(value="父ID集合" )
    private String pids;

    @ApiModelProperty(value="部门名称" , required = true )
    @NotBlank(message = "部门名称不能为空" )
    private String name;

    @ApiModelProperty(value="唯一编码" , required = true )
    @NotBlank(message = "唯一编码不能为空" )
    private String code;

    @ApiModelProperty(value="排序" )
    private Long sort;

    @ApiModelProperty(value="是否启用" )
    private Boolean enabled;

    @ApiModelProperty(value="部门负责人ID集合" )
    private String leaderIds;

    @ApiModelProperty(value="分管领导ID" )
    private Long mainLeaderId;

    @ApiModelProperty(value="备注" )
    private String remark;

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
