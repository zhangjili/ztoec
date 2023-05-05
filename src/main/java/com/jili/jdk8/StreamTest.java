package com.jili.jdk8;

import java.util.ArrayList;
import java.util.List;
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
    }
}
