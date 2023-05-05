package com.jili.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jili.entity.RatingAntimoneyLevel;
import com.jili.mapper.RatingAntimoneyLevelMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestApplicationTests {
    private static final Logger logger = LogManager.getLogger(TestApplicationTests.class);
    @Autowired
    private RatingAntimoneyLevelMapper ratingAntimoneyLevelMapper;


    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        RatingAntimoneyLevel ratingAntimoneyLevel = ratingAntimoneyLevelMapper.selectById(510);
        System.out.println(ratingAntimoneyLevel.toString());
    }

    @Test
    public void testWrapper(){
        QueryWrapper<RatingAntimoneyLevel> wrapper = new QueryWrapper<>();
        wrapper.eq("id","511");
        List<Map<String, Object>> maps = ratingAntimoneyLevelMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);

    }

    @Test
    public void testPageHelper(){
        QueryWrapper<RatingAntimoneyLevel> wrapper = new QueryWrapper<>();
        wrapper.ge("id",500);
        PageHelper.startPage(1,5);
        PageInfo<RatingAntimoneyLevel> ratingAntimoneyLevelPageInfo = new PageInfo<>(ratingAntimoneyLevelMapper.selectList(wrapper));
        List<RatingAntimoneyLevel> list = ratingAntimoneyLevelPageInfo.getList();
        ratingAntimoneyLevelPageInfo.getPages();
        ratingAntimoneyLevelPageInfo.getNextPage();
    }

    @Test
    public void testPageHelper01(){
        QueryWrapper<RatingAntimoneyLevel> wrapper = new QueryWrapper<>();
        wrapper.ge("id",500);
        PageInfo<RatingAntimoneyLevel> objectPageInfo = PageHelper.startPage(1, 20).doSelectPageInfo(() -> ratingAntimoneyLevelMapper.selectList(wrapper));
        objectPageInfo.getList();
        logger.info(objectPageInfo);

    }

    @Test
    public void testInsert(){
        RatingAntimoneyLevel ratingAntimoneyLevel = new RatingAntimoneyLevel();
        ratingAntimoneyLevel.setProjectType("1");
        ratingAntimoneyLevel.setCustomerType("0");
        ratingAntimoneyLevel.setApproveState("1");
        ratingAntimoneyLevel.setApproveIds("1,23,4");
        ratingAntimoneyLevel.setMerchantNo("123456789");
        int insert = ratingAntimoneyLevelMapper.insert(ratingAntimoneyLevel);
        logger.info(insert);

    }

}
