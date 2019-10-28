package com.codve;

/**
 * 创建环绕切片, 见 app2.xml
 * @author admin
 * @date 2019/10/28 11:17
 */
public class PersonInterceptor {

    public void before() {
        System.out.println("prepare");
    }

    public void after() {
        System.out.println("work finished");
    }
}
