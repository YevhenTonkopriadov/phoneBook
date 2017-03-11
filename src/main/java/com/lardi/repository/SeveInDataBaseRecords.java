package com.lardi.repository;

import com.lardi.model.Record;
import com.lardi.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by ellik on 11.03.2017.
 */

@Transactional
public class SeveInDataBaseRecords implements RecordRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public Iterable<Record> findByUser(User user) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Record where user=:user");
        query.setParameter("user", user);
        return query.list();
    }

    @Override
    public Record save(Record record) {
        Session session = factory.getCurrentSession();
        factory.getCurrentSession().save(record);
        return record;
    }

    @Override
    public Iterable<Record> findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from " + Record.class.getName());
        return query.list();
    }

    @Override
    public void delete(Long id) {
        Session session = factory.getCurrentSession();
        Record record = (Record)session.load(Record.class, id);
        if(null != record){
            session.delete(record);
        }
    }

    @Override
    public Record findOne(Long id) {
        Record record = (Record) factory.getCurrentSession().load(Record.class,id);
        return record;
    }
}
