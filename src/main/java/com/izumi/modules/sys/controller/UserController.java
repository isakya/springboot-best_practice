package com.izumi.modules.sys.controller;

import com.izumi.auth.UserPerm;
import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserParam;
import com.izumi.modules.sys.entity.User;
import com.izumi.modules.sys.enums.UserTypePerm;
import com.izumi.modules.sys.service.UserService;
import com.izumi.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Api(tags = "用户管理")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 添加用户
     * @param param
     * @return
     */
    @PostMapping("/user/save")
    @ApiOperation(value = "添加用户")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) UserParam param) {
        userService.save(param);
        return CommonResult.ok();
    }

    /**
     * 删除用户
     * @param param
     * @return
     */
    @PostMapping("/user/remove")
    @ApiOperation(value = "删除用户")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        userService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改用户
     * @param param
     * @return
     */
    @PostMapping("/user/update")
    @ApiOperation(value = "修改用户")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) UserParam param) {
        userService.update(param);
        return CommonResult.ok();
    }

    /**
     * 查询单个用户
     * @param param
     * @return
     */
    @PostMapping("/user/getById")
    @ApiOperation(value = "查询单个用户")
    @UserPerm(UserTypePerm.COMMON)
    public CommonResult<User> getById(@RequestBody IdParam param) {
        User User = userService.getById(param.getId());
        return CommonResult.data(User);
    }

    /**
     * 分页查询用户列表
     * @param param
     * @return
     */
    @PostMapping("/user/page")
    @ApiOperation(value = "查询用户列表")
    @UserPerm({UserTypePerm.COMMON,UserTypePerm.ADMIN})
    public CommonResult<CommonPage<User>> page(@RequestBody UserPageParam param) {
        return CommonResult.data(userService.page(param));
    }
}
