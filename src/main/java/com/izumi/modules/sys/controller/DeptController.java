package com.izumi.modules.sys.controller;

import com.izumi.auth.Perm;
import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.modules.sys.dto.DeptPageParam;
import com.izumi.modules.sys.dto.DeptParam;
import com.izumi.modules.sys.entity.Dept;
import com.izumi.modules.sys.service.DeptService;
import com.izumi.modules.sys.vo.DeptVO;
import com.izumi.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
* <p>
    * 部门 前端控制器
    * </p>
*
* @author izumi
* @since 2023-05-12
*/
@RestController
@Api(tags = "部门管理")
@RequiredArgsConstructor
@RequestMapping("/sys")
public class DeptController {
    private final DeptService deptService;

    /**
     * 添加部门
     * @param param
     * @return
     */
    @PostMapping("/dept/save")
    @ApiOperation(value = "添加部门")
    @Perm
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) DeptParam param) {
        deptService.save(param);
        return CommonResult.ok();
    }

    /**
     * 删除部门
     * @param param
     * @return
     */
    @PostMapping("/dept/remove")
    @ApiOperation(value = "删除部门")
    @Perm
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        deptService.removeBatchByIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改部门
     * @param param
     * @return
     */
    @PostMapping("/dept/update")
    @ApiOperation(value = "修改部门")
    @Perm
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) DeptParam param) {
        deptService.update(param);
        return CommonResult.ok();
    }

    /**
     * 查询单个部门
     * @param param
     * @return
     */
    @PostMapping("/dept/getById")
    @ApiOperation(value = "查询单个部门")
    @Perm
    public CommonResult<Dept> getById(@RequestBody IdParam param) {
        Dept dept = deptService.getById(param.getId());
        return CommonResult.data(dept);
    }
    /**
     * 分页查询部门列表
     * @param param
     * @return
     */
    @PostMapping("/dept/page")
    @ApiOperation(value = "查询部门列表")
    @Perm
    public CommonResult<CommonPage<DeptVO>> page(@RequestBody DeptPageParam param) {
        return CommonResult.data(deptService.page(param));
    }
    /**
     * 查询部门列表
     * @param param
     * @return
     */
    @PostMapping("/dept/list")
    @ApiOperation(value = "查询部门列表")
    @Perm
    public CommonResult<List<Dept>> list(@RequestBody DeptPageParam param) {
        return CommonResult.data(deptService.list(param.buildQueryWrapper()));
    }
}
