package com.izumi.dto;

import com.izumi.base.PageParam;
import com.izumi.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel
@Data
public class UserPageParam extends PageParam<User> {

}
