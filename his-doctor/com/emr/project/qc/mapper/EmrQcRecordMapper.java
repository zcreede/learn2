package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.RunTimeQcCheckedVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrQcRecordMapper {
   EmrQcRecord selectEmrQcRecordById(Long id);

   EmrQcRecord selectEmrQcRecordByPatientSection(@Param("patientId") String patientId, @Param("qcSection") String qcSection, @Param("qcBillNo") Long qcBillNo);

   List selectEmrQcRecordByPatientId(@Param("patientId") String patientId);

   List selectEmrQcRecordList(EmrQcRecord emrQcRecord);

   int insertEmrQcRecord(EmrQcRecord emrQcRecord);

   int updateEmrQcRecord(EmrQcRecord emrQcRecord);

   int deleteEmrQcRecordById(Long id);

   int deleteEmrQcRecordByIds(Long[] ids);

   List selectRunTimeQcChecked(RunTimeQcCheckedVo runTimeQcCheckedVo);

   List selectByPatientRunTimeQcNum(List patientIdList, String qcSection);

   RunTimeQcCheckedVo selectRunTimeQcCheckedOne(RunTimeQcCheckedVo runTimeQcCheckedVo);

   List selectListByQcBillNos(List qcBillNoList);

   void insertList(List list);

   List selectQcReturnList(String docCode, String patientId);

   EmrQcRecord selectEmrQcRecordByQcSection(@Param("qcSection") String qcSection, @Param("patientId") String patientId);
}
