package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.holiday.service.ITmBsHolidayService;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.mapper.EmrQcFlowMapper;
import com.emr.project.qc.mapper.EmrQcRecordMapper;
import com.emr.project.qc.service.IEmrQcFlowRecordService;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.revoke.domain.EmrIndexRevokeLog;
import com.emr.project.revoke.service.IEmrIndexRevokeLogService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.service.ISysEmrConfigService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmrArchiveTask {
   protected final Logger log = LoggerFactory.getLogger(EmrArchiveTask.class);
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrQcFlowRecordService emrQcFlowRecordService;
   @Autowired
   private EmrQcFlowMapper emrQcFlowMapper;
   @Autowired
   private EmrQcRecordMapper emrQcRecordMapper;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private ITmBsHolidayService tmBsHolidayService;
   @Autowired
   private IEmrIndexRevokeLogService emrIndexRevokeLogService;
   @Autowired
   private IMedicalinfoService medicalinfoService;

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void patientArchive() {
      this.log.debug("=============患者自动归档开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));

      try {
         String holidayConfig = this.sysEmrConfigService.selectSysEmrConfigByKey("0089");
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
            String syncStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0066");
            List<String> mrStateList = new ArrayList(Arrays.asList("00", "10", "11", "12", "14"));
            List<VisitinfoVo> list = this.visitinfoService.selectDeptArchiveList(Integer.parseInt(syncStr), mrStateList);
            if (list != null && !list.isEmpty()) {
               for(VisitinfoVo visitinfoVo : list) {
                  if (holidayConfigFlag) {
                     Date outTime = visitinfoVo.getOutTime();
                     Date fileDate = this.commonService.queryFileDate(outTime, Integer.parseInt(syncStr));
                     if ((new Date()).compareTo(fileDate) > 0) {
                        this.updateEmrFlowInfo(visitinfoVo);
                     }
                  } else {
                     this.updateEmrFlowInfo(visitinfoVo);
                  }
               }
            }
         }
      } catch (Exception e) {
         this.log.error("自动归档出现异常", e);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateEmrFlowInfo(VisitinfoVo visitinfoVo) {
      try {
         EmrQcFlow flow = this.emrQcFlowService.selectEmrQcFlowById(visitinfoVo.getOrgCd(), visitinfoVo.getPatientId());
         String flag = "";
         if (flow == null) {
            this.log.info("1111111111111111111患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "未提交科室质控");
            flag = "TRUE";
            flow = new EmrQcFlow();
            VisitinfoPersonalVo visitinfo = this.visitinfoService.selectVisitinfoPersonalById(visitinfoVo.getPatientId());
            flow.setOrgCd(visitinfo.getOrgCd());
            flow.setPatientId(visitinfoVo.getPatientId());
            flow.setPatientName(visitinfo.getPatientName());
            flow.setRecordNo(visitinfo.getRecordNo());
            flow.setInpNo(visitinfo.getInpNo());
            flow.setVisitId(visitinfo.getVisitId());
            flow.setDeptCd("sys");
            flow.setDeptName("系统");
            flow.setResDocCd(visitinfo.getResDocCd());
            flow.setResDocName(visitinfo.getResDocName());
            flow.setInDeptTime(visitinfo.getInDeptTime());
            flow.setOutDeptTime(visitinfo.getOutTime());
            flow.setId(SnowIdUtils.uniqueLong());
            flow.setCrePerCode("sys");
            flow.setCrePerName("系统");
         } else {
            flow.setAltPerCode("sys");
            flow.setAltPerName("系统");
         }

         if (visitinfoVo.getQcMrState().equals("00") || visitinfoVo.getQcMrState().equals("11")) {
            flow.setMrSubCd("sys");
            flow.setMrSubName("系统");
            flow.setMrSubTime(this.commonService.getDbSysdate());
            flow.setDeptQcState("0");
            this.log.info("2222222222222222222患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "填充科室质控流程信息");
         }

         if (visitinfoVo.getQcMrState().equals("00") || visitinfoVo.getQcMrState().equals("11") || visitinfoVo.getQcMrState().equals("10") || visitinfoVo.getQcMrState().equals("13")) {
            flow.setApplyDeptCd("sys");
            flow.setApplyDeptName("系统");
            flow.setApplyDocCd("sys");
            flow.setApplyDocName("系统");
            flow.setApplyFileTime(this.commonService.getDbSysdate());
            flow.setTermQcState("0");
         }

         flow.setFileDocCd("sys");
         flow.setFileDocName("系统");
         flow.setFileTime(this.commonService.getDbSysdate());
         flow.setFirstFileTime(flow.getFirstFileTime() == null ? this.commonService.getDbSysdate() : flow.getFirstFileTime());
         flow.setMrState("16");
         this.emrQcFlowRecordService.insertEmrQcFlowRecord(flow, "16", "病历归档", "localhost", (BasEmployee)null);
         this.log.info("2222222222222222222患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "填充终末质控流程信息");
         if (StringUtils.isEmpty(flag)) {
            this.emrQcFlowMapper.updateEmrQcFlow(flow);
            this.log.info("333333333333333333患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "修改qc_flow流程信息");
         } else {
            this.emrQcFlowMapper.insertEmrQcFlow(flow);
            this.log.info("333333333333333333患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "新增qc_flow流程信息");
         }

         EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByPatientSection(visitinfoVo.getPatientId(), "05", (Long)null);
         if (emrQcRecord != null) {
            emrQcRecord.setState("2");
            emrQcRecord.setStateName("质控完成");
            emrQcRecord.setBackoutFileBz("0");
            this.emrQcRecordMapper.updateEmrQcRecord(emrQcRecord);
         } else {
            emrQcRecord = new EmrQcRecord();
            emrQcRecord.setId(SnowIdUtils.uniqueLong());
            emrQcRecord.setPatientId(visitinfoVo.getPatientId());
            emrQcRecord.setPatientName(visitinfoVo.getPatientName());
            emrQcRecord.setSex(visitinfoVo.getSexCd());
            emrQcRecord.setInpNo(visitinfoVo.getPatientId());
            emrQcRecord.setInDeptCd(visitinfoVo.getDeptCd());
            emrQcRecord.setInDeptName(visitinfoVo.getDeptName());
            emrQcRecord.setQcSection("05");
            emrQcRecord.setQcdoctCd("sys");
            emrQcRecord.setQcdoctName("系统");
            emrQcRecord.setCrePerCode("sys");
            emrQcRecord.setCrePerName("系统");
            this.emrQcRecordMapper.insertEmrQcRecord(emrQcRecord);
         }
      } catch (Exception e) {
         this.log.error("患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "自动归档出现异常-----------", e);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void patientApplyTermTask() {
      this.log.debug("=============患者自动提交终末质控申请开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));

      try {
         String holidayConfig = this.sysEmrConfigService.selectSysEmrConfigByKey("0089");
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
            String syncStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0056");
            List<String> mrStateList = new ArrayList(Arrays.asList("00", "10", "11"));
            List<VisitinfoVo> list = this.visitinfoService.selectDeptArchiveList(Integer.parseInt(syncStr), mrStateList);
            if (CollectionUtils.isNotEmpty(list)) {
               for(VisitinfoVo visitinfoVo : list) {
                  if (holidayConfigFlag) {
                     Date outTime = visitinfoVo.getOutTime();
                     Date fileDate = this.commonService.queryFileDate(outTime, Integer.parseInt(syncStr));
                     if ((new Date()).compareTo(fileDate) > 0) {
                        this.patientApplyTerm(visitinfoVo);
                     }
                  } else {
                     this.patientApplyTerm(visitinfoVo);
                  }
               }
            }
         }
      } catch (Exception e) {
         this.log.error("自动提交终末质控申请出现异常", e);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void patientApplyTerm(VisitinfoVo visitinfoVo) {
      try {
         EmrQcFlow flow = this.emrQcFlowService.selectEmrQcFlowById(visitinfoVo.getOrgCd(), visitinfoVo.getPatientId());
         String flag = "";
         if (flow == null) {
            this.log.info("1111111111111111111患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "未提交科室质控");
            flag = "TRUE";
            flow = new EmrQcFlow();
            VisitinfoPersonalVo visitinfo = this.visitinfoService.selectVisitinfoPersonalById(visitinfoVo.getPatientId());
            flow.setOrgCd(visitinfoVo.getOrgCd());
            flow.setPatientId(visitinfoVo.getPatientId());
            flow.setPatientName(visitinfo.getPatientName());
            flow.setRecordNo(visitinfo.getRecordNo());
            flow.setInpNo(visitinfo.getInpNo());
            flow.setVisitId(visitinfo.getVisitId());
            flow.setDeptCd("sys");
            flow.setDeptName("系统");
            flow.setResDocCd(visitinfo.getResDocCd());
            flow.setResDocName(visitinfo.getResDocName());
            flow.setInDeptTime(visitinfo.getInDeptTime());
            flow.setOutDeptTime(visitinfo.getOutTime());
            flow.setId(SnowIdUtils.uniqueLong());
            flow.setCrePerCode("sys");
            flow.setCrePerName("系统");
         } else {
            flow.setAltPerCode("sys");
            flow.setAltPerName("系统");
         }

         flow.setMrSubCd("sys");
         flow.setMrSubName("系统");
         flow.setMrSubTime(this.commonService.getDbSysdate());
         flow.setDeptQcState("0");
         this.log.info("2222222222222222222患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "填充科室质控流程信息");
         flow.setApplyDeptCd("sys");
         flow.setApplyDeptName("系统");
         flow.setApplyDocCd("sys");
         flow.setApplyDocName("系统");
         flow.setApplyFileTime(this.commonService.getDbSysdate());
         flow.setMrState("12");
         this.log.info("2222222222222222222患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "填充终末质控流程信息");
         if (StringUtils.isEmpty(flag)) {
            this.emrQcFlowMapper.updateEmrQcFlow(flow);
            this.log.info("333333333333333333患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "修改qc_flow流程信息");
         } else {
            this.emrQcFlowMapper.insertEmrQcFlow(flow);
            this.log.info("333333333333333333患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "新增qc_flow流程信息");
         }

         EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByPatientSection(visitinfoVo.getPatientId(), "05", (Long)null);
         if (emrQcRecord != null) {
            emrQcRecord.setState("0");
            emrQcRecord.setStateName("未质控");
            this.emrQcRecordMapper.updateEmrQcRecord(emrQcRecord);
         } else {
            emrQcRecord = new EmrQcRecord();
            emrQcRecord.setId(SnowIdUtils.uniqueLong());
            emrQcRecord.setPatientId(visitinfoVo.getPatientId());
            emrQcRecord.setPatientName(visitinfoVo.getPatientName());
            emrQcRecord.setSex(visitinfoVo.getSexCd());
            emrQcRecord.setInpNo(visitinfoVo.getPatientId());
            emrQcRecord.setInDeptCd(visitinfoVo.getDeptCd());
            emrQcRecord.setInDeptName(visitinfoVo.getDeptName());
            emrQcRecord.setState("0");
            emrQcRecord.setStateName("未质控");
            emrQcRecord.setQcSection("05");
            emrQcRecord.setQcdoctCd("sys");
            emrQcRecord.setQcdoctName("系统");
            emrQcRecord.setCrePerCode("sys");
            emrQcRecord.setCrePerName("系统");
            this.emrQcRecordMapper.insertEmrQcRecord(emrQcRecord);
         }
      } catch (Exception e) {
         this.log.error("患者" + visitinfoVo.getPatientId() + visitinfoVo.getPatientName() + "自动提交终末质控申请出现异常-----------", e);
      }

   }

   public void patientApplyTermForBackTask(Integer limitHour) {
      if (limitHour == null) {
         limitHour = 24;
      }

      try {
         Date currDate = this.commonService.getDbSysdate();
         String termQcBackTimeBegin = DateUtils.dateFormat(DateUtils.addHours(currDate, 0 - limitHour), DateUtils.YYYY_MM_DD_HH_MM_SS);
         List<EmrQcFlow> emrQcFlowList = this.emrQcFlowService.selectTermBackLimitHourList("13", termQcBackTimeBegin);
         if (CollectionUtils.isNotEmpty(emrQcFlowList)) {
            for(EmrQcFlow qcFlow : emrQcFlowList) {
               try {
                  this.patientApplyTermForBack(qcFlow);
               } catch (Exception e) {
                  this.log.error("患者住院号：" + qcFlow.getPatientId() + " " + qcFlow.getPatientName() + " 终末质控退回后，自动归档出现异常：", e);
               }

               String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
               if (orderFlag.equals("1")) {
                  Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(qcFlow.getPatientId());
                  Baseinfomation baseInfo = this.commonService.findBaseInfo(qcFlow.getPatientId());
                  if (medicalinformation != null && baseInfo != null) {
                     EmrQcFlowScoreVo scoreVo = new EmrQcFlowScoreVo();
                     scoreVo.setTotalScore(qcFlow.getTermScore());
                     scoreVo.setLevelName(qcFlow.getTermGradeName());
                     this.emrQcFlowService.saveHisSqgd(medicalinformation, scoreVo, baseInfo, qcFlow);
                  }
               }
            }
         }
      } catch (Exception e) {
         this.log.error("终末质控退回后，自动归档出现异常：", e);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void patientApplyTermForBack(EmrQcFlow qcFlow) throws Exception {
      EmrQcFlow param = new EmrQcFlow();
      param.setId(qcFlow.getId());
      param.setMrState("16");
      this.emrQcFlowService.updateEmrQcFlowBySys(param);
      EmrIndexRevokeLog emrIndexRevokeLog = new EmrIndexRevokeLog();
      emrIndexRevokeLog.setId(SnowIdUtils.uniqueLong());
      emrIndexRevokeLog.setAdmissionNo(qcFlow.getPatientId());
      emrIndexRevokeLog.setCaseNo(qcFlow.getRecordNo());
      emrIndexRevokeLog.setPatientName(qcFlow.getPatientName());
      emrIndexRevokeLog.setHospitalizedCount(qcFlow.getVisitId() == null ? 1L : Long.valueOf((long)qcFlow.getVisitId()));
      emrIndexRevokeLog.setDischargeDepartmentNo(qcFlow.getDeptCd());
      emrIndexRevokeLog.setDischargeDepartmentName(qcFlow.getDeptName());
      emrIndexRevokeLog.setDischargeDepartmentName(qcFlow.getDeptName());
      emrIndexRevokeLog.setHospitalizedDate(qcFlow.getInDeptTime());
      emrIndexRevokeLog.setLeaveHospitalDate(qcFlow.getOutDeptTime());
      emrIndexRevokeLog.setRecallReason("终末质控退回状态后(终末质控-质控、已归档病历-撤销归档、病历召回审批-通过)，自动归档");
      emrIndexRevokeLog.setConTime(this.commonService.getDbSysdate());
      emrIndexRevokeLog.setConDocCd("system");
      emrIndexRevokeLog.setConDocName("系统");
      this.emrIndexRevokeLogService.insertEmrIndexRevokeLog(emrIndexRevokeLog);
   }
}
