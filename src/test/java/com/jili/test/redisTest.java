package com.jili.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class redisTest extends TestApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;


/*    @Test
    public void test1(){
        Object risk_level_100000000010938 =  redisTemplate.opsForValue().get("payCashier-40901662687831433002cardInfo");
        System.out.println(risk_level_100000000010938);

    }*/
}
