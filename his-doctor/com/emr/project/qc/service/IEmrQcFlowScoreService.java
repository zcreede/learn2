package com.emr.project.qc.service;

import com.emr.project.qc.domain.EmrQcFlowScore;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import java.util.List;

public interface IEmrQcFlowScoreService {
   EmrQcFlowScore selectEmrQcFlowScoreById(Long id);

   List selectEmrQcFlowScoreList(EmrQcFlowScore emrQcFlowScore);

   int insertEmrQcFlowScore(EmrQcFlowScore emrQcFlowScore);

   int updateEmrQcFlowScore(EmrQcFlowScore emrQcFlowScore);

   int deleteEmrQcFlowScoreByIds(Long[] ids);

   int deleteEmrQcFlowScoreById(Long id);

   EmrQcFlowScoreVo createScoreRecordList(EmrQcFlowScoreVo emrQcFlowScoreVo) throws Exception;

   EmrQcFlowScoreVo deptScoreRecordList(EmrQcFlowScoreVo emrQcFlowScoreVo) throws Exception;

   void saveEmrQcFlowScoreVo(EmrQcFlowScoreVo emrQcFlowScoreVo) throws Exception;

   void deleteFromEmrQcFlowScore();

   void deleteEmrQcListScoreAndListByQcId(String patientId, Long qcId);
}
