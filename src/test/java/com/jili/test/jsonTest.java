package com.jili.test;

import com.alibaba.fastjson.JSON;
import com.jili.entity.RatingAntimoneyLevel;

public class jsonTest {
    public static void main(String[] args) {
        RatingAntimoneyLevel r3 = new RatingAntimoneyLevel();
        r3.setMerchantNo("3");
        RatingAntimoneyLevel r1 = new RatingAntimoneyLevel();
        r1.setMerchantNo("1");
        RatingAntimoneyLevel r2 = new RatingAntimoneyLevel();
        r2.setMerchantNo("2");

        String s = JSON.toJSONString(r3);
        System.out.println(s);
        RatingAntimoneyLevel ratingAntimoneyLevel = JSON.parseObject(s, RatingAntimoneyLevel.class);
        System.out.println(ratingAntimoneyLevel);


    }
}
