package com.emr.project.task;

import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.service.IPatEventService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatEventTask {
   protected final Logger log = LoggerFactory.getLogger(PatEventTask.class);
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private RedisCache redisCache;

   public void insetPatEvent() {
      String patEventTime = (String)this.redisCache.getCacheObject("pat_event_time");
      String beginDate = null;
      String endDate = null;
      Date date = new Date();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String keyValue = df.format(date);
      if (StringUtils.isNotEmpty(patEventTime)) {
         beginDate = patEventTime;
         endDate = keyValue;
      }

      try {
         this.log.debug("------------------->生成病历事件开始<--------------");
         List<PatEvent> allList = new ArrayList();
         List<PatEvent> testList = this.patEventService.selectTestAlertList(beginDate, endDate);
         if (!testList.isEmpty()) {
            allList.addAll(testList);
         }

         List<PatEvent> examList = this.patEventService.selectExamAlertList(beginDate, endDate);
         if (!examList.isEmpty()) {
            allList.addAll(examList);
         }

         List<PatEvent> orderList = this.patEventService.selectOrderList(beginDate, endDate);
         if (!orderList.isEmpty()) {
            allList.addAll(orderList);
         }

         if (!allList.isEmpty()) {
            this.patEventService.insertList(allList);
         }

         this.redisCache.setCacheObject("pat_event_time", keyValue);
      } catch (Exception e) {
         this.log.error("定时任务生成事件出现异常！", e);
      }

   }
}
