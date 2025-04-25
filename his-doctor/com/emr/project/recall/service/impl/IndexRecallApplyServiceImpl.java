package com.emr.project.recall.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.borrowing.domain.req.BorrowReviewedListReq;
import com.emr.project.borrowing.domain.req.CheckMedicalRecordReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeListReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeReq;
import com.emr.project.common.service.ICommonService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.mapper.EmrQcFlowMapper;
import com.emr.project.recall.domain.IndexRecallApply;
import com.emr.project.recall.mapper.IndexRecallApplyMapper;
import com.emr.project.recall.service.IIndexRecallApplyService;
import com.emr.project.revoke.domain.EmrIndexRevokeLog;
import com.emr.project.revoke.service.IEmrIndexRevokeLogService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndexRecallApplyServiceImpl implements IIndexRecallApplyService {
   @Autowired
   private IndexRecallApplyMapper indexRecallApplyMapper;
   @Autowired
   private EmrQcFlowMapper emrQcFlowMapper;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private IEmrIndexRevokeLogService emrIndexRevokeLogService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public IndexRecallApply selectIndexRecallApplyById(Long id) {
      return this.indexRecallApplyMapper.selectIndexRecallApplyById(id);
   }

   public List selectIndexRecallApplyList(IndexRecallApply indexRecallApply) {
      return this.indexRecallApplyMapper.selectIndexRecallApplyList(indexRecallApply);
   }

   public int insertIndexRecallApply(IndexRecallApply indexRecallApply) {
      return this.indexRecallApplyMapper.insertIndexRecallApply(indexRecallApply);
   }

   public int updateIndexRecallApply(IndexRecallApply indexRecallApply) {
      return this.indexRecallApplyMapper.updateIndexRecallApply(indexRecallApply);
   }

   public int deleteIndexRecallApplyByIds(Long[] ids) {
      return this.indexRecallApplyMapper.deleteIndexRecallApplyByIds(ids);
   }

   public int deleteIndexRecallApplyById(Long id) {
      return this.indexRecallApplyMapper.deleteIndexRecallApplyById(id);
   }

   public Map checkMedicalRecord(CheckMedicalRecordReq req) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String userName = user.getUserName();
      Map<String, Object> map = new HashMap();
      map.put("flag", Boolean.FALSE);
      map.put("isSubmit", Boolean.FALSE);
      map.put("appStatus", Boolean.FALSE);
      map.put("isDoctor", Boolean.FALSE);
      String admissionNo = req.getInpNo();
      Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(admissionNo);
      if (medicalinformation != null) {
         String residentNo = medicalinformation.getResidentNo();
         if (StringUtils.isNotEmpty(residentNo) && userName.equals(residentNo)) {
            map.put("isDoctor", Boolean.TRUE);
         }
      }

      EmrQcFlow emrQcFlow = this.emrQcFlowMapper.checkMedicalRecord(admissionNo);
      if (emrQcFlow != null) {
         String mrState = emrQcFlow.getMrState();
         if (StringUtils.isNotEmpty(mrState) && ("14".equals(mrState) || "16".equals(mrState) || "13".equals(mrState) || "12".equals(mrState))) {
            map.put("flag", Boolean.TRUE);
            Date fileTime = emrQcFlow.getFileTime() != null ? emrQcFlow.getFileTime() : emrQcFlow.getApplyFileTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            map.put("fileTime", fileTime != null ? sdf.format(fileTime) : sdf.format(this.commonService.getDbSysdate()));
            IndexRecallApply indexRecallApply = this.indexRecallApplyMapper.selectRecallApplyByAdmissionNo(admissionNo, userName);
            if (indexRecallApply != null) {
               map.put("isSubmit", Boolean.TRUE);
               if (indexRecallApply.getAppStatus().equals("1")) {
                  map.put("appStatus", Boolean.TRUE);
               }
            }
         }
      }

      return map;
   }

   public List selectReviewedList(BorrowReviewedListReq req) {
      String appStatus = req.getAppStatus();
      String status = req.getStatus();
      String searchStr = req.getSearchStr();
      String appDeptCd = req.getAppDeptCd();
      Integer dateType = req.getDateType();
      String appDocName = req.getAppDocName();
      List<String> list = new ArrayList();
      String startTime = req.getStartTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + " 00:00:00";
      }

      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + " 23:59:59";
      }

      if ("1".equals(status)) {
         list.add("0");
      } else if ("2".equals(status)) {
         if (StringUtils.isNotEmpty(appStatus)) {
            list.add(appStatus);
         } else {
            list.add("1");
            list.add("2");
         }
      } else if (StringUtils.isNotEmpty(appStatus)) {
         list.add(appStatus);
      } else {
         list.add("0");
         list.add("1");
         list.add("2");
         list.add("3");
      }

      return this.indexRecallApplyMapper.selectReviewedList(list, startTime, endTime, searchStr, dateType, appDeptCd, appDocName);
   }

   public List getApplyList(IndexRecallApply indexRecallApply) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      indexRecallApply.setAppDocCd(user.getUserName());
      return this.indexRecallApplyMapper.selectIndexRecallApplyList(indexRecallApply);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateApply(IndexRecallApply indexRecallApply, String recallStatus) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      indexRecallApply.setAppStatus(recallStatus);
      indexRecallApply.setAltDate(new Date());
      indexRecallApply.setAltPerCode(user.getUserName());
      indexRecallApply.setAltPerName(user.getNickName());
      this.indexRecallApplyMapper.updateIndexRecallApply(indexRecallApply);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateStatus(SaveApplyAgreeListReq req, String status) throws Exception {
      List<SaveApplyAgreeReq> list = req.getList();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<IndexRecallApply> applyList = new ArrayList();

      for(SaveApplyAgreeReq saveReq : list) {
         Long id = saveReq.getId();
         IndexRecallApply recallApply = this.indexRecallApplyMapper.selectIndexRecallApplyById(id);
         if (recallApply != null) {
            recallApply.setAppStatus(status);
            recallApply.setConRemark(StringUtils.isNotEmpty(saveReq.getConRemark()) ? saveReq.getConRemark() : "");
            recallApply.setConTime(new Date());
            recallApply.setConDeptCd(user.getDept().getDeptCode());
            recallApply.setConDocName(user.getNickName());
            recallApply.setConDocCd(user.getUserName());
            recallApply.setConDeptName(user.getDept().getDeptName());
            this.indexRecallApplyMapper.updateIndexRecallApply(recallApply);
            applyList.add(recallApply);
         }
      }

      if ("1".equals(status)) {
         List<String> admissionNoList = (List)list.stream().map(SaveApplyAgreeReq::getAdmissionNo).distinct().collect(Collectors.toList());
         this.emrQcFlowMapper.updateMrStateByAdmissionNoList(admissionNoList);
         if (applyList.size() > 0) {
            List<EmrIndexRevokeLog> revokeLogs = new ArrayList();

            for(IndexRecallApply recallApply : applyList) {
               EmrIndexRevokeLog revokeLog = new EmrIndexRevokeLog();
               BeanUtils.copyProperties(recallApply, revokeLog);
               revokeLog.setId(SnowIdUtils.uniqueLong());
               revokeLogs.add(revokeLog);
            }

            if (revokeLogs.size() > 0) {
               this.emrIndexRevokeLogService.insertLogList(revokeLogs);
            }
         }
      }

   }

   public Boolean checkApplyByAdmissionNo(String admissionNo) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String userName = user.getUserName();
      IndexRecallApply apply = this.indexRecallApplyMapper.selectRecallApplyByAdmissionNo(admissionNo, userName);
      return apply != null ? Boolean.FALSE : Boolean.TRUE;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveApply(IndexRecallApply indexRecallApply) throws Exception {
      String admissionNo = indexRecallApply.getAdmissionNo();
      EmrQcFlow emrQcFlow = this.emrQcFlowMapper.checkMedicalRecord(admissionNo);
      if (emrQcFlow != null) {
         indexRecallApply.setMrState(emrQcFlow.getMrState());
         if (emrQcFlow.getMrState().equals("12")) {
            indexRecallApply.setFileDate((Date)null);
         }
      }

      Date dbSysdate = this.commonService.getDbSysdate();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      indexRecallApply.setId(SnowIdUtils.uniqueLong());
      indexRecallApply.setAppDocCd(user.getUserName());
      indexRecallApply.setAppDeptCd(user.getDept().getDeptCode());
      indexRecallApply.setAppDeptName(user.getDept().getDeptName());
      indexRecallApply.setAppDocName(user.getNickName());
      indexRecallApply.setAppTime(dbSysdate);
      indexRecallApply.setResidentCode(indexRecallApply.getResidentNo());
      indexRecallApply.setAppTime(dbSysdate);
      indexRecallApply.setCrePerCode(user.getUserName());
      indexRecallApply.setCreDate(dbSysdate);
      indexRecallApply.setCrePerName(user.getNickName());
      String flag = this.sysEmrConfigService.selectSysEmrConfigByKey("0143");
      if (StringUtils.isNotEmpty(flag)) {
         if ("0".equals(flag)) {
            indexRecallApply.setAppStatus("1");
            indexRecallApply.setConRemark("自动审批通过");
            indexRecallApply.setConTime(this.commonService.getDbSysdate());
            indexRecallApply.setConDocName("system");
            indexRecallApply.setConDocCd("system");
            this.emrQcFlowMapper.updateMrStateByAdmissionNoList(Collections.singletonList(admissionNo));
            EmrIndexRevokeLog revokeLog = new EmrIndexRevokeLog();
            BeanUtils.copyProperties(indexRecallApply, revokeLog);
            revokeLog.setId(SnowIdUtils.uniqueLong());
            this.emrIndexRevokeLogService.insertEmrIndexRevokeLog(revokeLog);
         } else {
            indexRecallApply.setAppStatus("0");
         }
      } else {
         indexRecallApply.setAppStatus("0");
      }

      this.indexRecallApplyMapper.insertIndexRecallApply(indexRecallApply);
   }
}
