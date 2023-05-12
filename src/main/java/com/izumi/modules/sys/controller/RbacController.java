package com.izumi.modules.sys.controller;

import com.izumi.auth.Perm;
import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.modules.sys.dto.RoleMenuParam;
import com.izumi.modules.sys.dto.RoleParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserRoleParam;
import com.izumi.modules.sys.service.RbacService;
import com.izumi.modules.sys.vo.UserVO;
import com.izumi.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "RBAC相关接口")
@RequiredArgsConstructor
@RequestMapping("/sys")
public class RbacController {
    private final RbacService rbacService;
    @PostMapping("/rbac/saveRoleMenu")
    @ApiOperation(value = "保存角色菜单关系")
    public CommonResult<?> saveRoleMenu(@RequestBody @Validated() List<RoleMenuParam> param) {
        rbacService.saveRoleMenu(param);
        return CommonResult.ok();
    }
    @PostMapping("/rbac/roleMenuIds")
    @ApiOperation(value = "根据角色ID获取菜单ID集合")
    public CommonResult<List<String>> roleMenuIds(@RequestBody @Validated() RoleMenuParam param) {
        return CommonResult.data(rbacService.roleMenuIds(param.getRoleId()));
    }
    @PostMapping("/rbac/userListByRoleId")
    @ApiOperation(value = "通过角色ID获取用户列表")
    public CommonResult<CommonPage<UserVO>> userListByRoleId(@RequestBody @Validated() UserPageParam param) {
        return CommonResult.data(rbacService.userListByRoleId(param));
    }
    @PostMapping("/rbac/userListExcludeRoleId")
    @ApiOperation(value = "获取用户列表-排除指定角色")
    public CommonResult<CommonPage<UserVO>> userListExcludeRoleId(@RequestBody @Validated() UserPageParam param) {
        return CommonResult.data(rbacService.userListExcludeRoleId(param));
    }
    @PostMapping("/rbac/saveUserRole")
    @ApiOperation(value = "保存用户和角色关系")
    public CommonResult<?> saveUserRole(@RequestBody @Validated() List<UserRoleParam> param) {
        rbacService.saveUserRole(param);
        return CommonResult.ok();
    }
    @PostMapping("/rbac/removeUserRole")
    @ApiOperation(value = "删除用户和角色关系")
    public CommonResult<?> removeUserRole(@RequestBody @Validated() List<UserRoleParam> param) {
        rbacService.removeUserRole(param);
        return CommonResult.ok();
    }
}
