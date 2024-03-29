package com.codve;

import org.aspectj.lang.annotation.*;

/**
 * 定义切面
 * @author admin
 * @date 2019/10/29 12:20
 */
@Aspect
public class PersonAspect {

    @Pointcut("execution (* com.codve.Person.work(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("prepare");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("finished.");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("something wrong happened.");
    }
}
