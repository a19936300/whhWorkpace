package com.binbinxiu.whh.whhbase.study;

import lombok.Singular;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池饱和策略
 */
public class PoolPolicyTest {

    private static ThreadPoolExecutor executor =
            //核心线程数、最大线程数
            new ThreadPoolExecutor(2,3,
                    //线程空闲后存活时间
                    60L, TimeUnit.SECONDS,
                    //有界的阻塞队列
                    new LinkedBlockingDeque<>(5));


    class Task implements Runnable{

        private String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println("线程【"+Thread.currentThread().getName()+"】正在执行【"+taskName+"】任务");
            try {
                Thread.sleep(1000L * 5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程【"+Thread.currentThread().getName()+"】已执行完【"+taskName+"】任务！！！     ");
        }
    }



    @AfterEach
    public void afterEach(){
        try {
            Thread.sleep(1000L * 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 任务1 和 任务2 提交核心线程执行
     * 任务3，4,5,6,7 提交到有界队列
     * 任务8，新建任务执行
     * 任务9 执行饱和策略
     */
    @Test
    public void abortPolicyTest(){
        //设置饱和策略为：终止策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i <= 10; i++) {
            try{
                Thread.sleep(100L);
                executor.execute(new Task("线程任务"+i));
            }catch (Exception e){
                System.out.println(e);
            }
        }

        executor.shutdown();
    }

    /**
     * 任务1 和 任务2 提交核心线程执行
     * 任务3，4,5,6,7 提交到有界队列
     * 任务8，新建任务执行
     * 任务9 10 抛弃
     */
    @Test
    public void discardPolicyTest(){
        //设置饱和策略为：抛弃策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 1; i <= 10; i++) {
            try{
                Thread.sleep(100L);
                executor.execute(new Task("线程任务"+i));
            }catch (Exception e){
                System.out.println(e);
            }
        }

        executor.shutdown();
    }

    /**
     * 任务1 和 任务2 提交核心线程执行
     * 任务3，4,5,6,7 提交到有界队列
     * 任务8，新建任务执行
     * 任务抛弃3，4
     */
    @Test
    public void discardOldestPolicyTest(){
        //设置饱和策略为：抛弃策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 1; i <= 10; i++) {
            try{
                Thread.sleep(100L);
                executor.execute(new Task("线程任务"+i));
            }catch (Exception e){
                System.out.println(e);
            }
        }

        executor.shutdown();
    }

    /**
     * 任务1 和 任务2 提交核心线程执行
     * 任务3，4,5,6,7 提交到有界队列
     * 任务8，新建任务执行
     * 主线程执行9,10
     */
    @Test
    public void callerRunsPolicyTest(){
        //设置饱和策略为：调用者策略
         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= 10; i++) {
            try{
                Thread.sleep(100L);
                executor.execute(new Task("线程任务"+i));
            }catch (Exception e){
                System.out.println(e);
            }
        }

        executor.shutdown();
    }

}
