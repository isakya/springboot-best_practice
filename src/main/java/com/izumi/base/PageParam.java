package com.izumi.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PageParam<T> {
    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;


    /**
     * 构建mybatis-plus分页对象
     */
    public IPage buildMpPage() {
        Page page = new Page(getPageNum(), getPageSize());
        return page;
    }
}
