package top.xudj.demo.domain2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.xudj.demo.domain2.entity.MessageEntity;

/**
 * Created by xudj on 17/11/11.
 */
public interface MessageRepository extends JpaRepository<MessageEntity, String> {

}
