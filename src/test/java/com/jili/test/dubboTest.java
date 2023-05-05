package com.jili.test;

import com.bailianpay.rating.api.MemRatingService;
import com.bailianpay.rating.bo.MemberRatingRequest;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class dubboTest{
    @DubboReference(version = "3.0.0")
    private MemRatingService memRatingService;


    @Test
    public void testMemRating(){
        MemberRatingRequest memberRatingRequest = new MemberRatingRequest();
        memberRatingRequest.setMemberNo("202023042000001070");
        System.out.println(memRatingService.rating(memberRatingRequest));
    }

/*    public static void main(String[] args) {
       try {
           System.out.println(3/0);
       }catch (Exception e){
           throw new RuntimeException("除以0了");
       }
    }*/

}
