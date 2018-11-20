package top.xudj.demo.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: demo
 * @description: 订单项
 * @author: xudj
 * @create: 2018-11-14 09:38
 **/
@Entity
@Table(name = "order_item")
@Data
@DynamicInsert
@DynamicUpdate
@Builder
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "order_id")
    private Integer orderId;
}
