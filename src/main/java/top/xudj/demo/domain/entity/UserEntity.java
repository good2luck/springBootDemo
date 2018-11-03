package top.xudj.demo.domain.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author xudj
 * @date 17/11/11
 * null 不参与sql
 */
@Entity
@Table(name = "USER_TEST")
@Data
@DynamicInsert
@DynamicUpdate
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid2")
    private String id;

    private String name;

    private String sex;

    private Byte age;

    /**
     * 时间不参与sql
     */
    @Column(insertable = false, updatable = false)
    private Timestamp createDate;

    @Column(insertable = false, updatable = false)
    private Timestamp updateDate;


}
