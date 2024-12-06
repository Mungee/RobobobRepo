package com.roboticks.robobob.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.roboticks.robobob.service.QuestionService.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Method: " + joinPoint.getSignature().getName() + " - Start");
        Object result = joinPoint.proceed();
        logger.info("Method: " + joinPoint.getSignature().getName() + " - End");
        return result;
    }
}
