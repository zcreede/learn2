package com.emr.project.qc.service;

import com.emr.project.qc.domain.EmrQcFlowScoreList;
import java.util.List;

public interface IEmrQcFlowScoreListService {
   EmrQcFlowScoreList selectEmrQcFlowScoreListById(Long id);

   List selectEmrQcFlowScoreListList(EmrQcFlowScoreList emrQcFlowScoreList);

   int insertEmrQcFlowScoreList(EmrQcFlowScoreList emrQcFlowScoreList);

   int updateEmrQcFlowScoreList(EmrQcFlowScoreList emrQcFlowScoreList);

   int deleteEmrQcFlowScoreListByIds(Long[] ids);

   int deleteEmrQcFlowScoreListById(Long id);
}
