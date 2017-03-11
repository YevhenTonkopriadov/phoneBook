package com.lardi.repository;

import com.lardi.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ellik on 12.03.2017.
 */


@Transactional
public class SeveInDataBaseUser implements UserRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public User findByUsername(String username) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from User u where u.username = :login");
        query.setParameter("login",username);
        List<User> results = query.list();
        if(results.size()==1){
            return (User)results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User save(User user) {
        Session session = factory.getCurrentSession();
        factory.getCurrentSession().save(user);
        return user;
    }
}
