package com.izumi.modules.sys.service;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.MenuPageParam;
import com.izumi.modules.sys.dto.MenuParam;
import com.izumi.modules.sys.vo.MenuVO;
import com.izumi.modules.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
public interface MenuService extends IService<Menu> {
     /**
     * 添加菜单
     * @param param
     * @return
     */
     boolean save(MenuParam param);

     /***
     * 更新菜单
     * @param param
     * @return
     */
     boolean update(MenuParam param);
     /**
     * 自定义分页查询菜单
     * @param param
     * @return
     */
     CommonPage<MenuVO> page(MenuPageParam param);
}
