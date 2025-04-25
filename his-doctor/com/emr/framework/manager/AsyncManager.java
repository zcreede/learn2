package com.emr.framework.manager;

import com.emr.common.utils.Threads;
import com.emr.common.utils.spring.SpringUtils;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsyncManager {
   private final int OPERATE_DELAY_TIME = 10;
   private ScheduledExecutorService executor = (ScheduledExecutorService)SpringUtils.getBean("scheduledExecutorService");
   private static AsyncManager me = new AsyncManager();

   private AsyncManager() {
   }

   public static AsyncManager me() {
      return me;
   }

   public void execute(TimerTask task) {
      this.executor.schedule(task, 10L, TimeUnit.MILLISECONDS);
   }

   public void shutdown() {
      Threads.shutdownAndAwaitTermination(this.executor);
   }
}
