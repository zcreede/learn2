package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.OrderListPrintVo;
import com.emr.project.pdf.service.IGeneratePDFService;
import com.emr.project.report.domain.vo.ReportGrfReqParamVo;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping({"/generatePdfTask"})
public class GeneratePdfTask extends BaseController {
   @Value("${job.valid}")
   private Integer jobValid;
   @Autowired
   private IGeneratePDFService generatePDFService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private RedisCache redisCache;

   public void generateOrderPDF() {
      try {
         this.log.warn("=============定时任务生成医嘱单pdf开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         Date lastLeaveHosDate = this.commonService.getDbSysdate();
         String generatePdfTime = (String)this.redisCache.getCacheObject("generate_pdf_order_time");
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String keyValue = sdf.format(lastLeaveHosDate);
         if (StringUtils.isNotEmpty(generatePdfTime)) {
            lastLeaveHosDate = sdf.parse(generatePdfTime);
         }

         this.log.warn("=============定时任务生成医嘱单pdf开始执行出院时间： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", lastLeaveHosDate));
         OrderListPrintVo orderListPrintVo = new OrderListPrintVo();
         this.generatePDFService.generateOrderPDF(orderListPrintVo, lastLeaveHosDate);
         this.log.warn("=============定时任务生成医嘱单pdf执行结束： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         this.redisCache.setCacheObject("generate_pdf_order_time", keyValue);
      } catch (Exception var6) {
         this.log.error("定时任务生成医嘱单pdf失败");
      }

   }

   public void generateFeePDF() {
      try {
         this.log.warn("=============定时任务生成费用总清单pdf开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         Date lastLeaveHosDate = this.commonService.getDbSysdate();
         String generatePdfTime = (String)this.redisCache.getCacheObject("generate_pdf_fee_time");
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String keyValue = sdf.format(lastLeaveHosDate);
         if (StringUtils.isNotEmpty(generatePdfTime)) {
            lastLeaveHosDate = sdf.parse(generatePdfTime);
         }

         this.log.warn("=============定时任务生成费用总清单pdf开始执行出院时间： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", lastLeaveHosDate));
         ReportGrfReqParamVo paramVo = new ReportGrfReqParamVo();
         this.generatePDFService.generateFeePDF(paramVo, lastLeaveHosDate);
         this.log.warn("=============定时任务生成费用总清单pdf执行结束： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         this.redisCache.setCacheObject("generate_pdf_fee_time", keyValue);
      } catch (Exception var6) {
         this.log.error("定时任务生成费用总清单pdf失败");
      }

   }
}
