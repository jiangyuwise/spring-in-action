package com.codve.repository.slave1;

import com.codve.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @date 2019/11/17 21:25
 */
@Repository("slave1UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
