package top.xudj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xudj.demo.domain.entity.OrderEntity;
import top.xudj.demo.service.OrderService;

/**
 * @program: demo
 * @description: 订单测试
 * @author: xudj
 * @create: 2018-11-14 09:43
 **/
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public OrderEntity findById(@PathVariable Integer id) {
        return this.orderService.findById(id);
    }


    /**
     * 测试jpa级联关系CascadeType
     * merge
     */
    @PostMapping("/cascadeType/merge")
    public OrderEntity testCascadeTypeMerge(@RequestBody OrderEntity orderParam) {
        return orderService.testCascadeTypeMerge(orderParam);
    }


    /**
     * 测试jpa级联关系CascadeType
     * persist
     */
    @PostMapping("/cascadeType/persist")
    public OrderEntity testCascadeTypePersist(@RequestBody OrderEntity orderParam) {
        return orderService.testCascadeTypePersist(orderParam);
    }

}
