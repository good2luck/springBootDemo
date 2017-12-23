package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.xudj.demo.config.RabbitConfig;

import java.util.UUID;

/**
 * Created by xudj on 17/12/23.
 */
@Component
@Slf4j
public class RabbitMqCallbackSender{
// implements RabbitTemplate.ConfirmCallback

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sender() {
//        rabbitTemplate.setConfirmCallback(this);
        String msg = "callbackSender: callback sender.";
        log.debug(msg);

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.debug("callbackSender UUID:{}", correlationData.getId());
        this.rabbitTemplate.convertAndSend("exchange", RabbitConfig.topicMessages, msg, correlationData);
    }

//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        log.debug("callback confirm:{}, b:{}, s:{}", correlationData.toString(), b, s);
//    }

}
