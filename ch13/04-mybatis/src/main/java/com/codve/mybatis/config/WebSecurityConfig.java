package com.codve.mybatis.config;

import com.codve.mybatis.model.auth.AuthEntryPoint;
import com.codve.mybatis.model.auth.AuthSuccessHandler;
import com.codve.mybatis.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @author admin
 * @date 2019/12/07 18:22
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
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
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authEntryPoint()).and()
            .authorizeRequests()
            .anyRequest().permitAll().and()
            .formLogin()
            .successHandler(successHandler())
            .failureHandler(failureHandler()).and()
            .logout();

        //@formatter:on
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthSuccessHandler successHandler() {
        return new AuthSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public AuthEntryPoint authEntryPoint() {
        return new AuthEntryPoint();
    }
}