package com.izumi.modules.sys.controller;

import cn.hutool.core.lang.Dict;
import com.izumi.base.CommonResult;
import com.izumi.exception.ErrEnumScanner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "通用接口")
@RequiredArgsConstructor
public class CommonController {
    private final ErrEnumScanner errEnumScanner;
    @PostMapping("/common/errEnumData")
    @ApiOperation(value = "获取错误码枚举")
    public CommonResult<List<Dict>> errEnumData() {
        return CommonResult.data(errEnumScanner.getDatas());
    }
}
