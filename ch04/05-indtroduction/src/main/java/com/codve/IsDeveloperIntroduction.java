package com.codve;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 使用引入为已有的类添加新功能, 需要声明一个 bean
 * @author admin
 * @date 2019/10/29 14:21
 */
@Aspect
public class IsDeveloperIntroduction {

    /**
     * value 表示要引入新功能的类, + 表示 Person 的子类要引入新功能, 而不是 Person
     * 注解的静态属性表示要引入的新功能
     * defaultImpl 表示新功能的实现
     */
    @DeclareParents(value = "com.codve.Person+",
            defaultImpl = IsDeveloperImpl.class)
    public static IsDeveloper isDeveloper;
}
