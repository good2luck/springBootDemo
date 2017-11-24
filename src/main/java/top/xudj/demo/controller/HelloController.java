package top.xudj.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

/**
 * Created by xudj on 17/11/1.
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String index() {
        log.debug("请求hello.");
        return "Hello World";
    }

}
