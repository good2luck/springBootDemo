package top.xudj.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;
import top.xudj.demo.domain.Events;
import top.xudj.demo.domain.States;

import java.util.EnumSet;

/**
 * Created by xudj on 17/12/23.
 */
@Configuration
// 启用Spring StateMachine状态机功能
@EnableStateMachine
@Slf4j
public class StateMachingConfig extends EnumStateMachineConfigurerAdapter<States, Events> {


    /**
     * 用来初始化当前状态机拥有哪些状态及初始状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.UNPAID)
                .states(EnumSet.allOf(States.class));
    }

    /**
     * 用来初始化当前状态机有哪些状态迁移动作，其中命名中我们很容易理解每一个迁移动作，
     * 都有来源状态source，目标状态target以及触发事件event
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(States.UNPAID)
                    .target(States.WAITING_FOR_RECEIVE)
                    .event(Events.PAY)  // 指定触发事件
                    .and()
                .withExternal()
                    .source(States.WAITING_FOR_RECEIVE)
                    .target(States.DONE)
                    .event(Events.RECEIVE); // 指定触发事件
    }


    /**
     * 为当前的状态机指定了状态监听器，其中listener()则是调用了下一个内容创建的监听器实例，用来处理各个发生的状态迁移事件
     *
     * 通过EventConfig注解代理listener()监听，实现相同效果
     * @param config
     * @throws Exception
     */
//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
//        config.withConfiguration()
//                .listener(listener());
//    }


    /**
     * 用来定义各个发生的状态迁移事件
     * @return
     */
    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void transition(Transition<States, Events> transition) {
                if(transition.getTarget().getId() == States.UNPAID) {
                    log.debug("订单创建，待支付");
                    return;
                }

                if(transition.getSource().getId() == States.UNPAID
                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
                    log.debug("支付完成，待收货");
                    return;
                }

                if(transition.getSource().getId() == States.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == States.DONE) {
                    log.debug("已收货，订单完成");
                    return;
                }
            }
        };
    }



}
