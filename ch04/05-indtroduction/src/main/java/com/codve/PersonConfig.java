package com.codve;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * TODO 缺少代码
 * @author admin
 * @date 2019/10/29 14:34
 */
@EnableAspectJAutoProxy
@Configuration
public class PersonConfig {

    @Bean
    public Person employee() {
        return new Employee();
    }

    @Bean
    public IsDeveloperIntroduction introduction() {
        return new IsDeveloperIntroduction();
    }

}
