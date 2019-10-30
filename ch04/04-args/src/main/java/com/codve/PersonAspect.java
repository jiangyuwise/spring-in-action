package com.codve;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义切面, 记录 employee 每次的工作小时数
 * @author admin
 * @date 2019/10/29 12:20
 */
@Aspect
public class PersonAspect {

    List<Integer> hours;

    public PersonAspect() {
        this.hours = new ArrayList<>();
    }

    public List<Integer> getHours() {
        return hours;
    }

    @Pointcut("execution(* com.codve.Person.work(int)) && args(hour)")
    public void pointcut(int hour) {

    }

    @Around("pointcut(hour)")
    public void before(ProceedingJoinPoint joinPoint, int hour) {
        hours.add(hour);
        System.out.println("prepare");
        try {
            joinPoint.proceed();
            System.out.println("finished.");
        } catch (Throwable throwable) {
            System.out.println("something wrong happened.");
        }
    }
}
