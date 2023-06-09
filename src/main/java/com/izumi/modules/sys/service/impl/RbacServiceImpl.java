package com.izumi.modules.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.RoleMenuParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserRoleParam;
import com.izumi.modules.sys.entity.RoleMenu;
import com.izumi.modules.sys.entity.UserRole;
import com.izumi.modules.sys.mapper.UserMapper;
import com.izumi.modules.sys.service.RbacService;
import com.izumi.modules.sys.service.RoleMenuService;
import com.izumi.modules.sys.service.UserRoleService;
import com.izumi.modules.sys.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RbacServiceImpl implements RbacService {
    private final RoleMenuService roleMenuService;
    private final UserRoleService userRoleService;
    private final UserMapper userMapper;
    @Override
    public void saveRoleMenu(List<RoleMenuParam> param) {
        if(CollectionUtil.isEmpty(param))return;
        List<RoleMenu> roleMenuList = BeanUtil.copyToList(param,RoleMenu.class);
        // 删除旧的关系
        roleMenuList.forEach(roleMenu->{
            LambdaQueryWrapper<RoleMenu> delLambdaQueryWrapper = Wrappers.lambdaQuery();
            delLambdaQueryWrapper.eq(RoleMenu::getRoleId,roleMenu.getRoleId())
                    .eq(RoleMenu::getMenuId,roleMenu.getMenuId());
            roleMenuService.remove(delLambdaQueryWrapper);
        });
        // 重新保存新的关系
        roleMenuService.saveBatch(roleMenuList);
    }

    @Override
    public List<String> roleMenuIds(Long roleId) {
        LambdaQueryWrapper<RoleMenu> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(RoleMenu::getRoleId,roleId);
        List<RoleMenu> roleMenuList = roleMenuService.list(lambdaQueryWrapper);
        List<String> menuIds = roleMenuList.stream().map(item->{
            return item.getMenuId().toString();
        }).collect(Collectors.toList());
        return menuIds;
    }

    @Override
    public CommonPage<UserVO> userListByRoleId(UserPageParam param) {
        IPage<UserVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        queryWrapper.eq("ur.role_id",param.getRoleId());
        List<UserVO> list = userMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }

    @Override
    public CommonPage<UserVO> userListExcludeRoleId(UserPageParam param) {
        return null;
    }

    @Override
    public void saveUserRole(List<UserRoleParam> params) {
        if(CollectionUtil.isEmpty(params))return;
        List<UserRole> userRoleList = BeanUtil.copyToList(params,UserRole.class);
        List<UserRole> insertList = userRoleList.stream().filter(item->{
            LambdaQueryWrapper<UserRole> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(UserRole::getUserId,item.getUserId())
                    .eq(UserRole::getRoleId,item.getRoleId());
            return userRoleService.count(lambdaQueryWrapper)==0;
        }).collect(Collectors.toList());
        if(CollectionUtil.isNotEmpty(insertList)) {
            userRoleService.saveBatch(userRoleList);
        }
    }

    @Override
    public void removeUserRole(List<UserRoleParam> params) {
        if(CollectionUtil.isEmpty(params))return;
        List<UserRole> userRoleList = BeanUtil.copyToList(params,UserRole.class);
        userRoleList.forEach(userRole->{
            LambdaQueryWrapper<UserRole> delLambdaQueryWrapper = Wrappers.lambdaQuery();
            delLambdaQueryWrapper.eq(UserRole::getUserId,userRole.getUserId())
                    .eq(UserRole::getRoleId,userRole.getRoleId());
            userRoleService.remove(delLambdaQueryWrapper);
        });
    }
}
