package com.jili.entity;

import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author sunzhiqiang23
 * @date 2021-06-16 21:11
 */
@Accessors(chain = true)
public class ComparisonEntity {
 
    /**
     *名字集合
     */
    private String names;
 
    /**
     * 字符串集合
     */
    private List<String> list;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}