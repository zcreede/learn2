package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcListScore;
import java.util.List;

public interface EmrQcListScoreMapper {
   EmrQcListScore selectEmrQcListScoreById(Long id);

   List selectEmrQcListScoreList(EmrQcListScore emrQcListScore);

   int insertEmrQcListScore(EmrQcListScore emrQcListScore);

   int updateEmrQcListScore(EmrQcListScore emrQcListScore);

   int deleteEmrQcListScoreById(Long id);

   int deleteEmrQcListScoreByIds(Long[] ids);

   void deleteEmrQcListScoreByQcId(Long qcId);
}
