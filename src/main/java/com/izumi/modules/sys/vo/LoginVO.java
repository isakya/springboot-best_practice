package com.izumi.modules.sys.vo;

import cn.hutool.core.collection.CollectionUtil;
import com.izumi.modules.sys.enums.UserTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class LoginVO {
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "访问凭证")
    private String token;
    @ApiModelProperty(value = "权限标识集合")
    private List<String> perms;
    @ApiModelProperty(value = "是否是超级管理员")
    private boolean superAdmin;
    private UserTypeEnum userType;
    /**
     * 是否有权限
     * @param perm
     * @return
     */
    public boolean hasPerm(String perm) {
        return CollectionUtil.isNotEmpty(perms) && perms.contains(perm);
    }
}
