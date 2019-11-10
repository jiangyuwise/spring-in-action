package com.codve;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public long count() {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select count(distinct u) from User u"
        );
        return (long) query.uniqueResult();
//        return (long) sessionFactory.getCurrentSession()
//                .getNamedQuery("User.count")
//                .uniqueResult();
    }

    public User save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        return user;
    }

    public void del(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return (User) sessionFactory.getCurrentSession()
                .getNamedQuery("User.findById")
                .setParameter("id", id)
                .uniqueResult();
    }

}
