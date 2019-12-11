package com.codve.mybatis.service;

import com.codve.mybatis.model.data.object.TokenDO;
import com.codve.mybatis.model.query.UserLoginQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2019/12/10 13:56
 */
public interface AuthService {

    /**
     * 使用用户密码授权
     * @param username username
     * @param password password
     * @return token
     */
    String passwordAuth(String username, String password);

    /**
     * 使用用户密码授权
     * @param request request
     * @param query UserLoginQuery
     * @return token
     */
    String passwordAuth2(HttpServletRequest request, UserLoginQuery query);

    /**
     * 验证 token
     * @param tokenDO tokenDO
     * @return boolean
     */
    boolean verify(TokenDO tokenDO);

}
