package com.jili.suanfa;

import java.util.Random;

public class Resident {

    private String name;    //居民的姓名
    private double x;    //居民所处位置的x坐标
    private double y;    //居民所处位置的y坐标
    private double directionX;    //居民每秒沿x轴移动的距离
    private double directionY;    //居民每秒沿y轴移动的距离

    public Resident(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
        setRandomDirection();
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setRandomDirection() {
        Random random = new Random();
        double angle = random.nextDouble() * 2 * Math.PI;
        directionX = Math.cos(angle);
        directionY = Math.sin(angle);
    }

    public void move() {
        x += 0.1 * directionX;
        y += 0.1 * directionY;
        //生成负数或者越界需要重新模拟移动
        if (x < 0 || x > 1000 || y < 0 || y > 1000) {
            setRandomDirection();
        }
    }

    //计算位置是否小于10
    public boolean isNearby(Resident other) {
        double distance = Math.sqrt((x - other.getX()) * (x - other.getX()) + (y - other.getY()) * (y - other.getY()));
        return distance <= 10;
    }
}
