package com.codve;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/4 11:37
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class AppConfigTest {

    @Autowired
    public DataSource dataSource;

    @Autowired
    public NamedParameterJdbcTemplate template;

    @Test
    public void dataBaseTest() {
        assertNotNull(dataSource);
    }

    @Test
    public void templateTest() {
        assertNotNull(template);
    }

}