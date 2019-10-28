package com.codve;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * @author admin
 * @date 2019/10/28 16:27
 */
@Configuration
public class DataSourceConfig {
    @Bean(destroyMethod = "shutdown")
    @Profile("dev")
    public DataSource h2() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
    }

    @Bean
    @Profile("pro")
    public DataSource jndi() {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiName("jdbc/myDB");
        factoryBean.setResourceRef(true);
        factoryBean.setProxyInterface(javax.sql.DataSource.class);
        return (DataSource) factoryBean.getObject();
    }
}
