package com.codve;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author admin
 * @date 2019/11/7 19:34
 */
public class App {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(App.class);
        logger.info("hello, world");
    }
}
