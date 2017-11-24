package top.xudj.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.xudj.demo.domain.User;
import top.xudj.demo.domain.entity.UserEntity;
import top.xudj.demo.domain.repository.UserRepository;


/**
 * Created by xudj on 17/11/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTransactionTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
//  @Rollback // 回滚
    public void test() {
        // 创建2条纪录
        UserEntity userEntity = new UserEntity();
        userEntity.setName("xu");
        userEntity.setSex("n");
        userEntity.setAge(new Byte("20"));

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setName("xu2");
//        sex数据长度为1
        userEntity2.setSex("n");
        userEntity2.setAge(new Byte("22"));

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setName("xu3");
        userEntity3.setSex("n");
        userEntity3.setAge(new Byte("23"));

        userRepository.save(userEntity);
        userRepository.save(userEntity2);
        userRepository.save(userEntity3);
    }


}
