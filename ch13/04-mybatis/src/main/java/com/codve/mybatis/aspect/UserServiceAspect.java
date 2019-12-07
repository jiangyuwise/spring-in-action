package com.codve.mybatis.aspect;

import com.codve.mybatis.model.data.object.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author admin
 * @date 2019/11/21 10:14
 */
@Aspect
@Slf4j
public class UserServiceAspect {

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

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

    @AfterThrowing(value = "findByIdPointcut(id)", argNames = "id")
    public void afterThrowingFindById(Long id) {
        log.warn("throws a exception when invoke findById({})", id);
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
        Object[] args = joinPoint.getArgs();
        UserDO userDO = (UserDO) args[0];
        if (userDO != null && userDO.getPassword() != null) {
            log.warn("encrypt password.");
            userDO.setPassword(encoder.encode(userDO.getPassword()));
        }
        return joinPoint.proceed();
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
