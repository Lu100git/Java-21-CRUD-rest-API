package com.company.records.service;

import com.company.records.dao.RecordDao;
import com.company.records.Record;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecordsService {
    
    @Autowired
    RecordDao recordDao;
    
    public List<Record> getRed() {
       return recordDao.findRed();
    }
    
    public List<Record> getGreen(){
        return recordDao.findGreen();
    }
    
    public List<Record> getBlue(){
        return recordDao.findBlue();
    }
    
    public List<Record> findByHostName(String hostname){
        return recordDao.findByHostName(hostname);
    }
    
    public Optional<Record> findById(int id){
        return recordDao.findById(id);
    }

    
    public void insertToRed(Record record) {
        this.recordDao.insertToRed(record);
    }

    public void insertToGreen(Record record) {
       this.recordDao.insertToGreen(record);
    }
    
    public void insertToBlue(Record record) {
       this.recordDao.insertToBlue(record);
    }
    
    
    public void update(Record record, int id) {
        this.recordDao.update(record, id);
    }


    public void deleteOnRed(String hostname) {
        this.recordDao.deleteOnRed(hostname);
    }

    public void deleteOnGreen(String hostname) {
        this.recordDao.deleteOnGreen(hostname);
    }

    public void deleteOnBlue(String hostname) {
        this.recordDao.deleteOnBlue(hostname);
    }
}
