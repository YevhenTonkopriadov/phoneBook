package com.lardi.repository;

import com.lardi.model.User;
import com.lardi.model.Record;

public interface RecordRepository {
    Iterable <Record> findByUser(User user);
    Record save(Record record);
    void delete(Long id);
    Record findOne(Long id);
    Iterable <Record> filteredRecordsCurrentUser(String findText, User user);
}
