package com.jili.ThreadTest;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class supplyAsyncTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        runAsync 无返回值
//        public static CompletableFuture<Void> runAsync(Runnable runnable)
//        public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
//        public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
//        public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)

        CompletableFuture.runAsync(() -> System.out.println("无返回结果异步任务"));

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World";
        });
        //以上形式是下面形式的简写
        CompletableFuture<String>  completableFuture = CompletableFuture.supplyAsync(new Supplier() {
            @Override
            public Object get() {
                return "Hello World22";
            }
        });


//        join()和get()方法都是用来获取CompletableFuture异步之后的返回值。
//        join()方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出。
//        get()方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch）
        System.out.println(future.join());
        System.out.println(future.get());
        System.out.println(completableFuture.get());

    }
}
