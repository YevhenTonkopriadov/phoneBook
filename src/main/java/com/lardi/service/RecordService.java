package com.lardi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lardi.model.Record;
import com.lardi.repository.RecordRepository;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Record save(Record record) {
        return recordRepository.save(record);
    }

    public Iterable<Record> findAll() {
        return recordRepository.findAll();
    }

    public void delete(Long id){recordRepository.delete(id);}

    public  Record  findOne(Long id){return recordRepository.findOne(id);}

}
