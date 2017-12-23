package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.xudj.demo.config.RabbitConfig;

/**
 * Created by xudj on 17/12/21.
 */
@Component
@Slf4j
@RabbitListener(queues = RabbitConfig.helloQueueName)
public class RabbitMqReceiver2 {


    /**
     * 接受队列数据
     */
    @RabbitHandler
    public void receiver(String context) {
        log.debug("receiver2:{}", context);
    }


}
