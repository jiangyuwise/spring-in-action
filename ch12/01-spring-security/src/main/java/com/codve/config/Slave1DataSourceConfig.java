package com.codve.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author admin
 * @date 2019/11/17 16:57
 */
@Configuration
@MapperScan(basePackages = Slave1DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "slave1SqlSessionFactory")
public class Slave1DataSourceConfig {

    static final String PACKAGE = "com.codve.dao.slave1";

    @Bean("slave1DataSourceProperties")
    @ConfigurationProperties("slave1.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("slave1DataSource")
    public DataSource dataSource() {
        DataSourceProperties dataSourceProperties = dataSourceProperties();
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean("slave1TransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean("slave1SqlSessionFactory")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("slave1DataSource") DataSource dataSource) {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.codve.model");
        return factoryBean;
    }
}
