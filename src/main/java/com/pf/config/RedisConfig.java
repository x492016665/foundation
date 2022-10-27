package com.pf.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author yk_xing
 */
@Configuration
public class RedisConfig {

    @Bean("redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //Jackson ObjectMapper 中的 enableDefaultTyping 方法由于安全原因，
        // 从 2.10.0 开始标记为过期，建议用 activateDefaultTyping 方法代替
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key 采用的String 的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //value 序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hashde key 也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //hash 的 value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean("reactiveRedisTemplate")
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        RedisSerializationContext<Object, Object> serializationContext = RedisSerializationContext
                .newSerializationContext()
                .key(fastJsonRedisSerializer)
                .value(fastJsonRedisSerializer)
                .hashKey(fastJsonRedisSerializer)
                .hashValue(fastJsonRedisSerializer).build();
        return new ReactiveRedisTemplate(reactiveRedisConnectionFactory, serializationContext);
    }

    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> defaultRedisSerializer() {
        return new FastJsonRedisSerializer(Object.class);
    }

    /*@Bean(name = "reactiveRedisTemplate")
    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        RedisSerializationContext.SerializationPair<String> keySerializationPair =
                RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer);
        RedisSerializationContext.SerializationPair<Object> valueSerializationPair =
                RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);
        RedisSerializationContext.SerializationPair<Object> hashValueSerializationPair =
                RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);

        RedisSerializationContext<String, Object> context = new RedisSerializationContext<String, Object>() {
            @Override
            public SerializationPair getKeySerializationPair() {
                return keySerializationPair;
            }

            @Override
            public SerializationPair getValueSerializationPair() {
                return valueSerializationPair;
            }

            @Override
            public SerializationPair getHashKeySerializationPair() {
                return keySerializationPair;
            }

            @Override
            public SerializationPair getHashValueSerializationPair() {
                return hashValueSerializationPair;
            }

            @Override
            public SerializationPair<String> getStringSerializationPair() {
                return keySerializationPair;
            }
        };
        return new ReactiveRedisTemplate<String, Object>(connectionFactory, context);
    }*/
}
