package com.codve.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/18 00:26
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringDataSourceConfigTest {

    @Autowired
    @Qualifier("springDataSource")
    private DataSource dataSource;

    @Autowired
    @Qualifier("springEntityManagerFactory")
    private EntityManagerFactory emf;

    @Autowired
    @Qualifier("springEntityManager")
    private EntityManager entityManager;

    @Autowired
    @Qualifier("springTransactionManager")
    private TransactionManager transactionManager;

    @Test
    public void dataSourceTest() {
        assertNotNull(dataSource);
    }

    @Test
    public void entityManagerTest() {
        assertNotNull(entityManager);
    }

    @Test
    public void emfTest() {
        assertNotNull(emf);
    }

    @Test
    public void transactionManagerTest() {
        assertNotNull(transactionManager);
    }

}