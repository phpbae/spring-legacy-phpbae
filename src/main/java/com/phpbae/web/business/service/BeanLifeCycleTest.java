package com.phpbae.web.business.service;

import org.springframework.stereotype.Service;


/**
 * xml을 확인해보자.
 * <bean id="beanLifeCycleTest" class="com.phpbae.web.business.service.BeanLifeCycleTest" init-method="initMethod" destroy-method="destroyMethod"> </bean>
 */

@Service
public class BeanLifeCycleTest {

    public void initMethod() {
        System.out.println("BeanLifeCycleTest init-method");
    }

    public void destroyMethod() {
        System.out.println("BeanLifeCycleTest destroy-method");
    }

    public void print() {
        System.out.println("BeanLifeCycleTest Service");
    }

}
