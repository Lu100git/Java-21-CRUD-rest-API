package com.company.records.dao;

import com.company.records.Record;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;


@Repository
public class RecordDao{

    private final JdbcClient jdbcClient;
    
    public RecordDao(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }
    
    public List<Record> findRed(){
        return jdbcClient.sql("select * from red").query(Record.class).list();
    }

    public List<Record> findGreen(){
        return jdbcClient.sql("select * from green").query(Record.class).list();
    }
    
    public List<Record> findBlue(){
        return jdbcClient.sql("select * from blue").query(Record.class).list();
    }

    
    public List<Record> findByHostName(String hostname){
        return jdbcClient.sql("select * from red where hostname = :hostname UNION ALL select * from green where hostname = :hostname UNION ALL select * from blue where hostname = :hostname;")
                    .param("hostname",hostname)
                    .query(Record.class)
                    .list();
    }

    // prototype function, it was never used
    public Optional<Record> findById(int id) {
        return this.jdbcClient
                   .sql("select * from red where id = :id")
                   .param("id", id)
                   .query(Record.class)
                   .optional();
    }
    
    
    public void insertToRed(Record record) {
       var inserted = jdbcClient.sql("insert into red(hostname, os, vm, location, `date`) values(?,?,?,?,?)")
               .params(List.of(record.caseInfo(), record.hostname(), record.os(), record.vm().toString(), record.location(), record.date()))
               .update();
       Assert.state(inserted == 1, "failed to create run: " + record.hostname());
    }
    
    public void insertToGreen(Record record) {
        var inserted = jdbcClient.sql("insert into green(hostname, os, vm, location, `date`) values(?,?,?,?,?)")
               .params(List.of(record.caseInfo(), record.hostname(), record.os(), record.vm().toString(), record.location(), record.date()))
               .update();
       Assert.state(inserted == 1, "failed to create run: " + record.hostname());
    }
    
    public void insertToBlue(Record record) {
        var inserted = jdbcClient.sql("insert into blue(hostname, os, vm, location, `date`) values(?,?,?,?,?)")
               .params(List.of(record.caseInfo(), record.hostname(), record.os(), record.vm().toString(), record.location(), record.date()))
               .update();
       Assert.state(inserted == 1, "failed to create run: " + record.hostname());
    }
    
    
    // this is not in used, fix it when you have a chance
    public void update(Record record, int id) {
       int updated = jdbcClient.sql("update red set hostname = ?, os = ?, vm = ?, location = ? where id = ?")
               .param(record.hostname())
               .param(record.os())
               .param(record.vm().toString())
               .param(record.location())
               .param(id)
               .update();
       Assert.state(updated == 1, "failed to update record: ");
   }


    public void deleteOnRed(String hostname) {
        int deleted = jdbcClient.sql("delete from red where hostname = :hostname")
                .param("hostname", hostname)
                .update();
        Assert.state(deleted == 1, "failed to delete record: " + hostname);        
    }

    public void deleteOnGreen(String hostname) {
        int deleted = jdbcClient.sql("delete from green where hostname = :hostname")
                .param("hostname", hostname)
                .update();
        Assert.state(deleted == 1, "failed to delete record: " + hostname);        
    }

    public void deleteOnBlue(String hostname) {
        int deleted = jdbcClient.sql("delete from blue where hostname = :hostname")
                .param("hostname", hostname)
                .update();
        Assert.state(deleted == 1, "failed to delete record: " + hostname);   
    }
}
