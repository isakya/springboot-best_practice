package com.izumi.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IdParam {
    @ApiModelProperty("id")
    private Long id;
}
