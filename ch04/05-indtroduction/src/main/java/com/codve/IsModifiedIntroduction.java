package com.codve;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 使用引入为已有的类添加新功能
 * @author admin
 * @date 2019/10/29 14:21
 */
@Aspect
public class IsModifiedIntroduction {

    @DeclareParents(value = "com.codve.Person+",
            defaultImpl = IsModifiedImpl.class)
    public static IsModified isModified;
}
