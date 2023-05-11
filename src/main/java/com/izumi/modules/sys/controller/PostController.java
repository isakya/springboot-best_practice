package com.izumi.modules.sys.controller;

import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.modules.sys.dto.PostPageParam;
import com.izumi.modules.sys.dto.PostParam;
import com.izumi.modules.sys.entity.Post;
import com.izumi.modules.sys.service.PostService;;
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
    * 岗位 前端控制器
    * </p>
*
* @author izumi
* @since 2023-05-11
*/
@RestController
@Api(tags = "岗位管理")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * 添加岗位
     * @param param
     * @return
     */
    @PostMapping("/sys/post/save")
    @ApiOperation(value = "添加岗位")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) PostParam param) {
        postService.save(param);
        return CommonResult.ok();
    }

    /**
     * 删除岗位
     * @param param
     * @return
     */
    @PostMapping("/post/remove")
    @ApiOperation(value = "删除岗位")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        postService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改岗位
     * @param param
     * @return
     */
    @PostMapping("/post/update")
    @ApiOperation(value = "修改岗位")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) PostParam param) {
        postService.update(param);
        return CommonResult.ok();
    }

    /**
     * 查询单个岗位
     * @param param
     * @return
     */
    @PostMapping("/post/getById")
    @ApiOperation(value = "查询单个岗位")
    public CommonResult<Post> getById(@RequestBody IdParam param) {
        Post post = postService.getById(param.getId());
        return CommonResult.data(post);
    }

    /**
     * 分页查询岗位列表
     * @param param
     * @return
     */
    @PostMapping("/post/page")
    @ApiOperation(value = "查询岗位列表")
    public CommonResult<CommonPage<Post>> page(@RequestBody PostPageParam param) {
        return CommonResult.data(postService.page(param));
    }
}
