package top.xudj.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);

		SpringApplication springApplication = new SpringApplication();
		//禁止命令行设置参数(命令行加属性不会报错，只会失效)
//		springApplication.setAddCommandLineProperties(false);
		springApplication.run(DemoApplication.class, args);
	}
}
