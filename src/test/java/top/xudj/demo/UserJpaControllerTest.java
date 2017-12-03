package top.xudj.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.domain.entity.UserEntity;
import top.xudj.demo.domain.repository.UserRepository;

import java.util.List;

/**
 * Created by xudj on 17/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserJpaControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void test(){
        List<UserEntity> userEntitys = this.userRepository.findUser("xudj");
        System.out.println(userEntitys);
    }

    /**
     * 测试缓存
     */
    @Test
    public void testCache() {
        String name = "xudj";
        List<UserEntity> userList = this.userRepository.findByName(name);
        System.out.println(userList);
        userList.get(0).setAge((byte)1);
        this.userRepository.save(userList);
        // 由于ehcache是进程内缓存，更新操作查询出来的是缓存中的对象并进行了更新，所以第二次取时是被更新后的对象
        // 而redis是置于sping应用之外的，更新操作并不会改变redis的存储，因此第二次取得数据仍是redis中存储的未被更新的数据
        userList = this.userRepository.findByName(name);
        System.out.println(userList);
    }

}
