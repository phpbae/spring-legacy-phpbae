package com.phpbae.web.business.service;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 *  싱글턴 인스턴스를 생성.
 */

@Service
@Scope(value = "singleton")
public class SingletonService {
    public void print() {
        System.out.println("SingletonService print() 실행.");
    }
}
