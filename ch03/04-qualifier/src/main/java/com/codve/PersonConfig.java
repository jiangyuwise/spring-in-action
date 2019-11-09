package com.codve;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 使用 Qualifier 解决冲突
 * @author admin
 * @date 2019/10/28 17:36
 */
@Configuration
@ComponentScan
public class PersonConfig {

    @Bean
    public Person employee() {
        return new Employee("Jimmy", "developer");
    }

    @Bean
    @Qualifier("student")
    public Person student() {
        return new Student("James", "CS");
    }
}
