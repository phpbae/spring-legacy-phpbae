package com.phpbae.web.business.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 어노테이션을 이용한 AOP 예제
 * 어노테이션으로 정의하더라도, Bean 정의 파일에 aop:aspectj-autoproxy 태그를 추가해줘야한다.
 */

@Aspect
@Component
public class AOPSampleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AOPSampleService.class);

    @Before(value = "execution(* com.phpbae.web.business.service..*Service.*(..))")
    public void before(JoinPoint joinPoint){
        // 메서드 시작 시, 동작하는 어드바이스
        System.out.println("Hello before! ** 메서드 호출되기 전에 나온다!");
        LOGGER.info("로깅");
    }

    @After(value = "execution(* com.phpbae.web.business.service..*Service.*(..))")
    public void after(JoinPoint joinPoint){
        // 메서드 종료 시, 동작하는 어드바이스
        System.out.println("Hello after! ** 메서드 호출된 후에 나온다!");
        LOGGER.info("로깅");
    }

    @AfterReturning(value = "execution(* com.phpbae.web.business.service..*Service.*(..))")
    public void afterRunning(JoinPoint joinPoint){
        // 메서드가 정상 종료 됬을 때, 동작하는 어드바이스
        System.out.println("Hello afterRunning! ** 메서드 호출된 후에 나온다!(성공)");
        LOGGER.info("로깅");
    }

    @Around(value = "execution(* com.phpbae.web.business.service..*Service.*(..))")
    public void around(JoinPoint joinPoint){
        // 메서드 호출 전 후에, 동작하는 어드바이스
        System.out.println("Hello around! ** 메서드 호출 전 후에 나온다!");
        LOGGER.info("로깅");
    }

    @AfterThrowing(value = "execution(* com.phpbae.web.business.service..*Service.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        // 메서드 호출이 예외를 던지면, 동작하는 어드바이스
        System.out.println("Hello afterThrowing!");
        LOGGER.info("로깅");
    }

}
