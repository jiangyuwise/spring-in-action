package com.codve;

/**
 * 使用 XML 定义切面, 见 app.xml
 * @author admin
 * @date 2019/10/29 12:20
 */
public class PersonAspect {

    public void before() {
        System.out.println("prepare");
    }

    public void afterReturning() {
        System.out.println("finished.");
    }

    public void afterThrowing() {
        System.out.println("something wrong happened.");
    }
}
