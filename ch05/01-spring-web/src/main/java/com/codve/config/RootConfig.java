package com.codve.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.context.annotation.ComponentScan.*;

/**
 * @author admin
 * @date 2019/10/29 17:54
 */
@Configuration
@ComponentScan(basePackages = "com.codve",
        excludeFilters = {@Filter(type= FilterType.ANNOTATION, value= EnableWebMvc.class)})
public class RootConfig {
}
