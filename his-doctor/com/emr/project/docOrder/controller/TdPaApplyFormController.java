package com.emr.project.docOrder.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.CDSS.domain.vo.xyt.CdssResVo;
import com.emr.project.CDSS.service.ICdssReqService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaApplyForm;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.service.ICheckCDSSService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.ClinItemMain;
import com.emr.project.tmpm.domain.DocumentType;
import com.emr.project.tmpm.service.IClinItemDetailService;
import com.emr.project.tmpm.service.IClinItemMainService;
import com.emr.project.tmpm.service.IDocumentTypeService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/tmpm/form"})
public class TdPaApplyFormController extends BaseController {
   @Autowired
   private ITdPaApplyFormService tdPaApplyFormService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ICdssReqService cdssReqService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private IDocumentTypeService documentTypeService;
   @Autowired
   private ICheckCDSSService checkCDSSService;
   @Autowired
   private IClinItemDetailService clinItemDetailService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IClinItemMainService clinItemMainService;

   @PreAuthorize("@ss.hasAnyPermi('tmpm:form:list,docOrder:check:list')")
   @GetMapping({"/list"})
   public AjaxResult list(TdPaApplyForm tdPaApplyForm) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(tdPaApplyForm.getPatientId())) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            List<TdPaApplyFormVo> list = this.tdPaApplyFormService.selectTdPaApplyFormList(tdPaApplyForm);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询检查检验申请单列表出现异常", e);
         ajaxResult = AjaxResult.error("查询检查检验申请单列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpm:form:query,docOrder:check:list')")
   @GetMapping({"/queryInfo"})
   public AjaxResult getInfo(String applyFormNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         this.tdPaApplyFormService.selectTdPaApplyFormById(applyFormNo);
      } catch (Exception e) {
         this.log.error("获取检查检验申请单详细信息出现异常", e);
         ajaxResult = AjaxResult.error("获取检查检验申请单详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpm:form:add')")
   @Log(
      title = "检查检验申请单",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaApplyFormVo tdPaApplyForm, OrderCommitVo orderCommitVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         if (tdPaApplyForm == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         TdPaApplyForm applyForm = null;
         if (flag && StringUtils.isNotEmpty(tdPaApplyForm.getApplyFormNo())) {
            applyForm = this.tdPaApplyFormService.selectTdPaApplyFormById(tdPaApplyForm.getApplyFormNo());
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         VisitinfoVo vparam = new VisitinfoVo();
         vparam.setPatientId(tdPaApplyForm.getPatientId());
         Visitinfo visitinfo = flag ? this.visitinfoService.selectPatientById(vparam) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("查询不到患者信息，请刷新页面再操作");
         }

         if (flag && !visitinfo.getInpNo().equals(tdPaApplyForm.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者信息不正确，请刷新页面再操作");
         }

         boolean userHasPatFlag = flag ? this.commonService.userHasPat(visitinfo, sysUser) : flag;
         if (flag && !userHasPatFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("只有患者所在科医生或者有临时授权的医生才能开立医嘱！");
         }

         if (flag && tdPaApplyForm.getApplyTime() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("开始时间不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getOrderDoctorCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("开单医师编号不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getOrderDoctorName())) {
            flag = false;
            ajaxResult = AjaxResult.error("开单医师姓名不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getPhysicianDptNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("开单科室编号不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getPhysicianDptName())) {
            flag = false;
            ajaxResult = AjaxResult.error("开单科室名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getApplyFormClassCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单据类别不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getDiagnosisCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("临床诊断编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getDiagnosisName())) {
            flag = false;
            ajaxResult = AjaxResult.error("临床诊断名称不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getMedicalRecordDigest())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历摘要不能为空");
         }

         if (flag && applyForm == null && (tdPaApplyForm.getItemList() == null || tdPaApplyForm.getItemList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目集合不能为空");
         }

         if (flag) {
            StringBuffer stringBuffer = new StringBuffer();
            Boolean partFlag = false;
            List<String> itemCdList = (List)tdPaApplyForm.getItemList().stream().map((t) -> t.getCpNo()).collect(Collectors.toList());
            List<ClinItemMain> clinItemMainList = this.clinItemMainService.selectClinItemMainByItemCds(itemCdList);
            Map<String, List<ClinItemMain>> stringListMap = (Map)clinItemMainList.stream().collect(Collectors.groupingBy(ClinItemMain::getItemCd));

            for(DrugAndClin drugAndClin : tdPaApplyForm.getItemList()) {
               if (flag) {
                  if (!drugAndClin.getOrderClassCode().equals("02")) {
                     if (StringUtils.isBlank(drugAndClin.getSpecCd()) || StringUtils.isBlank(drugAndClin.getSpecName())) {
                        flag = false;
                        ajaxResult = AjaxResult.error("选中项目标本不能为空");
                     }
                  } else {
                     if (StringUtils.isBlank(drugAndClin.getExamPartCd()) || StringUtils.isBlank(drugAndClin.getExamPartName())) {
                        flag = false;
                        ajaxResult = AjaxResult.error("选中项目部位不能为空");
                     }

                     if (flag) {
                        ClinItemMain clinItemMain = (ClinItemMain)((List)stringListMap.get(drugAndClin.getCpNo())).get(0);
                        if (clinItemMain != null && StringUtils.isNotBlank(clinItemMain.getExamPartCd())) {
                           List<String> clinPartCdList = Arrays.asList(clinItemMain.getExamPartCd().split(","));
                           if (StringUtils.isNotBlank(drugAndClin.getExamPartCd())) {
                              for(String partCd : Arrays.asList(drugAndClin.getExamPartCd().split(","))) {
                                 if (!clinPartCdList.contains(partCd)) {
                                    partFlag = true;
                                    stringBuffer.append(" " + drugAndClin.getCpName() + "请选择部位:" + clinItemMain.getExamPartName());
                                    stringBuffer.append(";");
                                    break;
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (StringUtils.isBlank(drugAndClin.getPerformDepCode())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("选中项目的执行科室不能为空，联系管理员维护项目的执行科室。选中项目编码：" + drugAndClin.getCpNo() + "，项目名称：" + drugAndClin.getCpName());
                  }
               }
            }

            if (partFlag) {
               flag = false;
               ajaxResult = AjaxResult.error("有不符合要求的部位：" + stringBuffer.toString());
            }
         }

         if (flag) {
            List<String> itemCdList = (List)tdPaApplyForm.getItemList().stream().map((s) -> s.getCpNo()).collect(Collectors.toList());
            List<ClinItemDetail> itemDetailList = this.clinItemDetailService.selectClinItemDetailByItemCds(itemCdList);
            Map<String, List<ClinItemDetail>> detailMapList = (Map)itemDetailList.stream().collect(Collectors.groupingBy((s) -> s.getItemCd()));

            for(DrugAndClin item : tdPaApplyForm.getItemList()) {
               String itemCd = item.getCpNo();
               List<ClinItemDetail> detailList = (List)detailMapList.get(itemCd);
               if (CollectionUtils.isEmpty(detailList)) {
                  flag = false;
                  ajaxResult = AjaxResult.error("选中项目【" + item.getCpName() + "】计费信息不能为空，请联系管理员进行维护");
               }
            }
         }

         if (flag) {
            ajaxResult = this.clinItemMainService.verifyExtendedAttri(tdPaApplyForm.getPatientId(), (List)null, tdPaApplyForm.getItemList(), ajaxResult);
            boolean clinAgeFlag = ajaxResult.get("clinAgeFlag") != null ? (Boolean)ajaxResult.get("clinAgeFlag") : true;
            boolean specChangeMsg = ajaxResult.get("specChangeFlag") != null ? (Boolean)ajaxResult.get("specChangeFlag") : true;
            if (flag && !clinAgeFlag) {
               flag = false;
               ajaxResult = AjaxResult.error((String)ajaxResult.get("ageMsg"));
            }

            if (flag && !specChangeMsg) {
               flag = false;
               ajaxResult = AjaxResult.error((String)ajaxResult.get("specChangeMsg"));
            }
         }

         DocumentType documentType = null;
         if (flag) {
            documentType = this.documentTypeService.selectDocumentTypeById(((DrugAndClin)tdPaApplyForm.getItemList().get(0)).getDocumentTypeNo());
         }

         if (flag && documentType != null && documentType.getInputFormat().equals("0")) {
            String patientSaveOrderFlag = (String)this.redisCache.getCacheObject("patient_save_order:" + tdPaApplyForm.getPatientId());
            if (StringUtils.isNotBlank(patientSaveOrderFlag) && patientSaveOrderFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前患者有正在保存医嘱的操作，请稍后再保存");
            } else {
               this.redisCache.setCacheObject("patient_save_order:" + tdPaApplyForm.getPatientId(), "1");
            }
         }

         CdssResVo cdssDateRes = null;
         String cdssFlag = flag ? this.sysEmrConfigService.selectSysEmrConfigByKey("0041") : null;
         String cdssFactory = flag ? this.sysEmrConfigService.selectSysEmrConfigByKey("0039") : null;
         if (flag && StringUtils.isNotBlank(cdssFlag) && cdssFlag.equals("1")) {
            if (StringUtils.isNotBlank(cdssFactory) && cdssFactory.equals("XYT")) {
               if (StringUtils.isBlank(tdPaApplyForm.getCdssConfirmFlag())) {
                  cdssDateRes = this.cdssReqService.getCheckResult_mk(tdPaApplyForm.getPatientId(), tdPaApplyForm);
                  if (cdssDateRes != null) {
                     flag = false;
                     ajaxResult = AjaxResult.error("合理审查有返回内容，请确认后再保存");
                     ajaxResult.put("cdssDateRes", cdssDateRes);
                     this.redisCache.deleteObject("patient_save_order:" + tdPaApplyForm.getPatientId());
                  }

                  if (cdssDateRes != null && cdssDateRes.getCdssConfirmFlag()) {
                     flag = false;
                     ajaxResult = AjaxResult.error("合理审查有返回内容，请确认后再保存");
                     ajaxResult.put("cdssDateRes", cdssDateRes);
                  }
               } else if (StringUtils.isNotBlank(tdPaApplyForm.getCdssConfirmFlag()) && !tdPaApplyForm.getCdssConfirmFlag().equals("1")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("合理审查有返回内容，请确认后再保存");
               }
            } else if (StringUtils.isNotBlank(cdssFactory) && cdssFactory.equals("LB")) {
               flag = this.checkCDSSService.checkCDSSBySexApply(tdPaApplyForm.getPatientId(), tdPaApplyForm.getItemList());
               if (!flag) {
                  ajaxResult = AjaxResult.error("当前患者医嘱内容与性别不符！");
               }
            }
         }

         if (flag) {
            List<TdPaApplyFormVo> list = this.tdPaApplyFormService.saveApplyFormList(tdPaApplyForm, orderCommitVo);
            this.redisCache.deleteObject("patient_save_order:" + tdPaApplyForm.getPatientId());
            Map<String, Object> map = this.tdPaApplyFormService.getPrintInfoList(list);
            ajaxResult = AjaxResult.success((Object)map);
            List<String> orderNoList = (List)((List)list.stream().filter((s) -> s.getOrderNo() != null).collect(Collectors.toList())).stream().map((t) -> t.getOrderNo()).distinct().collect(Collectors.toList());
            List<String> applyFormNoList = (List)((List)list.stream().filter((s) -> s.getApplyFormNo() != null).collect(Collectors.toList())).stream().map((t) -> t.getApplyFormNo()).distinct().collect(Collectors.toList());
            ajaxResult.put("orderNoList", orderNoList);
            ajaxResult.put("applyFormNoList", applyFormNoList);
         } else {
            this.redisCache.deleteObject("patient_save_order:" + tdPaApplyForm.getPatientId());
         }
      } catch (Exception e) {
         this.log.error("新增检查检验申请单出现异常", e);
         this.redisCache.deleteObject("patient_save_order:" + tdPaApplyForm.getPatientId());
         ajaxResult = AjaxResult.error("新增检查检验申请单出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpm:form:add')")
   @Log(
      title = "检查检验申请单",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/submit"})
   public AjaxResult edit(@RequestBody OrderCommitVo orderCommitVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && orderCommitVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空，请重新操作");
         }

         if (orderCommitVo.getNoSubmit() != null && orderCommitVo.getNoSubmit()) {
            return ajaxResult;
         }

         if (flag && StringUtils.isBlank(orderCommitVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空，请重新操作");
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         if (StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
            if (flag && StringUtils.isBlank(orderCommitVo.getEncryptedInfo())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名加密后密文不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderCommitVo.getSignCertificate())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书不能为空，请重新操作");
            }

            if (flag && StringUtils.isBlank(orderCommitVo.getSignedText())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名明文不能为空，请重新操作");
            }

            if (flag && orderCommitVo.getSubmitTime() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("提交时间不能为空，请重新操作");
            }
         }

         Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoById(orderCommitVo.getPatientId()) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者记录，请重新操作");
         }

         List<String> orderNoList = flag ? orderCommitVo.getOrderNoList() : null;
         if (flag && orderNoList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
         }

         List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
         if (flag && (orderItemList == null || orderItemList != null && orderItemList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
         }

         if (flag) {
            List var10000 = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("-1")).collect(Collectors.toList());
         } else {
            Object var12 = null;
         }

         List<TdPaOrderItem> orderItemList2 = flag ? (List)orderItemList.stream().filter((t) -> t.getOrderStatus().equals("-1")).collect(Collectors.toList()) : null;
         if (flag && (orderItemList2 == null || orderItemList2 != null && orderItemList2.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
         }

         List<String> applyFormNoList = orderCommitVo.getApplyFormNoList();
         if (flag && (applyFormNoList == null || applyFormNoList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("没有待提交的申请单记录，请重新操作");
         }

         if (flag) {
            this.tdPaApplyFormService.submitTdPaApplyForm(orderCommitVo, orderItemList2, visitinfo);
         }
      } catch (Exception e) {
         this.log.error("提交检查检验申请单出现异常", e);
         ajaxResult = AjaxResult.error("提交检查检验申请单出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpm:form:remove')")
   @Log(
      title = "检查检验申请单",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{applyFormNos}"})
   public AjaxResult remove(@PathVariable String[] applyFormNos) {
      return this.toAjax(this.tdPaApplyFormService.deleteTdPaApplyFormByIds(applyFormNos));
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list')")
   @GetMapping({"/selectPatDia"})
   public AjaxResult selectPatDia(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(patientId)) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            TdPaApplyFormVo tdPaApplyFormVo = this.tdPaApplyFormService.selectSelectPatDia(patientId);
            ajaxResult = AjaxResult.success((Object)tdPaApplyFormVo);
         }
      } catch (Exception e) {
         this.log.error("查询患者诊断、主诉、病历摘要出现异常", e);
         ajaxResult = AjaxResult.error("查询患者诊断、主诉、病历摘要出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list')")
   @GetMapping({"/newestCheck"})
   public AjaxResult newestCheck(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(patientId)) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            TdPaApplyFormVo tdPaApplyFormVo = this.tdPaApplyFormService.selectNewestCheck(patientId);
            ajaxResult = AjaxResult.success((Object)tdPaApplyFormVo);
         }
      } catch (Exception e) {
         this.log.error("查询最新一条检查检验信息出现异常", e);
         ajaxResult = AjaxResult.error("查询最新一条检查检验信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list,pat:report:resultQuery,emr:home:list,qc:flow:term,tmpm:form:testExamlist,pat:info:emrAllList,pat:examItem:result')")
   @GetMapping({"/flowChart"})
   public AjaxResult operFlowChart(TdPaApplyForm tdPaApplyForm) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tdPaApplyForm == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyForm.getApplyFormNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单编号不能为空");
         }

         if (flag) {
            List<Map<String, Object>> result = this.tdPaApplyFormService.selectCheckFlowChart(tdPaApplyForm.getApplyFormNo());
            ajaxResult = AjaxResult.success((Object)result);
         }
      } catch (Exception e) {
         this.log.error("查询检查检验流程图出现异常", e);
         ajaxResult = AjaxResult.error("查询检查检验流程图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpm:form:testExamlist')")
   @GetMapping({"/testExamListInfo"})
   public AjaxResult testExamListInfo() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         Boolean isQCDept = this.commonService.isQCDept();
         ajaxResult.put("isQCDept", isQCDept);
      } catch (Exception e) {
         this.log.error("查询检查检验流程图出现异常", e);
         ajaxResult = AjaxResult.error("查询检查检验流程图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpm:form:testExamlist')")
   @GetMapping({"/testExamList"})
   public TableDataInfo testExamList(TdPaApplyFormVo tdPaApplyFormVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isNotBlank(tdPaApplyFormVo.getApplyTimeEnd())) {
            Date d1 = DateUtils.parseDate(tdPaApplyFormVo.getApplyTimeEnd(), new String[]{DateUtils.YYYY_MM_DD});
            String applyTimeEnd = DateUtils.formatYMD(DateUtils.addDays(d1, 1));
            tdPaApplyFormVo.setApplyTimeEnd(applyTimeEnd);
         }

         if (StringUtils.isNotBlank(tdPaApplyFormVo.getReportDateEnd())) {
            Date d1 = DateUtils.parseDate(tdPaApplyFormVo.getReportDateEnd(), new String[]{DateUtils.YYYY_MM_DD});
            String reportDateEnd = DateUtils.formatYMD(DateUtils.addDays(d1, 1));
            tdPaApplyFormVo.setReportDateEnd(reportDateEnd);
         }

         this.startPage();
         List<TdPaApplyFormVo> list = this.tdPaApplyFormService.testExamList(tdPaApplyFormVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询检查检验列表出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "查询检查检验列表出现异常");
      }

      return tableDataInfo;
   }
}
