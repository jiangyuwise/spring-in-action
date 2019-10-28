package com.codve;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/28 16:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@ActiveProfiles("dev")
public class DataSourceConfigTest {

    @Autowired
    DataSource dataSource;

    @SuppressWarnings("Duplicates")
    @Test
    public void test() {
        assertNotNull(dataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<String> names = jdbcTemplate.query("select `user_name` from `user`", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("user_name");
            }
        });

        for (String name : names) {
            System.out.println(name);
        }

        assertEquals(3, names.size());
    }

}