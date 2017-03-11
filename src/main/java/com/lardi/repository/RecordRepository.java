package com.lardi.repository;

import com.lardi.model.User;
import com.lardi.model.Record;

public interface RecordRepository {
    Iterable <Record> findByUser(User user);
    Record save(Record record);
    Iterable <Record> findAll();
    void delete(Long id);
    Record findOne(Long id);
}
