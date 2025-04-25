package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.project.borrowing.service.IEmrIndexBorrowService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmrIndexBorrowTask {
   protected final Logger log = LoggerFactory.getLogger(EmrIndexBorrowTask.class);
   @Autowired
   private IEmrIndexBorrowService emrIndexBorrowService;

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void patientArchive() {
      this.log.debug("=============病例借阅自动归还开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));

      try {
         this.emrIndexBorrowService.autoReturn();
      } catch (Exception e) {
         this.log.error("病例借阅自动归出现异常", e);
      }

   }
}
