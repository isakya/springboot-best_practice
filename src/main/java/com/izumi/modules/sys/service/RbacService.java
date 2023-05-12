package com.izumi.modules.sys.service;

import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.RoleMenuParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserRoleParam;
import com.izumi.modules.sys.vo.UserVO;

import java.util.List;

/**
 * RBAC相关业务
 */
public interface RbacService {
    /**
     * 保存角色菜单关系
     * @param param
     */
    void saveRoleMenu(List<RoleMenuParam> param);

    /**
     * 根据角色ID获取菜单ID集合
     * @param roleId
     * @return
     */
    List<String> roleMenuIds(Long roleId);

    /**
     * 通过角色ID获取用户列表
     * @param param
     * @return
     */
    CommonPage<UserVO> userListByRoleId(UserPageParam param);

    /**
     * 获取用户列表-排除指定角色
     * @param param
     * @return
     */
    CommonPage<UserVO> userListExcludeRoleId(UserPageParam param);

    /**
     * 保存用户和角色关系
     * @param params
     */
    void saveUserRole(List<UserRoleParam> params);
    /**
     * 删除用户和角色关系
     * @param params
     */
    void removeUserRole(List<UserRoleParam> params);
}
