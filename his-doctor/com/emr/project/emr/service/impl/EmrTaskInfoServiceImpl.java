package com.emr.project.emr.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.mapper.EmrTaskInfoMapper;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.service.IImpDoctorTempService;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.service.IQcRuleIterEmrService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrTaskInfoServiceImpl implements IEmrTaskInfoService {
   @Autowired
   private EmrTaskInfoMapper emrTaskInfoMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IQcRuleIterEmrService qcRuleIterEmrService;
   @Autowired
   private IImpDoctorTempService impDoctorTempService;

   public EmrTaskInfo selectEmrTaskInfoById(Long id) {
      return this.emrTaskInfoMapper.selectEmrTaskInfoById(id);
   }

   public List selectEmrTaskInfoList(EmrTaskInfo emrTaskInfo) {
      return this.emrTaskInfoMapper.selectEmrTaskInfoList(emrTaskInfo);
   }

   public int insertEmrTaskInfo(EmrTaskInfo emrTaskInfo) {
      emrTaskInfo.setId(SnowIdUtils.uniqueLong());
      return this.emrTaskInfoMapper.insertEmrTaskInfo(emrTaskInfo);
   }

   public int updateEmrTaskInfo(EmrTaskInfo emrTaskInfo) {
      if ("2".equals(emrTaskInfo.getTreatFlag())) {
         EmrTaskInfo emrTaskInfo1 = this.emrTaskInfoMapper.selectEmrTaskInfoById(emrTaskInfo.getId());
         if (!emrTaskInfo1.getDeptCd().equals(emrTaskInfo1.getDutyDeptCd())) {
            ImpDoctorTemp impDoctorTemp = new ImpDoctorTemp();
            impDoctorTemp.setPatientId(emrTaskInfo1.getPatientId());
            impDoctorTemp.setDocCode(emrTaskInfo1.getDocCd());
            impDoctorTemp.setImpDeptCd(emrTaskInfo1.getDutyDeptCd());
            impDoctorTemp.setImpDocCd(emrTaskInfo1.getDocCd());
            impDoctorTemp.setImpRange("0");
            impDoctorTemp.setValidFlag("1");
            impDoctorTemp.setImpType("3");
            this.impDoctorTempService.updateImpDoctorTempForMove(impDoctorTemp);
         }
      }

      int update = 0;
      if (emrTaskInfo != null && "22".equals(emrTaskInfo.getBusSection())) {
         if (emrTaskInfo != null && emrTaskInfo.getIds().size() > 0) {
            update = this.emrTaskInfoMapper.updateEmrTaskInfoForRecover(emrTaskInfo);
         }
      } else {
         update = this.emrTaskInfoMapper.updateEmrTaskInfo(emrTaskInfo);
      }

      return update;
   }

   public int updateEmrTaskInfoForCancelSign(EmrTaskInfo emrTaskInfo) {
      return this.emrTaskInfoMapper.updateEmrTaskInfoForCancelSign(emrTaskInfo);
   }

   public int deleteEmrTaskInfoByIds(Long[] ids) {
      return this.emrTaskInfoMapper.deleteEmrTaskInfoByIds(ids);
   }

   public void deleteCancelEmrTaskInfo(List admissionNos) {
      if (CollectionUtils.isNotEmpty(admissionNos) && admissionNos.size() > 0) {
         this.emrTaskInfoMapper.deleteCancelEmrTaskInfo(admissionNos);
      }

   }

   public int deleteEmrTaskInfoById(Long id) {
      return this.emrTaskInfoMapper.deleteEmrTaskInfoById(id);
   }

   public int deleteEmrTaskInfo(EmrTaskInfo emrTaskInfo) {
      return this.emrTaskInfoMapper.deleteEmrTaskInfo(emrTaskInfo);
   }

   public int deleteTaskInfoByEventNo(String eventNo) {
      return this.emrTaskInfoMapper.deleteTaskInfoByEventNo(eventNo);
   }

   @Async
   public void insertListByGroup(List taskInfoList) throws Exception {
      if (taskInfoList != null && !taskInfoList.isEmpty()) {
         int size = taskInfoList.size() >= 100 ? 100 : taskInfoList.size();
         double num = Math.ceil((double)taskInfoList.size() / (double)100.0F);

         for(int i = 1; (double)i <= num; ++i) {
            int endSize = (double)i == num ? taskInfoList.size() : i * size;
            List<EmrTaskInfoVo> emrTaskInfoList = taskInfoList.subList((i - 1) * size, endSize);
            this.insertTaskList(emrTaskInfoList);
         }
      }

   }

   public void insertTaskList(List taskInfoList) throws Exception {
      if (taskInfoList != null && !taskInfoList.isEmpty()) {
         this.emrTaskInfoMapper.insertTaskList(taskInfoList);
      }

   }

   public void updateTaskInfoEventNo(String oldEventNo, String eventNo) throws Exception {
      this.emrTaskInfoMapper.updateTaskInfoEventNo(oldEventNo, eventNo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int branchAddTaskList(EmrTaskInfoVo emrTaskInfoVo) throws Exception {
      List<EmrTaskInfoVo> taskInfoList = new ArrayList();
      List<BasEmployee> basEmployeeList = emrTaskInfoVo.getBasEmployeeList();
      BasEmployee basEmployee1 = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrTaskInfoVo.setTaskAppDoc(basEmployee1.getEmplNumber());
      emrTaskInfoVo.setTaskAppDocName(basEmployee1.getEmplName());
      List<ImpDoctorTemp> impDoctorTempList = new ArrayList();

      for(BasEmployee basEmployee : basEmployeeList) {
         EmrTaskInfoVo emrTaskInfoVo1 = new EmrTaskInfoVo();
         BeanUtils.copyProperties(emrTaskInfoVo, emrTaskInfoVo1);
         emrTaskInfoVo1.setBusSection("22");
         emrTaskInfoVo1.setBusSectionName("自由流转");
         emrTaskInfoVo1.setId(SnowIdUtils.uniqueLong());
         emrTaskInfoVo1.setBeginTime(new Date());
         emrTaskInfoVo1.setTreatFlag("0");
         emrTaskInfoVo1.setDocCd(basEmployee.getEmplNumber());
         emrTaskInfoVo1.setDocName(basEmployee.getEmplName());
         emrTaskInfoVo1.setDutyDeptCd(basEmployee.getDeptCode());
         emrTaskInfoVo1.setDutyDeptName(basEmployee.getDeptName());
         taskInfoList.add(emrTaskInfoVo1);
         if (!basEmployee.getDeptCode().equals(emrTaskInfoVo.getDeptCd())) {
            ImpDoctorTemp impDoctorTemp = new ImpDoctorTemp();
            impDoctorTemp.setPatientId(emrTaskInfoVo.getPatientId());
            impDoctorTemp.setPatientName(emrTaskInfoVo.getPatientName());
            impDoctorTemp.setImpDocCd(basEmployee.getEmplNumber());
            impDoctorTemp.setImpDocName(basEmployee.getEmplName());
            impDoctorTemp.setImpRange("0");
            impDoctorTemp.setImpBegTime(new Date());
            impDoctorTemp.setImpDate(new Date());
            impDoctorTemp.setImpType("3");
            impDoctorTemp.setImpEndTime(DateUtils.addHours(new Date(), 24));
            impDoctorTemp.setImpAim("自由流转签名");
            impDoctorTempList.add(impDoctorTemp);
         }
      }

      if (taskInfoList != null && !taskInfoList.isEmpty()) {
         this.emrTaskInfoMapper.insertTaskList(taskInfoList);
      }

      if (impDoctorTempList.size() > 0) {
         this.impDoctorTempService.batchAddImpDoctorTemp(impDoctorTempList);
      }

      return 1;
   }

   public List selectInfoByOdcsAndIndex(EmrTaskInfoVo emrTaskInfoVo) {
      return this.emrTaskInfoMapper.selectInfoByDocsAndIndex(emrTaskInfoVo);
   }

   public List selectPatientNoCreIndexType(String patientId, String emrTypeCode, Long taskId) throws Exception {
      List<String> emrTypeCodeList = StringUtils.isBlank(emrTypeCode) ? null : this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeOrIter(emrTypeCode);
      return this.emrTaskInfoMapper.selectPatientNoCreIndexType(patientId, emrTypeCodeList, taskId);
   }

   public List selectEmrToDoList(String patientId) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<BackLogVo> result = new ArrayList();
      List<EmrTaskInfoVo> list = this.emrTaskInfoMapper.selectEmrToDoList(basEmployee.getEmplNumber(), patientId);
      if (list != null && !list.isEmpty()) {
         for(EmrTaskInfoVo emrTaskInfoVo : list) {
            BackLogVo backLogVo = new BackLogVo();
            String str = "";
            String bedNo = emrTaskInfoVo.getBedNo() == null ? "" : emrTaskInfoVo.getBedNo();
            String patientName = emrTaskInfoVo.getPatientName() == null ? "" : emrTaskInfoVo.getPatientName();
            String sex = emrTaskInfoVo.getSex() == null ? "" : emrTaskInfoVo.getSex();
            String caseNo = StringUtils.isNotBlank(emrTaskInfoVo.getRecordNo()) ? emrTaskInfoVo.getRecordNo() : "";
            String emrTypeName = emrTaskInfoVo.getEmrTypeName() == null ? "" : emrTaskInfoVo.getEmrTypeName();
            Date endTime = this.getEndTime(emrTaskInfoVo.getBeginTime(), emrTaskInfoVo.getLimitTime(), emrTaskInfoVo.getLimitTimeUnit());
            if (emrTaskInfoVo.getRemainHours() > 0) {
               emrTaskInfoVo.setRemainTime(emrTaskInfoVo.getRemainHours() + "小时");
               str = "【" + bedNo + "床 " + patientName + " " + sex + " " + caseNo + "】患者的【" + emrTypeName + "】应在【" + DateUtils.parseDateToStr("YYYY-MM-dd HH:mm:ss", endTime) + "】之前完成,剩余" + emrTaskInfoVo.getRemainHours() + "小时,请及时完成";
            } else {
               emrTaskInfoVo.setRemainTime("已超时" + Math.abs(emrTaskInfoVo.getRemainHours()));
               str = "【" + bedNo + "床 " + patientName + " " + sex + " " + caseNo + "】患者的【" + emrTypeName + "】应在【" + DateUtils.parseDateToStr("YYYY-MM-dd HH:mm:ss", endTime) + "】之前完成,现已超时" + Math.abs(emrTaskInfoVo.getRemainHours()) + "小时,请及时完成";
            }

            backLogVo.setInpNo(emrTaskInfoVo.getInpNo());
            backLogVo.setPatientId(emrTaskInfoVo.getPatientId());
            backLogVo.setPatientMainId(emrTaskInfoVo.getPatientMainId());
            backLogVo.setMessageStr(str);
            backLogVo.setType("1");
            backLogVo.setMrType(emrTaskInfoVo.getEmrTypeCode());
            result.add(backLogVo);
         }
      }

      return result;
   }

   private Date getEndTime(Date currDate, Integer finishLimitTime, String limitTimeUnit) {
      Date resDate = null;
      switch (limitTimeUnit) {
         case "H":
            resDate = DateUtils.addHours(currDate, finishLimitTime);
            break;
         case "D":
            resDate = DateUtils.addDays(currDate, finishLimitTime);
      }

      return resDate;
   }

   public void updateTaskInfoByAdd(Long taskId, Long mrFileId) throws Exception {
      EmrTaskInfo emrTaskInfo = this.selectEmrTaskInfoById(taskId);
      EmrTaskInfo param = new EmrTaskInfo();
      param.setId(taskId);
      param.setMrFileId(mrFileId);
      Date currDate = this.commonService.getDbSysdate();
      param.setFinishTime(currDate);
      if (emrTaskInfo.getBeginTime() != null) {
         String useTime = DateUtils.getDatePoor(currDate, emrTaskInfo.getBeginTime());
         param.setUseTime(useTime);
      }

      if (emrTaskInfo.getEndTime() != null) {
         param.setOvertimeFlag(currDate.compareTo(emrTaskInfo.getEndTime()) > 0 ? 1 : 0);
      }

      param.setTreatFlag("1");
      this.emrTaskInfoMapper.updateEmrTaskInfo(param);
   }

   public void updateTaskInfoByMrfileIdAndDocCdForFreeMove(Long mrFileId, String docCd) throws Exception {
      this.emrTaskInfoMapper.updateTaskInfoByMrfileIdAndDocCdForFreeMove(mrFileId, docCd);
   }

   public void updateTaskInfoByDel(Long mrFileId, String taskType) throws Exception {
      this.emrTaskInfoMapper.updateTaskInfoByDel(mrFileId, taskType);
   }

   public List selectByInfo(String taskType, String treatFlag, String docCd, String busId) {
      EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
      emrTaskInfo.setTaskType(taskType);
      emrTaskInfo.setTreatFlag(treatFlag);
      emrTaskInfo.setDocCd(docCd);
      emrTaskInfo.setBusId(busId);
      return this.selectEmrTaskInfoList(emrTaskInfo);
   }

   public List selectEmrUnWriteList(String patientId) throws Exception {
      return this.emrTaskInfoMapper.selectEmrUnWriteList(patientId);
   }

   public List selectAgiRuleTaskList(PatEventVo patEventVo) throws Exception {
      return this.emrTaskInfoMapper.selectAgiRuleTaskList(patEventVo);
   }

   public List selectAgiRuleTaskYcjList(PatEventVo patEventVo) throws Exception {
      return this.emrTaskInfoMapper.selectAgiRuleTaskYcjList(patEventVo);
   }

   public List selectOvertimeUnWriteList(String patientId, Long mainId, String qcSection) throws Exception {
      List<EmrTaskInfoVo> list = this.emrTaskInfoMapper.selectOvertimeUnWriteList(patientId);
      list.stream().forEach((t) -> {
         t.setMainId(mainId);
         t.setQcSection(qcSection);
      });
      return list;
   }

   public void updateTaskInfoByEventNo(List orderNoList) throws Exception {
      if (orderNoList != null && !orderNoList.isEmpty()) {
         this.emrTaskInfoMapper.updateTaskInfoByEventNo(orderNoList);
      }

   }

   public void updateTaskBusByIds(List taskId) throws Exception {
      this.emrTaskInfoMapper.updateTaskBusByIds(taskId);
   }

   public EmrTaskInfoVo selectTaskByEventNoAndBus(String busId, String treatFlag, String eventNo) throws Exception {
      return this.emrTaskInfoMapper.selectTaskByEventNoAndBus(busId, treatFlag, eventNo);
   }

   public void updateTaskInfoByMrfileId(Long mrFileId, String state) throws Exception {
      this.emrTaskInfoMapper.updateTaskInfoByMrfileId(mrFileId, state);
   }

   public void updateTaskInfoBydocCode(Long mrFileId, String docCode, Long id) throws Exception {
      this.emrTaskInfoMapper.updateTaskInfoBydocCode(mrFileId, docCode, id);
   }

   public void deleteEmrTaskInfoByMrFileId(Long mrFileId) throws Exception {
      this.emrTaskInfoMapper.deleteEmrTaskInfoByMrFileId(mrFileId);
   }

   public List selectNoFinishTaskList(String patientId, String taskType) throws Exception {
      return StringUtils.isNotBlank(patientId) && StringUtils.isNotBlank(taskType) ? this.emrTaskInfoMapper.selectNoFinishTaskList(patientId, taskType) : null;
   }

   public void updateTaskInfoById(Long id) throws Exception {
      this.emrTaskInfoMapper.updateTaskInfoById(id);
   }
}
