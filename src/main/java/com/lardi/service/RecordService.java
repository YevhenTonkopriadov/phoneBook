package com.lardi.service;

import com.lardi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.lardi.model.Record;
import com.lardi.repository.RecordRepository;
import java.util.*;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Record save(Record record) {
        return recordRepository.save(record);
    }

    public Iterable<Record> findAllRecordsCurrentUser() {
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return recordRepository.findByUser(user);
    }

    public void delete(Long id){recordRepository.delete(id);}

    public  Record  findOne(Long id){return recordRepository.findOne(id);}

    public Iterable<Record>  filteredRecordsCurrentUser(String findText) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList <Record> rezalt = new ArrayList<>();
        String [] arrayFindText = findText.split("[ ,.:;!?]");
        for (String find:arrayFindText ) {
            if (!find.isEmpty()){
                ArrayList <Record> rezaltTemp = (ArrayList<Record>) recordRepository. filteredRecordsCurrentUser(find, user);
                if (!rezaltTemp.isEmpty()){
                    rezalt.addAll(rezaltTemp);
                }
            }
        }
        Set <Record> set = new HashSet<>(rezalt);
        rezalt.clear();
        rezalt.addAll(set);
        return rezalt;
    }
}
