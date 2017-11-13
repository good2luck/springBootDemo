package top.xudj.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.domain.User;

import java.util.UUID;

/**
 * Created by xudj on 17/11/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void test () {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("name", "xudj");

        Assert.assertEquals("xudj", stringRedisTemplate.opsForValue().get("name"));

    }


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisObject() {
        //保存对象
        User user = new User();
        user.setId(123L);
        user.setName("xudj");
        user.setAge(20);
        redisTemplate.opsForValue().set("user", user);

        Assert.assertEquals("xudj", ((User)redisTemplate.opsForValue().get("user")).getName());

    }


}
