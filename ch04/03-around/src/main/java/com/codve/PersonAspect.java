package com.codve;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 定义切面
 * @author admin
 * @date 2019/10/29 12:20
 */
@Aspect
public class PersonAspect {

    // 定义切入点
    @Pointcut("execution (* com.codve.Person.work(..))")
    public void pointcut() {

    }

    // 定义环绕通知
    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("prepare");
            joinPoint.proceed();
            System.out.println("finished.");
        } catch (Throwable throwable) {
            System.out.println("something wrong happened.");
        }
    }

}
