package top.xudj.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.xudj.demo.domain.entity.PersonEntity;
import top.xudj.demo.domain.entity.Person_;
import top.xudj.demo.service.PersonService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @program: demo
 * @description: person业务
 * @author: xudj
 * @create: 2018-11-10 11:30
 **/
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * 一些典型的表达式是：
     * Root< T>，相当于一个 FROM 子句。
     * Predicate，其计算为布尔值 true 或 false（事实上，它被声明为 interface Predicate extends Expression< Boolean>）。
     * Path< T>，表示从 Root< ?> 表达式导航到的持久化属性。Root< T> 是一个没有父类的特殊 Path< T>。
     */
    @Override
    public PersonEntity loadById(int id) {
        // jpa
//        PersonEntity entity = entityManager.find(PersonEntity.class, id);
//        return entity;

        // JPQL
        String sql = "select id from person_test where age > 1";
        Query query = entityManager.createQuery(sql);
        List list = query.getResultList();
        System.out.println(list);

//        元模型
//        Metamodel metamodel = entityManager.getMetamodel();
//        EntityType<PersonEntity> pClass = metamodel.entity(PersonEntity.class);


        // jpa criteria api
        // 1，创建CriteriaQuery 的工厂
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        // 2，创建CriteriaQuery，是一个查询表达式节点树，其创建出的这些表达式节点可用于指定查询子句，
        // 比如 FROM、WHERE 和 ORDER BY
        CriteriaQuery createQuery = cb.createQuery(PersonEntity.class);
        // 3，在CriteriaQuery实例上设置查询表达式，查询表达式是在一个树中的节点
        // Root是一个查询表达式，表示持久化实例的范围，表示对类型为T的实例计算查询；这类似
        // 于JPQL与sql的from子句。
        Root root = createQuery.from(PersonEntity.class);
        // 4、Predicate是计算结果为true或false的常用表达式形式
        // 5、谓词由CriteriaBuilder构造
        // 谓词：是或不是等，如你是人，"是"则为谓词
        // root.get("age")是一个路径表达式，路径表达式是通过一个或多个持久化属性从根表达式进行导航得到的结果
        // root.get("age")表示Person的age属性从根表达式root导航
        Predicate predicate = cb.gt(root.get("age"), 1);

        createQuery.where(predicate);
        // 6，EntityManager 创建一个可执行查询；TypedQuery为Query的一个扩展
        TypedQuery tq = entityManager.createQuery(createQuery);
        // 7、携带类型返回结果
        List<PersonEntity> personList = tq.getResultList();
        return personList.get(0);
    }


    /**
     * 函数表达式
     * avg
     * 指定预测条件的方法
     * CriteriaQuery< T> select(Selection< ? extends T> selection);
     * CriteriaQuery< T> multiselect(Selection< ?>... selections);
     */
    @Override
    public double avgAge() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> query = criteriaBuilder.createQuery(Double.class);
        Root<PersonEntity> root = query.from(PersonEntity.class);
        query.select(criteriaBuilder.avg(root.get("age")));
        Double count = entityManager.createQuery(query).getSingleResult();
        log.info("avg:{}", count);
        return count;
    }

    /**
     * 谓词
     * where
     */
    public void where() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery(PersonEntity.class);
        Root<PersonEntity> root = query.from(PersonEntity.class);
        query.where(criteriaBuilder.and(
                criteriaBuilder.greaterThan(root.get("age"), 1),
                criteriaBuilder.lessThan(root.get("age"), 20)));
        List<PersonEntity> personEntityList = entityManager.createQuery(query).getResultList();
        log.info("personEntityList:{}", personEntityList);
    }

    /**
     * 生产查询结果的方法
     * < Y> CompoundSelection< Y> construct(Class< Y> result, Selection< ?>... terms);
     *     CompoundSelection< Object[]> array(Selection< ?>... terms);
     *     CompoundSelection< Tuple> tuple(Selection< ?>... terms);
     * @return
     */
    @Override
    public List<Object[]> array() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(PersonEntity.class);
        Root<PersonEntity> root = query.from(PersonEntity.class);
        // 可以用query.multiselect()包装成POJO对象
        query.select(cb.array(root.get(Person_.name), root.get(Person_.age)));
        List<Object[]> list = entityManager.createQuery(query).getResultList();
        log.info("list:{}", list);
        return list;
    }

}
