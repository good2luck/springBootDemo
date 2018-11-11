package top.xudj.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @program: demo
 * @description: 动物类
 * @author: xudj
 * @create: 2018-11-10 11:08
 **/
@Entity
@Table(name = "person_test")
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert
@DynamicUpdate
public class PersonEntity implements Serializable {

    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;


    @Column(name = "create_time", insertable = false, updatable = false)
    private Timestamp createDate;

    @Column(name = "update_time", insertable = false, updatable = false)
    private Timestamp updateDate;

}
