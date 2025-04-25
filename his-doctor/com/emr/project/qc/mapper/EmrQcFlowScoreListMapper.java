package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcFlowScoreList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrQcFlowScoreListMapper {
   EmrQcFlowScoreList selectEmrQcFlowScoreListById(Long id);

   List selectEmrQcFlowScoreListList(EmrQcFlowScoreList emrQcFlowScoreList);

   int insertEmrQcFlowScoreList(EmrQcFlowScoreList emrQcFlowScoreList);

   void insertEmrQcFlowScoreLists(List emrQcFlowScoreListVos);

   int updateEmrQcFlowScoreList(EmrQcFlowScoreList emrQcFlowScoreList);

   int deleteEmrQcFlowScoreListById(Long id);

   int deleteEmrQcFlowScoreListByIds(Long[] ids);

   List selectScoreListDescList(String qcSn);

   void deleteEmrQcFlowScoreByQcId(@Param("qcId") Long qcId);
}
