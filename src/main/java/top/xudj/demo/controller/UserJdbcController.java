package top.xudj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xudj.demo.service.UserJdbcService;

/**
 * Created by xudj on 17/11/11.
 */
@RestController
@RequestMapping("/jdbc")
public class UserJdbcController {


    @Autowired
    private UserJdbcService userJdbcService;


    /**
     * 创建
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/user")
    public int create(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return userJdbcService.create(name, age);
    }

    /**
     * 根据名称删除
     * @param name
     * @return
     */
    @DeleteMapping("/user")
    public int deleteByName(@RequestParam("name") String name){
        return userJdbcService.deleteByName(name);
    }

    /**
     * 查询个数
     * @return
     */
    @GetMapping(value = "/user")
    public Integer getAllUserCount(){
        return userJdbcService.getAllUserCount();
    }

}
