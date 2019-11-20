package com.codve.mybatis.service;

import com.codve.mybatis.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 14:10
 */
@Service
public interface UserService {

    int save(User user);

    int deleteById(Long id);

    int update(User user);

    User findById(Long id);

    List<User> find(User user,
                    Long start,
                    Long end,
                    List<Long> userIds,
                    Integer orderBy,
                    int pageNum,
                    int pageSize);
}
