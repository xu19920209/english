package com.tuyue.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/25.
 */
//@Aspect
//@Component("httpAspect")
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution(public * com.tuyue.*.*.*.*Controller.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.error("1*****************************************");
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //url
        logger.error("url={}", request.getRequestURI());
        //Method
        logger.error("Method={}", request.getMethod());
        //ip
        logger.error("IP={}", request.getRemoteAddr());
        //类方法
        logger.error("类方法={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数

        logger.error("参数={}", joinPoint.getArgs());

    }

    @After("log()")
    public void doAfter() {
        logger.error("2*****************************************");
    }

    @AfterReturning(returning = "o", pointcut = "log()")
    public void doAfterReturning(Object o) {
        logger.error("返回值={}", o);
        logger.error("3*****************************************");
    }
}
