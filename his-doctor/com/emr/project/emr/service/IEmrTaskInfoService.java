package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import java.util.List;

public interface IEmrTaskInfoService {
   EmrTaskInfo selectEmrTaskInfoById(Long id);

   List selectEmrTaskInfoList(EmrTaskInfo emrTaskInfo);

   int insertEmrTaskInfo(EmrTaskInfo emrTaskInfo);

   int updateEmrTaskInfo(EmrTaskInfo emrTaskInfo);

   int updateEmrTaskInfoForCancelSign(EmrTaskInfo emrTaskInfo);

   int deleteEmrTaskInfoByIds(Long[] ids);

   void deleteCancelEmrTaskInfo(List admissionNos);

   int deleteEmrTaskInfoById(Long id);

   int deleteEmrTaskInfo(EmrTaskInfo emrTaskInfo);

   int deleteTaskInfoByEventNo(String eventNo);

   void insertListByGroup(List taskInfoList) throws Exception;

   void insertTaskList(List taskInfoList) throws Exception;

   void updateTaskInfoEventNo(String oldEventNo, String eventNo) throws Exception;

   int branchAddTaskList(EmrTaskInfoVo emrTaskInfoVo) throws Exception;

   List selectInfoByOdcsAndIndex(EmrTaskInfoVo emrTaskInfoVo);

   List selectPatientNoCreIndexType(String patientId, String emrTypeCode, Long taskId) throws Exception;

   List selectEmrToDoList(String patientId) throws Exception;

   void updateTaskInfoByAdd(Long taskId, Long mrFileId) throws Exception;

   void updateTaskInfoByMrfileIdAndDocCdForFreeMove(Long mrFileId, String docCd) throws Exception;

   void updateTaskInfoByDel(Long mrFileId, String taskType) throws Exception;

   List selectByInfo(String taskType, String treatFlag, String docCd, String busId);

   List selectEmrUnWriteList(String patientId) throws Exception;

   List selectAgiRuleTaskList(PatEventVo patEventVo) throws Exception;

   List selectAgiRuleTaskYcjList(PatEventVo patEventVo) throws Exception;

   List selectOvertimeUnWriteList(String patientId, Long mainId, String qcSection) throws Exception;

   void updateTaskInfoByEventNo(List orderNoList) throws Exception;

   void updateTaskBusByIds(List taskId) throws Exception;

   EmrTaskInfoVo selectTaskByEventNoAndBus(String busId, String treatFlag, String eventNo) throws Exception;

   void updateTaskInfoByMrfileId(Long mrFileId, String state) throws Exception;

   void updateTaskInfoBydocCode(Long mrFileId, String docCode, Long id) throws Exception;

   void deleteEmrTaskInfoByMrFileId(Long mrFileId) throws Exception;

   List selectNoFinishTaskList(String patientId, String taskType) throws Exception;

   void updateTaskInfoById(Long id) throws Exception;
}
