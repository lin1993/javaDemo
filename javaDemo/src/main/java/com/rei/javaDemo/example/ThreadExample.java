package com.rei.javaDemo.example;

import cn.hutool.core.thread.ThreadUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程相关测试
 */
@Service
public class ThreadExample {
    private static final int THREADS_COUNT = 200;
    // 循环总次数
    private static int size = 100000;
    // 普通int
    public static int intNum = 0;
    // volatile int加锁
    public static volatile int countVolatile = 0;
    // AtomicInteger
    public static AtomicInteger atomicNum= new AtomicInteger(0);

    public static CountDownLatch countDownLatch = new CountDownLatch(200);
    /**
     * 多线程下线程安全测试
     */
    public void MultiThread(){
        try {
            one();
        }catch (Exception e){

        }

    }

    public void increase() {
        intNum++;
        countVolatile++;
        atomicNum.incrementAndGet();
    }
    private void one() throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i = 0; i< threads.length; i++) {
            threads[i] = new Thread(() -> {
                for(int i1 = 0; i1 < 1000; i1++) {
                    increase();
                }
                countDownLatch.countDown();
            });
            threads[i].start();
        }

        countDownLatch.await();

        System.out.println("普通int="+intNum);
        System.out.println("volatile int="+countVolatile);
        System.out.println("AtomicInteger int="+atomicNum);
        intNum = 0;
        countVolatile=0;
        atomicNum=new AtomicInteger(0);
    }

}
