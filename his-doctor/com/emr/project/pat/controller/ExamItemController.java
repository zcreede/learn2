package com.emr.project.pat.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.domain.vo.ReplaceUrlParamVo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.his.service.IHisSyncService;
import com.emr.project.mzInfo.service.IOutpatientInfoService;
import com.emr.project.pat.domain.ExamItem;
import com.emr.project.pat.domain.resp.ApplyFormPrintResp;
import com.emr.project.pat.domain.vo.ExamAlertResultVo;
import com.emr.project.pat.domain.vo.ExamItemReq;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestReportReq;
import com.emr.project.pat.service.IExamItemService;
import com.emr.project.pat.service.ITestExamAlertResultService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpm.domain.DocumentType;
import com.emr.project.tmpm.service.IDocumentTypeService;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/pat/examItem"})
public class ExamItemController extends BaseController {
   @Autowired
   private IExamItemService examItemService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private IOutpatientInfoService outpatientInfoService;
   @Autowired
   private IDocumentTypeService documentTypeService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IHisSyncService hisSyncService;
   @Autowired
   private ITestExamAlertResultService testExamAlertResultService;
   @Autowired
   private ITdPaApplyFormService tdPaApplyFormService;

   @PreAuthorize("@ss.hasAnyPermi('pat:examItem:list,emr:index:helper')")
   @GetMapping({"/list"})
   public TableDataInfo list(ExamItemVo examItemVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         Date startDate = examItemVo.getStartDate();
         Date endDate = examItemVo.getEndDate();
         if (startDate != null && endDate != null) {
            if (startDate.equals(endDate)) {
               Calendar calendar = new GregorianCalendar();
               calendar.setTime(endDate);
               calendar.add(5, 1);
               endDate = calendar.getTime();
            }

            examItemVo.setStartDate(startDate);
            examItemVo.setEndDate(endDate);
         }

         if (StringUtils.isEmpty(examItemVo.getVisitType())) {
            return new TableDataInfo(500, "就诊类型不能为空");
         }

         if (StringUtils.isEmpty(examItemVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者就诊id不能为空");
         } else {
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (orderFlag.equals("1")) {
               SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisExamItem.toString());
               SqlVo sqlVo = new SqlVo();
               sqlVo.setSqlStr(syncDatasource.getQuerySql());
               sqlVo.setInpNo(examItemVo.getPatientId());
               if (startDate != null && endDate != null) {
                  sqlVo.setBeginDateTime(startDate);
                  sqlVo.setEndDateTime(endDate);
               }

               this.startPage();
               List<ExamItemVo> list = this.examItemService.selectHisExamItemList(sqlVo);
               tableDataInfo = this.getDataTable(list);
            } else {
               String type = examItemVo.getVisitType();
               if (type.equals("2")) {
                  this.startPage();
                  List<ExamItemVo> list = this.examItemService.selectExamItemList(examItemVo);
                  tableDataInfo = this.getDataTable(list);
               } else {
                  this.startPage();
                  List<ExamItemVo> list = this.examItemService.selectMzExamItemList(examItemVo);
                  tableDataInfo = this.getDataTable(list);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("查询检查信息分页列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询检查信息分页列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:examItem:result,qc:flow:term,pat:info:emrAllList,qc:statis:checkCase')")
   @GetMapping({"/query"})
   public AjaxResult query(TestReportReq req) {
      try {
         if (req != null && req.getPatientId() != null) {
            String type = req.getType();
            if (!StringUtils.isEmpty(type) && !"zy".equals(type)) {
               DocumentType documentType = new DocumentType();
               List<DocumentType> documentTypes = this.documentTypeService.selectDocumentTypeList(documentType);
               Map<String, List<DocumentType>> documentMap = (Map)documentTypes.stream().collect(Collectors.groupingBy(DocumentType::getDocumentTypeCd));
               SysUser user = SecurityUtils.getLoginUser().getUser();
               List<ExamItemVo> list = this.outpatientInfoService.selectItemList(req.getPatientId()[0]);

               for(ExamItemVo examItemVo : list) {
                  if ("报告完成".equals(examItemVo.getItemState())) {
                     String docType = examItemVo.getDocType();
                     if (documentMap.containsKey(docType)) {
                        List<DocumentType> types = (List)documentMap.get(docType);
                        DocumentType document = (DocumentType)types.get(0);
                        if (StringUtils.isNotEmpty(document.getReportUrl())) {
                           ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(examItemVo.getInpNo(), examItemVo.getInpNo(), examItemVo.getCaseNo(), "1", user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), examItemVo.getApplyFormNo(), examItemVo.getId(), (String)null, (String)null);
                           String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, document.getReportUrl());
                           examItemVo.setReportUrl(reportUrl);
                        }
                     }
                  }
               }

               return AjaxResult.success((Object)list);
            } else {
               String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
               SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisExamItem.toString());
               if (orderFlag.equals("1")) {
                  SqlVo sqlVo = new SqlVo();
                  sqlVo.setSqlStr(syncDatasource.getQuerySql());
                  sqlVo.setInpNo(req.getPatientId()[0]);
                  List<ExamItemVo> list = this.examItemService.selectHisExamItemList(sqlVo);
                  return AjaxResult.success((Object)list);
               } else {
                  List<ExamItemVo> list = this.examItemService.selectItemList(req.getPatientId());
                  return AjaxResult.success((Object)list);
               }
            }
         } else {
            return AjaxResult.error("患者就诊id不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询检查申请列表出现异常", e);
         return AjaxResult.error("查询检查申请列表出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:examItem:result,emr:home:list,qc:flow:term,tmpm:form:testExamlist,pat:info:emrAllList')")
   @GetMapping({"/result"})
   public AjaxResult result(ExamItemReq req) {
      try {
         if (req != null && !StringUtils.isEmpty(req.getId())) {
            String type = req.getType();
            if (!StringUtils.isEmpty(type) && !"zy".equals(type)) {
               List<ExamItemVo> examItemVo = this.outpatientInfoService.selectExamItemResultList(req.getId());

               for(ExamItemVo vo : examItemVo) {
                  String appDeptCd = vo.getAppDeptCd();
                  if (StringUtils.isNotEmpty(appDeptCd)) {
                     Map<String, Object> deptMap = this.hisSyncService.selectDeptByCode(appDeptCd);
                     if (deptMap != null && deptMap.get("dept_name") != null) {
                        vo.setDeptName(deptMap.get("dept_name").toString());
                     }
                  }

                  String appDocCd = vo.getAppDocCd();
                  if (StringUtils.isNotEmpty(appDocCd)) {
                     Map<String, Object> staffMap = this.hisSyncService.selectStaffByCode(appDocCd);
                     if (staffMap != null && staffMap.get("xm") != null) {
                        vo.setAppDocName(staffMap.get("xm").toString());
                     }
                  }
               }

               return AjaxResult.success((Object)examItemVo);
            } else {
               String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
               SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisExamItem.toString());
               if (orderFlag.equals("1")) {
                  SqlVo sqlVo = new SqlVo();
                  sqlVo.setSqlStr(syncDatasource.getQuerySql());
                  sqlVo.setId(req.getId());
                  List<ExamItemVo> list = this.examItemService.selectHisExamItemList(sqlVo);
                  List<ExamItemVo> examItemVo = this.examItemService.selectHisExamItemResult(list);
                  return AjaxResult.success((Object)examItemVo);
               } else {
                  SysUser user = SecurityUtils.getLoginUser().getUser();
                  List<ExamItemVo> examItemVo = this.examItemService.selectExamItemResultList(req.getId());
                  ApplyFormPrintResp applyFormPrint = this.tdPaApplyFormService.queryApplyFormPrintResp(req.getId(), user);
                  AjaxResult ajaxResult = AjaxResult.success((Object)examItemVo);
                  ajaxResult.put("applyFormPrint", applyFormPrint);
                  return ajaxResult;
               }
            }
         } else {
            return AjaxResult.error("检查id不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询检查报告结果单出现异常", e);
         return AjaxResult.error("查询检查报告结果单出现异常");
      }
   }

   @PreAuthorize("@ss.hasPermi('emr:home:list')")
   @GetMapping({"/unLookList"})
   public AjaxResult unLookList(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<ExamItemVo> examItemVo = this.examItemService.selectUnLookList(patientId);
         ajaxResult = AjaxResult.success((Object)examItemVo);
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询首页患者未查看检查检验报告出现异常");
         this.log.error("查询首页患者未查看检查检验报告出现异常", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:home:list')")
   @PutMapping({"/updateLookState"})
   public AjaxResult updateLookState(@RequestBody ExamItem examItem) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (examItem == null) {
            ajaxResult = AjaxResult.error("报告单id不能为空");
         } else {
            this.examItemService.updateLookState(examItem);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("修改检查报告单出现异常");
         this.log.error("修改检查报告单出现异常", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:examItem:list,emr:index:helper')")
   @GetMapping({"/alertList"})
   public TableDataInfo alertList(ExamItemVo examItemVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         Date startDate = examItemVo.getStartDate();
         Date endDate = examItemVo.getEndDate();
         if (startDate != null && endDate != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(endDate);
            calendar.add(5, 1);
            endDate = calendar.getTime();
            examItemVo.setStartDate(startDate);
            examItemVo.setEndDate(endDate);
         }

         if (StringUtils.isEmpty(examItemVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者就诊id不能为空");
         } else {
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            this.startPage();
            if (orderFlag.equals("1")) {
               List<ExamAlertResultVo> list = this.examItemService.selectHisExamAlertList(examItemVo);
               if (!list.isEmpty()) {
                  for(ExamAlertResultVo vo : list) {
                     String handleDocName = vo.getHandleDocName();
                     if (StringUtils.isNotEmpty(handleDocName)) {
                        Map<String, Object> staffMap = this.hisSyncService.selectStaffByCode(handleDocName);
                        if (staffMap != null && staffMap.get("xm") != null) {
                           vo.setHandleDocName(staffMap.get("xm").toString());
                        }
                     }
                  }
               }

               tableDataInfo = this.getDataTable(list);
            } else {
               List<ExamAlertResultVo> list = this.testExamAlertResultService.selectExamAlertList(examItemVo);
               tableDataInfo = this.getDataTable(list);
            }
         }
      } catch (Exception e) {
         tableDataInfo = new TableDataInfo(500, "查询检查报告危急值列表出现异常，请联系管理员");
         this.log.error("查询检查报告危急值列表出现异常", e);
      }

      return tableDataInfo;
   }
}
