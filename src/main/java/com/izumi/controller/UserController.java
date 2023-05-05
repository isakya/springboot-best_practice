package com.izumi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @GetMapping("/hello")
    public String hello() {
        log.debug("UserController.hello");
        return "hello";
    }
}
