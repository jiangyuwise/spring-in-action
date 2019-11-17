package com.codve.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/11/16 23:07
 */
@Configuration
@MapperScan("com.codve.dao")
public class MapperConfiguration {
}
