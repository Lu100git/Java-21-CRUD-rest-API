package com.company.records;
import java.time.LocalDate;

// Entity
public record Record(Integer id, String hostname, String os, Vm vm, String location, LocalDate date) {
    public Record{
        date = LocalDate.now();
        
        if(location == null){
            location = "unknown location";
        }
    }
}
