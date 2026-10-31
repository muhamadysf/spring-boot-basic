package com.book_catalog_web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    // method kosong dibawah ini adalah signature pointcut

    // execute --> bisa sampai method dan  khusus pada method tertentu
    @Pointcut("execution(* com.book_catalog_web.controller.CategoryController.findCategoryList(..))")
    private void restAPI(){}

    // execute --> semua class controller yang ada pada package controller
    @Pointcut("execution(* com.book_catalog_web.controller.*.*(..))")
    private void restAPI2(){}

    // within ---> hanya sampai class, tidak bisa memilih method
    @Pointcut("within(com.book_catalog_web.controller.CategoryController)")
    private void withinPointcutExample(){}

    // args ---> hanya tipe argumen / parameter dan bisa dipakai bersamaan dengan designature/pointcut lainnya
    @Pointcut("args(com.book_catalog_web.dto.request.CategoryRequestDTO)")
    private void argsPointcutExample(){}


    // ===========================================================================

    // dibawah ini adalah aspect
//    @Before("withinPointcutExample()")
//    public void beforeExecutedLogging(){
//        log.info("Start Process");
//    }
//
//    @After("restAPI2() && argsPointcutExample()")
//    public void afterExecutedlogging(){
//        log.info("End Process");
//    }


    @Around("restAPI2()")
    public Object processingTimeLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        try {
            log.info("Start process {}", proceedingJoinPoint.getSignature().getName());
            return proceedingJoinPoint.proceed();
        } finally {
            log.info("End process of {}", proceedingJoinPoint.getSignature().getName());
        }
    }

}
