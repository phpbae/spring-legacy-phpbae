package com.phpbae.web.presentation.controller;


import com.phpbae.web.business.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/aop")
public class SampleController3 {

    @Autowired
    private SampleService sampleService;

    /**
     * 어노테이션 AOP 설정 예제.
     * @return
     */
    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity aopTest(){
        sampleService.getSampleData();
        sampleService.getJpaDataOwner();
        return new ResponseEntity("", HttpStatus.OK);
    }
}
