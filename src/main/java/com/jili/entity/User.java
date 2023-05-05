package com.jili.entity;

public class User {
    private Integer level;
    private String name;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "level=" + level +
                ", name='" + name + '\'' +
                '}';
    }
}
