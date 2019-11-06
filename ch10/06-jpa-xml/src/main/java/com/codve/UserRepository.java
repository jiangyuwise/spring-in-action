package com.codve;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/4 16:37
 */
@Service
@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public long count() {
        return (long) entityManager.createQuery("select count(*) from User").getSingleResult();
    }

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public void del(User user) {
        User merged = entityManager.merge(user);
        entityManager.remove(merged);
    }

    public User findById(Long id) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findById", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllNative() {
        String sql = "select * from `user`";
        return entityManager.createNativeQuery(sql, User.class).getResultList();
    }

}
