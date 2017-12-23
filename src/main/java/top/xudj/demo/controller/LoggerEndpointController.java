package top.xudj.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xudj on 17/12/23.
 */
@RestController
@Slf4j
public class LoggerEndpointController {

    /**
     * 通过GET：http://localhost:8081/demo/loggers 获取所有端点的日志级别
     * 通过GET：http://localhost:8081/demo/loggers/top.xudj.demo.controller 获取当前端点的日志级别
     * 通过POST：http://localhost:8081/demo/loggers/top.xudj.demo.controller（data:{"configuredLevel": "INFO"}）更改成INFO日志级别
     *
     * 再通过请求当前方法进行验证。
     * @return
     */
    @GetMapping("/test/logger")
    public String testLogLevel() {
        log.debug("Logger Level: DEBUG");
        log.info("Logger Level: INFO");
        log.error("Logger Level ：ERROR");
        return "";
    }

}
