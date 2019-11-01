package com.codve;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author admin
 * @date 2019/11/1 12:14
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class UserConfig implements TransactionManagementConfigurer {

    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        builder.addScript("classpath:schema.sql");
        builder.addScript("classpath:data.sql");
        return builder.build();
    }

    @Bean
    public SessionFactory sessionFactoryBean() throws Exception {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        Properties props = new Properties();

        factoryBean.setAnnotatedClasses(new Class<?>[]{User.class});
        props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        factoryBean.setHibernateProperties(props);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory);
        return manager;
    }
}
