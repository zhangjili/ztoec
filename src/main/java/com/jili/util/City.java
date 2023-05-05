package com.jili.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City {
    private static List<Resident> residentLists;

    //第一题：创建100000个居民对象
    public City(int numResidents) {
        residentLists = new ArrayList<>(numResidents);
        Random random = new Random();
        for (int i = 0; i < numResidents; i++) {
            String name = "居民" + i;
            double x = random.nextDouble() * 1000;
            double y = random.nextDouble() * 1000;
            residentLists.add(new Resident(name, x, y));
        }
    }

    //第二题：通过居民名字获取位置信息
    public Resident getResidentByName(String name) {
        for (Resident resident : residentLists) {
            if (resident.getName().equals(name)) {
                return resident;
            }
        }
        return null;
    }

    //开始移动，计算位置
    public void start() {
        for (Resident resident : residentLists) {
            resident.setRandomDirection();
        }
    }

    //模拟移动，
    public void step() {
        for (Resident resident : residentLists) {
            resident.move();
        }
    }

    //得到这个居民附近的其他居民
    public List<Resident> getNearbyResidents(Resident resident) {
        List<Resident> nearbyResidents = new ArrayList<>();
        for (Resident other : residentLists) {
            if (!other.equals(resident) && resident.isNearby(other)) {
                nearbyResidents.add(other);
            }
        }
        return nearbyResidents;
    }

    public static void main(String[] args) {
        //第一题：
        City city = new City(100000);

        //第二题
        Resident resident = city.getResidentByName("居民999");
        System.out.println(resident.getX() + ", " + resident.getY());
        city.start();
        //10s
        for (int i = 0; i < 10; i++) {
            city.step();
        }

        //第三题
        List<Resident> nearbyResidents = city.getNearbyResidents(resident);
        System.out.println("居民999附近的居民:");
        for (Resident nearbyResident : nearbyResidents) {
            System.out.println(nearbyResident.getName() + ": " + nearbyResident.getX() + ", " + nearbyResident.getY());
        }

        //第四题

    }
}
