package com.codve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author admin
 * @date 2019/11/8 13:23
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.debug("hello, world");
        logger.debug("{}", "hello, world");

    }
}
