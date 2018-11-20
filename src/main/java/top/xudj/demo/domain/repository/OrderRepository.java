package top.xudj.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.xudj.demo.domain.entity.OrderEntity;

/**
 * @author xudj
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}
