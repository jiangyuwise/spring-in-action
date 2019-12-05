package com.codve.mybatis.service;

import com.codve.mybatis.model.data.object.UserDO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @date 2019/12/5 10:16
 */
@Service
@CacheConfig(cacheNames = "KeyService")
public class KeyService {

    @Cacheable
    public UserDO method1() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("james");
        userDO.setBirthday(System.currentTimeMillis());
        return userDO;
    }

    @Cacheable
    public UserDO method2(Long id) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        userDO.setName("james");
        userDO.setBirthday(System.currentTimeMillis());
        return userDO;
    }

    @Cacheable
    public UserDO method3(Long id, String name) {
        UserDO userDO = new UserDO();
        userDO.setId(id);
        userDO.setName(name);
        userDO.setBirthday(System.currentTimeMillis());
        return userDO;
    }

    @Cacheable
    public UserDO method4(Long id, UserDO userDO) {
        if (userDO == null) {
            userDO = new UserDO();
        }
        userDO.setId(id);
        return userDO;
    }

}
