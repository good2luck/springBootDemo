package top.xudj.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.xudj.demo.domain.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @program: demo
 * @description: 用户状态管理业务测试
 * 主要测试hibernate：save、persist、update、merge和saveOrUpdate
 * @author: xudj
 * @create: 2018-11-13 22:08
 **/
@Service
@Slf4j
public class UserManageService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 在Hibernate中，持久化上下文所代表的org.hibernate.Session实例
     * 对于JPA，它是javax.persistence.EntityManager
     */
    /**
     * 应用中任何实体实例的三种状态：
     * 瞬态(transient) -这种情况下现在没有、过去也没有连接到一个会话上 ; 此实例在数据库中没有相应的行; 它通常只是一个你创建的新对象保存到数据库;
     * 持久态(persistent) -此实例具有独特的关联会话对象; 在flush会话到数据库中，这个实体是保证在数据库中有一个相应的记录一致;
     * 脱离态(detached) -这种情况下一度被连接到一个会话（在一个持续状态），但现在它不是; 如果从上下文中驱逐它，清除或关闭会话，或者通过序列化/反序列化过程放置实例，实例将进入此状态。
     */
    /**
     * http://www.baeldung.com/wp-content/uploads/2016/07/2016-07-11_13-38-11-1024x551.png
     * 当实体实例处于持续状态，您对这个实例的映射字段的所有更改将在flush被应用到相应的数据库记录和字段会议。
     * 在持续的实例可以被认为是“在线”，而分离的情况下已经“下线”，而不是监测变化。
     *
     * 这意味着，当你改变一个领域持续对象，你不必调用保存，更新或任何这些方法让这些对数据库的更改：
     * 你需要的是提交事务，或刷新或关闭会话，当你完成它
     */

    /**
     * 总结：
     * 如果你没有任何特别的要求，作为一个经验法则，你应该坚持使用persist和merge方法，
     * 因为它们是标准化，保证符合JPA规范。
     *
     * 对于你决定要切换到另一种持久性方案提供商的情况，他们也是很方便的。
     * 但他们可能有时没有“原始”Hibernate方法看上去这么有用：save，update和saveOrUpdate。
     */


    /**
     * persist()
     * 用于添加一个新的实体实例的持久化上下文，即过度一个瞬态到持久态
     */
    @Transactional(rollbackFor = Exception.class)
    public void persist() {
        UserEntity userEntity = UserEntity.builder()
                .name("persist")
                .build();
        // 执行后实体便将从瞬态转移到持久态
        entityManager.persist(userEntity);
        // INSERT语句的产生，仅在commiting事务，flush或关闭会话会发生
        log.info("persist:{}", userEntity);
    }

    public void persistHi() {
        UserEntity userEntity = UserEntity.builder()
                .name("persistHi")
                .build();
        Session session = (Session) entityManager.getDelegate();
        session.beginTransaction();
        session.persist(userEntity);
        // flush会产生insert语句
        session.flush();
        // 不管有没有flush(),最后都会有一次update age，因为persist()后，对应已经进入持久态；相当于从数据库查询出来
        userEntity.setAge((byte) 25);
        log.info("persistHi:{}", userEntity);
        session.getTransaction().commit();
    }

    @Transactional(rollbackFor = Exception.class)
    public void persistHiException() {
        UserEntity userEntity = UserEntity.builder()
                .name("persistHiException")
                .build();
        Session session = (Session) entityManager.getDelegate();
        session.persist(userEntity);
        session.evict(userEntity);
        // persist一个脱离态的实例，会抛异常
        // "exception": "org.hibernate.PersistentObjectException: detached entity passed to persist: ..."
        session.persist(userEntity);
        log.info("persistHiException:{}", userEntity);
    }


    /**
     * save()
     * "原始"的hibernate方法，不符合jpa规范
     */
    public void save() {
        UserEntity userEntity = UserEntity.builder()
                .name("save")
                .build();
        Session session = (Session) entityManager.getDelegate();
        session.beginTransaction();
        session.save(userEntity);
        // 打印出id1
        log.info("save:{}", userEntity);

        // 与persist不同的是：save的调用会给脱离态的实例创建一个新的持久化实例，
        // 并赋予它一个新的标识符，在提交或flush时会导致重复的记录在数据库中
//        session.evict(userEntity);
//        userEntity.setAge((byte) 24);
//        session.save(userEntity);

        session.getTransaction().commit();
    }


    /**
     * merge()
     * 主要意图是使用一个脱离态实体实例的字段值，去更新一个持久态的实体实例
     * merge的方法正是这么做的：
     * 1,通过id从所传递的对象中获取实体实例（从持久上下文中检索现有的实体实例，或者从数据库中加载新实例）;
     * 2,将字段从传递的对象复制到此实例;
     * 3,返回新更新的实例。
     */
    public void merge() {
        UserEntity userEntity = UserEntity.builder()
                .name("merge")
                .build();
        Session session = (Session) entityManager.getDelegate();
        session.beginTransaction();
        session.save(userEntity);
        // 需要flush执行insert语句
        session.flush();
        // 打印出id1
        log.info("first save:{}", userEntity);
        // 持久态到游离态
        session.evict(userEntity);

        // 不set age 下面merge不会进行更新操作，如果调用update则会
        userEntity.setAge((byte) 26);
        // 会根据id做一次select操作；提交事物后做一次更新操作
        UserEntity userEntity1 = (UserEntity) session.merge(userEntity);
        // 此时，userEntity1是持久态对象，userEntity是游离态对象
        log.info("merge:{}", userEntity1);

        session.getTransaction().commit();
    }


    /**
     * update()
     * 就像persist和save一样，update方法是一种“原始的”hibernate方法，是merge加入之前使用很久的方法
     * 语义：
     * 它作用于传递的对象（它的返回类型是void）; 它把传递的对象从脱离态转换成持久态;
     * 如果你传递一个此方法一个瞬态实体，会抛出一个异常
     */
    public void update() {
        UserEntity userEntity = UserEntity.builder()
                .name("update")
                .build();
        Session session = (Session) entityManager.getDelegate();
        session.beginTransaction();
        session.save(userEntity);
        // 需要flush执行insert语句
        session.flush();
        // 打印出id1
        log.info("first save:{}", userEntity);
        // 持久态到游离态
        session.evict(userEntity);

        userEntity.setAge((byte) 26);
        // 不会执行一次查询
        session.update(userEntity);

        session.getTransaction().commit();
    }

    /**
     * 试图调用更新一个短暂的实例将导致异常
     */
    public void updateException() {
        UserEntity userEntity = UserEntity.builder()
                .name("update")
                .build();
        Session session = (Session) entityManager.getDelegate();
        session.beginTransaction();
        // 更新瞬态对象会直接报错
        // "exception": "The given object has a null identifier: top.xudj.demo.domain.entity.UserEntity",
        session.update(userEntity);
        session.getTransaction().commit();
    }


    /**
     * saveOrUpdate()
     * 仅出现在Hibernate API中，没有标准化的对应方法。类似于update，它也可以用于重新连接实例
     * 与update的区别在于：是，当它应用到一个瞬态实例时，不抛出异常; 相反，它使这个瞬态持续
     * 你可能会认为这种方法是可以使对象持久化的通用工具，无论其状态是瞬态的或脱落态
     */
    public void saveOrUpdate(){
        UserEntity userEntity = UserEntity.builder()
                .name("saveOrUpdate")
                .build();
        Session session = (Session) entityManager.getDelegate();
        session.beginTransaction();
        // save
        session.saveOrUpdate(userEntity);

        userEntity.setAge((byte) 12);
        // update
        session.saveOrUpdate(userEntity);

        session.getTransaction().commit();
    }
}
