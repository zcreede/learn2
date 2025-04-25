package com.emr.project.qc.service.impl;

import com.emr.project.qc.domain.EmrQcListScore;
import com.emr.project.qc.mapper.EmrQcListScoreMapper;
import com.emr.project.qc.service.IEmrQcListScoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrQcListScoreServiceImpl implements IEmrQcListScoreService {
   @Autowired
   private EmrQcListScoreMapper emrQcListScoreMapper;

   public EmrQcListScore selectEmrQcListScoreById(Long id) {
      return this.emrQcListScoreMapper.selectEmrQcListScoreById(id);
   }

   public List selectEmrQcListScoreList(EmrQcListScore emrQcListScore) {
      return this.emrQcListScoreMapper.selectEmrQcListScoreList(emrQcListScore);
   }

   public int insertEmrQcListScore(EmrQcListScore emrQcListScore) {
      return this.emrQcListScoreMapper.insertEmrQcListScore(emrQcListScore);
   }

   public int updateEmrQcListScore(EmrQcListScore emrQcListScore) {
      return this.emrQcListScoreMapper.updateEmrQcListScore(emrQcListScore);
   }

   public int deleteEmrQcListScoreByIds(Long[] ids) {
      return this.emrQcListScoreMapper.deleteEmrQcListScoreByIds(ids);
   }

   public int deleteEmrQcListScoreById(Long id) {
      return this.emrQcListScoreMapper.deleteEmrQcListScoreById(id);
   }

   public void deleteEmrQcListScoreByQcId(Long qcId) throws Exception {
      this.emrQcListScoreMapper.deleteEmrQcListScoreByQcId(qcId);
   }
}
