package com.codve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/11 10:19
 */
@Component
public class UserTask {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private boolean finished = false;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void showAll() {
        logger.info("job start.");
        List<User> userList = userService.findAll();
        userList.forEach(e -> System.out.println(e.toString()));
        finished = true;
        logger.info("job finished.");
    }

    public boolean isFinished() {
        return finished;
    }
}
