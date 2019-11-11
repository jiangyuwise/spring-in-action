package com.codve;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author admin
 * @date 2019/11/11 10:59
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class AppConfigTest {

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(15000);
    }

}