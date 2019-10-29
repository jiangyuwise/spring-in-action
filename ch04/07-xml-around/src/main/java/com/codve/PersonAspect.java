package com.codve;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 使用 XML 定义环绕切面, 见 app.xml
 * @author admin
 * @date 2019/10/29 12:20
 */
public class PersonAspect {

    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("prepare");
        try {
            joinPoint.proceed();
            System.out.println("finish");
        } catch (Throwable throwable) {
            System.out.println("something wrong happened.");
        }
    }
}
