package com.emr.project.task;

import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.holiday.service.ITmBsHolidayService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmrDeptQcFlowTask {
   protected final Logger log = LoggerFactory.getLogger(EmrDeptQcFlowTask.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private IVisitinfoService visitInfoService;
   @Autowired
   private ITmBsHolidayService tmBsHolidayService;
   @Autowired
   private ICommonService commonService;

   public void autoSubmitDeptQcFlowTask() {
      this.log.debug("===============>>开始执行强制提交科室质控定时任务<<===============");

      try {
         String configValue = this.sysEmrConfigService.selectSysEmrConfigByKey("0067");
         String holidayConfig = this.sysEmrConfigService.selectSysEmrConfigByKey("0089");
         this.log.info("配置项配置值为：" + configValue);
         BigDecimal valueBig = new BigDecimal(configValue);
         if (StringUtils.isNotEmpty(configValue)) {
            Boolean flag = Boolean.TRUE;
            Boolean holidayConfigFlag = Boolean.FALSE;
            if (StringUtils.isNotEmpty(holidayConfig) && holidayConfig.equals("1")) {
               holidayConfigFlag = Boolean.TRUE;
               LocalDate nowDate = LocalDate.now();
               DayOfWeek dayOfWeek = nowDate.getDayOfWeek();
               Date dbSysdate = this.commonService.getDbSysdate();
               switch (dayOfWeek) {
                  case MONDAY:
                  case TUESDAY:
                  case WEDNESDAY:
                  case THURSDAY:
                  case FRIDAY:
                     int count = this.tmBsHolidayService.selectHolidayCount(dbSysdate, "1");
                     if (count != 0) {
                        flag = Boolean.FALSE;
                     }
                     break;
                  case SATURDAY:
                  case SUNDAY:
                     flag = Boolean.FALSE;
                     int count1 = this.tmBsHolidayService.selectHolidayCount(dbSysdate, "2");
                     if (count1 != 0) {
                        flag = Boolean.TRUE;
                     }
               }
            }

            if (flag) {
               Date date = new Date();
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
               Calendar calendar = Calendar.getInstance();
               calendar.setTime(date);
               calendar.add(5, valueBig.negate().intValue());
               String endDate = simpleDateFormat.format(calendar.getTime());
               List<Visitinfo> list = this.visitInfoService.selectVistinfoListByOuTime(endDate);
               List<Visitinfo> resultList = new ArrayList();
               if (holidayConfigFlag) {
                  if (list != null && list.size() > 0) {
                     for(Visitinfo info : list) {
                        Date outTime = info.getOutTime();
                        Date fileDate = this.commonService.queryFileDate(outTime, valueBig.intValue());
                        if (date.compareTo(fileDate) > 0) {
                           resultList.add(info);
                        }
                     }
                  }
               } else {
                  resultList = list;
               }

               if (resultList.size() > 0) {
                  this.emrQcFlowService.autoSubmitDeptQc(resultList);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("执行强制提交科室质控定时任务出现异常！", e);
      }

   }
}
