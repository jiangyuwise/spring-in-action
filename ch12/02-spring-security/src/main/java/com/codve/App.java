package com.codve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

/**
 * @author admin
 * @date 2019/11/16 19:35
 */
@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                JdbcTemplateAutoConfiguration.class,
                JdbcTemplateAutoConfiguration.class,
        }
)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
