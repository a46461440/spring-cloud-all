package com.zxc.common.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * {@link CompletableFuture}测试
 *
 * @author Zhou RunMing
 * @date 2019/1/14
 */
@Slf4j
public class CompletableFutureTest {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        this.log.info(String.valueOf(ForkJoinPool.getCommonPoolParallelism()));
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.log.info("inner thread");
            return "ok";
        }).thenApply(s -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.log.info("inner thread2");
            return s + ", you are good";
        });
        this.log.info("main thread");
        while (!completableFuture.isDone()) {
        }
        this.log.info("消耗时间" + (System.currentTimeMillis() - startTime));
    }

}
