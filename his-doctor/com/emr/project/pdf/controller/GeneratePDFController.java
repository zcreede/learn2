package com.emr.project.pdf.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.OrderListPrintVo;
import com.emr.project.pdf.domain.TArMedicalinformationPdf;
import com.emr.project.pdf.service.IGeneratePDFService;
import com.emr.project.pdf.service.ITArMedicalinformationPdfService;
import com.emr.project.report.domain.vo.ReportGrfReqParamVo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/tmpl/generatePDF"})
public class GeneratePDFController extends BaseController {
   @Value("${job.valid}")
   private Integer jobValid;
   @Autowired
   private IGeneratePDFService generatePDFService;
   @Autowired
   private ITArMedicalinformationPdfService medicalinformationPdfService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private RedisCache redisCache;

   @PreAuthorize("@ss.hasAnyPermi('tmpl:order:generatePDF')")
   @GetMapping({"/selectWaitGenOrderPDFPatList"})
   public TableDataInfo selectWaitGenOrderPDFPatList(TArMedicalinformationPdf medicalinformationPdf) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         Date lastLeaveHosDate = this.commonService.getDbSysdate();
         String generatePdfTime = (String)this.redisCache.getCacheObject("generate_pdf_order_time");
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         if (StringUtils.isNotEmpty(generatePdfTime)) {
            lastLeaveHosDate = sdf.parse(generatePdfTime);
         }

         medicalinformationPdf.setUpdateTime(lastLeaveHosDate);
         this.log.warn("=============定时任务生成医嘱单pdf开始执行redis时间： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", lastLeaveHosDate));
         this.startPage();
         List<TArMedicalinformationPdf> waitGenPdfList = this.medicalinformationPdfService.selectUnGenerateOrderPdfList(medicalinformationPdf);
         tableDataInfo = this.getDataTable(waitGenPdfList);
      } catch (Exception var7) {
         this.log.error("查询未生成医嘱pdf的患者列表出现异常");
         tableDataInfo = new TableDataInfo(500, "查询未生成医嘱pdf的患者列表出现异常，请联系系统管理员");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:order:generatePDF')")
   @PostMapping({"/generateOrderPDF"})
   public AjaxResult generateOrderPDF(@RequestBody OrderListPrintVo orderListPrintVo) {
      AjaxResult ajaxResult = AjaxResult.success("生成医嘱单PDF成功");

      try {
         Date lastLeaveHosDate = this.commonService.getDbSysdate();
         String generatePdfTime = (String)this.redisCache.getCacheObject("generate_pdf_order_time");
         Date date = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String keyValue = sdf.format(date);
         if (StringUtils.isNotEmpty(generatePdfTime)) {
            lastLeaveHosDate = sdf.parse(keyValue);
         }

         this.generatePDFService.generateOrderPDF(orderListPrintVo, lastLeaveHosDate);
      } catch (Exception var8) {
         this.log.error("生成医嘱单pdf失败");
         ajaxResult = AjaxResult.error("生成医嘱单pdf失败，请联系系统管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:fee:generatePDF')")
   @GetMapping({"/selectWaitGenFeePDFPatList"})
   public TableDataInfo selectWaitGenFeePDFPatList(TArMedicalinformationPdf medicalinformationPdf) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         Date lastLeaveHosDate = this.commonService.getDbSysdate();
         String generatePdfTime = (String)this.redisCache.getCacheObject("generate_pdf_fee_time");
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         if (StringUtils.isNotEmpty(generatePdfTime)) {
            lastLeaveHosDate = sdf.parse(generatePdfTime);
         }

         medicalinformationPdf.setUpdateTime(lastLeaveHosDate);
         this.log.warn("=============定时任务生成费用总清单pdf开始执行redis时间： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", lastLeaveHosDate));
         this.startPage();
         List<TArMedicalinformationPdf> waitGenPdfList = this.medicalinformationPdfService.selectUnGenerateFeePdfList(medicalinformationPdf);
         tableDataInfo = this.getDataTable(waitGenPdfList);
      } catch (Exception var7) {
         this.log.error("查询未生成费用pdf的患者列表出现异常");
         tableDataInfo = new TableDataInfo(500, "查询未生成费用pdf的患者列表出现异常，请联系系统管理员");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:fee:generatePDF')")
   @PostMapping({"/generateFeePDF"})
   public AjaxResult generateFeePDF(@RequestBody ReportGrfReqParamVo paramVo) {
      AjaxResult ajaxResult = AjaxResult.success("生成费用总清单PDF成功");

      try {
         Date lastLeaveHosDate = this.commonService.getDbSysdate();
         String generatePdfTime = (String)this.redisCache.getCacheObject("generate_pdf_fee_time");
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         if (StringUtils.isNotEmpty(generatePdfTime)) {
            lastLeaveHosDate = sdf.parse(generatePdfTime);
         }

         this.generatePDFService.generateFeePDF(paramVo, lastLeaveHosDate);
      } catch (Exception var6) {
         this.log.error("生成费用总清单pdf失败");
         ajaxResult = AjaxResult.error("生成费用总清单pdf失败，请联系系统管理员");
      }

      return ajaxResult;
   }
}
