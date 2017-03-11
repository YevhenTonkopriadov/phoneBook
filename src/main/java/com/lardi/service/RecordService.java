package com.lardi.service;

import com.lardi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lardi.model.Record;
import com.lardi.repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Iterable<Record> findAllRecordsCurrentUser() {
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return recordRepository.findByUser(user);
    }

    public void delete(Long id){recordRepository.delete(id);}

    public  Record  findOne(Long id){return recordRepository.findOne(id);}

}
