package com.zxc.common.config;

import com.zxc.common.CommonApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Component
@Slf4j
public class RedissonConfigTest extends CommonApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testRedissonClient() throws InterruptedException {
        RLock lock = this.redissonClient.getLock("lock");
        boolean getLock = lock.tryLock(10, 20, TimeUnit.SECONDS); //第一个time是等待获取锁时间,第二个是锁住的时间
        this.log.info(String.valueOf(getLock));
        new Thread(() -> {
            RLock tLock = redissonClient.getLock("lock");
            try {
                boolean getTLock = tLock.tryLock(10, 3, TimeUnit.SECONDS);
                log.info(String.valueOf(getTLock));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                tLock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        lock.unlock();
    }


}