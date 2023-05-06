package com.izumi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.izumi.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
