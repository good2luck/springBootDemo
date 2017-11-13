package top.xudj.demo.config;

import com.sun.xml.internal.ws.encoding.soap.DeserializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.xudj.demo.domain.User;

/**
 * Created by xudj on 17/11/13.
 */
@Configuration
public class RedisConfig {

    /**
     * 如果想要redis存储对象，需要，springboot并不支持直接使用
     * 需要自己实现RedisSerializer<T> 接口来对传入对象进行序列化和反序列化
     */

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate () {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return  template;
    }


}

class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();
    static final byte[] EMPTY_ARRAY = new byte[0];

    /**
     * 序列化
     * @param o
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null){
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(o);
        } catch (Exception e) {
            throw new SerializationException("cannot serialize", e);
        }
    }


    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)){
            return null;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception e) {
            throw new DeserializationException("cannot deserialize", e);
        }
    }


    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }

}
