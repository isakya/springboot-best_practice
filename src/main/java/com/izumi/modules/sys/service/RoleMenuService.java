package com.izumi.modules.sys.service;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.RoleMenuPageParam;
import com.izumi.modules.sys.dto.RoleMenuParam;
import com.izumi.modules.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * r_角色菜单关系 服务类
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
public interface RoleMenuService extends IService<RoleMenu> {
     /**
     * 添加r_角色菜单关系
     * @param param
     * @return
     */
     boolean save(RoleMenuParam param);

     /***
     * 更新r_角色菜单关系
     * @param param
     * @return
     */
     boolean update(RoleMenuParam param);
     /**
     * 自定义分页查询r_角色菜单关系
     * @param param
     * @return
     */
     CommonPage<RoleMenu> page(RoleMenuPageParam param);
}
