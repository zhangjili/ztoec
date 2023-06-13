package com.jili.futureTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class allOfTest {
    public static void main(String[] args) {

        ArrayList<CompletableFuture<String>> completableFutures1 = new ArrayList<CompletableFuture<String>>() {
            {
                add(CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "future1完成！";
                }));
                add(CompletableFuture.supplyAsync(() -> {
                    return "future2完成！";
                }));
            }
        };


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("future1完成！");
            return "future1完成！";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("future2完成！");
            return "future2完成！";
        });

        List<CompletableFuture<String>> completableFutures = Arrays.asList(future1, future2);
        //allOf方法用来实现多 CompletableFuture 的同时返回。
        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));

        try {
            //会阻塞在这里，等待所有的任务执行完毕
            combindFuture.get();
            Map<String, CompletableFuture<String>> collect = completableFutures.stream().collect(Collectors.toMap(CompletableFuture::join, e -> e));
            List<String> collect1 = completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
            System.out.println(collect);
            System.out.println(collect1);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
