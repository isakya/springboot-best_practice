package com.izumi.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
@ApiModel
public class CommonPage<T> {
    @ApiModelProperty(value = "记录总数")
    private int recordCount;
    @ApiModelProperty(value = "总页数")
    private int totalPage;
    @ApiModelProperty(value = "每页大小")
    private int pageSize;
    @ApiModelProperty(value = "当前页")
    private int pageNum;
    @ApiModelProperty(value = "数据")
    private List<T> rows;
}
