package com.izumi.modules.sys.service.impl;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.RoleMenuPageParam;
import com.izumi.modules.sys.dto.RoleMenuParam;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;

import com.izumi.modules.sys.entity.RoleMenu;
import com.izumi.modules.sys.mapper.RoleMenuMapper;
import com.izumi.modules.sys.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * r_角色菜单关系 服务实现类
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Override
    @Transactional(rollbackFor = Exception.class) // 表示所有的异常都要回滚
    public boolean save(RoleMenuParam param) {
        RoleMenu RoleMenu = new RoleMenu();
        BeanUtils.copyProperties(param, RoleMenu);
        return super.save(RoleMenu);
    }

    @Override
    public boolean update(RoleMenuParam param) {
        RoleMenu RoleMenu = new RoleMenu();
        BeanUtils.copyProperties(param, RoleMenu);
        return super.updateById(RoleMenu);
    }

    @Override
    public CommonPage<RoleMenu> page(RoleMenuPageParam param) {
        IPage<RoleMenu> page = param.buildMpPage();
        QueryWrapper<RoleMenu> queryWrapper = param.buildQueryWrapper();
        List<RoleMenu> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
}
