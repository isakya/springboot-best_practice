package com.izumi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izumi.base.CommonPage;
import com.izumi.dto.RolePageParam;
import com.izumi.dto.RoleParam;
import com.izumi.entity.Role;
import com.izumi.mapper.RoleMapper;
import com.izumi.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public boolean save(RoleParam param) {
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
        return super.save(role);
    }

    @Override
    public boolean update(RoleParam param) {
        Role role = new Role();
        BeanUtils.copyProperties(param, role);
        return super.updateById(role);
    }

    @Override
    public CommonPage<Role> page(RolePageParam param) {
        IPage<Role> page = param.buildMpPage();
        QueryWrapper<Role> queryWrapper = param.buildQueryWrapper();
        List<Role> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
}
