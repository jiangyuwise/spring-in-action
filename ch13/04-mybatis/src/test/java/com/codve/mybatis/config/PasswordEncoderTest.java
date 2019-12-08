package com.codve.mybatis.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/12/6 15:58
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PasswordEncoderTest {

    private Logger logger = LoggerFactory.getLogger(PasswordEncoder.class);

    @Autowired
    private PasswordEncoder encoder;

    @Test
    void test() {
        String password = "123456";
        String encoded = encoder.encode(password);

        assertTrue(encoder.matches(password, encoded));
        logger.error(encoded);
    }

    @Test
    void test2() {
        String password = "123456";
        String encoded = "$2a$10$ixuzQ0ce9AvvflfAuPZowuuMiBIj6UA3kDY2lneIXf5vQYSyniYMy";

        assertTrue(encoder.matches(password, encoded));
        logger.error(encoded);
    }
}