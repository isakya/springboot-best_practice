package com.izumi.modules.sys.service.impl;
import com.izumi.base.CommonPage;
import com.izumi.modules.sys.dto.MenuPageParam;
import com.izumi.modules.sys.dto.MenuParam;
import com.izumi.modules.sys.vo.MenuVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.izumi.modules.sys.entity.Menu;
import com.izumi.modules.sys.mapper.MenuMapper;
import com.izumi.modules.sys.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    @Transactional(rollbackFor = Exception.class) // 表示所有的异常都要回滚
    public boolean save(MenuParam param) {
        Menu Menu = new Menu();
        BeanUtils.copyProperties(param, Menu);
        return super.save(Menu);
    }

    @Override
    public boolean update(MenuParam param) {
        Menu Menu = new Menu();
        BeanUtils.copyProperties(param, Menu);
        return super.updateById(Menu);
    }

    @Override
    public CommonPage<MenuVO> page(MenuPageParam param) {
        IPage<MenuVO> page = param.buildMpPage();
        QueryWrapper<Menu> queryWrapper = param.buildQueryWrapper();
        List<MenuVO> list = baseMapper.selectCustom(page, queryWrapper);
        page.setRecords(list);
        return CommonPage.toPage(page);
    }
}
