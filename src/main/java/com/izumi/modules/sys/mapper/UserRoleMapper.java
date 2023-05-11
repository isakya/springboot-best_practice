package com.izumi.modules.sys.mapper;

import com.izumi.modules.sys.entity.UserRole;
import com.izumi.modules.sys.vo.UserRoleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * r_用户角色关系 Mapper 接口
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<UserRoleVO> selectCustom(IPage<UserRoleVO>page, @Param(Constants.WRAPPER) Wrapper<UserRole>wrapper);
}
