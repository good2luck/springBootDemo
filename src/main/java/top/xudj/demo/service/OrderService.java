package top.xudj.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xudj.demo.domain.entity.OrderEntity;
import top.xudj.demo.domain.repository.OrderRepository;
import top.xudj.demo.util.UpdateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * @program: demo
 * @description: 订单业务
 * @author: xudj
 * @create: 2018-11-14 09:45
 **/
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public OrderEntity findById(Integer id) {
        Optional<OrderEntity> orderEntityOptional = this.orderRepository.findById(id);
        return orderEntityOptional.orElse(null);
    }


    /**
     * 测试级联
     * CascadeType.Merge：新增
     * @param orderParam
     */
    public OrderEntity testCascadeTypeMerge(OrderEntity orderParam) {
        Optional<OrderEntity> orderEntityOptional = this.orderRepository
                .findById(orderParam.getId());
        if (!orderEntityOptional.isPresent()) {
            log.warn("未找到");
            return null;
        }
        OrderEntity orderEntity = orderEntityOptional.get();
        UpdateUtils.copyNonNullProperties(orderParam, orderEntity);
        OrderEntity orderEntityRes = this.orderRepository.save(orderEntity);
        log.info("orderResult:{}", orderEntityRes);
        return orderEntityRes;
    }

    /**
     * 测试级联
     * CascadeType.Persist：新增
     * @param orderParam
     */
    public OrderEntity testCascadeTypePersist(OrderEntity orderParam) {
        OrderEntity orderEntity = new OrderEntity();
        UpdateUtils.copyNonNullProperties(orderParam, orderEntity);
        OrderEntity orderEntityRes = this.orderRepository.save(orderEntity);
        log.info("orderResult:{}", orderEntityRes);
        return orderEntityRes;
    }
}
