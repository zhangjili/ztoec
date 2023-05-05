package com.jili.ThreadTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future1完成！";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "future2完成！";
        });
//        allOf()：当所有给定的 CompletableFuture 完成时，返回一个新的 CompletableFuture
//        allOf 的返回值是 CompletableFuture<Void>类型，
//        这是因为 每个传入的 CompletableFuture 的返回值都可能不同，
//        所以组合的结果是 无法用某种类型来表示的，索性返回 Void 类型。
//        thenApply接收一个函数作为参数，使用该函数处理上一个CompletableFuture调用的结果，并返回一个具有处理结果的Future对象。
        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future1, future2);
        CompletableFuture<List<String>> listCompletableFuture =
                combindFuture.thenApply(e -> Arrays.asList(future1.join(), future2.join()));
        //或者
        CompletableFuture<List<String>> listCompletableFuture1 =
                combindFuture.thenApply(s ->{
                    List<String> strings = Arrays.asList(future1.join(), future2.join());
                    return strings;
                });
        //或者
        CompletableFuture<List<String>> objectCompletableFuture2 = combindFuture.thenApply(new Function<Void, List<String>>() {
            @Override
            public List<String> apply(Void unused) {
                return Arrays.asList(future1.join(), future2.join());
            }
        });


        try {
            System.out.println(listCompletableFuture.get());
            System.out.println(listCompletableFuture1.get());
            System.out.println(objectCompletableFuture2.get());
//            System.out.println(combindFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//双冒号运算操作符是类方法的句柄，lambda表达式的一种简写
/*        方法调用
        person -> person.getAge();
        可以替换成
        Person::getAge

        x -> System.out.println(x)
        可以替换成
        System.out::println
*/
    }
}
