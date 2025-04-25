package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PatientAddSyncTask {
   protected final Logger log = LoggerFactory.getLogger(PatientAddSyncTask.class);

   public void addSyncData() {
      Date currentDate = new Date();
      String begTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, currentDate) + " 00:00:00";
      Date nextDate = DateUtils.addDays(currentDate, 1);
      String endTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, nextDate) + " 00:00:00";
      new SqlVo((String)null, begTimeStr, endTimeStr);
   }
}
