package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author admin
 * @date 2019/10/28 17:36
 */
@Configuration
public class PersonConfig {

    @Bean
    public Person employee() {
        return new Employee("Jimmy", "developer");
    }

    // 使用 Primary 解决冲突
    @Bean
    @Primary
    public Person student() {
        return new Student("James", "CS");
    }

    @Bean
    @Autowired
    public Group group(Person person) {
        return new Group(person);
    }
}
