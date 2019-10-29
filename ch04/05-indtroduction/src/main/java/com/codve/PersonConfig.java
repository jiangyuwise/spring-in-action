package com.codve;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/10/29 14:34
 */
@Configuration
public class PersonConfig {

    @Bean
    public Person employee() {
        return new Employee();
    }

    @Bean
    public IsModifiedIntroduction introduction() {
        return new IsModifiedIntroduction();
    }

    @Bean
    public ProxyFactoryBean proxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.addAdvisor(introduction());
        proxyFactoryBean.setTarget(employee());
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setOptimize(true);
        return proxyFactoryBean;
    }
}
