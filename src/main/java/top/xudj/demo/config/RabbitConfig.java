package top.xudj.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by xudj on 17/12/21.
 */
@Configuration
public class RabbitConfig {

    public final static String helloQueueName = "hello";
    public final static String userQueueName = "user";

    public final static String topicMessage = "topic.message";
    public final static String topicMessages = "topic.messages";

    public final static String fanoutA = "fanout.A";
    public final static String fanoutB = "fanout.B";
    public final static String fanoutC = "fanout.C";

    @Bean
    public Queue helloQueue() {
        return new Queue(helloQueueName);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(userQueueName);
    }


    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue(topicMessage);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(topicMessages);
    }
    //===============以上是验证topic Exchange的队列==========


    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue(fanoutA);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(fanoutB);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(fanoutC);
    }
    //===============以上是验证Fanout Exchange的队列==========


//    topic 是RabbitMQ中最灵活的一种方式，可以根据binding_key自由的绑定不同的队列
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

//    Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout转发器发送消息，绑定了这个转发器的所有队列都收到这个消息。
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }


    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     *
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     *
     * 说明：参数中注入的参数名为其它bean的名(默认方法名称)
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }


    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

}
