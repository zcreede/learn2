package com.emr.project.webservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.borrowing.domain.EmrIndexBorrow;
import com.emr.project.borrowing.service.IEmrIndexBorrowService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.webservice.domain.EmrProjectMessage;
import com.emr.project.webservice.domain.req.MrhpBackReq;
import com.emr.project.webservice.enums.RpcRmrExceptionEnum;
import com.emr.project.webservice.service.IEmrProjectMessageService;
import com.emr.project.webservice.utils.SignUtil;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/rpc/mrhp"})
public class MrisMrHpController extends BaseController {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private IEmrProjectMessageService emrProjectMessageService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private IEmrIndexBorrowService emrIndexBorrowService;

   @PostMapping({"/back"})
   public AjaxResult back(@RequestBody MrhpBackReq req) {
      this.log.warn("病案系统退回病案首页病历质控状态已归档自动创建已通过的借阅申请72小时有效接口入参：" + JSONObject.toJSONString(req));
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String projectCode = req.getProjectCode();
         if (StringUtils.isEmpty(projectCode)) {
            return AjaxResult.error(RpcRmrExceptionEnum.PROJECT_CODE_ERROR.getCode(), RpcRmrExceptionEnum.PROJECT_CODE_ERROR.getMessage());
         }

         EmrProjectMessage message = this.emrProjectMessageService.selectEmrProjectMessageById(projectCode);
         if (message == null) {
            return AjaxResult.error(RpcRmrExceptionEnum.PROJECT_CODE_EXISTENT.getCode(), RpcRmrExceptionEnum.PROJECT_CODE_EXISTENT.getMessage());
         }

         String appKey = message.getProjectKey();
         this.log.warn("病案系统退回病案首页接口入参：：" + JSONObject.toJSONString(req));
         String signString = SignUtil.encryptString((HashMap)JSONObject.parseObject(JSONObject.toJSONString(req), HashMap.class), appKey);
         this.log.warn("病案系统退回病案首页接口入参加密：：" + signString);
         if (StringUtils.isEmpty(signString)) {
            return AjaxResult.error(RpcRmrExceptionEnum.SIGN_NULL.getCode(), RpcRmrExceptionEnum.SIGN_NULL.getMessage());
         }

         String sign = req.getSign();
         if (StringUtils.isEmpty(sign)) {
            return AjaxResult.error(RpcRmrExceptionEnum.SIGN_NULL.getCode(), RpcRmrExceptionEnum.SIGN_NULL.getMessage());
         }

         if (!signString.equals(sign)) {
            return AjaxResult.error(RpcRmrExceptionEnum.SIGN_ERROR.getCode(), RpcRmrExceptionEnum.SIGN_ERROR.getMessage());
         }

         String admissionNo = req.getAdmissionNo();
         if (StringUtils.isBlank(admissionNo)) {
            return AjaxResult.error(RpcRmrExceptionEnum.PARAMETER_NULL.getCode(), RpcRmrExceptionEnum.PARAMETER_NULL.getMessage());
         }

         Medicalinformation m = this.medicalinfoService.selectMedicalinfoByPatientId(admissionNo);
         if (m == null) {
            return AjaxResult.error(RpcRmrExceptionEnum.PARAMETER_INVALID.getCode(), RpcRmrExceptionEnum.PARAMETER_INVALID.getMessage());
         }

         EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(m.getHospitalCode(), m.getAdmissionNo());
         if (emrQcFlow != null && (emrQcFlow.getMrState().equals("16") || emrQcFlow.getMrState().equals("14"))) {
            EmrIndexBorrow emrIndexBorrowReq = new EmrIndexBorrow();
            List<String> admissionNoList = Arrays.asList(m.getAdmissionNo());
            emrIndexBorrowReq.setAdmissionNoList(admissionNoList);
            List<EmrIndexBorrow> emrIndexBorrowList = this.emrIndexBorrowService.selectEmrIndexBorrowValidList(emrIndexBorrowReq);
            List<EmrIndexBorrow> emrIndexBorrowAppDocList = (List)emrIndexBorrowList.stream().filter((t) -> m.getResidentCode().equals(t.getAppDocCd())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(emrIndexBorrowAppDocList) && emrIndexBorrowAppDocList.size() > 0) {
               List<EmrIndexBorrow> brrowAPpDocList = (List)emrIndexBorrowList.stream().filter((t) -> "0".equals(t.getAppStatus())).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(brrowAPpDocList) && brrowAPpDocList.size() > 0) {
                  Date dateCurr = this.commonService.getDbSysdate();
                  EmrIndexBorrow emrIndexBorrow = new EmrIndexBorrow();
                  emrIndexBorrow.setId(((EmrIndexBorrow)brrowAPpDocList.get(0)).getId());
                  emrIndexBorrow.setAppStatus("1");
                  emrIndexBorrow.setAltPerCode("system");
                  emrIndexBorrow.setAltPerName("系统管理员");
                  emrIndexBorrow.setAltDate(dateCurr);
                  this.emrIndexBorrowService.updateEmrIndexBorrow(emrIndexBorrow);
               }
            } else {
               Date dateCurr = this.commonService.getDbSysdate();
               List<String> deptCodeList = Arrays.asList(m.getDepartmentNo(), m.getDischargeDepartmentNo());
               List<SysDept> deptList = this.sysDeptService.selectListByDeptCodeList(m.getHospitalCode(), deptCodeList);
               Map<String, List<SysDept>> deptMap = (Map<String, List<SysDept>>)(CollectionUtils.isNotEmpty(deptList) ? (Map)deptList.stream().collect(Collectors.groupingBy((t) -> t.getDeptCode())) : new HashMap(1));
               Date borrowEndTime = DateUtils.addDays(dateCurr, 3);
               EmrIndexBorrow emrIndexBorrow = new EmrIndexBorrow();
               emrIndexBorrow.setId(SnowIdUtils.uniqueLong());
               emrIndexBorrow.setAppDocCd(m.getResidentCode());
               emrIndexBorrow.setAppDocName(m.getResidentName());
               emrIndexBorrow.setAppDeptCd(m.getDepartmentNo());
               String appDeptName = CollectionUtils.isNotEmpty((Collection)deptMap.get(m.getDepartmentNo())) ? ((SysDept)((List)deptMap.get(m.getDepartmentNo())).get(0)).getDeptName() : null;
               emrIndexBorrow.setAppDeptName(appDeptName);
               emrIndexBorrow.setAppTime(dateCurr);
               emrIndexBorrow.setBorrowPurpose("病案首页缺陷修改");
               emrIndexBorrow.setBorrowPeriod(3L);
               emrIndexBorrow.setBorrowPeriodUnit("D");
               emrIndexBorrow.setBorrowEndTime(borrowEndTime);
               emrIndexBorrow.setCaseNo(m.getCaseNo());
               emrIndexBorrow.setAdmissionNo(m.getAdmissionNo());
               emrIndexBorrow.setHospitalizedCount((long)m.getHospitalizedCount());
               emrIndexBorrow.setPatientName(m.getName());
               emrIndexBorrow.setConTime(dateCurr);
               emrIndexBorrow.setConDocCd("system");
               emrIndexBorrow.setConDocName("系统管理员");
               emrIndexBorrow.setConRemark("病案系统退回");
               emrIndexBorrow.setAppStatus("1");
               emrIndexBorrow.setCrePerCode("system");
               emrIndexBorrow.setCrePerName("系统管理员");
               emrIndexBorrow.setCreDate(dateCurr);
               emrIndexBorrow.setDischargeDepartmentNo(m.getDischargeDepartmentNo());
               String dischargeDepartmentName = CollectionUtils.isNotEmpty((Collection)deptMap.get(m.getDischargeDepartmentNo())) ? ((SysDept)((List)deptMap.get(m.getDischargeDepartmentNo())).get(0)).getDeptName() : null;
               emrIndexBorrow.setDischargeDepartmentName(dischargeDepartmentName);
               emrIndexBorrow.setLeaveHospitalDate(m.getLeaveHospitalDate());
               emrIndexBorrow.setResidentCode(m.getResidentCode());
               emrIndexBorrow.setResidentNo(m.getResidentNo());
               emrIndexBorrow.setResidentName(m.getResidentName());
               this.emrIndexBorrowService.insertEmrIndexBorrow(emrIndexBorrow);
            }
         }
      } catch (Exception e) {
         this.log.error("病案系统退回病案首页接口出现异常：", e);
         ajaxResult = AjaxResult.error("病案系统退回病案首页接口出现异常，请联系管理员！");
      }

      return ajaxResult;
   }
}
