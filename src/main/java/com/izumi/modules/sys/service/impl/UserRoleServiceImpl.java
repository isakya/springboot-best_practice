package com.izumi.modules.sys.service.impl;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.UserRolePageParam;
import com.izumi.modules.sys.dto.UserRoleParam;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;

import com.izumi.modules.sys.entity.UserRole;
import com.izumi.modules.sys.mapper.UserRoleMapper;
import com.izumi.modules.sys.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * r_用户角色关系 服务实现类
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    @Transactional(rollbackFor = Exception.class) // 表示所有的异常都要回滚
    public boolean save(UserRoleParam param) {
        UserRole UserRole = new UserRole();
        BeanUtils.copyProperties(param, UserRole);
        return super.save(UserRole);
    }

    @Override
    public boolean update(UserRoleParam param) {
        UserRole UserRole = new UserRole();
        BeanUtils.copyProperties(param, UserRole);
        return super.updateById(UserRole);
    }

    @Override
    public CommonPage<UserRole> page(UserRolePageParam param) {
        IPage<UserRole> page = param.buildMpPage();
        QueryWrapper<UserRole> queryWrapper = param.buildQueryWrapper();
        List<UserRole> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
}
