package com.izumi.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import java.util.stream.Collectors;


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
        QueryWrapper<Role> queryWrapper = Wrappers.query();
        if(StrUtil.isNotEmpty(param.getM_EQ_code())) {
            queryWrapper.eq("code", param.getM_EQ_code());
        }
        if(StrUtil.isNotEmpty(param.getM_EQ_name())) {
            queryWrapper.eq("name", param.getM_EQ_name());
        }
        if(StrUtil.isNotEmpty(param.getM_LIKE_name())) {
            queryWrapper.like("name", param.getM_LIKE_name());
        }
        if(CollectionUtil.isNotEmpty(param.getM_BT_id()) && param.getM_BT_id().size() == 2) {
            Long value1 = param.getM_BT_id().get(0);
            Long value2 = param.getM_BT_id().get(1);
            queryWrapper.between("id", value1, value2);
        }
        if(CollectionUtil.isNotEmpty(param.getM_IN_roleType())) {
            queryWrapper.in("role_type", param.getM_IN_roleType());
        }
        roleMapper.selectPage(page, queryWrapper);
        return CommonResult.data(CommonPage.toPage(page));
    }
}
