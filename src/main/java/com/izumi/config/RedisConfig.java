package com.izumi.config;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.izumi.modules.sys.vo.LoginVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, LoginVO> tokenRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, LoginVO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisSerializer<LoginVO>() {
            @Override
            public byte[] serialize(LoginVO loginVO) throws SerializationException {
                if(ObjectUtil.isNull(loginVO)) {
                    return new byte[0];
                }
                String json = JSONUtil.toJsonStr(loginVO);
                return json.getBytes(Charset.defaultCharset());
            }

            @Override
            public LoginVO deserialize(byte[] bytes) throws SerializationException {
                String json = StrUtil.str(bytes, Charset.defaultCharset());
                if(StrUtil.isEmpty(json)) return null;
                return JSONUtil.toBean(json, LoginVO.class);
            }
        });
        return redisTemplate;
    }
}
