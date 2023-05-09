package com.izumi.interceptor;

import cn.hutool.core.util.StrUtil;
import com.izumi.auth.ITokenStore;
import com.izumi.exception.ServiceException;
import com.izumi.modules.sys.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final ITokenStore tokenStore;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.debug("AuthInterceptor-preHandle,url:" + uri);
        if("/sys/login".equalsIgnoreCase(uri)) {
            // 白名单
            return true;
        }
        String token = request.getHeader("Authorization");
        if(StrUtil.isEmpty(token)) {
            ServiceException.throwBiz(99990403, "token不存在或已过期");
        }
        token = token.replace("Bearer ", "");
        LoginVO vo = tokenStore.getToken(token);
        if(vo == null) {
            ServiceException.throwBiz(99990403, "token不存在或已过期");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("AuthInterceptor-postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("AuthInterceptor-afterCompletion");
    }
}
