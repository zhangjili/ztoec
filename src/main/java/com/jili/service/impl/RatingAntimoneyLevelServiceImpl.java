package com.jili.service.impl;

import com.jili.annotation.abc;
import com.jili.entity.RatingAntimoneyLevel;
import com.jili.mapper.RatingAntimoneyLevelMapper;
import com.jili.service.RatingAntimoneyLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jili.util.DateUtil;
import com.zrj.pay.data.api.DataQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 反洗钱评级等级划分表 服务实现类
 * </p>
 *
 * @author zjl
 * @since 2023-03-08
 */
@Slf4j
@Service
@abc("rate")
@RabbitListener(queues = "liu")//监听的队列名称
public class RatingAntimoneyLevelServiceImpl extends ServiceImpl<RatingAntimoneyLevelMapper, RatingAntimoneyLevel> implements RatingAntimoneyLevelService {

    @Autowired
    private RatingAntimoneyLevelMapper ratingAntimoneyLevelMapper;

    public String insert(String merNo){
        RatingAntimoneyLevel ratingAntimoneyLevel = new RatingAntimoneyLevel();
        ratingAntimoneyLevel.setMerchantNo(merNo);
        ratingAntimoneyLevel.setProjectType("1");
        ratingAntimoneyLevel.setCustomerType("0");
        ratingAntimoneyLevel.setApproveState("1");
        return  ratingAntimoneyLevelMapper.insert(ratingAntimoneyLevel)+"";
    }

    //rabbit 消费者
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

//定时任务
//    @Scheduled(cron = "*/2 * * * * ?")
    public void pri(){
        log.info(DateUtil.getDatetime(new Date()));
    }


//项目启动时调用
//    @PostConstruct
//    public void qi(){
//        log.info("abcdef$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//    }

//    @PostConstruct
    public void liu(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Collectors.groupingBy根据一个或多个属性对集合中的项目进行分组
        RatingAntimoneyLevel r3 = new RatingAntimoneyLevel();
        r3.setMerchantNo("3");
        RatingAntimoneyLevel r1 = new RatingAntimoneyLevel();
        r1.setMerchantNo("1");
        RatingAntimoneyLevel r2 = new RatingAntimoneyLevel();
        r2.setMerchantNo("2");
        List<RatingAntimoneyLevel> ratingAntimoneyLevels = Arrays.asList(r3, r1, r2);
        log.info(String.valueOf(ratingAntimoneyLevels));
        Map<String, List<RatingAntimoneyLevel>> collect = ratingAntimoneyLevels.stream().collect(Collectors.groupingBy(RatingAntimoneyLevel::getMerchantNo));
        log.info(String.valueOf(collect));
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Collectors.groupingBy根据一个或多个属性对集合中的项目进行分组
        RatingAntimoneyLevel r3 = new RatingAntimoneyLevel();
        r3.setMerchantNo("3");
        RatingAntimoneyLevel r1 = new RatingAntimoneyLevel();
        r1.setMerchantNo("1");
        RatingAntimoneyLevel r2 = new RatingAntimoneyLevel();
        r2.setMerchantNo("2");
        List<RatingAntimoneyLevel> ratingAntimoneyLevels = Arrays.asList(r3, r1, r2);
        log.info(String.valueOf(ratingAntimoneyLevels));
        Map<String, List<RatingAntimoneyLevel>> collect = ratingAntimoneyLevels.stream().collect(Collectors.groupingBy(RatingAntimoneyLevel::getMerchantNo));
        log.info(String.valueOf(collect));
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

}
