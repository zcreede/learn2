package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrQcListMapper {
   EmrQcList selectEmrQcListById(Long id);

   List selectEmrQcListList(EmrQcListVo emrQcList);

   List selectEmrqcListListIndexName(EmrQcListVo emrQcList);

   EmrQcListVo selectEmrqcListById(EmrQcListVo emrQcList);

   int insertEmrQcList(EmrQcList emrQcList);

   int updateEmrQcList(EmrQcList emrQcList);

   int updateEmrQcListByMrFileId(EmrQcList emrQcList);

   void updateEmrQcLists(List list);

   int deleteEmrQcListById(Long id);

   int deleteEmrQcListByIds(Long[] ids);

   int insertEmrQcLists(List list);

   List selectQcDoctByPatientSection(EmrQcList emrQcList);

   List selectByPatientsAndQcState(@Param("patientId") String patientId, List qcStateList, @Param("mainId") Long mainId);

   List selectByPatientsAndQcStateAndRecordBz(@Param("patientId") String patientId, List qcStateList, @Param("mainId") Long mainId, @Param("recordBz") String recordBz);

   List selectEmrSubFileQcList(@Param("patientId") String patientId, List qcStateList, @Param("qcSection") String qcSection);

   List selectByQcStateList(List qcStateList);

   void updateSystemQcState(List list, @Param("qcState") String qcState);

   void updateQconTimesById(@Param("id") Long id);

   List selectUnUpdateEmrList(String docCode, String patientId);

   List selectMissingFileListByMrFileId(String mrFileId);

   List selectEmrQcListScore(EmrQcListVo emrQcListVo);

   List selectEmrQcListScoreVoList(String qcSection, String patientId, List qcStateList);

   List getQcFlowStatistic(EmrQcFlowVo emrQcFlowVo);

   List selectEmrQcListByMrFileId(@Param("mrFileId") Long mrFileId, @Param("list") List list);
}
