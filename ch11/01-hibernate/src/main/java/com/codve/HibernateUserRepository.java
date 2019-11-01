package com.codve;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/1 11:18
 */
@Repository
public class HibernateUserRepository implements UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public long count() {
        return currentSession().createCriteria(User.class).list().size();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User findOne(User user) {
        return null;
    }

    @Override
    public List<User> findByUsername(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
