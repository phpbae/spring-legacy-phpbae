package com.phpbae.web.business.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPLogging {

    private static final Logger logger = LoggerFactory.getLogger(AOPLogging.class);

    @Before(value = "execution(* com.phpbae.web.business.service..*Service.*(..))")
    public void beforeLogging(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature(); //메서드의 이름
        Object[] objects = joinPoint.getArgs(); //파라미터 목록
        if (objects.length != 0) {
            logger.info("before : " + signature.getName() + " " + objects[0]);
        } else {
            logger.info("before : " + signature.getName());
        }

    }

    @After(value = "execution(* com.phpbae.web.business.service..*Service.*(..))")
    public void afterLogging(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature(); //메서드의 이름
        Object[] objects = joinPoint.getArgs(); //파라미터 목록
        if (objects.length != 0) {
            logger.info("after : " + signature.getName() + " " + objects[0]);
        } else {
            logger.info("after : " + signature.getName());
        }
    }
}
