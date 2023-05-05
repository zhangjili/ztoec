package com.jili.controller;


import com.jili.entity.RatingAntimoneyLevel;
import com.jili.service.RatingAntimoneyLevelService;
import com.jili.service.impl.RatingAntimoneyLevelServiceImpl;
import com.zrj.pay.data.api.DataQueryService;
import com.zrj.pay.data.bo.DataQueryBaseRequest;
import com.zrj.pay.data.bo.DataQueryBaseResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 反洗钱评级等级划分表 前端控制器
 * </p>
 *
 * @author zjl
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/rating-antimoney-level")
public class RatingAntimoneyLevelController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @DubboReference(interfaceClass =DataQueryService.class,version = "1.0.0")
    private DataQueryService dataQueryService;

    @Autowired
    private RatingAntimoneyLevelService ratingAntimoneyLevelService;

    @RequestMapping(value = "/abc", method = RequestMethod.GET)
    public String add(String merNo) {
//        String insert = ratingAntimoneyLevelService.insert(merNo);
        System.out.println(merNo);
        return "success";
    }

    @RequestMapping(value = "/aa", method = RequestMethod.GET)
    public String add(RatingAntimoneyLevel ratingAntimoneyLevel) {
        System.out.println(ratingAntimoneyLevel);
        return "success";
    }

    @RequestMapping(value = "/bb/{id}/{MerNo}", method = RequestMethod.GET)
    public String add(@PathVariable("id") String id__, @PathVariable("MerNo") String MN) {
        System.out.println(id__);
        System.out.println(MN);
        return "success";
    }

    @GetMapping("/bb")
    public void demo(@RequestParam(name = "window") String name) {
        System.out.println("name=" + name);
    }

    @GetMapping("/mq")
    public String mq() {
        String messageId = String.valueOf("123456789");
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：lieDeKey 发送到交换机 liuExchang
        rabbitTemplate.convertAndSend("liuExchang", "lieDeKey", map);
        return "ok";
    }

/*
    入参以JSON对象的方式传入：
    {
            "id":"343434",
            "projectType":"1"
    }
 */

    @PostMapping(path = "/demo1")
    public void demo1(@RequestBody RatingAntimoneyLevel ratingAntimoneyLevel) {
        System.out.println(ratingAntimoneyLevel.toString());
    }

//    或者
    @PostMapping(path = "/demo2")
    public void demo2(@RequestBody Map<String, String> ratingAntimoneyLevel) {
        System.out.println(ratingAntimoneyLevel.get("id"));
    }

    @GetMapping(path = "/dubboTest")
    public DataQueryBaseResponse pathTest(){
        DataQueryBaseRequest req = new DataQueryBaseRequest();
        req.setSqlId("findConHis");
        req.setSqlNamespace("risk.antimoney");
        req.setSqlSource("11");
        HashMap<String, Object> map = new HashMap<>();
        map.put("start_time","2023-03-17 00:00:00");
        map.put("pages","1");
        map.put("end_time","2023-03-18 00:00:00");
        map.put("currentPage","1");
        req.setSqlParam(map);
        req.setPageNo(1);
        req.setPageSize(33);
        return dataQueryService.queryData(req);
    }

}
