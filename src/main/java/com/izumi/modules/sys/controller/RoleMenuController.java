package com.izumi.modules.sys.controller;

import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.modules.sys.dto.RoleMenuPageParam;
import com.izumi.modules.sys.dto.RoleMenuParam;
import com.izumi.modules.sys.entity.RoleMenu;
import com.izumi.modules.sys.service.RoleMenuService;;
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
    * r_角色菜单关系 前端控制器
    * </p>
*
* @author izumi
* @since 2023-05-11
*/
@RestController
@Api(tags = "r_角色菜单关系管理")
@RequiredArgsConstructor
public class RoleMenuController {
    private final RoleMenuService roleMenuService;

    /**
     * 添加r_角色菜单关系
     * @param param
     * @return
     */
    @PostMapping("/sys/roleMenu/save")
    @ApiOperation(value = "添加r_角色菜单关系")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) RoleMenuParam param) {
        roleMenuService.save(param);
        return CommonResult.ok();
    }

    /**
     * 删除r_角色菜单关系
     * @param param
     * @return
     */
    @PostMapping("/roleMenu/remove")
    @ApiOperation(value = "删除r_角色菜单关系")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        roleMenuService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改r_角色菜单关系
     * @param param
     * @return
     */
    @PostMapping("/roleMenu/update")
    @ApiOperation(value = "修改r_角色菜单关系")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) RoleMenuParam param) {
        roleMenuService.update(param);
        return CommonResult.ok();
    }

    /**
     * 查询单个r_角色菜单关系
     * @param param
     * @return
     */
    @PostMapping("/roleMenu/getById")
    @ApiOperation(value = "查询单个r_角色菜单关系")
    public CommonResult<RoleMenu> getById(@RequestBody IdParam param) {
        RoleMenu roleMenu = roleMenuService.getById(param.getId());
        return CommonResult.data(roleMenu);
    }

    /**
     * 分页查询r_角色菜单关系列表
     * @param param
     * @return
     */
    @PostMapping("/roleMenu/page")
    @ApiOperation(value = "查询r_角色菜单关系列表")
    public CommonResult<CommonPage<RoleMenu>> page(@RequestBody RoleMenuPageParam param) {
        return CommonResult.data(roleMenuService.page(param));
    }
}
