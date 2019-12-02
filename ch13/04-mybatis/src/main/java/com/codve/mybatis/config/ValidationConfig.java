package com.codve.mybatis.config;

import com.codve.mybatis.validation.StringTrimModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/11/30 21:21
 */
@Configuration
public class ValidationConfig {

    @Bean
    public StringTrimModule stringTrimModule() {
        return new StringTrimModule();
    }
}
