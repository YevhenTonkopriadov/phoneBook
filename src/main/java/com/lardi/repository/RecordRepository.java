package com.lardi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lardi.model.Record;

@Repository
public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {

    @Override
    void delete(Long id);

    @Override
    Record findOne(Long id);
}
