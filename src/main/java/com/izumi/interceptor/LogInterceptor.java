package com.izumi.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.izumi.holder.LoginUserHolder;
import com.izumi.log.LogHolder;
import com.izumi.log.LogParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        Long userId = LoginUserHolder.getUserId();
        String userName = LoginUserHolder.getUserName();
        LogParam logParam = new LogParam();
        // 请求流水号-唯一,方便日志核查
        logParam.setRequestNo(StrUtil.uuid());
        logParam.setUserId(userId);
        logParam.setUserName(userName);
        logParam.setUrl(url);
        // 客户端IP
        String ip = ServletUtil.getClientIP(request);
        logParam.setIp(ip);
        // 浏览器信息
        String ua = request.getHeader("User-Agent");
        logParam.setBrower(ua);
        // 请求开始时间
        logParam.setStartTime(new Date());
        LogHolder.set(logParam);
        // 请求头返回流水号
        response.setHeader("request-no", logParam.getRequestNo());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            LogParam logParam = LogHolder.get();
            logParam.setEndTime(new Date());
            // 总体请求时长
            logParam.setTime(logParam.getEndTime().getTime()-logParam.getStartTime().getTime());
            LogHolder.writeLog();
        } catch(Exception e) {
            log.error("异常", e);
        } finally {
            LogHolder.remove();
        }
    }
}
