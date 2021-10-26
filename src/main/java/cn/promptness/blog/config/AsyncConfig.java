package cn.promptness.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : Lynn
 * @date : 2019-05-23 21:33
 */
@Configuration
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AsyncConfig {

    /**
     * 线程池维护线程的核心线程数数量.
     * Set the ThreadPoolExecutor's core pool size.
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 线程池维护线程的预警阀值数量
     * Set the ThreadPoolExecutor's maximum pool size.
     */
    private int maxPoolSize = Runtime.getRuntime().availableProcessors() * 4;

    /**
     * 持有等待执行的任务队列.
     * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     */
    private int queueCapacity = Runtime.getRuntime().availableProcessors() * 10;

    @Bean
    @Lazy
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        String threadNamePrefix = "blog-executor-";
        executor.setThreadNamePrefix(threadNamePrefix);

        //空闲线程的存活时间.
        executor.setKeepAliveSeconds(15);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //如果不初始化，导致找到不到执行器
        executor.initialize();
        return executor;
    }


}
