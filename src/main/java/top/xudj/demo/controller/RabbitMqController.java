package top.xudj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xudj.demo.component.RabbitMqCallbackSender;
import top.xudj.demo.component.RabbitMqSender;

/**
 * Created by xudj on 17/12/22.
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitMqController {


    @Autowired
    private RabbitMqSender sender;

    @Autowired
    private RabbitMqCallbackSender rabbitMqCallbackSender;

    /**
     * 最简单的hello生产和消费实现（单生产者和单消费者）
     */
    @GetMapping("/hello")
    public void hello() {
        sender.sender();
    }


    /**
     * 单生产者-多消费者
     */
    @GetMapping("/oneToMany")
    public void oneToMany() {
        for(int i=0;i<10;i++){
            sender.senderMsg("hellomsg:"+i);
        }
    }


    /**
     * 多生产者-多消费者
     */
    @GetMapping("/manyToMany")
    public void manyToMany() {
        for (int i = 0; i < 10; i++) {
            sender.senderMsg("hellomsg: " + i);
            sender.senderMsg2("hellomsg: " + i);
        }
    }


    /**
     * 消费实体
     */
    @GetMapping("/user")
    public void user() {
        this.sender.senderUser();
    }


    /**
     * topic exchange类型rabbitmq测试
     */
    @GetMapping("/topicTest")
    public void topicTest() {
        this.sender.sendTopMsg();
    }


    /**
     * fanout exchange类型rabbitmq测试
     */
    @GetMapping("/fanoutTest")
    public void fanoutTest() {
        this.sender.sendFanout();
    }


    /**
     * callback
     */
    @GetMapping("/callback")
    public void callback() {
        this.rabbitMqCallbackSender.sender();
    }
}

