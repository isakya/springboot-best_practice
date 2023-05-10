package com.izumi.modules.sys.service;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.RolePageParam;
import com.izumi.modules.sys.dto.RoleParam;
import com.izumi.modules.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
public interface RoleService extends IService<Role> {
     /**
     * 添加角色
     * @param param
     * @return
     */
     boolean save(RoleParam param);

     /***
     * 更新角色
     * @param param
     * @return
     */
     boolean update(RoleParam param);
     /**
     * 自定义分页查询角色
     * @param param
     * @return
     */
     CommonPage<Role> page(RolePageParam param);
}
