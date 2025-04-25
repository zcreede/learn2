package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcFlowRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowRecordVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrQcFlowRecordMapper {
   EmrQcFlowRecord selectEmrQcFlowRecordById(Long id);

   List selectEmrQcFlowRecordByQcSns(List list);

   List selectEmrQcFlowRecordList(EmrQcFlowRecord emrQcFlowRecord);

   List selectEmrQcFlowRecordVoList(EmrQcFlowRecordVo emrQcFlowRecordVo);

   int insertEmrQcFlowRecord(EmrQcFlowRecord emrQcFlowRecord);

   int updateEmrQcFlowRecord(EmrQcFlowRecord emrQcFlowRecord);

   int deleteEmrQcFlowRecordById(Long id);

   int deleteEmrQcFlowRecordByIds(Long[] ids);

   List selectEmrQcFlowRecordByPatientId(@Param("patientId") String patientId, @Param("operTypeCd") String operTypeCd);
}
