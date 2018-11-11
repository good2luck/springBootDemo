package top.xudj.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xudj.demo.domain.entity.PersonEntity;
import top.xudj.demo.service.PersonService;

import java.util.List;


/**
 * @program: demo
 * @description: JPA2.0 Criteria API
 * @author: xudj
 * @create: 2018-11-10 11:26
 **/
@RestController
@Slf4j
@RequestMapping("/person")
public class PersonJpaController {

    @Autowired
    private PersonService personService;

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public PersonEntity loadById(@PathVariable int id) {
        return this.personService.loadById(id);
    }


    /**
     * 平均年龄
     *
     * @return
     */
    @GetMapping(value = "/avg/age")
    public double avgAge() {
        return this.personService.avgAge();
    }

    /**
     * object;array
     *
     * @return
     */
    @GetMapping(value = "/array")
    public List<Object[]> array() {
        return this.personService.array();
    }

}
