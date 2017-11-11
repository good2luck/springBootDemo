package top.xudj.demo.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.xudj.demo.domain.User;

import javax.management.Query;
import java.util.*;

/**
 * Created by xudj on 17/11/1.
 */
@RestController
// 通过这里配置使下面的映射都在/users下
@RequestMapping(value = "/users")
@Slf4j
public class UserController {

    // 创建线程安全的Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());


    @GetMapping(value = "")
    @ApiOperation(value = "获取用户列表", notes = "查询所有")
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    /**
     * @param user
     * @return
     * @ApiOperation：备注方法名及注意说明
     * @ApiImplicitParam：描述请求参数及类型，dataType须和实体名对应, 默认body类型
     */
    @PostMapping(value="")
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    public String postUser(@RequestBody User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        log.info("Get Info postUser with parameters: user : {}", user);
        users.put(user.getId(), user);
        return "success";
    }



    @GetMapping(value="/{id}")
    @ApiOperation(value = "获取单个用户", notes = "根据url中的ID获取")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    public User getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        log.info("Get Info getUser with parameters: id : {}", id);
        return users.get(id);
    }


    @PutMapping(value="/{id}")
    @ApiOperation(value = "更新用户信息", notes = "根据uel中的id查询用户，并更新成传入的User信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        log.info("Get Info putUser with parameters: id : {}, user : {}", id, user);
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }


    @DeleteMapping(value="/{id}")
    @ApiOperation(value = "删除用户", notes = "根据url中的Id删除指定用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        log.info("Get Info deleteUser with parameters: id : {}", id);
        users.remove(id);
        return "success";
    }

}
