package com.codve.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author admin
 * @date 2019/11/18 18:57
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.codve.mybatis.dao")
public class MapperConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setLimit(2);
        return page;
    }
}
