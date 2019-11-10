package com.codve;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * @author admin
 * @date 2019/11/6 17:20
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public Properties properties1() {
        Properties properties = new Properties();
        properties.put("databaseName", "pro_spring");
        properties.put("user", "spring");
        properties.put("password", "qZF08fDcidSRJ2CI");
        return properties;
    }

    @Bean
    public DataSource dataSource1() {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setUniqueResourceName("dataSource1");
        dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        dataSource.setXaProperties(properties1());
        dataSource.setPoolSize(5);
        return dataSource;
    }

    @Bean
    @Qualifier("jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1() {
        return new JdbcTemplate(dataSource1());
    }

    @Bean
    public Properties properties2() {
        Properties properties = new Properties();
        properties.put("databaseName", "pro_spring_slave_1");
        properties.put("user", "spring");
        properties.put("password", "qZF08fDcidSRJ2CI");
        return properties;
    }

    @Bean
    public DataSource dataSource2() {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setUniqueResourceName("dataSource2");
        dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        dataSource.setXaProperties(properties2());
        dataSource.setPoolSize(5);
        return dataSource;
    }

    @Bean
    @Qualifier("jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2() {
        return new JdbcTemplate(dataSource2());
    }

    @Bean(initMethod = "init", destroyMethod = "shutdownForce")
    public UserTransactionService userTransactionService() {
        Properties properties = new Properties();
        properties.put("com.atomikos.icatch.service",
                "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
        return new UserTransactionServiceImp(properties);
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    @DependsOn("userTransactionService")
    public UserTransactionManager userTransactionManager() {
        UserTransactionManager manager = new UserTransactionManager();
        manager.setStartupTransactionService(false);
        manager.setForceShutdown(true);
        return manager;
    }

    @Bean
    @DependsOn("userTransactionService")
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(300);
        return userTransaction;
    }

    @Bean
    public JtaTransactionManager transactionManager() throws SystemException {
        JtaTransactionManager manager = new JtaTransactionManager();
        manager.setTransactionManager(userTransactionManager());
        manager.setUserTransaction(userTransaction());
        return manager;
    }

}
