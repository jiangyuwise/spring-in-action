package com.codve.conn.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author admin
 * @date 2019/11/18 14:06
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    public void test1() {
        assertNotNull(transactionManager);
    }
}
