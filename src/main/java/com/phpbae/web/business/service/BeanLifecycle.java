package com.phpbae.web.business.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


/**
 * Bean 생명주기
 * InitializingBean / DisposableBean 인터페이스 구현
 */

@Service
public class BeanLifecycle implements InitializingBean, DisposableBean {

    /**
     * 초기 처리를 담당.
     * Bean 생성과 properties 주입 후에 호출이 된다.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanLifecycle init-method");
    }

    /**
     * 종료 처리를 담당.
     * Bean 소멸전에 호출이 된다.
     * 단, scope value 값이 prototype 경우 처리 불가능.
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("BeanLifecycle destroy-method");
    }

    public void print(){
        System.out.println("BeanLifecycle Service");
    }
}
