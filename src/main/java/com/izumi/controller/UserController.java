package com.izumi.controller;

import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import com.izumi.base.CommonPage;
import com.izumi.base.CommonResult;
import com.izumi.entity.User;
import com.izumi.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "用户管理")
public class UserController {
    private final DataSource dataSource;
    private final RedisTemplate<String ,String> redisTemplate;

    @GetMapping("/hello")
    @ApiOperation(value = "hello") // 备注接口名称
    public String hello() {
        log.debug("UserController.hello");
        return "hello" + 1 / 0;
    }

    @PostMapping("/user/save")
    public List<Entity> save(String userName, String password) throws SQLException {
        if (userName == null || password == null) {
            ServiceException.throwBiz(999, "用户名或密码不能为空");
        }
        System.out.println(dataSource);
        List<Entity> list = DbUtil.use().findAll("sys_user");
        return list;
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "获取用户信息")
    public CommonResult info(String userId) {
        User user = new User();
        user.setId(1L);
        user.setUserName("admin");
        return CommonResult.data(user);
    }

    @PostMapping("/user/list")
    @ApiOperation(value = "获取用户列表")
    public CommonResult<CommonPage<User>> list() {
        CommonPage<User> page = new CommonPage<>();
        page.setPageNum(1);
        page.setPageSize(10);
        page.setTotalPage(100);
        page.setRecordCount(10);
        page.setRows(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(new Long(i));
            user.setUserName("admin" + i);
            page.getRows().add(user);
        }
        return CommonResult.data(page);
    }

    // 从redis中取数据
    @GetMapping("/getFromRedis")
    public String getFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
