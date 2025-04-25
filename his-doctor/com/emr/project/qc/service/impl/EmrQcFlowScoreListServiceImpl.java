package com.emr.project.qc.service.impl;

import com.emr.project.qc.domain.EmrQcFlowScoreList;
import com.emr.project.qc.mapper.EmrQcFlowScoreListMapper;
import com.emr.project.qc.service.IEmrQcFlowScoreListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrQcFlowScoreListServiceImpl implements IEmrQcFlowScoreListService {
   @Autowired
   private EmrQcFlowScoreListMapper emrQcFlowScoreListMapper;

   public EmrQcFlowScoreList selectEmrQcFlowScoreListById(Long id) {
      return this.emrQcFlowScoreListMapper.selectEmrQcFlowScoreListById(id);
   }

   public List selectEmrQcFlowScoreListList(EmrQcFlowScoreList emrQcFlowScoreList) {
      return this.emrQcFlowScoreListMapper.selectEmrQcFlowScoreListList(emrQcFlowScoreList);
   }

   public int insertEmrQcFlowScoreList(EmrQcFlowScoreList emrQcFlowScoreList) {
      return this.emrQcFlowScoreListMapper.insertEmrQcFlowScoreList(emrQcFlowScoreList);
   }

   public int updateEmrQcFlowScoreList(EmrQcFlowScoreList emrQcFlowScoreList) {
      return this.emrQcFlowScoreListMapper.updateEmrQcFlowScoreList(emrQcFlowScoreList);
   }

   public int deleteEmrQcFlowScoreListByIds(Long[] ids) {
      return this.emrQcFlowScoreListMapper.deleteEmrQcFlowScoreListByIds(ids);
   }

   public int deleteEmrQcFlowScoreListById(Long id) {
      return this.emrQcFlowScoreListMapper.deleteEmrQcFlowScoreListById(id);
   }
}
