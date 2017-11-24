package top.xudj.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xudj on 17/11/1.
 */
@Controller
public class IndexController {


    /**
     * 主页
     * @param map
     * @return
     */
    @GetMapping("/")
    public String index(ModelMap map) {
        // 加属性值
        map.addAttribute("host", "https://github.com/jueyanlove/springBootDemo");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    /**
     * hello.html
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 登录
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
