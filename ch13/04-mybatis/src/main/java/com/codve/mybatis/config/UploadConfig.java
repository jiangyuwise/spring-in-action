package com.codve.mybatis.config;

import com.codve.mybatis.properties.UploadProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/11/25 17:54
 */
@Configuration
public class UploadConfig {

    @Bean
    public UploadProperties uploadProperties() {
        return new UploadProperties();
    }
}
