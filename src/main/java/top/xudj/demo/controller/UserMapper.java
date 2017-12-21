package top.xudj.demo.controller;

import org.apache.ibatis.annotations.*;
import top.xudj.demo.domain.UserOTD;
import top.xudj.demo.domain.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by xudj on 17/12/3.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE name = #{name}")
    List<UserEntity> findByName(@Param("name") String name);

    @Insert("INSERT INTO user(id, name, age) VALUES(UUID(), #{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    // map的参数只需与{}中同名即可
    @Insert("INSERT INTO user(id, name, age) VALUES(UUID(), #{name}, #{age})")
    int insertMap(Map<String, Object> map);

    // userEntity的属性需与{}中同名即可
    @Insert("INSERT INTO user(id, name, age) VALUES(UUID(), #{name}, #{age})")
    int insertUser(UserEntity userEntity);

    @Update("UPDATE user SET age = #{age} WHERE name = #{name}")
    int update(UserEntity userEntity);

    @Delete("delete from user WHERE name = #{name}")
    int delete(String name);

    @Results({
            @Result(property = "userName", column = "name"),
            @Result(property = "userAge", column = "age")
    })
    @Select("SELECT name, age FROM user WHERE name = #{name}")
    List<UserOTD> findByNameRes(@Param("name") String name);
}
