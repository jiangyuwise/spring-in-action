package com.codve.mybatis.aspect;

import com.codve.mybatis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author admin
 * @date 2019/11/21 10:14
 */
@Aspect
@Slf4j
public class UserServiceAspect {

    @Pointcut("execution (* com.codve.mybatis.service.UserService.findById(Long)) && args(id)")
    public void findByIdPointcut(Long id) {

    }

    @Before(value = "findByIdPointcut(id)", argNames = "id")
    public void beforeFindById(Long id) {
        log.warn("before invoke UserService.findById({})", id);
    }

    @After(value = "findByIdPointcut(id)", argNames = "id")
    public void afterFindById(Long id) {
        log.warn("after invoke UserService.findById({})", id);
    }

    @Around("execution (* com.codve.mybatis.service.UserService.findById(..))")
    public Object aroundFindById(ProceedingJoinPoint joinPoint) throws Throwable {
        log.error("start findById");
        Object object = joinPoint.proceed();
        log.error("end findById");
        return object;
    }

    @Around("execution(* com.codve.mybatis.service.UserService.save(..))")
    public Object aroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
        log.warn("start UserService.save()");
        Object object = joinPoint.proceed();
        log.warn("finish UserService.save()");
        return object;
    }

    @Pointcut("execution(* com.codve.mybatis.service.UserService.deleteById(..))")
    public void deleteByIdPointcut() {

    }

    @AfterReturning("deleteByIdPointcut()")
    public void afterReturnDeleteById() {
        log.warn("after return UserService.deleteById().");
    }

    @Pointcut("execution(* com.codve.mybatis.service.UserService.find(..))")
    public void findPointcut() {}

    @Around("findPointcut()")
    public Object aroundFind(ProceedingJoinPoint joinPoint) throws Throwable {
        log.warn("start UserService.find()");
        Object object = joinPoint.proceed();
        log.warn("finish UserService.find()");
        return object;
    }
}
