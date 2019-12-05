package com.codve.mybatis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author admin
 * @date 2019/12/4 19:11
 */
public class CacheKeyGenerator implements KeyGenerator {

    private ObjectMapper objectMapper;

    public CacheKeyGenerator() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.configure(JsonWriteFeature.QUOTE_FIELD_NAMES.mappedFeature(), false);
    }

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringJoiner joiner = new StringJoiner("_");
        joiner.add(method.getName());

        Arrays.stream(params).filter(Objects::nonNull).forEach(e -> {
            joiner.add(e.getClass().getSimpleName());
            if (isSimpleType(e.getClass())) {
                joiner.add(e.toString());
            } else {
                try {
                    joiner.add(objectMapper.writeValueAsString(e));
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return joiner.toString();
    }

    private boolean isSimpleType(Class<?> cls) {
        return (ClassUtils.isPrimitiveOrWrapper(cls)
                || cls.isEnum()
                || CharSequence.class.isAssignableFrom(cls)
                || Number.class.isAssignableFrom(cls)
                || Date.class.isAssignableFrom(cls)
                || URI.class == cls
                || URL.class == cls
                || Locale.class == cls
                || Class.class == cls
        );
    }
}
