package com.izumi.modules.sys.controller;

import com.izumi.base.CommonResult;
import com.izumi.modules.sys.dto.LoginParam;
import com.izumi.modules.sys.service.UserService;
import com.izumi.modules.sys.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "权限相关")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    /**
     * 登录
     * @param param
     * @return
     */
    @PostMapping("/sys/login")
    @ApiOperation(value = "登录")
    public CommonResult<LoginVO> login(@RequestBody @Validated() LoginParam param) {
        return CommonResult.data(userService.login(param));
    }

    /**
     * 登录
     * @param authorization
     * @return
     */
    @PostMapping("/sys/logout")
    @ApiOperation(value = "退出登录")
    public CommonResult<?> logout(@RequestHeader() String authorization) {
        String token = authorization.replace("Bearer ", "");
        userService.logout(token);
        return CommonResult.ok();
    }
}
