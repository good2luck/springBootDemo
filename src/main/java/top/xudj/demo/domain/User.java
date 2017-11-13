package top.xudj.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xudj on 17/11/1.
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

}
