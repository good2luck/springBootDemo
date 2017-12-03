package top.xudj.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.controller.UserMapper;
import top.xudj.demo.domain.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by xudj on 17/12/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MybatisTest {


    @Autowired
    private UserMapper userMapper;


    @Test
    @Transactional
    @Rollback
    public void testMapper() {
        String name = "xdj2";
        int i = userMapper.insert(name, 22);
        System.out.println("影响行数：" + i);

        List<UserEntity> list = userMapper.findByName(name);
        System.out.println(list);
    }

}
