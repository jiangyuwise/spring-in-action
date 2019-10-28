package com.codve;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/10/28 17:14
 */
@Configuration
public class PersonConfig {

    @Bean
    @Conditional(PersonExistCondition.class)
    public Person person() {
        return new Person();
    }
}
