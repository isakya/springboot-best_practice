package com.izumi.controller;

import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import com.izumi.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final DataSource dataSource;

    @GetMapping("/hello")
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
}
