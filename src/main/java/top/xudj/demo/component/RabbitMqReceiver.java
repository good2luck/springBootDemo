package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.xudj.demo.config.RabbitConfig;
import top.xudj.demo.domain.entity.UserEntity;

/**
 * Created by xudj on 17/12/21.
 */
@Component
@Slf4j
public class RabbitMqReceiver {


    /**
     * 接受String类型队列数据
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.helloQueueName)
    public void receiver(String context) {
        log.debug("receiver:{}", context);
    }


    /**
     * 接受User类型队列数据
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.userQueueName)
    public void receiverUser(UserEntity userEntity) {
        log.debug("receiver user:{}", userEntity);
    }


    /**
     * 接受top.message类型队列数据
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.topicMessage)
    public void receiverMsg(String msg) {
        log.debug("receiver top.message:{}", msg);
    }


    /**
     * 接受top.messages类型队列数据
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.topicMessages)
    public void receiverMsgs(String msg) {
        log.debug("receiver top.messages:{}", msg);
    }


    /**
     * 接收fanout.A队列数据
     * @param msg
     */
    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void processA(String msg) {
        System.out.println("FanoutReceiverA  : " + msg);
    }


    /**
     * 接收fanout.B队列数据
     * @param msg
     */
    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void processB(String msg) {
        System.out.println("FanoutReceiverB  : " + msg);
    }


    /**
     * 接收fanout.C队列数据
     * @param msg
     */
    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void processC(String msg) {
        System.out.println("FanoutReceiverC  : " + msg);
    }
}
