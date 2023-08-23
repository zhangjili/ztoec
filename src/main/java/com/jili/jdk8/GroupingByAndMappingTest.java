package com.jili.jdk8;

import com.jili.jdk8.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByAndMappingTest {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Ram", 30));
        list.add(new Person("Shyam", 20));
        list.add(new Person("Shiv", 20));
        list.add(new Person("Mahesh", 30));

        Map<Integer, List<Person>> collect = list.stream().collect(Collectors.groupingBy(Person::getAge));
        collect.forEach((k,v) -> System.out.println(k +"person"+v.toString()));

        Map<Integer, String> nameByAgeMap = list.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.joining(",", "[", "]"))));
        nameByAgeMap.forEach((k, v) -> System.out.println("Age:" + k + "  Persons: " + v));
    }
/*
运行结果:
	Age:20  Persons: [Shyam,Shiv]
	Age:30  Persons: [Ram,Mahesh]
封装为Map后返回
*/
    }
