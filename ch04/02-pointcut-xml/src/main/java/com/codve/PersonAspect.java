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
    public void prepare() {
        System.out.println("prepare");
    }

    @AfterReturning("pointcut()")
    public void after() {
        System.out.println("finished.");
    }

    @AfterThrowing("pointcut()")
    public void error() {
        System.out.println("something wrong happened.");
    }
}
