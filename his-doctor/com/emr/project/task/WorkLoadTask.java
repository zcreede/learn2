package com.emr.project.task;

import com.emr.project.system.service.IWorkLoadMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkLoadTask {
   private final Logger log = LoggerFactory.getLogger(WorkLoadTask.class);
   @Autowired
   private IWorkLoadMainService workLoadMainService;

   public void workLoad() {
      this.log.debug("=============工作量上报自动确认开始执行========");
      this.workLoadMainService.updateStatusByTask();
   }
}
