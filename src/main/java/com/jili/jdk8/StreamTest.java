package com.jili.jdk8;

import com.jili.jdk8.entity.MemberRuleSetting;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
//        filter()和map()的区别：
//        filter是过滤操作，返回结果为true的数据；而map的作用是将流中的每一个元素T映射为R
        List<Integer> list1 = list.stream().filter(i -> i <= 5).collect(Collectors.toList());
        System.out.println(list1);
        List<Integer> list2 = list.stream().map(i -> i*i).collect(Collectors.toList());
        System.out.println(list2);

        MemberRuleSetting memberRuleSetting = new MemberRuleSetting();
        memberRuleSetting.setId(15);
        memberRuleSetting.setBatchNo("2023071017115356658");
        memberRuleSetting.setMemberNo("202022122000000346");
        memberRuleSetting.setRuleNo("RS77866544");
        memberRuleSetting.setMemberType(1);
        memberRuleSetting.setFlag(1);
        memberRuleSetting.setDel(0);


        MemberRuleSetting memberRuleSetting1 = new MemberRuleSetting();
        memberRuleSetting1.setId(15);
        memberRuleSetting1.setBatchNo("2023071017115356658");
        memberRuleSetting1.setMemberNo("202022122000000346");
        memberRuleSetting1.setRuleNo("RS77866545");
        memberRuleSetting1.setMemberType(1);
        memberRuleSetting1.setFlag(1);
        memberRuleSetting1.setDel(0);
        ArrayList<MemberRuleSetting> collect = new ArrayList<>();
        collect.add(memberRuleSetting);
        collect.add(memberRuleSetting1);

        Map<Integer, Map<String, String>> collect1 = collect.stream().collect(
                Collectors.groupingBy(MemberRuleSetting::getMemberType,
                        Collectors.groupingBy(MemberRuleSetting::getMemberNo,
                                Collectors.mapping(MemberRuleSetting::getRuleNo,
                                        Collectors.joining(";")))));

        Map<Integer, Map<String, String>> collect2 = collect.stream().collect(Collectors.groupingBy(MemberRuleSetting::getMemberType, Collectors.groupingBy(MemberRuleSetting::getMemberNo, Collectors.mapping(MemberRuleSetting::getRuleNo, Collectors.joining(";")))));
        collect2.forEach((k, v) -> v.forEach((k1, v2) -> {
            Set<String> set = new HashSet<>(Arrays.asList(v2.split(";")));
            System.out.println(k); //会员编号
            System.out.println(k1); //会员号
            System.out.println(set); //规则编号
        }));
    }
}
