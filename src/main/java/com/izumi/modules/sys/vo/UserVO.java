package com.izumi.modules.sys.vo;

import com.izumi.modules.sys.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "UserVO对象", description = "用户VO")
public class UserVO extends User {
    private String postName;
}
