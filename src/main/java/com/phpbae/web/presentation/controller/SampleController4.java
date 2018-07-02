package com.phpbae.web.presentation.controller;

import com.phpbae.web.business.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class SampleController4 {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity test(){
        transactionService.getTestData();
        return new ResponseEntity("", HttpStatus.OK);
    }

    @GetMapping(value = "test2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity test2(){
        transactionService.transactionTest();
        return new ResponseEntity("", HttpStatus.OK);
    }
}
