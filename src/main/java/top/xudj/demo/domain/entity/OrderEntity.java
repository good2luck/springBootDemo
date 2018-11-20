package top.xudj.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @program: demo
 * @description: 订单
 * @author: xudj
 * @create: 2018-11-14 09:37
 **/
@Entity
@Table(name = "order_test")
@Data
@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /**
     * mappedBy值为属性名称，非数据库对应字端
     * 不加cascade：各种关系需要自己维护
     */
    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private List<OrderItem> items;

//    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
//    private List<OrderItem> items;

}
