package top.xudj.demo.service;

import top.xudj.demo.domain.entity.UserEntity;

import java.util.List;

/**
 *
 * @author xudj
 * @date 17/11/11
 */
public interface UserJpaService {
    String create(UserEntity userEntity);

    UserEntity loadById(String id);

    UserEntity update(String id, UserEntity userEntity);

    List<String> delete(List<String> ids);
}
