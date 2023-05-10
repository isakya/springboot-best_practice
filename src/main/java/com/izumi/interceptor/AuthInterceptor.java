package com.izumi.interceptor;

import cn.hutool.core.util.StrUtil;
import com.izumi.auth.ITokenStore;
import com.izumi.exception.ServiceException;
import com.izumi.modules.sys.enums.UserTypeEnum;
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
        log.debug("AuthInterceptor-preHandle,uri:" + uri);
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
        // 处理权限标识
        String perm = StrUtil.removePrefix(uri, "/").replaceAll("/", ":");
        if(!vo.isSuperAdmin() && vo.hasPerm(perm)) {
            ServiceException.throwBiz(99990406, "您没有权限访问，请联系管理员");
        }

        // 第一步：获取当前用户所属用户类型枚举 == userTypeEnum
        // 1 => UserTypeEnum.ADMIN
        // 2 => UserTypeEnum.COMMON
        UserTypeEnum userType = vo.getUserType();
        System.err.println(userType);
        // 第二步：拿到请求方法中的注解@UserPerm({UserTypePerm.COMMON,UserTypePerm.ADMIN}) == userTypeEnumArr

        // 第三步：userTypeEnum in ==> userTypeEnumArr

        // 如果userTypeEnum在userTypeEnumArr里，则有权限，否则无权限
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
