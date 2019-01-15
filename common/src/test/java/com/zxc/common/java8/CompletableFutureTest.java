package com.zxc.common.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.*;

/**
 * {@link CompletableFuture}测试
 *
 * @author Zhou RunMing
 * @date 2019/1/14
 */
@Slf4j
public class CompletableFutureTest {

    /**
     * {@link CompletableFuture}在执行的时候默认是使用{@link ForkJoinPool}里的线程(5个),
     *
     * @throws ExecutionException
     * @throws InterruptedException
     * @see CompletableFuture#supplyAsync(Supplier) 这是带返回值的异步执行
     * @see CompletableFuture#runAsync(Runnable) 这是不带返回值的异步执行
     * @see CompletableFuture#thenApply(Function) 这是带返回值的消耗继续执行
     * @see CompletableFuture#thenAccept(Consumer) 这是不带返回值的消耗继续执行
     * 以上方法都是基本的异步执行
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        this.log.info(String.valueOf(ForkJoinPool.getCommonPoolParallelism()));
        long startTime = System.currentTimeMillis();
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.log.info("inner thread");
            return "1";
        }).thenApply(s -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.log.info("inner thread2");
            return s + ", you are good";
        }).thenAccept(s -> {
            Optional<String> optional = Optional.of(s);
            if (optional.get().contains("you are good"))
                this.log.info("you are a good person");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.log.info(s.toString());
        });
        this.log.info("main thread");
        while (!completableFuture.isDone()) {
        }
        this.log.info("消耗时间" + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenRun(() -> this.log.info("ok"));
        while (!voidCompletableFuture.isDone()) {
        }
        this.log.info("消耗时间" + (System.currentTimeMillis() - startTime));
    }

    /**
     * @throws ExecutionException
     * @throws InterruptedException
     * @see CompletableFuture#thenCompose(Function) 可以改变结果,返回同类型的{@link CompletableFuture}
     * @see CompletableFuture#thenApply(Function) 与它作用相同,但是不能改变类型
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = createCF(2);
        CompletableFuture<String> composeCompletableFuture = completableFuture.thenCompose(s -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return CompletableFuture.supplyAsync(() -> String.format("after change %s", s));
        });
        completableFuture.thenApply(s -> s + "after");
        this.log.info(completableFuture.get());
//        this.log.info(composeCompletableFuture.get());
        Thread.sleep(3000);
    }

    /**
     * @see CompletableFuture#thenCombine(CompletionStage, BiFunction)
     * 在这里解释一下{@link BiFunction}类比于{@link Function}它只接受一个参数并且
     * 输出一个结果,{@link BiFunction}能接受两个参数并且输出一个结果
     * 该方法将两个{@link CompletableFuture}结果连接起来
     */
    @Test
    public void testCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = createCF(2);
        CompletableFuture<String> appendCompletableFuture = createCF(1);
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> result = completableFuture.thenCombine(appendCompletableFuture, (s1, s2) -> {
            this.log.info("inner thread");
            return s1 + s2;
        });
        this.log.info(result.get() + " and it cost:" + (System.currentTimeMillis() - startTime));
    }

    /**
     * @see CompletableFuture#thenAcceptBoth(CompletionStage, BiConsumer)
     * 该方法将两个{@link CompletableFuture}结果连接起来 但是不输出结果,不同于{@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     * 见{@link #testCombine()}
     */
    @Test
    public void testAcceptBoth() {
        CompletableFuture<String> completableFuture = createCF(1);
        CompletableFuture<String> appendCompletableFuture = createCF(2);
        CompletableFuture<Void> result = completableFuture.thenAcceptBoth(appendCompletableFuture, (s1, s2) -> {
            this.log.info(s1 + s2);
        });
        while (!result.isDone()) ;
        this.log.info("the operation id done");
    }

    /**
     * 全部完成才行
     */
    @Test
    public void testAllOf() {
        CompletableFuture<String> completableFuture1 = createCF(1);
        CompletableFuture<String> completableFuture2 = createCF(2);
        CompletableFuture<String> completableFuture3 = createCF(3);
        CompletableFuture<Void> result = CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3);
        while (!result.isDone()) ;
        this.log.info("the operation id done");
    }

    /**
     * 完成一个即可,其他的任务还是会执行完成
     */
    @Test
    public void testAnyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = createCF(1);
        CompletableFuture<String> completableFuture2 = createCF(2);
        CompletableFuture<String> completableFuture3 = createCF(3);
        CompletableFuture<Object> result = CompletableFuture.anyOf(completableFuture1, completableFuture2, completableFuture3);
        while (!result.isDone()) ;
        this.log.info("the operation id done:" + result.get().toString());
        Thread.sleep(4000);
    }

    /**
     * 见字识意
     */
    @Test
    public void testAcceptEither() {
        CompletableFuture<String> completableFuture = createCF(1);
        CompletableFuture<String> appendCompletableFuture = createCF(2);
        CompletableFuture<Void> result = completableFuture.acceptEither(appendCompletableFuture, s -> {
            this.log.info(s);
        });
        while (!result.isDone()) ;
        this.log.info("the operation id done");
    }

    @Test
    public void testExceptionHandler() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() -> {
//                    int a = 1 / 0;
                    return "ok";
                }).exceptionally(ex -> {
                    this.log.info("error:" + ex.getMessage());
                    return "error";
                });
        this.log.info(completableFuture.get());
        CompletableFuture<String> completableFuture2 =
                CompletableFuture.supplyAsync(() -> {
                    int a = 1 / 0;
                    return "ok";
                }).handle((s, ex) -> {
                    this.log.info(s);
                    if (Optional.ofNullable(ex).isPresent())
                        this.log.info("error:" + ex.getMessage());
                    return Optional.ofNullable(ex).isPresent() ? "error" : s;
                });
        this.log.info(completableFuture2.get());
    }


    private static ArrayList<String> numbers = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "4", "5"));

    /**
     * 辅助方法
     *
     * @param index
     * @return
     */
    private static CompletableFuture<String> createCF(int index) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    log.info("inside future: waiting for detecting...");
                    for (int i = 1; i <= index; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("running inside Future... " + i + " sec");
                    }
                    log.info("inside future: done...");

                    return numbers.get(index);
                } catch (Throwable e) {
                    return "not detected";
                }
            }
        });
    }

}
