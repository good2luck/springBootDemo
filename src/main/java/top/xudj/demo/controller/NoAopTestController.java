package top.xudj.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xudj.demo.service.UserJpaService;

/**
 * @program: demo
 * @description: test aop
 * @author: xudj
 * @create: 2018-11-20 21:45
 **/
@RestController
@RequestMapping("/no/aop")
@Slf4j
public class NoAopTestController {

    @Autowired
    private UserJpaService userJpaService;

    public NoAopTestController() {
        log.info(this.toString());
    }


    @RequestMapping("/testPubNoAop")
    public String testPubNoAop() {
        log.info("{}:testPubNoAop run", this.getClass().toString());
        return this.toString();
    }

    @RequestMapping("/testPriNoAop")
    private String testPriNoAop() {
        log.info("{}:testPriNoAop run", this.getClass().toString());
        return this.toString();
    }


    public void setUserJpaService(UserJpaService userJpaService) {
        this.userJpaService = userJpaService;
    }
}
