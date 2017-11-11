package top.xudj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xudj.demo.domain.entity.UserEntity;
import top.xudj.demo.service.UserJdbcService;
import top.xudj.demo.service.UserJpaService;

/**
 * Created by xudj on 17/11/11.
 */
@RestController
@RequestMapping("/jpa")
public class UserJpaController {
    /**
     * Spring-data-jpa依赖于Hibernate
     */


    @Autowired
    private UserJpaService userJpaService;


    /**
     * 创建
     * @return
     */
    @PostMapping(value = "/user")
    public String create(@RequestBody UserEntity userEntity) {
        return userJpaService.create(userEntity);
    }


    /**
     * 根据ID查询
     * @return
     */
    @GetMapping("/user/{id}")
    public UserEntity loadById(@PathVariable("id") String id){
        return userJpaService.loadById(id);
    }


    /**
     * 根据ID更新
     * @return
     */
    @PutMapping("/user/{id}")
    public UserEntity update(@PathVariable("id") String id, @RequestBody UserEntity userEntity){
        return userJpaService.update(id, userEntity);
    }

}
