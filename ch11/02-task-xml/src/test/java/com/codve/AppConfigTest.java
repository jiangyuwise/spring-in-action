package com.codve;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author admin
 * @date 2019/11/11 10:29
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class AppConfigTest {

    @Autowired
    private UserTask userJob;

    @Test
    public void jobTest() throws InterruptedException {
        Thread.sleep(10000);
    }
}