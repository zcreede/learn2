package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.project.sys.domain.JobRecord;
import com.emr.project.sys.service.IJobRecordService;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HisInfoSyncAddTask {
   protected final Logger log = LoggerFactory.getLogger(HisInfoSyncAddTask.class);
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private IJobRecordService jobRecordService;

   public void addSyncDataOneHour(String syncCode) {
      if (StringUtils.isNotBlank(syncCode)) {
         Date currentDate = new Date();
         Date begTime = DateUtils.addHours(currentDate, -1);
         String begTimeStr = DateUtils.parseDateToStr("yyyy-MM-dd HH", begTime) + ":00:00";
         String endTimeStr = DateUtils.parseDateToStr("yyyy-MM-dd HH", currentDate) + ":00:00";
         new SqlVo((String)null, begTimeStr, endTimeStr);
         JobRecord addJobParam = null;

         try {
            this.syncDatasourceService.syncAddDataBySqlVoAndCode(syncCode);
         } catch (Exception e) {
            this.log.error("同步 syncCode: {}, begTimeStr: {}, endTimeStr: {}", new Object[]{syncCode, begTimeStr, endTimeStr});
            this.log.error("同步 syncCode: {} 出现异常：{}", syncCode, e);
            addJobParam = new JobRecord();
            addJobParam.setJobCode(syncCode);
            addJobParam.setJobStatus(0);
            addJobParam.setParamsJson("hisInfoSyncAddTask.addSyncDataOneHour('" + syncCode + "','" + begTimeStr + "','" + endTimeStr + "')");
            addJobParam.setJobType("sync");
            addJobParam.setJobBeanName("hisInfoSyncAddTask");
            addJobParam.setJobBeanMethod("addSyncDataOneHour");
         } finally {
            if (addJobParam != null) {
               this.jobRecordService.insertJobRecord(addJobParam);
            }

         }
      } else {
         this.log.error("同步His信息，syncCode不能为空, 当前时间：{}", DateUtils.getTime());
      }

   }

   public void addSyncDataOneHour(String syncCode, String begTimeStr, String endTimeStr) {
      if (StringUtils.isNotBlank(syncCode)) {
         new SqlVo((String)null, begTimeStr, endTimeStr);
         JobRecord addJobParam = null;

         try {
            this.syncDatasourceService.syncAddDataBySqlVoAndCode(syncCode);
         } catch (Exception e) {
            this.log.error("同步 syncCode: {}, begTimeStr: {}, endTimeStr: {}", new Object[]{syncCode, begTimeStr, endTimeStr});
            this.log.error("同步 syncCode: {} 出现异常：{}", syncCode, e);
         } finally {
            if (addJobParam != null) {
               this.jobRecordService.insertJobRecord(addJobParam);
            }

         }
      } else {
         this.log.error("同步His信息，syncCode不能为空, 当前时间：{}", DateUtils.getTime());
      }

   }

   public void syncData(String syncCode) {
      if (StringUtils.isNotBlank(syncCode)) {
         String endTimeStr = DateUtils.parseDateToStr("yyyy-MM-dd HH", new Date()) + ":00:00";
         new SqlVo((String)null, (String)null, endTimeStr);
         JobRecord addJobParam = null;

         try {
            this.syncDatasourceService.syncAddDataBySqlVoAndCode(syncCode);
         } catch (Exception e) {
            this.log.error("同步 syncCode: {}, endTimeStr: {}", syncCode, endTimeStr);
            this.log.error("同步 syncCode: {} 出现异常：{}", syncCode, e);
            addJobParam = new JobRecord();
            addJobParam.setJobCode(syncCode);
            addJobParam.setJobStatus(0);
            addJobParam.setParamsJson("hisInfoSyncAddTask.syncData('" + syncCode + "','" + endTimeStr + "')");
            addJobParam.setJobType("sync");
            addJobParam.setJobBeanName("hisInfoSyncAddTask");
            addJobParam.setJobBeanMethod("addSyncDataOneHour");
         } finally {
            if (addJobParam != null) {
               this.jobRecordService.insertJobRecord(addJobParam);
            }

         }
      } else {
         this.log.error("同步His信息，syncCode不能为空, 当前时间：{}", DateUtils.getTime());
      }

   }

   public void syncData(String syncCode, String endTimeStr) {
      if (StringUtils.isNotBlank(syncCode)) {
         new SqlVo((String)null, (String)null, endTimeStr);

         try {
            this.syncDatasourceService.syncAddDataBySqlVoAndCode(syncCode);
         } catch (Exception e) {
            this.log.error("同步 syncCode: {}, endTimeStr: {}", syncCode, endTimeStr);
            this.log.error("同步 syncCode: {} 出现异常：{}", syncCode, e);
         }
      } else {
         this.log.error("同步His信息，syncCode不能为空, 当前时间：{}", DateUtils.getTime());
      }

   }
}
