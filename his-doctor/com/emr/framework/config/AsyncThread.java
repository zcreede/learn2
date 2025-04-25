package com.emr.framework.config;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncThread {
   public static final int cpuNum = Runtime.getRuntime().availableProcessors();

   @Bean(
      name = {"poolTaskExecutor"}
   )
   public TaskExecutor workExecutor() {
      ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
      threadPoolTaskExecutor.setThreadNamePrefix("emr-java-async-");
      threadPoolTaskExecutor.setCorePoolSize(cpuNum);
      threadPoolTaskExecutor.setMaxPoolSize(cpuNum * 2);
      threadPoolTaskExecutor.setQueueCapacity(1000);
      threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
      threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
      threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
      threadPoolTaskExecutor.afterPropertiesSet();
      return threadPoolTaskExecutor;
   }
}
