package com.izumi;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.izumi.base.BaseJunit;
import com.izumi.modules.sys.dto.LoginParam;
import com.izumi.modules.sys.dto.UserPageParam;
import com.izumi.modules.sys.entity.User;
import com.izumi.modules.sys.mapper.UserMapper;
import com.izumi.modules.sys.service.UserService;
import com.izumi.modules.sys.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

class IzumiBootApplicationTests extends BaseJunit {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @Test
    void selectUserTest() {
        List<User> list = userMapper.selectList(Wrappers.query());
        System.err.println(JSONUtil.toJsonStr(list));
    }
    @Test
    void loginTest() {
        LoginParam loginParam = new LoginParam();
        loginParam.setUserName("admin");
        loginParam.setPassword("123456");
        LoginVO loginVO = userService.login(loginParam);
        System.err.println(JSONUtil.toJsonStr(loginVO));
    }

    @Test
    void testUserPage() {
        UserPageParam pageParam = new UserPageParam();
        userService.page(pageParam);
        System.err.println(JSONUtil.toJsonStr(userService.page(pageParam).getRows()));
    }
}
