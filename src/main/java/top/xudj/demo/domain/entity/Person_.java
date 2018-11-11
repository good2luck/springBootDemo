package top.xudj.demo.domain.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @program: demo
 * @description: person类的规范元模型
 * @author: xudj
 * @create: 2018-11-10 15:25
 **/
@StaticMetamodel(PersonEntity.class)
public class Person_ {

    public static volatile SingularAttribute<PersonEntity, Integer> id;
    public static volatile SingularAttribute<PersonEntity, String> name;
    public static volatile SingularAttribute<PersonEntity, Integer> age;

}
