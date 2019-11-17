package com.codve.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author admin
 * @date 2019/11/17 16:57
 */
@Configuration
@MapperScan(basePackages = SpringDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "springSqlSessionFactory")
public class SpringDataSourceConfig {

    static final String PACKAGE = "com.codve.dao.spring";
    static final String MAPPERS = "classpath:mapper/*.xml";

    @Bean("springDataSourceProperties")
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("springDataSource")
    @Primary
    public DataSource dataSource() {
        DataSourceProperties dataSourceProperties = dataSourceProperties();
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean("springTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean("springSqlSessionFactory")
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("springDataSource") DataSource dataSource) throws IOException {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.codve.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(MAPPERS);
        factoryBean.setMapperLocations(resources);
        return factoryBean;
    }
}
