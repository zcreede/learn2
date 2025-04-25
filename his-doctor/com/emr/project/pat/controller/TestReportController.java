package com.emr.project.pat.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.CDSS.xyt.RequestUtil;
import com.emr.project.common.domain.vo.ReplaceUrlParamVo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.his.service.IHisSyncService;
import com.emr.project.mzInfo.service.IOutpatientInfoService;
import com.emr.project.pat.domain.TestReport;
import com.emr.project.pat.domain.TestResult;
import com.emr.project.pat.domain.resp.ApplyFormPrintResp;
import com.emr.project.pat.domain.vo.TestReportKbVo;
import com.emr.project.pat.domain.vo.TestReportReq;
import com.emr.project.pat.domain.vo.TestReportVo;
import com.emr.project.pat.service.ITestReportService;
import com.emr.project.pat.service.ITestResultService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpm.domain.DocumentType;
import com.emr.project.tmpm.service.IDocumentTypeService;
import com.emr.project.webservice.service.TestReportWebService;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/pat/report"})
public class TestReportController extends BaseController {
   @Autowired
   private ITestReportService testReportService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private ITestResultService testResultService;
   @Autowired
   private IOutpatientInfoService outpatientInfoService;
   @Autowired
   private IHisSyncService hisSyncService;
   @Autowired
   private IDocumentTypeService documentTypeService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private TestReportWebService testReportWebService;
   @Autowired
   private ITdPaApplyFormService tdPaApplyFormService;

   @PreAuthorize("@ss.hasAnyPermi('pat:report:list,emr:index:helper,td:apply:add')")
   @GetMapping({"/list"})
   public TableDataInfo list(TestReportVo testReportVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isEmpty(testReportVo.getVisitType())) {
            return new TableDataInfo(500, "就诊类型不能为空");
         }

