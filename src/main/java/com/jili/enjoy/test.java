package com.jili.enjoy;

import com.jfinal.kit.Kv;
import com.jfinal.template.Engine;

import java.util.*;

public class test {
    public static void main(String[] args) {
        Engine engine = Engine.use();
        //设置才classpath下加载模版
        engine.setToClassPathSourceFactory();
        engine.setDevMode(true);

        List<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
// list={"item1", "item2", "item3"}

        Student student = new Student();
        student.setAge(11);
        student.setName("zhangsan");

        Map map = new LinkedHashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
// map={"key1": "value1", "key2": "value2", "key3": "value3"}

        Kv kv = Kv.by("map", map);
        kv.set("list", list);
        kv.set("student",student);

        StringBuilder stringBuilder = engine.getTemplate("templates/template.txt").renderToStringBuilder(kv);
        System.out.println(stringBuilder);
    }
}
