package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAopHelper.class);

    /**
     * @GetMapping 설정된 메소드 또는 클래스 설정
     * GetMapping 어노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    @Pointcut("execution(* com.example.demo.aop.HelloController.hello())")
//    @Pointcut("execution(String com.example.demo.aop.HelloController.hello())")
//    @Pointcut("execution(public String hello*(..))") // 리턴타입, 메서드 이름이 hello로 시작하고, 파라미터가 0개 이상인 메서드 호출
//    @Pointcut("execution(* hello*())") // 메서드 이름이 hello로 시작하고, 파라미터가 0개인 메서드 호출
    public void GetMapping() {
    }

    /**
     * @param joinPoint
     */
    @Before("GetMapping()")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("=====================AspectJ TEST  : Before Logging Start=====================");
        LOGGER.info("=====================AspectJ TEST  : Before Logging End=====================");
    }

    /**
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        LOGGER.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("GetMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("=====================AspectJ TEST  : Around Logging Start=====================");
        try {
            Object result = joinPoint.proceed();
            LOGGER.info("=====================AspectJ TEST  : Around Logging END=====================");
            return result;
        } catch (Exception e) {
            LOGGER.error("=====================AspectJ Around Exception=====================");
            LOGGER.error(e.toString());
            return null;
        }
    }
}
