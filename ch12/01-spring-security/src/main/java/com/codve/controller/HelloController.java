package com.codve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author admin
 * @date 2019/11/16 19:39
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello", method = GET)
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "home", method = GET)
    public String home() {
        return "home";
    }
}
