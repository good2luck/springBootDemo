package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.xudj.demo.config.RabbitConfig;
import top.xudj.demo.domain.entity.UserEntity;

import java.util.Date;

/**
 * Created by xudj on 17/12/21.
 */
@Component
@Slf4j
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * mq发送
     */
    public void sender() {
        String context = "hello " + new Date();
        log.debug("sender:{}", context);
        // hello队列
        this.amqpTemplate.convertAndSend(RabbitConfig.helloQueueName, context);
    }


    /**
     * mq发送
     */
    public void senderMsg(String msg) {
        String context = msg + new Date();
        log.debug("sender:{}", context);
        // hello队列
        this.amqpTemplate.convertAndSend(RabbitConfig.helloQueueName, context);
    }


    /**
     * mq发送
     */
    public void senderMsg2(String msg) {
        String context = msg + new Date();
        log.debug("sender2:{}", context);
        // hello队列
        this.amqpTemplate.convertAndSend(RabbitConfig.helloQueueName, context);
    }

    /**
     * mq发送User
     */
    public void senderUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("xudj");
        userEntity.setAge((byte)18);
        userEntity.setSex("x");
        log.debug("sender user:{}", userEntity);
        // hello队列
        this.amqpTemplate.convertAndSend(RabbitConfig.userQueueName, userEntity);
    }


    /**
     * mq发送topMsg
     */
    public void sendTopMsg() {
        String msg1 = "top message ------";
        log.debug("send " + msg1);
        /**
         * 第一个参数是：交换机的名字
         * 第二个参数是：路由的key
         * 第三个参数是：数据
         */
        this.amqpTemplate.convertAndSend("exchange", RabbitConfig.topicMessage, msg1);

        String msg2 = "top messages ======";
        log.debug("send " + msg2);
        this.amqpTemplate.convertAndSend("exchange", RabbitConfig.topicMessages, msg2);
    }


    /**
     * mq发送fanout
     */
    public void sendFanout() {
        String msg = "fanout msg";
        log.debug("sender:{}", msg);
        /**
         * 第一个参数是：交换机的名字
         * 第二个参数是：路由的key
         * 第三个参数是：数据
         */
        this.amqpTemplate.convertAndSend("fanoutExchange", "abcd.ee", msg);
    }


}
