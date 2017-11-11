package top.xudj.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xudj.demo.domain.entity.UserEntity;
import top.xudj.demo.domain.repository.UserRepository;
import top.xudj.demo.service.UserJpaService;

/**
 * Created by xudj on 17/11/11.
 */
@Service
@Slf4j
public class UserJpaServiceImpl implements UserJpaService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public String create(UserEntity userEntity) {
        UserEntity user = this.userRepository.save(userEntity);
        log.info("user info:{}", user);
        return user.getId();
    }

    @Override
    public UserEntity loadById(String id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public UserEntity update(String id, UserEntity userEntity) {
        UserEntity user = this.userRepository.findOne(id);
        user.setAge(userEntity.getAge());
        user.setName(userEntity.getName());
        user.setSex(userEntity.getSex());
        log.info("user:{}", user);
        // Integer特别，字段为null，仍参与执行sql
        // 如若没有数据更新，则不执行sql
        return this.userRepository.save(user);
    }
}
