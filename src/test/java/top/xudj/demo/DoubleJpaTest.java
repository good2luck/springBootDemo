package top.xudj.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.domain.entity.UserEntity;
import top.xudj.demo.domain.repository.UserRepository;
import top.xudj.demo.domain2.entity.MessageEntity;
import top.xudj.demo.domain2.repository.MessageRepository;

/**
 * Created by xudj on 17/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoubleJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;


    @Test
    public void test(){
        userRepository.save(new UserEntity());

        messageRepository.save(new MessageEntity());
    }



}
