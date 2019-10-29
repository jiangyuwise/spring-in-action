package com.codve;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author admin
 * @date 2019/10/29 12:32
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class PersonConfig {

    @Bean
    public PersonAspect personAspect() {
        return new PersonAspect();
    }

    @Bean
    public Person employee() {
        return new Employee();
    }
}
