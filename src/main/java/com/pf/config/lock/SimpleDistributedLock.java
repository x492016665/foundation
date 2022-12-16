package com.pf.config.lock;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.locks.RedisTemplateSimpleDistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

/**
 * @author yk_xing
 */
@Slf4j
@Component
public class SimpleDistributedLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Lock getLock(@NonNull String key) {
        return new RedisTemplateDistributedLock(stringRedisTemplate, key, 60 * 1000);
    }

    private static class RedisTemplateDistributedLock extends RedisTemplateSimpleDistributedLock {

        /**
         * 尝试3次
         */
        private int RETRY_COUNT = 3;

        public RedisTemplateDistributedLock(@NonNull StringRedisTemplate redisTemplate, int leaseMilliseconds) {
            super(redisTemplate, leaseMilliseconds);
        }

        public RedisTemplateDistributedLock(@NonNull StringRedisTemplate redisTemplate, String key, int leaseMilliseconds) {
            super(redisTemplate, key, leaseMilliseconds);
        }

        @Override
        public void lock() {
            int retryCount = 1;
            while (!tryLock()) {
                if (retryCount >= RETRY_COUNT) {
                    log.info("第{}次尝试获取锁失败,放弃尝试", retryCount);
                    throw new DistributedLockFailException();
                }
                try {
                    log.info("第{}次尝试获取锁失败", retryCount++);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Ignore
                }
            }
        }
    }
}
