package com.lardi.repository;

import com.lardi.model.Record;
import com.lardi.model.User;

/**
 * Created by ellik on 11.03.2017.
 */
public class SaveInFileRecords implements RecordRepository {

    @Override
    public Iterable<Record> findByUser(User user) {
        return null;
    }

    @Override
    public Record save(Record record) {
        return null;
    }

    @Override
    public Iterable<Record> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Record findOne(Long id) {
        return null;
    }
}
