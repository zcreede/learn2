package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrTaskInfoMapper {
   EmrTaskInfo selectEmrTaskInfoById(Long id);

   List selectEmrTaskInfoListFiled();

   List selectEmrTaskInfoList(EmrTaskInfo emrTaskInfo);

   int insertEmrTaskInfo(EmrTaskInfo emrTaskInfo);

   int updateEmrTaskInfo(EmrTaskInfo emrTaskInfo);

   int updateEmrTaskInfoForRecover(EmrTaskInfo emrTaskInfo);

   int updateEmrTaskInfoForCancelSign(EmrTaskInfo emrTaskInfo);

   int deleteEmrTaskInfoById(Long id);

   int deleteEmrTaskInfoByIds(Long[] ids);

   void deleteCancelEmrTaskInfo(@Param("admissionNos") List admissionNos);

   int deleteEmrTaskInfo(EmrTaskInfo emrTaskInfo);

   int deleteTaskInfoByEventNo(String eventNo);

   void insertTaskList(List taskInfoList);

   List selectPatientNoCreIndexType(String patientId, List emrTypeCodeList, Long id);

   List selectEmrToDoList(String docCode, String patientId);

   List selectEmrUnWriteList(String patientId);

   List selectInfoByDocsAndIndex(EmrTaskInfoVo emrTaskInfoVo);

   int updateTaskInfoByDel(Long mrFileId, String taskType);

   void updateTaskInfoEventNo(@Param("oldEventNo") String oldEventNo, @Param("eventNo") String eventNo);

   List selectAgiRuleTaskList(PatEventVo patEventVo);

   List selectAgiRuleTaskYcjList(PatEventVo patEventVo);

   List selectOvertimeUnWriteList(String patientId);

   void updateTaskInfoByEventNo(List orderNoList);

   void updateTaskBusByIds(List taskId);

   EmrTaskInfoVo selectTaskByEventNoAndBus(String busId, String treatFlag, String eventNo);

   void updateTaskInfoByMrfileId(Long mrFileId, String state);

   void updateTaskInfoByMrfileIdAndDocCdForFreeMove(@Param("mrFileId") Long mrFileId, @Param("docCd") String docCd);

   void updateTaskInfoBydocCode(Long mrFileId, String docCode, Long id);

   void deleteEmrTaskInfoByMrFileId(Long mrFileId);

   List selectNoFinishTaskList(String patientId, String taskType);

   void updateTaskInfoById(@Param("id") Long id);
}
