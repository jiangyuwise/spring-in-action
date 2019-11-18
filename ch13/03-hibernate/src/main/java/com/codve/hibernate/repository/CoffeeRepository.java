package com.codve.hibernate.repository;

import com.codve.hibernate.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author admin
 * @date 2019/11/18 17:02
 */
@Repository
@Transactional
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
