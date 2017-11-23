package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xudj on 17/11/19.
 */
@Component
@Aspect
@Slf4j
@Order(5)
public class WebLogAspect {

    /* 线程内共享数据 */
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();


    /**
     * 切入点
     */
    @Pointcut("execution(public * top.xudj.demo.controller..*.*(..))")
    public void webLog() {}


    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefoe(JoinPoint joinPoint) {
        log.info("前置通知执行了");
        threadLocal.set(System.currentTimeMillis());

        // 接收到请求参数，纪录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 纪录请求内容
        log.info("url:{}", request.getRequestURL());
        log.info("http method:{}", request.getMethod());
        log.info("ip:{}", request.getRemoteAddr());
        log.info("class method:{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("args:{}", joinPoint.getArgs());
    }

    /**
     * 方法执行返回值处理（后置返回通知）
     * 注意：
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("后置返回通知执行了");
        Long time = System.currentTimeMillis() - threadLocal.get();
        log.debug("方法执行时间：{}ms", time);
        // 处理完请求，返回内容
        log.info("response : {}", ret);
    }

    /**
     * 环绕通知
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("环绕通知执行了");
        try {
            //调用执行目标方法
            Object object = proceedingJoinPoint.proceed();
            log.info("object:{}", object);
            return object;
        } catch (Throwable e) {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * 后置异常通知
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "webLog()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        log.info("后置异常通知执行了");
        //目标方法名：
        if(exception instanceof NullPointerException){
            log.error("空指针异常");
        } else{
            log.error("未知异常");
        }
    }

    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     * @param joinPoint
     */
    @After("webLog()")
    public void doAfterAdvice(JoinPoint joinPoint){
        log.info("后置通知执行了");
    }

}
