package com.phpbae.web.presentation.controller;

import com.phpbae.web.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
@RequestMapping("/rest")
public class SampleController2 {

    @Autowired
    private PrototypeService prototypeService;

    @Autowired
    private SingletonService singletonService;

    @Autowired
    private BeanLifecycle beanLifecycle;

    @Autowired
    private BeanLifecycle2 beanLifecycle2;

    @Autowired
    private BeanLifeCycleTest beanLifeCycleTest;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @GetMapping(value = "doA", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity doA() {
        System.out.println("singletonService : " + singletonService.hashCode());
        System.out.println("prototypeService : " + prototypeService.hashCode());
        System.out.println("--------------------getBean()");
        System.out.println("singletonService : " + webApplicationContext.getBean("singletonService").hashCode());
        System.out.println("prototypeService : " + webApplicationContext.getBean("prototypeService").hashCode());
        return new ResponseEntity("", HttpStatus.OK);
    }

    /**
     * Bean 생명주기 관련 예제.
     * 소멸되는건, 실행 후, WAS를 내려보자.
     * @return
     */
    @GetMapping(value = "doB", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity doB() {
        beanLifecycle.print();
        beanLifecycle2.print();
        beanLifeCycleTest.print();

        return new ResponseEntity("", HttpStatus.OK);
    }

}
