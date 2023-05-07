package com.izumi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.dto.UserParam;
import com.izumi.modules.sys.entity.User;

public interface UserService extends IService<User> {
    /**
     * 添加用户
     * @param param
     * @return
     */
    public boolean save(UserParam param);

    /***
     * 更新用户
     * @param param
     * @return
     */
    public boolean update(UserParam param);
    /**
     * 自定义分页
     * @param param
     * @return
     */
    CommonPage<User> page(UserPageParam param);
}
