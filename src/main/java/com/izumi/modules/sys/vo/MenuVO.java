package com.izumi.modules.sys.vo;

import com.izumi.modules.sys.entity.Menu;
import io.swagger.annotations.ApiModel;
import lombok.Data;
/**
* <p>
* 菜单
* </p>
*
* @author izumi
* @since 2023-05-12
*/
@Data
@ApiModel(value = "MenuVO对象", description = "菜单VO")
public class MenuVO extends Menu {
}
