package com.emr.project.task;

import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.project.esSearch.service.IEmrFileElemService;
import com.emr.project.esSearch.service.IEmrFileService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class EmrFileAndEmrElemTask {
   protected final Logger log = LoggerFactory.getLogger(EmrFileAndEmrElemTask.class);
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private IEmrFileService emrFileService;
   @Autowired
   private IEmrFileElemService emrFileElemService;
   @Resource(
      name = "poolTaskExecutor"
   )
   private TaskExecutor taskExecutor;

   public void synEmrFileData2Es() {
      this.taskExecutor.execute(() -> {
         try {
            String esUpdate = (String)this.redisCache.getCacheObject("emr_file_synEs_time");
            String beginDate = null;
            String endDate = null;
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String keyValue = df.format(date);
            if (StringUtils.isNotEmpty(esUpdate)) {
               this.emrFileService.syncEmrFileAddToEs(esUpdate, keyValue);
               this.emrFileElemService.syncEmrFileElemAddToEs(esUpdate, keyValue);
               this.emrFileService.deleteEmrFileElemAddToEs(esUpdate, keyValue);
            } else {
               this.emrFileService.syncEmrFileAllToEs();
               this.emrFileElemService.syncEmrFileElemAllToEs();
               this.emrFileService.deleteEmrFileElemAllToEs();
            }

            this.log.debug("同步es数据,执行完毕！----" + keyValue);
            this.redisCache.setCacheObject("emr_file_synEs_time", keyValue);
         } catch (Exception e) {
            this.log.error("增量向es同步病历文件失败", e);
         }

      });
   }
}
