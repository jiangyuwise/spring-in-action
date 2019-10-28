package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 通过 env 获取环境变量
 * @author admin
 * @date 2019/10/28 19:04
 */
@Configuration
@PropertySource("classpath:app.properties")
public class PersonConfig {

    @Autowired
    public Environment env;

    @Bean
    public Person person() {
        return new Person(env.getProperty("person.name"),
                env.getProperty("person.age", Integer.class, 20));
    }
}
