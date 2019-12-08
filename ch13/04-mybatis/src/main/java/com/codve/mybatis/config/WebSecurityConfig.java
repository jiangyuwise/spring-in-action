package com.codve.mybatis.config;

import com.codve.mybatis.service.UserService;
import com.codve.mybatis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author admin
 * @date 2019/12/07 18:22
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userServiceImpl = userService;
    }

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //@formatter:off
        auth
            .userDetailsService(userServiceImpl);
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .authorizeRequests()
            .antMatchers("/user/delete/*").hasRole("USER")
            .antMatchers("/article/delete/*").hasAuthority("ROLE_USER")
            .anyRequest().permitAll().and()
            .formLogin().and()
            .httpBasic().and()
            .rememberMe().tokenValiditySeconds(30).key("123456")
        ;
        //@formatter:on
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}