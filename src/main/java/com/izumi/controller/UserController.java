package com.izumi.controller;

import com.izumi.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @GetMapping("/hello")
    public String hello() {
        log.debug("UserController.hello");
        return "hello" + 1 / 0;
    }

    @PostMapping("/user/save")
    public String save(String userName, String password) {
        if (userName == null || password == null) {
            ServiceException.throwBiz(999, "用户名或密码不能为空");
        }
        return userName + "/" + password;
    }
}
