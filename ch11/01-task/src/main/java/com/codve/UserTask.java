package com.codve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/11 10:19
 */
@Component
public class UserTask {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "0/1 * * * * *")
    @Async
    public void task1() throws InterruptedException {
        System.out.println("task1: " + Thread.currentThread().getName());
        while (true) {
            Thread.sleep(5000);
        }
    }

    @Scheduled(cron = "0/1 * * * * *")
    @Async
    public void task2() {
        System.out.println("task2: " + Thread.currentThread().getName());
    }
}
