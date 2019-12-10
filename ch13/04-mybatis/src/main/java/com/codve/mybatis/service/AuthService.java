package com.codve.mybatis.service;

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
}
