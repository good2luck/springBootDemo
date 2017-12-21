package top.xudj.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.controller.UserMapper;
import top.xudj.demo.domain.UserOTD;
import top.xudj.demo.domain.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xudj on 17/12/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class MybatisTest {


    @Autowired
    private UserMapper userMapper;


    @Test
    @Rollback
    public void testMapper() {
        String name = "xdj2";
        int i = userMapper.insert(name, 22);
        System.out.println("影响行数：" + i);

        List<UserEntity> list = userMapper.findByName(name);
        System.out.println(list);
    }


    @Test
    @Rollback(false)
    public void testMapperExtMap() {
        System.out.println("Get Into testMapperExtMap.");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "xudj");
        map.put("age", 18);
        int i = userMapper.insertMap(map);
        Assert.assertEquals(i, 1);
    }

    @Test
    @Rollback
    public void testMapperExtUser() {
        System.out.println("Get Into testMapperExtUser.");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("xudj");
        userEntity.setAge((byte)19);
        int i = userMapper.insertUser(userEntity);
        Assert.assertEquals(i, 1);
    }

    @Test
    @Rollback
    public void testMapperUpdate() {
        System.out.println("Get Into testMapperUpdate.");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("xudj");
        userEntity.setAge((byte)20);
        int i = userMapper.insertUser(userEntity);
        Assert.assertEquals(i, 1);

        // update
        userEntity.setAge((byte)21);
        i = userMapper.update(userEntity);
        Assert.assertEquals(i, 1);

        // delete
        i = userMapper.delete("xudj");
        Assert.assertEquals(i, 1);
    }


    // @Results注解测试
    @Test
    public void testResult(){
        List<UserOTD> users = userMapper.findByNameRes("xudj");
        System.out.println(users);
    }


}
