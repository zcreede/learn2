package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.EmrSearchCaseDetail;
import com.emr.project.emr.domain.EmrSearchHistoryDetail;
import com.emr.project.emr.mapper.EmrSearchHistoryDetailMapper;
import com.emr.project.emr.service.IEmrSearchHistoryDetailService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrSearchHistoryDetailServiceImpl implements IEmrSearchHistoryDetailService {
   @Autowired
   private EmrSearchHistoryDetailMapper emrSearchHistoryDetailMapper;

   public EmrSearchHistoryDetail selectEmrSearchHistoryDetailById(Long id) {
      return this.emrSearchHistoryDetailMapper.selectEmrSearchHistoryDetailById(id);
   }

   public List selectEmrSearchHistoryDetailList(EmrSearchHistoryDetail emrSearchHistoryDetail) {
      return this.emrSearchHistoryDetailMapper.selectEmrSearchHistoryDetailList(emrSearchHistoryDetail);
   }

   public int insertEmrSearchHistoryDetail(EmrSearchHistoryDetail emrSearchHistoryDetail) {
      return this.emrSearchHistoryDetailMapper.insertEmrSearchHistoryDetail(emrSearchHistoryDetail);
   }

   public void insertEmrSearchHistoryDetailList(List emrSearchHistoryDetailList, Long historyId) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<EmrSearchHistoryDetail> insertList = new ArrayList();

      for(EmrSearchCaseDetail detail : emrSearchHistoryDetailList) {
         detail.setId(SnowIdUtils.uniqueLong());
         detail.setCaseId(historyId);
         detail.setCrePerCode(basEmployee.getEmplNumber());
         detail.setCrePerName(basEmployee.getEmplName());
         EmrSearchHistoryDetail emrSearchHistoryDetail = new EmrSearchHistoryDetail(detail);
         insertList.add(emrSearchHistoryDetail);
      }

      this.emrSearchHistoryDetailMapper.insertEmrSearchHistoryDetailList(insertList);
   }

   public int updateEmrSearchHistoryDetail(EmrSearchHistoryDetail emrSearchHistoryDetail) {
      return this.emrSearchHistoryDetailMapper.updateEmrSearchHistoryDetail(emrSearchHistoryDetail);
   }

   public int deleteEmrSearchHistoryDetailByIds(Long[] ids) {
      return this.emrSearchHistoryDetailMapper.deleteEmrSearchHistoryDetailByIds(ids);
   }

   public int deleteEmrSearchHistoryDetailById(Long id) {
      return this.emrSearchHistoryDetailMapper.deleteEmrSearchHistoryDetailById(id);
   }

   public List selectEmrSearchHistoryDetailByHistoryId(Long historyId) throws Exception {
      return this.emrSearchHistoryDetailMapper.selectEmrSearchHistoryDetailByHistoryId(historyId);
   }

   public void deleteEmrSearchHistoryDetailByHisId(Long historyId) throws Exception {
      this.emrSearchHistoryDetailMapper.deleteEmrSearchHistoryDetailByHisId(historyId);
   }
}
