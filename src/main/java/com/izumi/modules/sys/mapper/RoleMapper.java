package com.izumi.modules.sys.mapper;

import com.izumi.modules.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectCustom(IPage<Role>page, @Param(Constants.WRAPPER) Wrapper<Role>wrapper);
}
