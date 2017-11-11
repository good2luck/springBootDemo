package top.xudj.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.xudj.demo.domain.entity.UserEntity;

import java.util.List;

/**
 * Created by xudj on 17/11/11.
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("from UserEntity u where u.name=:name")
    List<UserEntity> findUser(@Param("name") String name);
}
