package com.codve;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;

/**
 * @author admin
 * @date 2019/10/29 14:25
 */
public class IsModifiedImpl extends DelegatingIntroductionInterceptor implements IsModified {

    private static final String methodName = "setName";
    private boolean isModified = false;

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Method method = mi.getMethod();
        if (methodName.equals(method.getName())) {
            isModified = true;
        }
        return super.invoke(mi);
    }
}
