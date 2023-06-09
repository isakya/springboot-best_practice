package com.izumi.interceptor;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.izumi.auth.AuthProperties;
import com.izumi.auth.ITokenStore;
import com.izumi.auth.Perm;
import com.izumi.auth.UserPerm;
import com.izumi.exception.ServiceException;
import com.izumi.holder.LoginUserHolder;
import com.izumi.modules.sys.enums.AuthErrEnum;
import com.izumi.modules.sys.enums.UserAdminTypeEnum;
import com.izumi.modules.sys.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final ITokenStore tokenStore;
    private final AuthProperties authProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.debug("AuthInterceptor-preHandle,uri:" + uri);
        if (authProperties.getIgnoreUri().contains(uri)) {
            // 白名单
            return true;
        }
        String token = request.getHeader("Authorization");
        if (StrUtil.isEmpty(token)) {
            ServiceException.throwBiz(AuthErrEnum.TOKEN_EXPIRE);
        }
        token = token.replace("Bearer ", "");
        LoginVO vo = tokenStore.getToken(token);
        if (vo == null) {
            ServiceException.throwBiz(AuthErrEnum.TOKEN_EXPIRE);
        }
        // 当前线程绑定用户信息
        LoginUserHolder.set(vo);
        // 处理权限标识
        // String perm = StrUtil.removePrefix(uri, "/").replaceAll("/", ":");
        // if(!vo.isSuperAdmin() && vo.hasPerm(perm)) {
        //     ServiceException.throwBiz(99990406, "您没有权限访问，请联系管理员");
        // }

        // 第一步：获取当前用户所属用户类型枚举 == userTypeEnum
        // 1 => UserTypeEnum.ADMIN
        // 2 => UserTypeEnum.COMMON
        if(authProperties.getSkipAuthUri().contains(uri)) {
            return true;
        }
        UserAdminTypeEnum userType = vo.getAdminType();
        if (UserAdminTypeEnum.ADMIN.equals(userType)) return true;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Perm perm = handlerMethod.getMethodAnnotation(Perm.class);
            if (perm == null) {
                // 未定义有权限标识，说明登录即可访问
                return true;
            }
            String permCode = perm.value();
            if(StrUtil.isEmpty(permCode)) {
                // uri=/sys/user/page ==> sys/user/page ==> sys:user:page
                permCode = StrUtil.removePrefix(uri, "/").replaceAll("/", ":");
            }
            if(!vo.hasPerm(permCode)) {
                ServiceException.throwBiz(AuthErrEnum.NOT_AUTH);
            }
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
        LoginUserHolder.remove();
    }
}
