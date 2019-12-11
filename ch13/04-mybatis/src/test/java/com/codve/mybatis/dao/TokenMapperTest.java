package com.codve.mybatis.dao;

import com.codve.mybatis.model.data.object.TokenDO;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/12/11 13:01
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class TokenMapperTest {

    @Autowired
    private DataSource dataSource;

    private static ResourceDatabasePopulator populator;

    @Autowired
    private TokenMapper tokenMapper;

    @BeforeAll
    static void setUpAll() {
        populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data/token_data.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
    }

    @Test
    void testSave1() {
        TokenDO tokenDO = new TokenDO();
        assertEquals(1, tokenMapper.save(tokenDO));
    }

    @Test
    void testSave2() {
        TokenDO tokenDO = new TokenDO();
        tokenDO.setAppType(1);
        tokenDO.setDeviceType(1);
        tokenDO.setCreateTime(System.currentTimeMillis());
        tokenDO.setExpireTime(System.currentTimeMillis() + 3000);
        tokenDO.setIp("0.0.0.0");
        assertEquals(1, tokenMapper.save(tokenDO));
    }

    @Test
    void testFind() {
        TokenDO tokenDO = new TokenDO();
        tokenDO.setUserId(1L);

        List<TokenDO> result = tokenMapper.find(tokenDO);
        assertTrue(result.size() > 0);
    }

    @Test
    void testEncode() {
        Long userId = 1L;
        long time = System.currentTimeMillis();
        long random = new Random().nextInt(1000);
        String value = String.valueOf(random + userId + time) + "secret";
        String result = Hashing.sha256()
                .hashString(value, UTF_8)
                .toString();
        log.warn(result);
    }

    @Test
    void testUpdate() {
        TokenDO tokenDO = new TokenDO();
        tokenDO.setUserId(1L);

        List<TokenDO> tokenDOList = tokenMapper.find(tokenDO);
        TokenDO result = tokenDOList.get(0);
        result.setToken("hello, world");
        assertEquals(1, tokenMapper.update(result));

    }

}