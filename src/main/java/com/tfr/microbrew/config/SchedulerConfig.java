package com.tfr.microbrew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Config for Scheduler thread pool
 *
 * Created by Erik on 4/3/2017.
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public Executor schedulerExecutor() {
        return Executors.newScheduledThreadPool(4);
    }

}
