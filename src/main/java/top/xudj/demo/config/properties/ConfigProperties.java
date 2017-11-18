package top.xudj.demo.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by xudj on 17/11/18.
 */
@Data
@Component
public class ConfigProperties {

    @Value("${top.xudj.name}")
    private String name;

    @Value("${top.xudj.title}")
    private String title;

    @Value("${top.xudj.desc}")
    private String desc;

    @Value("${top.xudj.value}")
    private String value;

    @Value("${top.xudj.uuid}")
    private String uuid;

    @Value("${top.xudj.number}")
    private int number;

    @Value("${top.xudj.bignumber}")
    private long bignumber;

    @Value("${top.xudj.test1}")
    private String test1;

    @Value("${top.xudj.test2}")
    private String test2;
}
