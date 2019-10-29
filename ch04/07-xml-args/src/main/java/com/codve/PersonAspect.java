package com.codve;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 XML 定义带有参数的前置切面, 记录 employee 的工作小时数 见 app.xml
 * @author admin
 * @date 2019/10/29 12:20
 */
public class PersonAspect {

    private List<Integer> hours;

    public PersonAspect() {
        this.hours = new ArrayList<>();
    }

    public List<Integer> getHours() {
        return hours;
    }

    public void before(int hour) {
        hours.add(hour);
    }
}
