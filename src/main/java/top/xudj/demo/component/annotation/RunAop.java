package top.xudj.demo.component.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @program: demo
 * @description: aop注解
 * @author: xudj
 * @create: 2018-11-20 21:44
 **/
@Service
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RunAop {
    /**
     * referer白名单
     *
     * @return
     */
    String[] whiteList() default {};

    /**
     * 是否中断接口调用
     *
     * @return
     */
    boolean interrupt() default false;
}
