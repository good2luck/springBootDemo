package top.xudj.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.component.RabbitMqSender;

/**
 * Created by xudj on 17/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private RabbitMqSender sender;

    @Test
    public void testMq() {
        for (int i = 0; i < 10; i++) {
            sender.sender();
        }
    }


}
