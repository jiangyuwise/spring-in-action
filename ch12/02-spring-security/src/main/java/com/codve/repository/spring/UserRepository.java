package com.codve.repository.spring;

import com.codve.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @date 2019/11/17 21:25
 */
@Repository("springUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.id=:id")
    User selectById2(@Param("id") Long id);
}
