package com.izumi.interceptor;

import cn.hutool.core.util.StrUtil;
import com.izumi.holder.LoginUserHolder;
import com.izumi.log.LogHolder;
import com.izumi.log.LogParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Long userId = LoginUserHolder.getUserId();
        String userName = LoginUserHolder.getUserName();
        LogParam logParam = new LogParam();
        // 请求流水号-唯一,方便日志核查
        logParam.setRequestNo(StrUtil.uuid());
        logParam.setUserId(userId);
        logParam.setUserName(userName);
        logParam.setUrl(uri);
        LogHolder.set(logParam);
        // 请求头返回流水号
        response.setHeader("request-no", logParam.getRequestNo());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            LogHolder.writeLog();
        } catch(Exception e) {
            log.error("异常", e);
        } finally {
            LogHolder.remove();
        }
    }
}
