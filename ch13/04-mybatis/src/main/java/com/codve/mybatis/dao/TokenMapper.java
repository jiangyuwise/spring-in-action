package com.codve.mybatis.dao;

import com.codve.mybatis.model.data.object.TokenDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/11 12:37
 */
@Repository
public interface TokenMapper {

    int save(TokenDO tokenDO);

    int update(TokenDO tokenDO);

    List<TokenDO> find(TokenDO tokenDO);

    TokenDO findByToken(String token);
}
