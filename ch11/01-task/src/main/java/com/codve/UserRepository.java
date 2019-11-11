package com.codve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/10 18:28
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
}
