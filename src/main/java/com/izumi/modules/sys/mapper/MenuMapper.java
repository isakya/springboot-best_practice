package com.izumi.modules.sys.mapper;

import com.izumi.modules.sys.entity.Menu;
import com.izumi.modules.sys.vo.MenuVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuVO> selectCustom(IPage<MenuVO>page, @Param(Constants.WRAPPER) Wrapper<Menu>wrapper);
}
