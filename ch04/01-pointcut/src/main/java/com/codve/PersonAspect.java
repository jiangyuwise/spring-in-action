package com.codve;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 定义切面
 * @author admin
 * @date 2019/10/29 12:20
 */
@Aspect
public class PersonAspect {

    @Before("execution (* com.codve.Person.work(..))")
    public void before() {
        System.out.println("prepare");
    }

    @AfterReturning("execution (* com.codve.Person.work(..))")
    public void afterReturning() {
        System.out.println("finished.");
    }

    @AfterThrowing("execution (* com.codve.Person.work(..))")
    public void afterThrowing() {
        System.out.println("something wrong happened.");
    }
}
