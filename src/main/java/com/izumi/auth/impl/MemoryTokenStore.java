package com.izumi.auth.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.izumi.auth.ITokenStore;
import com.izumi.modules.sys.vo.LoginVO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
// 如果没有redis的话就启用这个类
@ConditionalOnMissingBean(RedisTokenStore.class)
public class MemoryTokenStore implements ITokenStore {
    TimedCache<String, LoginVO> tokenTimedCache = CacheUtil.newTimedCache(1000*60*60*2);
    @Override
    public void setToken(LoginVO vo) {
        tokenTimedCache.put(vo.getToken(), vo);
    }

    @Override
    public LoginVO getToken(String token) {
        return tokenTimedCache.get(token);
    }

    @Override
    public void removeToken(String token) {
        tokenTimedCache.remove(token);
    }
}
