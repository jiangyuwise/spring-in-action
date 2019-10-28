package com.codve;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 如果 环境变量中存在 person 配置, 就实例化 person bean.
 * 可以在 jvm 中添加 -Dperson=true 通过测试
 * @author admin
 * @date 2019/10/28 17:15
 */
public class PersonExistCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env.containsProperty("person");
    }
}
