package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.project.docOrder.service.EmrTaskInfoTaskService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequestMapping({"/emrTaskInfoTask"})
public class EmrTaskInfoTask {
   protected final Logger log = LoggerFactory.getLogger(EmrTaskInfoTask.class);
   @Autowired
   private EmrTaskInfoTaskService emrTaskInfoTaskService;

   @GetMapping({"/start"})
   public void transferEmrTaskInfo() {
      this.log.info("归档患者任务提醒转移定时任务开始执行", DateUtils.dateFormat(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));

      try {
         this.emrTaskInfoTaskService.transferEmrTaskInfo();
      } catch (Exception e) {
         this.log.error("患者执行单记录转移定时任务出现异常", e);
      }

      this.log.info("归档患者任务提醒转移定时任务结束执行", DateUtils.dateFormat(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));
   }
}
