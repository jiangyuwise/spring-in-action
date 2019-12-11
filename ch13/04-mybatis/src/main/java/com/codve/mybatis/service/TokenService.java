package com.codve.mybatis.service;

import com.codve.mybatis.model.data.object.TokenDO;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/11 14:30
 */
public interface TokenService {

    int save(TokenDO tokenDO);

    int update(TokenDO tokenDO);

    List<TokenDO> find(TokenDO tokenDO);

    TokenDO findByToken(String token);
}
