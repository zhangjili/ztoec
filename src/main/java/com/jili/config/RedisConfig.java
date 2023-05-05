package com.jili.config;

import com.qianbao.redis.service.RedisClient;
import com.zrj.pay.core.cache.RedisServiceImpl;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    @DubboReference(version = "1.0.0")
    private RedisClient redisClient;

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
//            throws UnknownHostException {
//        // 创建模板
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        // 设置连接工厂
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        // 设置序列化工具
//        GenericJackson2JsonRedisSerializer jsonRedisSerializer =
//                new GenericJackson2JsonRedisSerializer();
//        // key和 hashKey采用 string序列化
//        redisTemplate.setKeySerializer(RedisSerializer.string());
//        redisTemplate.setHashKeySerializer(RedisSerializer.string());
//        // value和 hashValue采用 JSON序列化
//        redisTemplate.setValueSerializer(jsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
//        return redisTemplate;
//    }

//    @Bean
//    public RedisComponent redisComponent(){
//        RedisComponent redisComponent = new RedisComponent();
//        RedisServiceImpl redisService = new RedisServiceImpl();
//        redisService.setRedisClient(redisClient);
//        redisService.setNamespace("pay_risk-3620");
//        redisComponent.setRedisService(redisService);
//        redisComponent.setExpire("21600");
//        return redisComponent;
//
//    }

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //String的序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        //key序列化方式采用String类型
        template.setKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson类型
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hash的key序列化方式也是采用String类型
        template.setHashKeySerializer(stringRedisSerializer);
        //hash的value也是采用jackson类型
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
}


}
