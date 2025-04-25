package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.tmpm.domain.DiagSetDetail;
import com.emr.project.tmpm.mapper.DiagSetDetailMapper;
import com.emr.project.tmpm.service.IDiagSetDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagSetDetailServiceImpl implements IDiagSetDetailService {
   @Autowired
   private DiagSetDetailMapper diagSetDetailMapper;
   @Autowired
   private ICommonService commonService;

   public DiagSetDetail selectDiagSetDetailById(Long id) {
      return this.diagSetDetailMapper.selectDiagSetDetailById(id);
   }

   public List selectDiagSetDetailList(DiagSetDetail diagSetDetail) {
      return this.diagSetDetailMapper.selectDiagSetDetailList(diagSetDetail);
   }

   public int insertDiagSetDetail(DiagSetDetail diagSetDetail) {
      return this.diagSetDetailMapper.insertDiagSetDetail(diagSetDetail);
   }

   public void insertDiagSetDetailList(List diagSetDetailList) throws Exception {
      this.diagSetDetailMapper.insertDiagSetDetailList(diagSetDetailList);
   }

   public int updateDiagSetDetail(DiagSetDetail diagSetDetail) {
      return this.diagSetDetailMapper.updateDiagSetDetail(diagSetDetail);
   }

   public int deleteDiagSetDetailByIds(Long[] ids) {
      return this.diagSetDetailMapper.deleteDiagSetDetailByIds(ids);
   }

   public int deleteDiagSetDetailById(Long id) {
      return this.diagSetDetailMapper.deleteDiagSetDetailById(id);
   }

   public List selectDetailListBySetCd(String setCd) throws Exception {
      return this.diagSetDetailMapper.selectDetailListBySetCd(setCd);
   }

   public void deleteDiagSetDetailBySetCd(String setCd) throws Exception {
      this.diagSetDetailMapper.deleteDiagSetDetailBySetCd(setCd);
   }

   public void saveDiagSetDetail(List diagSetDetailList) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      this.diagSetDetailMapper.deleteDiagSetDetailBySetCd(((DiagSetDetail)diagSetDetailList.get(0)).getSetCd());
      Integer sort = 1;

      for(DiagSetDetail detail : diagSetDetailList) {
         detail.setId(SnowIdUtils.uniqueLong());
         Integer var6 = sort;
         sort = sort + 1;
         detail.setSortNumber(var6);
         detail.setCreDate(this.commonService.getDbSysdate());
         detail.setCrePerCode(basEmployee.getEmplNumber());
         detail.setCrePerName(basEmployee.getEmplName());
      }

      this.insertDiagSetDetailList(diagSetDetailList);
   }
}
