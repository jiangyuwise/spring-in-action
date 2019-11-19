package com.codve.mybatis.service;

import com.codve.mybatis.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 14:10
 */
@Service
public interface UserService {


    User findById(Long id);

    void deleteById(Long id);

    List<User> findComplex(User user, Long start, Long end, List<Long> ids, Integer orderBy);
}
