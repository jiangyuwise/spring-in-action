package com.codve;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/11 15:26
 */
public class UserQueryImpl implements UserQuery {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findByBirthday(Long start, Long end) {
        String sql = "from User u where u.birthday >= :start and u.birthday < :end";
        TypedQuery<User> query = entityManager.createQuery(sql, User.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }
}
