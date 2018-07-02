package com.phpbae.web.business.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * 어노테이션을 이용한 AOP 예제
 * 어노테이션으로 정의하더라도, Bean 정의 파일에 aop:aspectj-autoproxy 태그를 추가해줘야한다.
 */

@Aspect
@Component
public class AOPSampleComponent {

    @AfterReturning(value = "execution(* com.phpbae.web.business.service..*Service.*(..))")
    public void afterRunning(JoinPoint joinPoint) {
        // 메서드가 정상 종료 됬을 때, 동작하는 어드바이스
        System.out.println("Hello afterRunning! ** 메서드 호출된 후에 나온다!(성공)");
    }

    @Around(value = "execution(* com.phpbae.web.business.service..*Service.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 메서드 호출 전 후에, 동작하는 어드바이스
        System.out.println("Hello around! ** 메서드 호출전에 나온다! : " + proceedingJoinPoint.getSignature().getName());
        proceedingJoinPoint.proceed();
        System.out.println("Hello around! ** 메서드 호출후에 나온다! : " + proceedingJoinPoint.getSignature().getName());
        return proceedingJoinPoint.proceed();
    }

    @AfterThrowing(value = "execution(* com.phpbae.web.business.service..*Service.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        // 메서드 호출이 예외를 던지면, 동작하는 어드바이스
        System.out.println("Hello afterThrowing!");
    }

}
