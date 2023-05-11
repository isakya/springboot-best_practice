package com.izumi.modules.sys.controller;

import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.modules.sys.dto.MenuPageParam;
import com.izumi.modules.sys.dto.MenuParam;
import com.izumi.modules.sys.entity.Menu;
import com.izumi.modules.sys.service.MenuService;
import com.izumi.modules.sys.vo.MenuVO;
import com.izumi.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
* <p>
    * 菜单 前端控制器
    * </p>
*
* @author izumi
* @since 2023-05-12
*/
@RestController
@Api(tags = "菜单管理")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    /**
     * 添加菜单
     * @param param
     * @return
     */
    @PostMapping("/sys/menu/save")
    @ApiOperation(value = "添加菜单")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) MenuParam param) {
        menuService.save(param);
        return CommonResult.ok();
    }

    /**
     * 删除菜单
     * @param param
     * @return
     */
    @PostMapping("/menu/remove")
    @ApiOperation(value = "删除菜单")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        menuService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改菜单
     * @param param
     * @return
     */
    @PostMapping("/menu/update")
    @ApiOperation(value = "修改菜单")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) MenuParam param) {
        menuService.update(param);
        return CommonResult.ok();
    }

    /**
     * 查询单个菜单
     * @param param
     * @return
     */
    @PostMapping("/menu/getById")
    @ApiOperation(value = "查询单个菜单")
    public CommonResult<Menu> getById(@RequestBody IdParam param) {
        Menu menu = menuService.getById(param.getId());
        return CommonResult.data(menu);
    }

    /**
     * 分页查询菜单列表
     * @param param
     * @return
     */
    @PostMapping("/menu/page")
    @ApiOperation(value = "查询菜单列表")
    public CommonResult<CommonPage<MenuVO>> page(@RequestBody MenuPageParam param) {
        return CommonResult.data(menuService.page(param));
    }
}
