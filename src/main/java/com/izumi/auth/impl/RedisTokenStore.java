package com.izumi.auth.impl;


import cn.hutool.core.convert.Convert;
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

    /**
     * 读取配置文件的值
     */
    // 方式一：@Value --必须配默认值，不然报错
    @Value("${auth.expire:2}")
    public long expire;

    // 方式二：
    private final Environment environment;
    
    @Override
    public void setToken(LoginVO vo) {
        String key = PREFIX + vo.getToken();
        redisTemplate.opsForValue().set(key, vo);
        // 设置有效期
        redisTemplate.expire(key, expire, TimeUnit.HOURS);
        String expire = environment.getProperty("auth.expire");
        // 转换类型，如果转不成功则默认为2L
        Long lexpire = Convert.toLong(expire, 2L);
        System.err.println(lexpire);
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
