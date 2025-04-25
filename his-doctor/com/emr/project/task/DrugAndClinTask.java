package com.emr.project.task;

import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.tmpm.service.IClinitemUseLogService;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DrugAndClinTask {
   protected final Logger log = LoggerFactory.getLogger(DrugAndClinTask.class);
   @Autowired
   private IClinitemUseLogService clinitemUseLogService;
   @Autowired
   private ITdPaOrderDetailService orderDetailService;
   @Autowired
   private ICommonService commonService;

   public void clinitemUseLog() {
      this.log.info("更新医嘱项目使用次数+++++++++++++");

      try {
         Date lastUpdateDate = this.clinitemUseLogService.selectMaxAltDate();
         lastUpdateDate = lastUpdateDate == null ? this.commonService.getDbSysdate() : lastUpdateDate;
         List<TdPaOrderDetailVo> orderDetailVoList = this.orderDetailService.selectListByTime(lastUpdateDate, (Date)null);
         if (CollectionUtils.isNotEmpty(orderDetailVoList)) {
            this.clinitemUseLogService.updateClinitemUseLogTask(orderDetailVoList);
         }
      } catch (Exception e) {
         this.log.error("更新医嘱项目使用次数出现异常：", e);
      }

   }

   public void clinitemUseLog(Date lastUpdateDate) {
      try {
         if (lastUpdateDate == null) {
            this.clinitemUseLogService.selectMaxAltDate();
         }
      } catch (Exception e) {
         this.log.error("更新医嘱项目使用次数出现异常：", e);
      }

   }
}
