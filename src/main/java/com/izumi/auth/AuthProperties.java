package com.izumi.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix="auth")
@Data
public class AuthProperties {
    // token存储类型
    private String tokenStoreType;
    // 有效时长-单位：小时
    private Long expire;
    // 不需要登录的uri
    private List<String> ignoreUri = new ArrayList<>();
    // 不需要访问权限的uri ==> 只需要登录就能访问
    private List<String> skipAuthUri = new ArrayList<>();

    /**
     * 初始化默认白名单
     */
    @PostConstruct
    public void initDefaultIgnoreUri() {
        ignoreUri.add("/sys/login");
    }
}
