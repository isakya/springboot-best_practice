package com.izumi.modules.sys.service.impl;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.RolePageParam;
import com.izumi.modules.sys.dto.RoleParam;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;

import com.izumi.modules.sys.entity.Role;
import com.izumi.modules.sys.mapper.RoleMapper;
import com.izumi.modules.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    @Transactional(rollbackFor = Exception.class) // 表示所有的异常都要回滚
    public boolean save(RoleParam param) {
        Role Role = new Role();
        BeanUtils.copyProperties(param, Role);
        return super.save(Role);
    }

    @Override
    public boolean update(RoleParam param) {
        Role Role = new Role();
        BeanUtils.copyProperties(param, Role);
        return super.updateById(Role);
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
