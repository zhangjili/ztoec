package com.jili.ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return "返回 hello Callable";
            }
        });
        futureTask.run();
//        new Thread(futureTask).start();

        FutureTask abc = new FutureTask(new Runnable() {
            @Override
            public void run() {
            }
        },"返回默认值");
        abc.run();

        System.out.println(futureTask.get());
        System.out.println(abc.get());
    }
}
