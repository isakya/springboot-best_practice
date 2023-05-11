package com.izumi.modules.sys.dto;

import com.izumi.base.PageParam;
import com.izumi.modules.sys.entity.Dept;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class DeptPageParam extends PageParam<Dept> {

}
