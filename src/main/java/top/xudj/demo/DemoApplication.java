package top.xudj.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import top.xudj.demo.config.StateMachingConfig;
import top.xudj.demo.domain.Events;
import top.xudj.demo.domain.States;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private StateMachine<States, Events> stateMachine;

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("启动加载数据...");
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.RECEIVE);
	}
}
