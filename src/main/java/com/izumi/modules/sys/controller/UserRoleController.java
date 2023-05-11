package com.izumi.modules.sys.controller;

import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.modules.sys.dto.UserRolePageParam;
import com.izumi.modules.sys.dto.UserRoleParam;
import com.izumi.modules.sys.entity.UserRole;
import com.izumi.modules.sys.service.UserRoleService;;
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
    * r_用户角色关系 前端控制器
    * </p>
*
* @author izumi
* @since 2023-05-11
*/
@RestController
@Api(tags = "r_用户角色关系管理")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

    /**
     * 添加r_用户角色关系
     * @param param
     * @return
     */
    @PostMapping("/sys/userRole/save")
    @ApiOperation(value = "添加r_用户角色关系")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) UserRoleParam param) {
        userRoleService.save(param);
        return CommonResult.ok();
    }

    /**
     * 删除r_用户角色关系
     * @param param
     * @return
     */
    @PostMapping("/userRole/remove")
    @ApiOperation(value = "删除r_用户角色关系")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        userRoleService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改r_用户角色关系
     * @param param
     * @return
     */
    @PostMapping("/userRole/update")
    @ApiOperation(value = "修改r_用户角色关系")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) UserRoleParam param) {
        userRoleService.update(param);
        return CommonResult.ok();
    }

    /**
     * 查询单个r_用户角色关系
     * @param param
     * @return
     */
    @PostMapping("/userRole/getById")
    @ApiOperation(value = "查询单个r_用户角色关系")
    public CommonResult<UserRole> getById(@RequestBody IdParam param) {
        UserRole userRole = userRoleService.getById(param.getId());
        return CommonResult.data(userRole);
    }

    /**
     * 分页查询r_用户角色关系列表
     * @param param
     * @return
     */
    @PostMapping("/userRole/page")
    @ApiOperation(value = "查询r_用户角色关系列表")
    public CommonResult<CommonPage<UserRole>> page(@RequestBody UserRolePageParam param) {
        return CommonResult.data(userRoleService.page(param));
    }
}
