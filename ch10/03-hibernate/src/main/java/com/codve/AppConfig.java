package com.codve;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author admin
 * @date 2019/11/4 18:26
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        sessionFactoryBean.setAnnotatedClasses(User.class, Article.class);
        Properties properties = new Properties();
        properties.put("dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.jdbc.fetch_size", 50);

        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public TransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
