package com.izumi.modules.sys.controller;

import com.izumi.auth.Perm;
import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.modules.sys.dto.RolePageParam;
import com.izumi.modules.sys.dto.RoleParam;
import com.izumi.modules.sys.entity.Role;
import com.izumi.modules.sys.service.RoleService;
import com.izumi.modules.sys.vo.RoleVO;
import com.izumi.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



/**
* <p>
    * 角色 前端控制器
    * </p>
*
* @author izumi
* @since 2023-05-12
*/
@RestController
@Api(tags = "角色管理")
@RequiredArgsConstructor
@RequestMapping("/sys")
public class RoleController {
    private final RoleService roleService;

    /**
     * 添加角色
     * @param param
     * @return
     */
    @PostMapping("/role/save")
    @ApiOperation(value = "添加角色")
    @Perm
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) RoleParam param) {
        roleService.save(param);
        return CommonResult.ok();
    }

    /**
     * 删除角色
     * @param param
     * @return
     */
    @PostMapping("/role/remove")
    @ApiOperation(value = "删除角色")
    @Perm
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        roleService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改角色
     * @param param
     * @return
     */
    @PostMapping("/role/update")
    @ApiOperation(value = "修改角色")
    @Perm
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) RoleParam param) {
        roleService.update(param);
        return CommonResult.ok();
    }

    /**
     * 查询单个角色
     * @param param
     * @return
     */
    @PostMapping("/role/getById")
    @ApiOperation(value = "查询单个角色")
    @Perm
    public CommonResult<Role> getById(@RequestBody IdParam param) {
        Role role = roleService.getById(param.getId());
        return CommonResult.data(role);
    }

    /**
     * 分页查询角色列表
     * @param param
     * @return
     */
    @PostMapping("/role/page")
    @ApiOperation(value = "查询角色列表")
    @Perm
    public CommonResult<CommonPage<RoleVO>> page(@RequestBody RolePageParam param) {
        return CommonResult.data(roleService.page(param));
    }
}
