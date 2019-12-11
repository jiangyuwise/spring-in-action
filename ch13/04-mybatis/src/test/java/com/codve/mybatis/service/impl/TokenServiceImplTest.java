package com.codve.mybatis.service.impl;

import com.codve.mybatis.model.data.object.TokenDO;
import com.codve.mybatis.service.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author admin
 * @date 2019/12/11 14:36
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TokenServiceImplTest {

    @Autowired
    private TokenService tokenService;

    @Test
    void testSave() {
        TokenDO tokenDO = new TokenDO();
        assertEquals(1, tokenService.save(tokenDO));
    }
}