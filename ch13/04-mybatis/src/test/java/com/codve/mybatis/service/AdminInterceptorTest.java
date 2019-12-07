package com.codve.mybatis.service;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author admin
 * @date 2019/12/7 15:02
 */
class AdminInterceptorTest {

    @Test
    void test() {
        Enhancer enhancer = new Enhancer();

        AdminService adminService = new AdminService();
        AdminInterceptor interceptor = new AdminInterceptor();

        enhancer.setSuperclass(adminService.getClass());
        enhancer.setCallback(interceptor);

        AdminService proxy = (AdminService) enhancer.create();
        proxy.save(1);
    }

}