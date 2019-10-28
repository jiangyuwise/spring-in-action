package com.codve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/28 16:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@ActiveProfiles("pro")
public class DataSourceConfigTest2 {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        assertNull(dataSource);


    }
}