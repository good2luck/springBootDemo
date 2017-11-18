package top.xudj.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.config.properties.ConfigProperties;

/**
 * Created by xudj on 17/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConfigPropertiesTest {

    @Autowired
    private ConfigProperties properties;


    @Test
    public void testProp() {
        Assert.assertEquals("xudj", properties.getName());
        Assert.assertEquals("springBootDemo", properties.getTitle());
        Assert.assertEquals("xudj write springBootDemo", properties.getDesc());
    }

    @Test
    public void testRandom() {
        System.out.println(properties.toString());
    }

}
