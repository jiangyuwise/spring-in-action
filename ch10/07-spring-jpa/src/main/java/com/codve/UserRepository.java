package com.codve;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/4 16:37
 */
public interface UserRepository extends CrudRepository<User, Long>, UserQuery {
    List<User> findByName(String name);
}
