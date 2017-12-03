package top.xudj.demo.controller;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.xudj.demo.domain.entity.UserEntity;

import java.util.List;

/**
 * Created by xudj on 17/12/3.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE name = #{name}")
    List<UserEntity> findByName(@Param("name") String name);

    @Insert("INSERT INTO user(id, name, age) VALUES(UUID(), #{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}
