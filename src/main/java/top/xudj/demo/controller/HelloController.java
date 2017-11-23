package top.xudj.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xudj on 17/11/1.
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String index() {
        log.debug("开始执行方法.");
        return "Hello World";
    }

    @GetMapping("/hello/global/exception")
    public String testGlobalException(){
        // 全局异常处理测试
        int i = 1 / 0;
        return "exception";
    }

}
