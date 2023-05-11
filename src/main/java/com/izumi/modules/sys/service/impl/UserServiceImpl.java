package com.izumi.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izumi.auth.ITokenStore;
import com.izumi.base.CommonPage;
import com.izumi.exception.ServiceException;
import com.izumi.modules.sys.dto.LoginParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserParam;
import com.izumi.modules.sys.entity.User;
import com.izumi.modules.sys.enums.AuthErrEnum;
import com.izumi.modules.sys.enums.UserTypeEnum;
import com.izumi.modules.sys.mapper.UserMapper;
import com.izumi.modules.sys.service.UserService;
import com.izumi.modules.sys.vo.LoginVO;
import com.izumi.modules.sys.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final ITokenStore tokenStore;
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
    public CommonPage<UserVO> page(UserPageParam param) {
        IPage<UserVO> page = param.buildMpPage();
        QueryWrapper queryWrapper = param.buildQueryWrapper();
        List<UserVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }

    @Override
    public LoginVO login(LoginParam param) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUserName, param.getUserName());
        String enPassword = SecureUtil.md5(param.getPassword());
        System.err.println(enPassword);
        wrapper.eq(User::getPassword, enPassword);
        User user = baseMapper.selectOne(wrapper);
        if(user == null) {
            ServiceException.throwBiz(AuthErrEnum.USERNAME_OR_PASSWORD_ERR);
        }
        LoginVO vo = new LoginVO();
        vo.setUserId(user.getId());
        vo.setToken(StrUtil.uuid());
        vo.setUserName(user.getUserName());
        // vo.setUserType(UserTypeEnum.codeToEnum(user.getUserType()));

        // 权限分配
        // if(Integer.valueOf(1).equals(user.getUserType())) {
        //     vo.setSuperAdmin(true);
        //     vo.setPerms(new ArrayList<>());
        // } else if(Integer.valueOf(2).equals(user.getUserType())){
        //     vo.setSuperAdmin(false);
        //     vo.setPerms(CollectionUtil.newArrayList("user:getById","role:page","role:getById"));
        // } else {
        //     vo.setSuperAdmin(false);
        //     vo.setPerms(new ArrayList<>());
        // }
        tokenStore.setToken(vo);
        return vo;
    }

    @Override
    public void logout(String token) {
        tokenStore.removeToken(token);
    }
}
