package com.izumi.auth.impl;


import cn.hutool.core.convert.Convert;
import com.izumi.auth.AuthProperties;
import com.izumi.auth.ITokenStore;
import com.izumi.modules.sys.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
// 读取yml文件中的属性然后判断是否有该值
@ConditionalOnProperty(prefix = "auth", name = "tokenStoreType", havingValue = "redis")
public class RedisTokenStore implements ITokenStore {
    private final RedisTemplate<String, LoginVO> redisTemplate;
    private final String PREFIX = "TOKEN_STORE:";
    private final AuthProperties authProperties;
    @Override
    public void setToken(LoginVO vo) {
        String key = PREFIX + vo.getToken();
        redisTemplate.opsForValue().set(key, vo);
        long expire = authProperties.getExpire();
        // 设置有效期
        redisTemplate.expire(key, expire, TimeUnit.HOURS);
    }

    @Override
    public LoginVO getToken(String token) {
        String key = PREFIX + token;
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void removeToken(String token) {
        String key = PREFIX + token;
        redisTemplate.delete(key);
    }
}
