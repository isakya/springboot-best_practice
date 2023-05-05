package com.izumi.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class CommonResult<T> {
    @ApiModelProperty(value = "状态码（0：成功，其他失败）")
    private int code;
    @ApiModelProperty(value = "消息描述")
    private String msg;
    @ApiModelProperty(value = "返回的数据")
    private T data;

    public CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功
     * @return
     */
    public static CommonResult data(Object data) {
        return new CommonResult(0, "成功", data);
    }

    public static CommonResult fail(String msg) {
        return new CommonResult(9999, msg, null);
    }

    public static CommonResult fail(int code, String msg) {
        return new CommonResult(code, msg, null);
    }
}
