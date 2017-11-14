package top.xudj.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.domain.User;
import top.xudj.demo.domain.UserMongoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xudj on 17/11/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MongoTest {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Before
    public void setUp() {
        userMongoRepository.deleteAll();
    }

    @Test
    public void test() {
        // 创建三个user,并存入mongo
        User user1 = new User(1L, "xudj1", 21);
        User user2 = new User(2L, "xudj2", 22);
        User user3 = new User(3L, "xudj3", 23);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        userMongoRepository.save(users);
        Assert.assertEquals(3, userMongoRepository.findAll().size());

        // 删除一个, 验证数量
        userMongoRepository.delete(1L);
        Assert.assertEquals(2, userMongoRepository.findAll().size());


        User user = userMongoRepository.findOne(2L);
        System.out.println(user);
    }

}
