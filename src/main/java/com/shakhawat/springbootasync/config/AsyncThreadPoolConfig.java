package com.shakhawat.springbootasync.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncThreadPoolConfig {

    @Value("${com.shakhawat.async.corepoolsize:10}")
    private int corePoolSize;

    @Value("${com.shakhawat.async.maxpoolsize:20}")
    private int maxPoolSize;

    @Value("${com.shakhawat.async.queueCapacity:25}")
    private int queueCapacity;

    @Bean(name = "asyncTaskExecutor")
    public Executor asyncTaskExecutor(){
        log.info("Creating Async Task Executor for ServiceAPI to serve request in parallel threads");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize); // Minimum number of threads in the pool
        executor.setMaxPoolSize(maxPoolSize); // Maximum number of threads in the pool
        executor.setQueueCapacity(queueCapacity); //Keep this commented else throws error if queue is full.
        executor.setThreadNamePrefix("AsyncThread-TaskExecutor-");
        executor.initialize();
        return executor;
    }

}
