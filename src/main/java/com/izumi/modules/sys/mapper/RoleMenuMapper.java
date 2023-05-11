package com.izumi.modules.sys.mapper;

import com.izumi.modules.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * r_角色菜单关系 Mapper 接口
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    List<RoleMenu> selectCustom(IPage<RoleMenu>page, @Param(Constants.WRAPPER) Wrapper<RoleMenu>wrapper);
}