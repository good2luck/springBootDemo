package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @program: demo
 * @description: 测试aop
 * @author: xudj
 * @create: 2018-11-20 21:42
 **/
@Component
@Aspect
@Slf4j
public class TestAspect {


    /**
     * 切点
     */
    @Pointcut("@annotation(top.xudj.demo.component.annotation.RunAop)")
    public void cut(){
    }

    /**
     * 增强方法
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("cut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("环绕通知执行了");
        try {
            //调用执行目标方法
            Object object = proceedingJoinPoint.proceed();
            log.info("around object:{}", object);
            return object;
        } catch (Throwable e) {
            e.printStackTrace ();
        }
        return null;
    }

}
