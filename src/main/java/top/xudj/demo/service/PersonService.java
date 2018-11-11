package top.xudj.demo.service;

import top.xudj.demo.domain.entity.PersonEntity;

import java.util.List;

/**
 * @author xudj
 */
public interface PersonService {

    PersonEntity loadById(int id);

    double avgAge();

    List<Object[]> array();
}
