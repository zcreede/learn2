package com.emr.project.qc.service;

import com.emr.project.qc.domain.EmrQcListScore;
import java.util.List;

public interface IEmrQcListScoreService {
   EmrQcListScore selectEmrQcListScoreById(Long id);

   List selectEmrQcListScoreList(EmrQcListScore emrQcListScore);

   int insertEmrQcListScore(EmrQcListScore emrQcListScore);

   int updateEmrQcListScore(EmrQcListScore emrQcListScore);

   int deleteEmrQcListScoreByIds(Long[] ids);

   int deleteEmrQcListScoreById(Long id);

   void deleteEmrQcListScoreByQcId(Long qcId) throws Exception;
}
