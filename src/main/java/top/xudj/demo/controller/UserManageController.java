package top.xudj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;
import top.xudj.demo.service.UserManageService;

/**
 * @program: demo
 * @description: 测试hibernate实体状态管理
 * @author: xudj
 * @create: 2018-11-13 22:27
 **/
@RestController
@RequestMapping("/user/manage")
public class UserManageController {

    @Autowired
    private UserManageService userManageService;

    @GetMapping("/{id}")
    public String test(@PathVariable int id) {
        if (id == 1) {
            userManageService.persist();
        } else if (id == 2){
            userManageService.persistHi();
        } else if (id == 3) {
            userManageService.persistHiException();
        } else if(id == 4) {
            userManageService.save();
        } else if(id == 5) {
            userManageService.merge();
        } else if (id == 6){
            userManageService.update();
        } else if(id == 7){
            userManageService.updateException();
        } else if(id == 8) {
            userManageService.saveOrUpdate();
        }
        return "success";
    }

}
