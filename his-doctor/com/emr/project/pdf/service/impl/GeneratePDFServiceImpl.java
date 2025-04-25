package com.emr.project.pdf.service.impl;

import com.alibaba.fastjson.JSON;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.GeneratePDFUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.security.LoginUser;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.OrderListInfoVo;
import com.emr.project.docOrder.domain.vo.OrderListPrintVo;
import com.emr.project.docOrder.domain.vo.OrderListSearchVo;
import com.emr.project.docOrder.domain.vo.OrderListVo;
import com.emr.project.docOrder.domain.vo.OrderPdfListVo;
import com.emr.project.docOrder.service.ITdPaOrderListService;
import com.emr.project.pdf.domain.TArMedicalinformationPdf;
import com.emr.project.pdf.service.IGeneratePDFService;
import com.emr.project.pdf.service.ITArMedicalinformationPdfService;
import com.emr.project.report.domain.vo.ReportGrfReqParamVo;
import com.emr.project.report.service.IReportGrfService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.task.HisInfoSyncAddTask;
import com.github.pagehelper.util.StringUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratePDFServiceImpl implements IGeneratePDFService {
   protected final Logger log = LoggerFactory.getLogger(HisInfoSyncAddTask.class);
   private int LONG_PAGE_SIZE = 25;
   private int TEMP_PAGE_SIZE = 25;
   private int DECOCTION_PAGE_SIZE = 25;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITdPaOrderListService tdPaOrderListService;
   @Autowired
   private GeneratePDFUtils generatePDFUtils;
   @Autowired
   private ITArMedicalinformationPdfService medicalinformationPdfService;
   @Autowired
   private IReportGrfService reportGrfService;
   @Autowired
   private ICommonService commonService;

   public void generateOrderPDF(OrderListPrintVo orderListPrintVo, Date lastLeaveHosDate) throws Exception {
      orderListPrintVo.setPrintType(1);
      String blankStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0047");
      String allFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("004702");
      String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
      String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
      String orderPdfFile = this.sysEmrConfigService.selectSysEmrConfigByKey("0140");
      int pageSize = 0;
      String typeCode = null;
      List<TArMedicalinformationPdf> medPdfBatchInsertList = new ArrayList();
      if (orderListPrintVo != null && orderListPrintVo.getOrderListSearchVo() != null) {
         LoginUser loginUser = SecurityUtils.getLoginUser();
         SysUser user = new SysUser();
         if (loginUser != null && loginUser.getUser() != null) {
            user = loginUser.getUser();
         } else {
            user.setUserName("sys");
            user.setNickName("系统");
         }

         String orderListType = orderListPrintVo.getOrderListType();
         Boolean isBaby = false;
         if (StringUtil.isNotEmpty(orderListPrintVo.getOrderListSearchVo().getBabyAdmissionNo())) {
            isBaby = true;
         }

         if (StringUtil.isNotEmpty(orderListType)) {
            switch (orderListType) {
               case "1":
                  orderListPrintVo.getOrderListSearchVo().setOrderType("1");
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("0");
                  pageSize = this.LONG_PAGE_SIZE;
                  typeCode = "100031001";
                  break;
               case "2":
                  orderListPrintVo.getOrderListSearchVo().setOrderType("2");
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("0");
                  pageSize = this.TEMP_PAGE_SIZE;
                  typeCode = "100031002";
                  break;
               case "3":
                  orderListPrintVo.getOrderListSearchVo().setOrderType((String)null);
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("1");
                  pageSize = this.DECOCTION_PAGE_SIZE;
                  typeCode = "100031003";
            }

            List<OrderListInfoVo> list = this.tdPaOrderListService.queryPrintData(orderListPrintVo, pageSize, blankStr, allFlag);
            if (CollectionUtils.isNotEmpty(list) && list.size() > 0) {
               List<OrderPdfListVo> listAll = new ArrayList();

               for(OrderListInfoVo orderListInfoVo : list) {
                  List<OrderPdfListVo> orderPdfList = this.getOrderPdfListVos(caFlag, caType, orderListInfoVo);
                  listAll.addAll(orderPdfList);
               }

               List<Map<String, Object>> xmlData = (List)listAll.stream().map((iter) -> {
                  Map<String, Object> map = (Map)JSON.parseObject(JSON.toJSONString(iter), Map.class);
                  return map;
               }).collect(Collectors.toList());
               this.getWaitInsertList(orderPdfFile, typeCode, medPdfBatchInsertList, user, orderListType, listAll, xmlData, isBaby);
            }
         } else {
            orderListPrintVo.getOrderListSearchVo().setOrderType("1");
            orderListPrintVo.getOrderListSearchVo().setHerbalFlag("0");
            orderListPrintVo.setOrderListType("1");
            pageSize = this.LONG_PAGE_SIZE;
            typeCode = "100031001";
            List<OrderListInfoVo> orderType1List = this.tdPaOrderListService.queryPrintData(orderListPrintVo, pageSize, blankStr, allFlag);
            if (CollectionUtils.isNotEmpty(orderType1List) && orderType1List.size() > 0) {
               List<OrderPdfListVo> listAll = new ArrayList();

               for(OrderListInfoVo orderListInfoVo : orderType1List) {
                  List<OrderPdfListVo> orderPdfList = this.getOrderPdfListVos(caFlag, caType, orderListInfoVo);
                  listAll.addAll(orderPdfList);
               }

               List<Map<String, Object>> xmlData = (List)listAll.stream().map((iter) -> {
                  Map<String, Object> map = (Map)JSON.parseObject(JSON.toJSONString(iter), Map.class);
                  return map;
               }).collect(Collectors.toList());
               orderListType = "1";
               this.getWaitInsertList(orderPdfFile, typeCode, medPdfBatchInsertList, user, orderListType, listAll, xmlData, isBaby);
            }

            orderListPrintVo.getOrderListSearchVo().setOrderType("2");
            orderListPrintVo.getOrderListSearchVo().setHerbalFlag("0");
            orderListPrintVo.setOrderListType("2");
            pageSize = this.TEMP_PAGE_SIZE;
            typeCode = "100031002";
            List<OrderListInfoVo> orderType2List = this.tdPaOrderListService.queryPrintData(orderListPrintVo, pageSize, blankStr, allFlag);
            if (CollectionUtils.isNotEmpty(orderType2List) && orderType2List.size() > 0) {
               List<OrderPdfListVo> listAll = new ArrayList();

               for(OrderListInfoVo orderListInfoVo : orderType2List) {
                  List<OrderPdfListVo> orderPdfList = this.getOrderPdfListVos(caFlag, caType, orderListInfoVo);
                  listAll.addAll(orderPdfList);
               }

               List<Map<String, Object>> xmlData = (List)listAll.stream().map((iter) -> {
                  Map<String, Object> map = (Map)JSON.parseObject(JSON.toJSONString(iter), Map.class);
                  return map;
               }).collect(Collectors.toList());
               orderListType = "2";
               this.getWaitInsertList(orderPdfFile, typeCode, medPdfBatchInsertList, user, orderListType, listAll, xmlData, isBaby);
            }

            orderListPrintVo.getOrderListSearchVo().setOrderType((String)null);
            orderListPrintVo.getOrderListSearchVo().setHerbalFlag("1");
            orderListPrintVo.setOrderListType("3");
            pageSize = this.DECOCTION_PAGE_SIZE;
            typeCode = "100031003";
            List<OrderListInfoVo> orderType3List = this.tdPaOrderListService.queryPrintData(orderListPrintVo, pageSize, blankStr, allFlag);
            if (CollectionUtils.isNotEmpty(orderType3List) && orderType3List.size() > 0) {
               List<OrderPdfListVo> listAll = new ArrayList();

               for(OrderListInfoVo orderListInfoVo : orderType3List) {
                  List<OrderPdfListVo> orderPdfList = this.getOrderPdfListVos(caFlag, caType, orderListInfoVo);
                  listAll.addAll(orderPdfList);
               }

               List<Map<String, Object>> xmlData = (List)listAll.stream().map((iter) -> {
                  Map<String, Object> map = (Map)JSON.parseObject(JSON.toJSONString(iter), Map.class);
                  return map;
               }).collect(Collectors.toList());
               orderListType = "3";
               this.getWaitInsertList(orderPdfFile, typeCode, medPdfBatchInsertList, user, orderListType, listAll, xmlData, isBaby);
            }
         }
      } else {
         TArMedicalinformationPdf medicalinformationPdf = new TArMedicalinformationPdf();
         medicalinformationPdf.setUpdateTime(lastLeaveHosDate);
         List<TArMedicalinformationPdf> waitGenPdfList = this.medicalinformationPdfService.selectUnGenerateOrderPdfList(medicalinformationPdf);
         LoginUser loginUser = SecurityUtils.getLoginUser();
         SysUser user = new SysUser();
         if (loginUser != null && loginUser.getUser() != null) {
            user = loginUser.getUser();
         } else {
            user.setUserName("sys");
            user.setNickName("系统");
         }

         if (CollectionUtils.isNotEmpty(waitGenPdfList) && waitGenPdfList.size() > 0) {
            this.log.warn("=============定时任务生成医嘱单pdf本次执行条数： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()) + "数量：" + waitGenPdfList.size());

            for(TArMedicalinformationPdf medPdf : waitGenPdfList) {
               try {
                  Boolean isBaby = false;
                  Thread.sleep(10000L);
                  if (StringUtil.isNotEmpty(medPdf.getBabyAdmissionno())) {
                     isBaby = true;
                  }

                  OrderListSearchVo orderListSearchVo = new OrderListSearchVo();
                  orderListSearchVo.setPatientId(medPdf.getAdmissionNo());
                  orderListSearchVo.setBabyAdmissionNo(medPdf.getBabyAdmissionno());
                  orderListSearchVo.setHerbalFlag("0");
                  orderListPrintVo.setOrderListSearchVo(orderListSearchVo);
                  orderListPrintVo.getOrderListSearchVo().setOrderType("1");
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("0");
                  orderListPrintVo.setOrderListType("1");
                  pageSize = this.LONG_PAGE_SIZE;
                  typeCode = "100031001";
                  List<OrderListInfoVo> orderType1List = this.tdPaOrderListService.queryPrintData(orderListPrintVo, pageSize, blankStr, allFlag);
                  if (CollectionUtils.isNotEmpty(orderType1List) && orderType1List.size() > 0) {
                     List<OrderPdfListVo> listAll = new ArrayList();

                     for(OrderListInfoVo orderListInfoVo : orderType1List) {
                        List<OrderPdfListVo> orderPdfList = this.getOrderPdfListVos(caFlag, caType, orderListInfoVo);
                        listAll.addAll(orderPdfList);
                     }

                     List<Map<String, Object>> xmlData = (List)listAll.stream().map((iter) -> {
                        Map<String, Object> map = (Map)JSON.parseObject(JSON.toJSONString(iter), Map.class);
                        return map;
                     }).collect(Collectors.toList());
                     String orderListType = "1";
                     Thread.sleep(10000L);
                     this.getWaitInsertList(orderPdfFile, typeCode, medPdfBatchInsertList, user, orderListType, listAll, xmlData, isBaby);
                  }

                  orderListPrintVo.getOrderListSearchVo().setOrderType("2");
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("0");
                  orderListPrintVo.setOrderListType("2");
                  pageSize = this.TEMP_PAGE_SIZE;
                  typeCode = "100031002";
                  List<OrderListInfoVo> orderType2List = this.tdPaOrderListService.queryPrintData(orderListPrintVo, pageSize, blankStr, allFlag);
                  if (CollectionUtils.isNotEmpty(orderType2List) && orderType2List.size() > 0) {
                     List<OrderPdfListVo> listAll = new ArrayList();

                     for(OrderListInfoVo orderListInfoVo : orderType2List) {
                        List<OrderPdfListVo> orderPdfList = this.getOrderPdfListVos(caFlag, caType, orderListInfoVo);
                        listAll.addAll(orderPdfList);
                     }

                     List<Map<String, Object>> xmlData = (List)listAll.stream().map((iter) -> {
                        Map<String, Object> map = (Map)JSON.parseObject(JSON.toJSONString(iter), Map.class);
                        return map;
                     }).collect(Collectors.toList());
                     String orderListType = "2";
                     Thread.sleep(5000L);
                     this.getWaitInsertList(orderPdfFile, typeCode, medPdfBatchInsertList, user, orderListType, listAll, xmlData, isBaby);
                  }

                  orderListPrintVo.getOrderListSearchVo().setOrderType((String)null);
                  orderListPrintVo.getOrderListSearchVo().setHerbalFlag("1");
                  orderListPrintVo.setOrderListType("3");
                  pageSize = this.DECOCTION_PAGE_SIZE;
                  typeCode = "100031003";
                  List<OrderListInfoVo> orderType3List = this.tdPaOrderListService.queryPrintData(orderListPrintVo, pageSize, blankStr, allFlag);
                  if (CollectionUtils.isNotEmpty(orderType3List) && orderType3List.size() > 0) {
                     List<OrderPdfListVo> listAll = new ArrayList();

                     for(OrderListInfoVo orderListInfoVo : orderType3List) {
                        List<OrderPdfListVo> orderPdfList = this.getOrderPdfListVos(caFlag, caType, orderListInfoVo);
                        listAll.addAll(orderPdfList);
                     }

                     List<Map<String, Object>> xmlData = (List)listAll.stream().map((iter) -> {
                        Map<String, Object> map = (Map)JSON.parseObject(JSON.toJSONString(iter), Map.class);
                        return map;
                     }).collect(Collectors.toList());
                     String orderListType = "3";
                     Thread.sleep(10000L);
                     this.getWaitInsertList(orderPdfFile, typeCode, medPdfBatchInsertList, user, orderListType, listAll, xmlData, isBaby);
                  }
               } catch (Exception e) {
                  this.log.error("定时任务生成医嘱单线程中断");
                  throw new Exception("定时任务生成医嘱单线程中断", e);
               }
            }
         }
      }

   }

   private List getOrderPdfListVos(String caFlag, String caType, OrderListInfoVo orderListInfoVo) {
      List<OrderListVo> orderList = orderListInfoVo.getList();
      List<OrderPdfListVo> orderPdfList = new ArrayList(orderListInfoVo.getList().size());

      for(OrderListVo orderListVo : orderList) {
         OrderPdfListVo orderPdfListVo = new OrderPdfListVo();
         BeanUtils.copyProperties(orderListVo, orderPdfListVo);
         orderPdfListVo.setCaFlag(caFlag);
         orderPdfListVo.setCaType(caType);
         if (orderListVo.getOrderStartTime() != null) {
            String OrderStartTimeS = DateUtils.dateFormat(orderListVo.getOrderStartTime(), DateUtils.YY_MM_DD_HH_MM);
            orderPdfListVo.setOrderStartTime(OrderStartTimeS);
         }

         if (orderListVo.getOrderPerformTime() != null) {
            String OrderPerformTimeS = DateUtils.dateFormat(orderListVo.getOrderPerformTime(), DateUtils.YY_MM_DD_HH_MM);
            orderPdfListVo.setOrderPerformTime(OrderPerformTimeS);
         }

         if (orderListVo.getOrderStopDate() != null) {
            String OrderStopDateS = DateUtils.dateFormat(orderListVo.getOrderStopDate(), DateUtils.MM_DD);
            orderPdfListVo.setOrderStopDate(OrderStopDateS);
         }

         if (orderListVo.getOrderStopTime() != null) {
            String OrderStopTimeS = DateUtils.dateFormat(orderListVo.getOrderStopTime(), DateUtils.HH_MM);
            orderPdfListVo.setOrderStopTime(OrderStopTimeS);
         }

         orderPdfList.add(orderPdfListVo);
      }

      return orderPdfList;
   }

   public void generateFeePDF(ReportGrfReqParamVo paramVo, Date lastLeaveHosDate) throws Exception {
      List<TArMedicalinformationPdf> medPdfBatchInsertList = new ArrayList();
      LoginUser loginUser = SecurityUtils.getLoginUser();
      SysUser user = new SysUser();
      if (loginUser != null && loginUser.getUser() != null) {
         user = loginUser.getUser();
      } else {
         user.setUserName("sys");
         user.setNickName("系统");
      }

      String PdfFile = this.sysEmrConfigService.selectSysEmrConfigByKey("014001");
      String typeCode = "100009002";
      if (paramVo != null && StringUtil.isNotEmpty(paramVo.getV_admission_no())) {
         List<Map<String, Object>> mapList = this.reportGrfService.feeSummaryDayDataListForPdf(paramVo);
         String filePath = this.generatePDFUtils.generateReportPdf(paramVo.getV_admission_no(), typeCode, mapList, PdfFile, false);
         if (StringUtil.isNotEmpty(filePath)) {
            TArMedicalinformationPdf waitGenPdf = this.medicalinformationPdfService.selectMedByPatientId(paramVo.getV_admission_no());
            TArMedicalinformationPdf medicalinformationPdf = new TArMedicalinformationPdf();
            medicalinformationPdf.setId(SnowIdUtils.uniqueLong());
            medicalinformationPdf.setHospitalCode(waitGenPdf.getHospitalCode());
            medicalinformationPdf.setDeptCode(waitGenPdf.getDeptCode());
            medicalinformationPdf.setPatientId(waitGenPdf.getPatientId());
            medicalinformationPdf.setCaseNo(waitGenPdf.getCaseNo());
            medicalinformationPdf.setAdmissionNo(waitGenPdf.getAdmissionNo());
            medicalinformationPdf.setPatientName(waitGenPdf.getPatientName());
            medicalinformationPdf.setGenType("2");
            medicalinformationPdf.setLeaveHospitalDate(waitGenPdf.getLeaveHospitalDate());
            medicalinformationPdf.setPdfPath(filePath);
            medicalinformationPdf.setCrePerName(user.getNickName());
            medicalinformationPdf.setCrePerCode(user.getUserName());
            medicalinformationPdf.setCreDate(this.commonService.getDbSysdate());
            this.medicalinformationPdfService.insertTArMedicalinformationPdf(medicalinformationPdf);
            medPdfBatchInsertList.add(medicalinformationPdf);
         }
      } else {
         TArMedicalinformationPdf medicalPdf = new TArMedicalinformationPdf();
         medicalPdf.setUpdateTime(lastLeaveHosDate);

         for(TArMedicalinformationPdf medPdf : this.medicalinformationPdfService.selectUnGenerateFeePdfList(medicalPdf)) {
            try {
               paramVo.setV_admission_no(medPdf.getAdmissionNo());
               List<Map<String, Object>> mapList = this.reportGrfService.feeSummaryDayDataListForPdf(paramVo);
               this.log.warn("=============定时任务生成费用总清单开始生成患者：" + medPdf.getAdmissionNo() + "pdf开始： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
               Thread.sleep(10000L);
               String filePath = this.generatePDFUtils.generateReportPdf(paramVo.getV_admission_no(), typeCode, mapList, PdfFile, false);
               this.log.warn("=============定时任务生成费用总清单开始生成患者：" + medPdf.getAdmissionNo() + "pdf结束： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
               if (StringUtil.isNotEmpty(filePath)) {
                  TArMedicalinformationPdf waitGenPdf = this.medicalinformationPdfService.selectMedByPatientId(paramVo.getV_admission_no());
                  TArMedicalinformationPdf medicalinformationPdf = new TArMedicalinformationPdf();
                  medicalinformationPdf.setId(SnowIdUtils.uniqueLong());
                  medicalinformationPdf.setHospitalCode(waitGenPdf.getHospitalCode());
                  medicalinformationPdf.setDeptCode(waitGenPdf.getDeptCode());
                  medicalinformationPdf.setPatientId(waitGenPdf.getPatientId());
                  medicalinformationPdf.setCaseNo(waitGenPdf.getCaseNo());
                  medicalinformationPdf.setAdmissionNo(waitGenPdf.getAdmissionNo());
                  medicalinformationPdf.setPatientName(waitGenPdf.getPatientName());
                  medicalinformationPdf.setGenType("2");
                  medicalinformationPdf.setLeaveHospitalDate(waitGenPdf.getLeaveHospitalDate());
                  medicalinformationPdf.setPdfPath(filePath);
                  medicalinformationPdf.setCrePerName(user.getNickName());
                  medicalinformationPdf.setCrePerCode(user.getUserName());
                  medicalinformationPdf.setCreDate(this.commonService.getDbSysdate());
                  this.medicalinformationPdfService.insertTArMedicalinformationPdf(medicalinformationPdf);
                  medPdfBatchInsertList.add(medicalinformationPdf);
               }
            } catch (Exception e) {
               this.log.error("定时任务生成费用总清单线程中断");
               throw new Exception("定时任务生成费用总清单线程中断", e);
            }
         }
      }

   }

   public Date lastLeaveHosDate(String genType) throws Exception {
      TArMedicalinformationPdf medicalinformationPdf = this.medicalinformationPdfService.lastLeaveHosDate(genType);
      return medicalinformationPdf != null ? medicalinformationPdf.getLeaveHospitalDate() : null;
   }

   private void getWaitInsertList(String orderPdfFile, String typeCode, List medPdfBatchInsertList, SysUser user, String orderListType, List listAll, List xmlData, Boolean isBaby) throws Exception {
      this.log.warn("=============定时任务生成医嘱单开始生成患者：" + ((OrderPdfListVo)listAll.get(0)).getPatientId() + "pdf开始： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      String filePath = this.generatePDFUtils.generateReportPdf(((OrderPdfListVo)listAll.get(0)).getPatientId(), typeCode, xmlData, orderPdfFile, isBaby);
      this.log.warn("=============定时任务生成医嘱单开始生成患者：" + ((OrderPdfListVo)listAll.get(0)).getPatientId() + "pdf结束： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      if (StringUtil.isNotEmpty(filePath)) {
         new TArMedicalinformationPdf();
         TArMedicalinformationPdf waitGenPdf;
         if (StringUtil.isNotEmpty(((OrderPdfListVo)listAll.get(0)).getBabyAdmissionNo())) {
            waitGenPdf = this.medicalinformationPdfService.selectMedByPatientId(((OrderPdfListVo)listAll.get(0)).getBabyAdmissionNo());
         } else {
            waitGenPdf = this.medicalinformationPdfService.selectMedByPatientId(((OrderPdfListVo)listAll.get(0)).getPatientId());
         }

         TArMedicalinformationPdf medicalinformationPdf = new TArMedicalinformationPdf();
         medicalinformationPdf.setId(SnowIdUtils.uniqueLong());
         medicalinformationPdf.setHospitalCode(waitGenPdf.getHospitalCode());
         medicalinformationPdf.setDeptCode(waitGenPdf.getDeptCode());
         medicalinformationPdf.setPatientId(((OrderPdfListVo)listAll.get(0)).getPatientId());
         medicalinformationPdf.setCaseNo(waitGenPdf.getCaseNo());
         medicalinformationPdf.setAdmissionNo(waitGenPdf.getAdmissionNo());
         medicalinformationPdf.setBabyAdmissionno(((OrderPdfListVo)listAll.get(0)).getBabyAdmissionNo());
         medicalinformationPdf.setPatientName(((OrderPdfListVo)listAll.get(0)).getPatientName());
         medicalinformationPdf.setGenType("1");
         medicalinformationPdf.setLeaveHospitalDate(waitGenPdf.getLeaveHospitalDate());
         medicalinformationPdf.setOrderType(orderListType);
         medicalinformationPdf.setPdfPath(filePath);
         medicalinformationPdf.setCrePerName(user.getNickName());
         medicalinformationPdf.setCrePerCode(user.getUserName());
         medicalinformationPdf.setCreDate(this.commonService.getDbSysdate());
         this.medicalinformationPdfService.insertTArMedicalinformationPdf(medicalinformationPdf);
         medPdfBatchInsertList.add(medicalinformationPdf);
      }

   }
}
