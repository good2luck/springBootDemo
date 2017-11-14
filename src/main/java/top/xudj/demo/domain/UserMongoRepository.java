package top.xudj.demo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by xudj on 17/11/13.
 */
public interface UserMongoRepository extends MongoRepository<User, Long> {

    User findByName(String name);

}
