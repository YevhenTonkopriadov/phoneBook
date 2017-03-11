package com.lardi.repository;

import com.lardi.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lardi.model.Record;

@Repository
public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {
    Iterable <Record> findByUser(User user);
}
