package com.codve;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/4 16:37
 */
@Repository
@Transactional(readOnly = true, propagation= Propagation.NOT_SUPPORTED)
public class UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    public User add(User user) {
//        Serializable id = getSession().save(user);
//    }

//    public void add(User user) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.persist(user);
//        transaction.commit();
//        session.close();
//    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User u").list();
    }

//    public long count() {
//        return sessionFactory.getCurrentSession().createQuery("from User u").
//    }

    public User save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        return user;
    }

    public void del(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

}
