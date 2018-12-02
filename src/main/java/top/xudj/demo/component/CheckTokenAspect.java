package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by xudj on 17/11/23.
 */
//@Component
@Aspect
@Slf4j
@Order(10)
public class CheckTokenAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(public * top.xudj.demo.controller.UserJpaController.*(..))")
    public void checkToken() {}


    @Before("checkToken()")
    public void before(JoinPoint joinPoint) {
        log.info("CheckTokenAspect before");
    }


    @AfterReturning(pointcut = "checkToken()", returning = "res")
    public void afterReturn(Object res) {
        // 处理完请求，返回内容
        log.info("check response : {}", res);
    }


}
