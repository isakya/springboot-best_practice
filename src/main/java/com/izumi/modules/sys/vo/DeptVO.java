package com.izumi.modules.sys.vo;

import com.izumi.modules.sys.entity.Dept;
import io.swagger.annotations.ApiModel;
import lombok.Data;
/**
* <p>
* 部门
* </p>
*
* @author izumi
* @since 2023-05-12
*/
@Data
@ApiModel(value = "DeptVO对象", description = "部门VO")
public class DeptVO extends Dept {
}
