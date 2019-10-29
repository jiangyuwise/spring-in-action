package com.codve;

import org.aspectj.lang.annotation.*;

/**
 * 使用 XML 定义切面, 见 app.xml
 * @author admin
 * @date 2019/10/29 12:20
 */
public class PersonAspect {

    public void prepare() {
        System.out.println("prepare");
    }

    public void after() {
        System.out.println("finished.");
    }

    public void error() {
        System.out.println("something wrong happened.");
    }
}
