package com.izumi.auth;

import com.izumi.modules.sys.vo.LoginVO;

/**
 * token存储接口
 */
public interface ITokenStore {
    /**
     * 保存token
     * @param vo
     */
    public void setToken(LoginVO vo);

    /**
     * 获取token
     * @param token
     * @return
     */
    public LoginVO getToken(String token);

    /**
     * 删除token
     * @param token
     */
    public void removeToken(String token);
}
