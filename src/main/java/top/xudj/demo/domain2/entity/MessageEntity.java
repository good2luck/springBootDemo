package top.xudj.demo.domain2.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by xudj on 17/11/11.
 */
@Entity
@Table(name = "message")
@DynamicUpdate
@DynamicInsert
@Data
public class MessageEntity implements Serializable{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String content;


    @Column(insertable = false, updatable = false)
    private Timestamp createDate;


    @Column(insertable = false, updatable = false)
    private Timestamp updateDate;


}
