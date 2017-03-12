package com.lardi.repository;

import com.lardi.model.Record;
import com.lardi.model.User;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by ellik on 11.03.2017.
 */
public class SaveInFileRecords implements RecordRepository {
    private final String fileName = "record.csv";
    private ArrayList<Record> records = new ArrayList<>();

    @Override
    public Iterable<Record> findByUser(User user) {
        records = read(fileName);
        ArrayList<Record> recordsFindByUser = new ArrayList<>();
        records.stream().filter(e->user.getId().equals(e.getUser().getId())).forEach(e->recordsFindByUser.add(e));
        return recordsFindByUser;
    }

    @Override
    public Record save(Record record) {
        if (record.getId()!=null) {
            delete(record.getId());
        }
        int size = records.size();
        if (size!=0) {
            record.setId(new Long(records.get(size-1).getId()+1));
        } else{
            record.setId(new Long(1));
        }
        records.add(record);
        wrire(fileName,records);
        return  record;
    }

    public Iterable<Record> findAll() {
        records = read(fileName);
        return records;
    }

    @Override
    public void delete(Long id) {
        ArrayList<Record> recordsTemp = new ArrayList<>();
        for (Record record: records) {
            if(!record.getId().equals(id)){
                recordsTemp.add(record);
            }
        }
        records= recordsTemp;
        recordsTemp=null;
        wrire(fileName,records);
    }

    @Override
    public Record findOne(Long id) {
        Record findRecord = null;
        for (Record record: records) {
            if(record.getId().equals(id)){
                findRecord=record;
                break;
            }
        }
        return findRecord;
    }

    @Override
    public Iterable<Record> filteredRecordsCurrentUser(String findText, User user) {
        ArrayList <Record> findRecord = new ArrayList<>();
        ArrayList<Record> recordsFindByUser = new ArrayList<>();
        records.stream().filter(e->user.getId().equals(e.getUser().getId())).forEach(e->recordsFindByUser.add(e));
        recordsFindByUser.stream().filter(e-> e.getLastname().contains(findText)|| e.getName().contains(findText)||e.getPhone().contains(findText)).forEach(e->findRecord.add(e));
        return findRecord;
    }

    private static ArrayList<Record> read(String fileName)  {
        ArrayList <Record> records = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()){
            return records;
        }
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream( file.getAbsoluteFile()),"utf8"));
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    String [] recordComponent = line.split(";");
                    Record record = new Record();
                    record.setId(Long.parseLong(recordComponent[0]));
                    User user = new User();
                    user.setId(Long.parseLong(recordComponent[1]));
                    record.setUser(user);
                    record.setLastname(recordComponent[2]);
                    record.setName(recordComponent[3]);
                    record.setSurname(recordComponent[4]);
                    record.setPhone(recordComponent[5]);
                    record.setPhoneHome(recordComponent[6]);
                    record.setAdress(recordComponent[7]);
                    record.setEmail(recordComponent[8]);
                    records.add(record);
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return records;
    }
    private static void wrire(String fileName, ArrayList<Record> records){
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(FileWriter writer = new FileWriter(file, false)) {
            for (Record record:records ) {
                String text = record.getId()+";"+record.getUser().getId()+";"+record.getLastname()+";"+
                        record.getName()+";"+record.getSurname()+";"+record.getPhone()+";"+
                        record.getPhoneHome()+";"+record.getAdress().replaceAll(";","")+";"+record.getEmail()+";";
                writer.write(text);
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();;
        }
    }
}