package com.emr.project.task;

import com.emr.project.emr.service.IEditStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditStateCloseTask {
   protected final Logger log = LoggerFactory.getLogger(EmrArchiveTask.class);
   @Autowired
   private IEditStateService editStateService;

   public void closeEdit(Integer mine) {
      this.log.debug("更新病历编辑记录更新时间任务开始");

      try {
         mine = mine == null ? 30 : mine;
         this.editStateService.updateCloseEdit(mine);
      } catch (Exception e) {
         this.log.error("更新病历编辑记录更新时间出现异常，", e);
      }

   }
}
