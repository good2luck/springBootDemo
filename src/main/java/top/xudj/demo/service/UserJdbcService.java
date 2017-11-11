package top.xudj.demo.service;

/**
 * Created by xudj on 17/11/11.
 */
public interface UserJdbcService {


    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    int create(String name, Integer age);


    /**
     * 根据名称删除
     * @param name
     */
    int deleteByName(String name);


    /**
     * 获取用户总量
     * @return
     */
    Integer getAllUserCount();

}
