package com.izumi.modules.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izumi.base.CommonPage;
import com.izumi.exception.ServiceException;
import com.izumi.modules.sys.dto.LoginParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserParam;
import com.izumi.modules.sys.entity.User;
import com.izumi.modules.sys.mapper.UserMapper;
import com.izumi.modules.sys.service.UserService;
import com.izumi.modules.sys.vo.LoginVO;
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

    @Override
    public LoginVO login(LoginParam param) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUserName, param.getUserName());
        wrapper.eq(User::getPassword, param.getPassword());
        User user = baseMapper.selectOne(wrapper);
        if(user == null) {
            ServiceException.throwBiz(99999999, "用户名或密码错误");
        }
        LoginVO vo = new LoginVO();
        vo.setUserId(user.getId());
        vo.setToken(StrUtil.uuid());
        return vo;
    }
}
