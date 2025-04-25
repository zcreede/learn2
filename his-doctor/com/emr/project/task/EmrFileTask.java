package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.project.esSearch.service.IEmrFileService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class EmrFileTask {
   protected final Logger log = LoggerFactory.getLogger(EmrFileTask.class);
   @Autowired
   private IEmrFileService emrFileService;

   @Scheduled(
      cron = "0 15 10 ? * MON"
   )
   public void syncEmrFileData() {
      this.log.debug("========增量向es同步病历文件开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();
      String begTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, date) + " 00:00:00";
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(5, 1);
      String endTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, date) + " 00:00:00";

      try {
         this.emrFileService.syncEmrFileAddToEs(begTimeStr, endTimeStr);
         this.log.debug("========增量向es同步病历文件结束执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      } catch (Exception e) {
         this.log.error("增量向es同步病历文件失败", e);
      }

   }
}
