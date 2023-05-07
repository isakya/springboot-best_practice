package com.izumi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izumi.base.CommonPage;
import com.izumi.dto.UserPageParam;
import com.izumi.dto.UserParam;
import com.izumi.entity.User;
import com.izumi.mapper.UserMapper;
import com.izumi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserParam param) {
        User user = new User();
        BeanUtils.copyProperties(param, user);
        return super.save(user);
    }

    @Override
    public boolean update(UserParam param) {
        User user = new User();
        BeanUtils.copyProperties(param, user);
        return super.updateById(user);
    }

    @Override
    public CommonPage<User> page(UserPageParam param) {
        IPage<User> page = param.buildMpPage();
        QueryWrapper<User> queryWrapper = param.buildQueryWrapper();
        List<User> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
}
