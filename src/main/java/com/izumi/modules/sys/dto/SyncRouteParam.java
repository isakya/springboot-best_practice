package com.izumi.modules.sys.dto;

import cn.hutool.core.lang.Dict;
import com.izumi.modules.sys.entity.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "SyncRouteParam",description = "同步前端路由实体")
public class SyncRouteParam extends Menu {
    @ApiModelProperty(value = "扩展属性")
    private Dict ext;

    private List<SyncRouteParam> children;
}
