package top.xudj.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import top.xudj.demo.service.UserJdbcService;

/**
 * Created by xudj on 17/11/11.
 */
@Service
public class UserJdbcServiceImpl implements UserJdbcService {

    /* 自动配置spring的jdbc */
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    @Override
    public int create(String name, Integer age) {
        return jdbcTemplate.update("INSERT INTO USER(ID, NAME, AGE) VALUES(UUID(), ?, ?)", name, age);
    }

    @Override
    public int deleteByName(String name) {
        return jdbcTemplate.update("DELETE FROM USER WHERE NAME = ?", name);
    }

    @Override
    public Integer getAllUserCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM USER", Integer.class);
    }
}
