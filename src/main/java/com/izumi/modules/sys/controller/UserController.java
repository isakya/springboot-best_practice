package com.izumi.modules.sys.controller;

import com.izumi.auth.Perm;
import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserParam;
import com.izumi.modules.sys.entity.User;
import com.izumi.modules.sys.service.UserService;;
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


/**
* <p>
    * 用户 前端控制器
    * </p>
*
* @author izumi
* @since 2023-05-11
*/
@RestController
@Api(tags = "用户管理")
@RequiredArgsConstructor
@RequestMapping("/sys")
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
    public CommonResult<User> getById(@RequestBody IdParam param) {
        User user = userService.getById(param.getId());
        return CommonResult.data(user);
    }

    /**
     * 分页查询用户列表
     * @param param
     * @return
     */
    @PostMapping("/user/page")
    @ApiOperation(value = "查询用户列表")
    @Perm
    public CommonResult<CommonPage<UserVO>> page(@RequestBody UserPageParam param) {
        return CommonResult.data(userService.page(param));
    }
}
