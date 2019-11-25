package com.codve.mybatis.config;

import com.codve.mybatis.properties.UploadProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author admin
 * @date 2019/11/25 17:16
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UploadConfigTest {

    @Autowired
    private UploadProperties uploadProperties;

    @Test
    void test() {
        assertNotNull(uploadProperties);
        assertEquals("", uploadProperties.getLocation());
        System.out.println(uploadProperties.getLocation());
    }
}