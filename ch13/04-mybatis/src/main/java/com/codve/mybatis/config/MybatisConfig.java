package com.codve.mybatis.config;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/11/19 13:13
 */
@Configuration
@MapperScan(basePackages = "com.codve.mybatis.dao")
public class MybatisConfig {

}
