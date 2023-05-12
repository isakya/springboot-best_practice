package com.izumi.modules.sys.dto;

import com.izumi.base.PageParam;
import com.izumi.modules.sys.entity.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class MenuPageParam extends PageParam<Menu> {
    @ApiModelProperty(value = "应用编码-等值查询")
    private String m_EQ_appCode;
}
