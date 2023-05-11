package com.izumi.modules.sys.service.impl;
import com.izumi.modules.sys.entity.UserRole;
import com.izumi.modules.sys.mapper.UserRoleMapper;
import com.izumi.modules.sys.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * r_用户角色关系 服务实现类
 * </p>
 *
 * @author izumi
 * @since 2023-05-12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
