package com.izumi.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class IdsParam {
    @ApiModelProperty("ids集合")
    private List<Long> ids;
}
