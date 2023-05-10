package com.izumi.holder;

import com.izumi.modules.sys.vo.LoginVO;

/**
 * 当前登录持有者
 */
public class LoginUserHolder {
    public static ThreadLocal<LoginVO> LOGIN_THEAD_LOCAL = new ThreadLocal<>();
    public static void set(LoginVO vo) {
        LOGIN_THEAD_LOCAL.set(vo);
    }
    public static LoginVO get() {
        return LOGIN_THEAD_LOCAL.get();
    }
    public static void remove() {
        LOGIN_THEAD_LOCAL.remove();
    }

    /**
     * 当前用户ID
     * @return
     */
    public static Long getUserId() {
        LoginVO loginVO = LOGIN_THEAD_LOCAL.get();
        if(loginVO != null) {
            return loginVO.getUserId();
        }
        return 0L;
    }
}
