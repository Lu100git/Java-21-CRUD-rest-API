package com.company.records.controller;

import com.company.records.service.RecordsService;
import com.company.records.Record;
import exceptions.RecordNotFoundException;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("records")
public class RecordsController {
    
    private final RecordsService recordsService;
    
    @Autowired
    public RecordsController(RecordsService recordsService){
        this.recordsService = recordsService;
    }
    
    // GET
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("red")
    public List<Record> getRed(){
        return recordsService.getRed();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("green")
    public List<Record> getGreen(){
        return recordsService.getGreen();
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("blue")
    public List<Record> getBlue(){
        return recordsService.getBlue();
    }
    
    // GET BY HOSTNAME
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("allBldgs/{hostname}")
    public List<Record> findByHostName(@PathVariable String hostname){
        return recordsService.findByHostName(hostname);
    }


    // POST  
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("insert/red")
    void insertToRed(@RequestBody Record record){
        this.recordsService.insertToRed(record);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("insert/green")
    void insertToGreen(@RequestBody Record record){
        this.recordsService.insertToGreen(record);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("insert/blue")
    void insertToBlue(@RequestBody Record record){
        this.recordsService.insertToBlue(record);
    }
    
    
    // PUT, NEVER USED, KEEP IT, JUST IN CASE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("red/{id}")
    void update(@RequestBody Record record, @PathVariable int id){
        this.recordsService.update(record, id);   
    }
    
    // DELETE    
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/red/{hostname}")
    void deleteOnRed(@PathVariable String hostname){
        this.recordsService.deleteOnRed(hostname);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/green/{hostname}")
    void deleteOnGreen(@PathVariable String hostname){
        this.recordsService.deleteOnGreen(hostname);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/blue/{hostname}")
    void deleteOnBlue(@PathVariable String hostname){
        this.recordsService.deleteOnBlue(hostname);
    }
}
