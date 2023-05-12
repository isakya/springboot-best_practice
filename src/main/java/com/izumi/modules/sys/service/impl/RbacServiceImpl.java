package com.izumi.modules.sys.service.impl;

import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.RoleMenuParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserRoleParam;
import com.izumi.modules.sys.service.RbacService;
import com.izumi.modules.sys.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbacServiceImpl implements RbacService {
    @Override
    public void saveRoleMenu(List<RoleMenuParam> param) {

    }

    @Override
    public List<String> roleMenuIds(Long roleId) {
        return null;
    }

    @Override
    public CommonPage<UserVO> userListByRoleId(UserPageParam param) {
        return null;
    }

    @Override
    public CommonPage<UserVO> userListExcludeRoleId(UserPageParam param) {
        return null;
    }

    @Override
    public void saveUserRole(List<UserRoleParam> params) {

    }

    @Override
    public void removeUserRole(List<UserRoleParam> params) {

    }
}
