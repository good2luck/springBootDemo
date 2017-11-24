package top.xudj.demo.component;

import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudj on 17/11/24.
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    // mongodb日志存储
    private Logger logger = LoggerFactory.getLogger("MongoDB");

    @Pointcut(value = "execution(* top.xudj.demo.controller..*.*(..))")
    public void webLog(){}


    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefoe(JoinPoint joinPoint) {
        log.info("前置通知执行了");

        // 接收到请求参数，纪录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 纪录请求内容
        log.info("url:{}", request.getRequestURL());
        log.info("http method:{}", request.getMethod());
        log.info("ip:{}", request.getRemoteAddr());
        log.info("class method:{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("args:{}", joinPoint.getArgs());

        // 获取要记录到mongodb的日志内容
        BasicDBObject basicDBObject = getBasicDBObject(request, joinPoint);
        // "替换成'
        logger.info(basicDBObject.toJson().replace("\"","'"));
    }

    private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint) {
        // 基本信息
        BasicDBObject r = new BasicDBObject();
        r.append("requestURL", request.getRequestURL().toString());
        r.append("requestURI", request.getRequestURI());
        r.append("queryString", request.getQueryString());
        r.append("remoteAddr", request.getRemoteAddr());
        r.append("remoteHost", request.getRemoteHost());
        r.append("remotePort", request.getRemotePort());
        r.append("localAddr", request.getLocalAddr());
        r.append("localName", request.getLocalName());
        r.append("method", request.getMethod());
        r.append("headers", getHeadersInfo(request));
        r.append("parameters", request.getParameterMap());
        r.append("classMethod", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        r.append("args", Arrays.toString(joinPoint.getArgs()));
        return r;

    }

    /**
     * 请求头
     * @param request
     * @return
     */
    private Object getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
