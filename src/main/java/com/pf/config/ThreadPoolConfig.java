package com.pf.config;

import cn.hutool.core.thread.RejectPolicy;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;
import com.pf.config.filter.UserMDCServletFilter;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author yk_xing
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(1, ThreadUtil.newNamedThreadFactory("system-scheduled-pool-executor", true));
    }

    @Bean
    @Primary
    public ThreadPoolExecutor poolExecutor() {
        ThreadFactory threadFactory = ThreadFactoryBuilder.create()
                .setNamePrefix("system-pool-executor")
                .setDaemon(true).build();
        int corePoolSize = 20;
        int maxPoolSize = 200;
        int keepAliveTime = 5;
        TimeUnit timeUnit = TimeUnit.MINUTES;
        BlockingQueue workQueue = new ArrayBlockingQueue<>(20);
        RejectedExecutionHandler handler = RejectPolicy.CALLER_RUNS.getValue();
        return new MDCThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue, threadFactory, handler);
    }

    private static class MDCThreadPoolExecutor extends ThreadPoolExecutor {

        public MDCThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, @NotNull TimeUnit unit, @NotNull BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MDCThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, @NotNull TimeUnit unit, @NotNull BlockingQueue<Runnable> workQueue, @NotNull ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MDCThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, @NotNull TimeUnit unit, @NotNull BlockingQueue<Runnable> workQueue, @NotNull RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public MDCThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, @NotNull TimeUnit unit, @NotNull BlockingQueue<Runnable> workQueue, @NotNull ThreadFactory threadFactory, @NotNull RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        public void execute(@NotNull Runnable command) {
            Map<String, String> content = MDC.getCopyOfContextMap();
            super.execute(() -> {
                try {
                    //日志上下文拷贝
                    if (content == null) {
                        Map<String, String> _content = new HashMap<>();
                        _content.put(UserMDCServletFilter.USER_ID, Thread.currentThread().getName());
                        _content.put(UserMDCServletFilter.REQUEST_ID,
                                Objects.toString(Thread.currentThread().getId()));
                        MDC.setContextMap(_content);
                    } else {
                        MDC.setContextMap(content);
                    }
                    command.run();
                } finally {
                    MDC.clear();
                }
            });
        }

        @NotNull
        @Override
        public Future<?> submit(@NotNull Runnable task) {
            Map<String, String> content = MDC.getCopyOfContextMap();
            return super.submit(() -> {
                //日志上下文拷贝
                MDC.setContextMap(content);
                task.run();
            });
        }
    }
}
