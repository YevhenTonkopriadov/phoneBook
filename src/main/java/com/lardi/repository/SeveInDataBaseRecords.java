package com.lardi.repository;

import com.lardi.model.Record;
import com.lardi.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created by ellik on 11.03.2017.
 */

public class SeveInDataBaseRecords implements RecordRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    @Transactional
    public Iterable<Record> findByUser(User user) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from record");
        return query.list();
    }

    @Override
    public Record save(Record record) {
        return null;
    }

    @Override
    @Transactional
    public Iterable<Record> findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from " + Record.class.getName());
        return query.list();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Record findOne(Long id) {
        return null;
    }
}
