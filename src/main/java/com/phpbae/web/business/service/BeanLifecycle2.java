package com.phpbae.web.business.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * Bean 생명주기
 * InitializingBean / DisposableBean 인터페이스 구현
 */

@Service
public class BeanLifecycle2 {

    /**
     * 초기 처리를 담당.
     * 단, Argument 는 없어야 하며, void 반환형이어야 한다.
     * Bean 생성과 properties 주입 후에 호출이 된다.
     */
    //init-method
    @PostConstruct
    public void initMethod() {
        System.out.println("BeanLifecycle2 init-method");
    }

    /**
     * 종료 처리를 담당.
     * 단, Argument 는 없어야 하며, void 반환형이어야 한다.
     * Bean 소멸전에 호출이 된다.
     */
    //destroy-method
    @PreDestroy
    public void destroyMethod() {
        System.out.println("BeanLifecycle2 destroy-method");
    }

    public void print(){
        System.out.println("BeanLifecycle2 Service");
    }
}
