package com.jili.futureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class thenApplyTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("第一次运算：" + result);
            return result;
            //将上一段任务的执行结果作为下一阶段任务的入参参与重新计算，产生新的结果。
            /*
        thenApply转换的是泛型中的类型，返回的是同一个CompletableFuture；
        thenCompose将内部的CompletableFuture调用展开来并使用上一个CompletableFutre调用的结果在下一步的CompletableFuture调用中进行运算，是生成一个新的CompletableFuture。
*/
        }).thenApply(number -> {
            int result = number * 3;
            System.out.println("第二次运算：" + result);
            return result;
        });
        System.out.println(future.get());
    }
}
