package com.izumi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.base.IdParam;
import com.izumi.base.IdsParam;
import com.izumi.dto.RolePageParam;
import com.izumi.dto.RoleParam;
import com.izumi.entity.Role;
import com.izumi.mapper.RoleMapper;
import com.izumi.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "角色管理")
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 添加角色
     * @param param
     * @return
     */
    @PostMapping("/role/save")
    @ApiOperation(value = "添加角色")
    public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) RoleParam param) {
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
        roleMapper.insert(role);
        return CommonResult.ok();
    }

    /**
     * 删除角色
     * @param param
     * @return
     */
    @PostMapping("/role/remove")
    @ApiOperation(value = "删除角色")
    public CommonResult<?> remove(@RequestBody IdsParam param) {
        roleMapper.deleteBatchIds(param.getIds());
        return CommonResult.ok();
    }

    /**
     * 修改角色
     * @param param
     * @return
     */
    @PostMapping("/role/update")
    @ApiOperation(value = "修改角色")
    public CommonResult<?> update(@RequestBody @Validated({Groups.Update.class}) RoleParam param) {
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
        roleMapper.updateById(role);
        return CommonResult.ok();
    }

    /**
     * 查询单个角色
     * @param param
     * @return
     */
    @PostMapping("/role/info")
    @ApiOperation(value = "查询单个角色")
    public CommonResult<Role> info(@RequestBody IdParam param) {
        Role role = roleMapper.selectById(param.getId());
        return CommonResult.data(role);
    }

    /**
     * 分页查询角色列表
     * @param param
     * @return
     */
    @PostMapping("/role/page")
    @ApiOperation(value = "查询角色列表")
    public CommonResult<CommonPage<Role>> page(@RequestBody RolePageParam param) {
        IPage<Role> page = param.buildMpPage();
        roleMapper.selectPage(page, Wrappers.emptyWrapper());
        return CommonResult.data(CommonPage.toPage(page));
    }
}
