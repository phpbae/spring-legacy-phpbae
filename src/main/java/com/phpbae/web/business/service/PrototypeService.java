package com.phpbae.web.business.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 * prototype : 이용할 때마다, 인스턴스를 생성.
 */

@Service
@Scope(value = "prototype")
public class PrototypeService {

    public void print(){
        System.out.println("PrototypeService print() 실행.");
    }

}
