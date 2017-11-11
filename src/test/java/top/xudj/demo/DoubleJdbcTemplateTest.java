package top.xudj.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xudj on 17/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoubleJdbcTemplateTest {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;


    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;


    @Before
    public void setUp(){
        primaryJdbcTemplate.update("DELETE FROM USER");
        secondaryJdbcTemplate.update("DELETE FROM MESSAGE");
    }


    /**
     * test
     */
    @Test
    public void test() {
        // 第一个数据源插入一条记录
        primaryJdbcTemplate.update("INSERT INTO USER (ID, NAME, AGE) VALUES(UUID(), ?, ?)", "xudj", 20);

        // 第二个数据源插入一条记录
        secondaryJdbcTemplate.update("INSERT INTO MESSAGE(id, content) VALUES(UUID(), ?)", "haha");

        // check
        Assert.assertEquals("1", primaryJdbcTemplate.queryForObject("SELECT COUNT(1) FROM USER", String.class));
        Assert.assertEquals("1", secondaryJdbcTemplate.queryForObject("SELECT COUNT(1) FROM MESSAGE", String.class));

    }


}
