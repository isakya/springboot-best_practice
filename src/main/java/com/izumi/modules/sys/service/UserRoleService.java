package com.izumi.modules.sys.service;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.UserRolePageParam;
import com.izumi.modules.sys.dto.UserRoleParam;
import com.izumi.modules.sys.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * r_用户角色关系 服务类
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
public interface UserRoleService extends IService<UserRole> {
     /**
     * 添加r_用户角色关系
     * @param param
     * @return
     */
     boolean save(UserRoleParam param);

     /***
     * 更新r_用户角色关系
     * @param param
     * @return
     */
     boolean update(UserRoleParam param);
     /**
     * 自定义分页查询r_用户角色关系
     * @param param
     * @return
     */
     CommonPage<UserRole> page(UserRolePageParam param);
}
