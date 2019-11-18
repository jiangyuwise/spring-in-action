package com.codve.conn.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author admin
 * @date 2019/11/18 13:07
 */
@Component
public class DataInitializer {

    private DataSource dataSource;

    private ResourceDatabasePopulator populator;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initMethod() {
        populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data/data.sql"));
    }

    public void init() {
        populator.execute(dataSource);
    }
}
