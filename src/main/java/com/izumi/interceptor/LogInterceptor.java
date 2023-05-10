package com.izumi.interceptor;

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
        logParam.setUserId(userId);
        logParam.setUserName(userName);
        logParam.setUrl(uri);
        LogHolder.set(logParam);
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
