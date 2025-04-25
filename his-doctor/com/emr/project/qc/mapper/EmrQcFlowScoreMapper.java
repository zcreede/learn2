package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcFlowScore;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrQcFlowScoreMapper {
   EmrQcFlowScore selectEmrQcFlowScoreById(Long id);

   List selectEmrQcFlowScoreList(EmrQcFlowScore emrQcFlowScore);

   int insertEmrQcFlowScore(EmrQcFlowScore emrQcFlowScore);

   int insertEmrQcFlowScoreList(List emrQcFlowScoreList);

   int updateEmrQcFlowScore(EmrQcFlowScore emrQcFlowScore);

   int deleteEmrQcFlowScoreById(Long id);

   int deleteEmrQcFlowScoreByIds(Long[] ids);

   List selectEmrScoreList(EmrQcFlowScore emrQcFlowScore);

   void deleteEmrQcFlowScoreByQcId(@Param("patientId") String patientId, @Param("qcId") Long qcId);
}