         if (StringUtils.isEmpty(testReportVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者就诊id不能为空");
         } else {
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (orderFlag.equals("1")) {
               SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisTestReport.toString());
               SqlVo sqlVo = new SqlVo();
               sqlVo.setSqlStr(syncDatasource.getQuerySql());
               sqlVo.setInpNo(testReportVo.getPatientId());
               if (testReportVo.getStartDate() != null && testReportVo.getEndDate() != null) {
                  sqlVo.setBeginDateTime(testReportVo.getStartDate());
                  sqlVo.setEndDateTime(testReportVo.getEndDate());
               }

               this.startPage();
               List<TestReport> list = this.testReportService.selectHisReportList(sqlVo);
               tableDataInfo = this.getDataTable(list);
            } else {
               String type = testReportVo.getVisitType();
               if (type.equals("2")) {
                  this.startPage();
                  List<TestReport> list = this.testReportService.selectTestReportList(testReportVo);
                  tableDataInfo = this.getDataTable(list);
               } else {
                  this.startPage();
                  List<TestReport> list = this.testReportService.selectMzTestReportList(testReportVo);
                  tableDataInfo = this.getDataTable(list);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("查询检验报告列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询检验报告列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:report:resultQuery,qc:flow:term,pat:info:emrAllList,qc:statis:checkCase')")
   @GetMapping({"/query"})
   public AjaxResult query(TestReportReq req) {
      try {
         if (null != req && req.getPatientId() != null) {
            String type = req.getType();
            AjaxResult ajaxResult;
            if (!StringUtils.isEmpty(type) && !"zy".equals(type)) {
               DocumentType documentType = new DocumentType();
               List<DocumentType> documentTypes = this.documentTypeService.selectDocumentTypeList(documentType);
               Map<String, List<DocumentType>> documentMap = (Map)documentTypes.stream().collect(Collectors.groupingBy(DocumentType::getDocumentTypeCd));
               SysUser user = SecurityUtils.getLoginUser().getUser();
               List<TestReportVo> list = this.outpatientInfoService.selectTestReportList(req.getPatientId()[0]);

               for(TestReportVo testReportVo : list) {
                  if ("报告完成".equals(testReportVo.getItemState())) {
                     String docType = testReportVo.getDocType();
                     if (documentMap.containsKey(docType)) {
                        List<DocumentType> types = (List)documentMap.get(docType);
                        DocumentType document = (DocumentType)types.get(0);
                        if (StringUtils.isNotEmpty(document.getReportUrl())) {
                           ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(testReportVo.getInpNo(), testReportVo.getInpNo(), testReportVo.getCaseNo(), "1", user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), testReportVo.getAppCd(), testReportVo.getId(), (String)null, (String)null);
                           String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, document.getReportUrl());
                           testReportVo.setReportUrl(reportUrl);
                        }
                     }
                  }
               }

               ajaxResult = AjaxResult.success((Object)list);
            } else {
               String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
               SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisTestReport.toString());
               if (orderFlag.equals("1")) {
                  SqlVo sqlVo = new SqlVo();
                  sqlVo.setSqlStr(syncDatasource.getQuerySql());
                  sqlVo.setInpNo(req.getPatientId()[0]);
                  List<TestReport> list = this.testReportService.selectHisReportList(sqlVo);
                  ajaxResult = AjaxResult.success((Object)list);
               } else {
                  List<TestReportVo> list = this.testReportService.selectAppReportItemList(req.getPatientId());
                  ajaxResult = AjaxResult.success((Object)list);
               }
            }

            return ajaxResult;
         } else {
            return AjaxResult.error("患者就诊id不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询检验申请列表出现异常", e);
         AjaxResult ajaxResult = AjaxResult.error("查询检验申请列表出现异常");
         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:report:resultQuery,emr:home:list,qc:flow:term,tmpm:form:testExamlist,pat:info:emrAllList')")
   @GetMapping({"/resultList"})
   public AjaxResult resultList(TestReportVo testReportVo) throws Exception {
      new ArrayList();

      AjaxResult ajaxResult;
      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         String type = testReportVo.getType();
         DocumentType documentTypeReq = new DocumentType();
         List<DocumentType> documentTypeList = this.documentTypeService.selectDocumentTypeList(documentTypeReq);
         if (!StringUtils.isEmpty(type) && !"zy".equals(type)) {
            List var20 = this.outpatientInfoService.selectTestReportResultListByAppCd(testReportVo.getAppCd());
            if (!var20.isEmpty()) {
               for(TestReportVo vo : var20) {
                  List<DocumentType> documentTypeLists = (List)documentTypeList.stream().filter((t) -> t.getDocumentTypeCd().equals(vo.getDocumentTypeCd())).collect(Collectors.toList());
                  if (CollectionUtils.isNotEmpty(documentTypeLists) && documentTypeLists.size() > 0) {
                     DocumentType documentType = (DocumentType)documentTypeLists.get(0);
                     ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(vo.getInpNo(), vo.getInpNo(), vo.getCaseNo(), "1", user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), vo.getAppCd(), vo.getId(), (String)null, (String)null);
                     String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, documentType.getReportUrl());
                     vo.setReportUrl(reportUrl);
                     vo.setBrowserType(documentType.getBrowserType());
                     vo.setBrowserTypeName(documentType.getBrowserTypeName());
                  }

                  String appDeptCd = vo.getAppDeptCd();
                  if (StringUtils.isNotEmpty(appDeptCd)) {
                     Map<String, Object> deptMap = this.hisSyncService.selectDeptByCode(appDeptCd);
                     if (deptMap != null && deptMap.get("dept_name") != null) {
                        vo.setAppDeptName(deptMap.get("dept_name").toString());
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
            }

            ajaxResult = AjaxResult.success((Object)var20);
         } else {
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (orderFlag.equals("1")) {
               SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisTestReport.toString());
               SqlVo sqlVo = new SqlVo();
               sqlVo.setSqlStr(syncDatasource.getQuerySql());
               sqlVo.setInpNo(testReportVo.getPatientId());
               List<TestReport> list = this.testReportService.selectHisReportList(sqlVo);
               List resultList = this.testReportService.selectHisReport(testReportVo.getAppCd(), list);
               if (null != resultList) {
                  syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisTestResult.toString());

                  for(TestReportVo vo : resultList) {
                     List<DocumentType> documentTypeLists = (List)documentTypeList.stream().filter((t) -> t.getDocumentTypeCd().equals(vo.getDocumentTypeCd())).collect(Collectors.toList());
                     if (CollectionUtils.isNotEmpty(documentTypeLists) && documentTypeLists.size() > 0) {
                        DocumentType documentType = (DocumentType)documentTypeLists.get(0);
                        ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(vo.getInpNo(), vo.getInpNo(), vo.getCaseNo(), "1", user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), vo.getAppCd(), vo.getId(), (String)null, (String)null);
                        String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, documentType.getReportUrl());
                        vo.setReportUrl(reportUrl);
                        vo.setBrowserType(documentType.getBrowserType());
                        vo.setBrowserTypeName(documentType.getBrowserTypeName());
                     }

                     sqlVo = new SqlVo();
                     sqlVo.setSqlStr(syncDatasource.getQuerySql());
                     sqlVo.setReportId(vo.getId());
                     List<TestResult> list1 = this.testResultService.selectHisResultList(sqlVo);
                     if (null != list1 && list1.size() > 0) {
                        vo.setReasultList(list1);
                     }
                  }
               }

               ajaxResult = AjaxResult.success((Object)resultList);
            } else {
               if (StringUtils.isEmpty(testReportVo.getAppCd())) {
                  return AjaxResult.error("申请单编号参数不能为空");
               }

               List var19 = this.testReportService.selectTestresultList(testReportVo);
               if (CollectionUtils.isNotEmpty(var19) && var19.size() > 0) {
                  for(TestReportVo vo : var19) {
                     ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(vo.getInpNo(), vo.getInpNo(), vo.getCaseNo(), vo.getHospitalizedCount(), user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), vo.getAppCd(), vo.getId(), (String)null, (String)null);
                     String reportUrl = this.commonService.replaceUrlParam(replaceUrlParamVo, vo.getReportUrl());
                     vo.setReportUrl(reportUrl);
                     vo.setBrowserType(vo.getBrowserType());
                     vo.setBrowserTypeName(vo.getBrowserTypeName());
                  }
               }

               ajaxResult = AjaxResult.success((Object)var19);
               ApplyFormPrintResp applyFormPrint = this.tdPaApplyFormService.queryApplyFormPrintResp(testReportVo.getAppCd(), user);
               ajaxResult.put("applyFormPrint", applyFormPrint);
            }
         }
      } catch (Exception e) {
         this.log.error("查询检验报告结果信息出现异常", e);
         ajaxResult = AjaxResult.error("查询检验报告结果信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:report:resultQuery,emr:home:list,qc:flow:term,tmpm:form:testExamlist,pat:info:emrAllList,lisReport:inspectionReport:list')")
   @GetMapping({"/resultKnowledgeBase"})
   public AjaxResult resultKnowledgeBase(String rptItemId) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         TestReportKbVo testReportKbVo = this.testReportWebService.resultKnowledgeBase(rptItemId);
         if (testReportKbVo != null && "0".equals(testReportKbVo.getCode())) {
            ajaxResult = AjaxResult.success((Object)testReportKbVo);
         } else {
            ajaxResult = AjaxResult.error(testReportKbVo.getErrorMessage());
         }
      } catch (Exception e) {
         this.log.error("查询检验知识库出现异常", e);
         ajaxResult = AjaxResult.error("查询检验知识库出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:report:getInfo')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") String id) {
      return AjaxResult.success((Object)this.testReportService.selectTestReportById(id));
   }

   @PreAuthorize("@ss.hasPermi('pat:report:add')")
   @Log(
      title = "检验报告",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TestReport testReport) {
      return this.toAjax(this.testReportService.insertTestReport(testReport));
   }

   @PreAuthorize("@ss.hasPermi('pat:report:edit')")
   @Log(
      title = "检验报告",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TestReport testReport) {
      return this.toAjax(this.testReportService.updateTestReport(testReport));
   }

   @PreAuthorize("@ss.hasPermi('pat:report:remove')")
   @Log(
      title = "检验报告",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable String[] ids) {
      return this.toAjax(this.testReportService.deleteTestReportByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:report:resultQuery,qc:flow:term')")
   @GetMapping({"/analysisList/{patientIds}"})
   public AjaxResult analysisList(@PathVariable("patientIds") String[] patientIds) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         List<TestReportVo> list = this.testReportService.selectAnalysisList(patientIds);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询趋势分析列表出现异常", e);
         ajaxResult = AjaxResult.error("查询趋势分析列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:report:list,docOrder:check:list,emr:home:list')")
   @PutMapping({"/receReport"})
   public AjaxResult receReport(@RequestBody TestReport testReport) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");

      try {
         if (StringUtils.isEmpty(testReport.getAppCd())) {
            ajaxResult = AjaxResult.error("申请单编号不能为空");
         } else {
            this.testReportService.updateTestReportByAppCd(testReport);
         }
      } catch (Exception e) {
         this.log.error("修改检验报告接收状态出现异常", e);
         ajaxResult = AjaxResult.error("修改检验报告接收状态出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:examItem:result,pat:report:resultQuery')")
   @GetMapping({"/cdssInfo"})
   public AjaxResult getCdssInfo() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         Map<String, Object> result = new HashMap();
         String cdssFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0041");
         result.put("cdssFlag", cdssFlag);
         String cdssManufactor = this.sysEmrConfigService.selectSysEmrConfigByKey("0039");
         result.put("cdssManufactor", cdssManufactor);
         if (StringUtils.isNotBlank(cdssFlag) && cdssFlag.equals("1") && cdssManufactor.equals("XYT")) {
            String cdssBaseUrl = this.sysEmrConfigService.selectSysEmrConfigByKey("003901");
            String cdssBhtKey = this.sysEmrConfigService.selectSysEmrConfigByKey("003902");
            Map<String, String> yxtHeaderMap = RequestUtil.getHeaderMap(cdssBhtKey, user.getUserName(), user.getNickName(), user.getDept().getDeptName());
            result.put("cdssBaseUrl", cdssBaseUrl);
            result.put("cdssBhtKey", cdssBhtKey);
            result.put("DOCTOR_ID", yxtHeaderMap.get("DOCTOR_ID"));
            result.put("DOCTOR_NAME", yxtHeaderMap.get("DOCTOR_NAME"));
            result.put("DOCTOR_DEPT", yxtHeaderMap.get("DOCTOR_DEPT"));
         }

         ajaxResult = AjaxResult.success((Object)result);
      } catch (Exception e) {
         this.log.error("查询CDSS配置信息出现异常，", e);
         ajaxResult = AjaxResult.error("查询CDSS配置信息出现异常，请联系管理员！");
      }

      return ajaxResult;
   }
}
