package com.example.dealervehicle.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.dealervehicle..*(..)) && (@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service)) && !within(com.example.dealervehicle.security.*)")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering {} with args {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.example.dealervehicle..*(..)) && (@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service)) && !within(com.example.dealervehicle.security.*)", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("Exiting {} with result {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.dealervehicle..*(..)) && !within(com.example.dealervehicle.security.*)", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in {}: {}", joinPoint.getSignature(), ex.getMessage(), ex);
    }
}






