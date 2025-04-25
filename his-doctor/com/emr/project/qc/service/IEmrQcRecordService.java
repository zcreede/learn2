package com.emr.project.qc.service;

import com.emr.project.emr.domain.vo.IndexSaveVo;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckedVo;
import java.util.List;

public interface IEmrQcRecordService {
   EmrQcRecord selectEmrQcRecordById(Long id);

   EmrQcRecord selectEmrQcRecordByPatientSection(String patientId, String qcSection, Long qcBillNo);

   List selectEmrQcRecordList(EmrQcRecord emrQcRecord);

   int insertEmrQcRecord(EmrQcRecord emrQcRecord);

   int updateEmrQcRecord(EmrQcRecord emrQcRecord);

   int deleteEmrQcRecordByIds(Long[] ids);

   int deleteEmrQcRecordById(Long id);

   List getCheckResultQcList(MrHpVo mrHpVo, List checkSetList, List checkResultVoList, List qcExcepationList) throws Exception;

   List getEmrCheckResultQcList(IndexSaveVo indexSaveVo, List qcRulesVoList, List qcExcepationList) throws Exception;

   void saveMrHpQcRecord(MrHpVo mrHpVo) throws Exception;

   List selectRunTimeQcChecked(RunTimeQcCheckedVo runTimeQcCheckedVo) throws Exception;

   List selectByPatientRunTimeQcNum(List patientIdList, String qcSection);

   RunTimeQcCheckedVo selectRunTimeQcCheckedOne(RunTimeQcCheckedVo runTimeQcCheckedVo) throws Exception;

   EmrQcRecord randomCheck(RunTimeQcCheckVo runTimeQcCheckVo) throws Exception;

   EmrQcRecord qcDoing(String patientId, String qcSection, Long qcBillNo) throws Exception;

   void randomVerify(RunTimeQcCheckedVo runTimeQcCheckedVo) throws Exception;

   EmrQcRecord randomCheckFinish(EmrQcRecord emrQcRecord, EmrQcFlowScoreVo emrQcFlowScoreVo, String ip) throws Exception;

   void randomVerifyFinish(Long id) throws Exception;

   List selectListByQcBillNos(List qcBillNoList) throws Exception;

   void insertList(List list) throws Exception;

   List selectQcReturnList(String patientId) throws Exception;

   EmrQcRecord selectEmrQcRecordByQcSection(String qcSection, String patientId);

   void saveMrisHpQcRecord(TdCmHpLineVo mrHpVo) throws Exception;
}
