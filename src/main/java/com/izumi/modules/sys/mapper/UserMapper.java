package com.izumi.modules.sys.mapper;

import com.izumi.modules.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.izumi.modules.sys.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author izumi
 * @since 2023-05-11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserVO> selectCustom(IPage<UserVO>page, @Param(Constants.WRAPPER) Wrapper<User>wrapper);
}
