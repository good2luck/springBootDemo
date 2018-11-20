package top.xudj.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.xudj.demo.domain.entity.OrderItem;

/**
 * @author xudj
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
