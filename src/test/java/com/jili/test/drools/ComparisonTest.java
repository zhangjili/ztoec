package com.jili.test.drools;

import com.jili.entity.ComparisonEntity;

import com.jili.test.TestApplicationTests;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
 
/**
 * @author sunzhiqiang23
 * @date 2021-06-17 23:46
 */
public class ComparisonTest extends TestApplicationTests {
 
    @Resource
    public KieBase kieBase;
 
    @Test
    public void testComparison(){
        KieSession kieSession = kieBase.newKieSession();
        ComparisonEntity comparisonEntity = new ComparisonEntity();
        comparisonEntity.setNames("张三");


        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");

        comparisonEntity.setList(list);

        kieSession.insert(comparisonEntity);
        //通过规则过滤器实现只执行指定规则  根据名字匹配
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_contains"));
//        kieSession.fireAllRules(2);  只执行两个策略
        //执行全部匹配到的策略
//        kieSession.fireAllRules();
        kieSession.dispose();
    }
}