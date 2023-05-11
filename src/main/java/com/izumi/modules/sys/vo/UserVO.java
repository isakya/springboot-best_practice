package com.izumi.modules.sys.vo;

import com.izumi.modules.sys.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserVO对象", description = "用户VO")
public class UserVO extends User {
    @ApiModelProperty(value = "岗位名称")
    private String postName;
}
