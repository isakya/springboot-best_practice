package com.izumi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.izumi.base.CommonPage;
import com.izumi.dto.RolePageParam;
import com.izumi.dto.RoleParam;
import com.izumi.entity.Role;

public interface RoleService extends IService<Role> {
    /**
     * 添加角色
     * @param param
     * @return
     */
    public boolean save(RoleParam param);

    /***
     * 更新角色
     * @param param
     * @return
     */
    public boolean update(RoleParam param);
    /**
     * 自定义分页
     * @param param
     * @return
     */
    CommonPage<Role> page(RolePageParam param);
}
