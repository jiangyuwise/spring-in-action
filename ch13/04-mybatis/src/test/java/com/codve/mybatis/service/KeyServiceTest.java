package com.codve.mybatis.service;

import com.codve.mybatis.model.data.object.UserDO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/12/5 10:18
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class KeyServiceTest {

    private static ObjectMapper mapper;

    @BeforeAll
    static void setUpAll() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(JsonWriteFeature.QUOTE_FIELD_NAMES.mappedFeature(), false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    private KeyService keyService;

    private Logger logger = LoggerFactory.getLogger(KeyServiceTest.class);

    // 无参数
    @Test
    void test1() {
        UserDO userDO = keyService.method1();
        assertNotNull(userDO);
        logger.warn(userDO.toString());
    }

    // 有 int 型参数, 但传了 null
    @Test
    void test2() {
        UserDO userDO = keyService.method2(null);
        assertNotNull(userDO);
        logger.warn(userDO.toString());
    }

    @Test
    void test3() {
        UserDO userDO = keyService.method2(1L);
        assertNotNull(userDO);
        logger.warn(userDO.toString());
    }

    // 有 2 个参数, 其中1 个参数是 null
    @Test
    void test4() {
        UserDO userDO = keyService.method3(1L, null);
        assertNotNull(userDO);
        logger.warn(userDO.toString());
    }

    @Test
    void test5() {
        UserDO userDO = keyService.method3(1L, "james");
        assertNotNull(userDO);
        logger.warn(userDO.toString());
    }

    // 参数为 int 和自定义 POJO, 但自定义 POJO 为 null
    @Test
    void test6() {
        UserDO userDO = keyService.method4(1L, null);
        assertNotNull(userDO);
        logger.warn(userDO.toString());
    }

    @Test
    void test7() {
        UserDO userDO = keyService.method4(1L, new UserDO());
        assertNotNull(userDO);
        logger.warn(userDO.toString());
    }

    @Test
    void test8() {
        UserDO userDO = new UserDO();
        userDO.setId(10L);
        userDO.setName("Alice");
        userDO.setBirthday(System.currentTimeMillis());

        UserDO result = keyService.method4(1L, userDO);
        assertNotNull(result);
        logger.warn(result.toString());
    }

    @Test
    void testMapper() throws JsonProcessingException {
        UserDO userDO = new UserDO();
        userDO.setId(10L);
        userDO.setName("Alice");
        userDO.setBirthday(System.currentTimeMillis());

        Object[] arrs = new Object[]{userDO};

        Arrays.stream(arrs).forEach(e -> {
            try {
                String str = mapper.writeValueAsString(userDO);
                logger.warn(str);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        });
    }

    @Test
    void strTest() throws JsonProcessingException {
        String str = "{\"@class\":\"com.codve.mybatis.model.data.object.UserDO\",\"id\":1,\"name\":\"Alice\",\"birthday\":1575516494167}";
        logger.warn(str);

        UserDO userDO = mapper.readValue(str, UserDO.class);
        logger.warn(userDO.toString());
    }

}