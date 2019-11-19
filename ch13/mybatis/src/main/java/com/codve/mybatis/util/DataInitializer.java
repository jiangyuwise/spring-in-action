package com.codve.mybatis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author admin
 * @date 2019/11/18 19:07
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
        populator.addScript(new ClassPathResource("data/schema.sql"));
        populator.addScript(new ClassPathResource("data/data.sql"));
    }

    public void init() {
        populator.execute(dataSource);
    }

}
