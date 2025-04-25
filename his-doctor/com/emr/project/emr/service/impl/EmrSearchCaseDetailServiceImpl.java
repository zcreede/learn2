package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.EmrSearchCaseDetail;
import com.emr.project.emr.mapper.EmrSearchCaseDetailMapper;
import com.emr.project.emr.service.IEmrSearchCaseDetailService;
import com.emr.project.system.domain.BasEmployee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrSearchCaseDetailServiceImpl implements IEmrSearchCaseDetailService {
   @Autowired
   private EmrSearchCaseDetailMapper emrSearchCaseDetailMapper;

   public EmrSearchCaseDetail selectEmrSearchCaseDetailById(Long id) {
      return this.emrSearchCaseDetailMapper.selectEmrSearchCaseDetailById(id);
   }

   public List selectEmrSearchCaseDetailList(EmrSearchCaseDetail emrSearchCaseDetail) {
      return this.emrSearchCaseDetailMapper.selectEmrSearchCaseDetailList(emrSearchCaseDetail);
   }

   public int insertEmrSearchCaseDetail(EmrSearchCaseDetail emrSearchCaseDetail) {
      return this.emrSearchCaseDetailMapper.insertEmrSearchCaseDetail(emrSearchCaseDetail);
   }

   public int updateEmrSearchCaseDetail(EmrSearchCaseDetail emrSearchCaseDetail) {
      return this.emrSearchCaseDetailMapper.updateEmrSearchCaseDetail(emrSearchCaseDetail);
   }

   public int deleteEmrSearchCaseDetailByIds(Long[] ids) {
      return this.emrSearchCaseDetailMapper.deleteEmrSearchCaseDetailByIds(ids);
   }

   public int deleteEmrSearchCaseDetailById(Long id) {
      return this.emrSearchCaseDetailMapper.deleteEmrSearchCaseDetailById(id);
   }

   public void deleteEmrSearchCaseDetailByCaseId(Long caseId) throws Exception {
      this.emrSearchCaseDetailMapper.deleteEmrSearchCaseDetailByCaseId(caseId);
   }

   public void insertEmrSearchCaseDetailList(List detailList, Long caseId) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();

      for(EmrSearchCaseDetail detail : detailList) {
         detail.setId(SnowIdUtils.uniqueLong());
         detail.setCaseId(caseId);
         detail.setCrePerCode(basEmployee.getEmplNumber());
         detail.setCrePerName(basEmployee.getEmplName());
      }

      this.emrSearchCaseDetailMapper.insertEmrSearchCaseDetailList(detailList);
   }

   public List selectEmrSearchCaseDetailByCaseId(Long caseId) throws Exception {
      return this.emrSearchCaseDetailMapper.selectEmrSearchCaseDetailByCaseId(caseId);
   }
}
