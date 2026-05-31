package com.example.eCommerceApp.WebApp.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalLoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalLoggingAspect.class);

    @Around("execution(* com.example.eCommerceApp.WebApp.Controllers..*(..)) || " +
            "execution(* com.example.eCommerceApp.WebApp.Services..*(..))")
    public Object logAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().toShortString();
        logger.info(">> Started: {}", methodName);

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        logger.info("<< Finished: {} | {}ms", methodName, duration);
        return result;
    }
}
