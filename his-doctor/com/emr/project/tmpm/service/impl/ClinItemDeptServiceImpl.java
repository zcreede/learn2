package com.emr.project.tmpm.service.impl;

import com.emr.project.tmpm.domain.ClinItemDept;
import com.emr.project.tmpm.mapper.ClinItemDeptMapper;
import com.emr.project.tmpm.service.IClinItemDeptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinItemDeptServiceImpl implements IClinItemDeptService {
   @Autowired
   private ClinItemDeptMapper clinItemDeptMapper;

   public ClinItemDept selectClinItemDeptById(String deptCd) {
      return this.clinItemDeptMapper.selectClinItemDeptById(deptCd);
   }

   public List selectClinItemDeptList(ClinItemDept clinItemDept) {
      return this.clinItemDeptMapper.selectClinItemDeptList(clinItemDept);
   }

   public int insertClinItemDept(ClinItemDept clinItemDept) {
      return this.clinItemDeptMapper.insertClinItemDept(clinItemDept);
   }

   public int updateClinItemDept(ClinItemDept clinItemDept) {
      return this.clinItemDeptMapper.updateClinItemDept(clinItemDept);
   }

   public int deleteClinItemDeptByIds(String[] deptCds) {
      return this.clinItemDeptMapper.deleteClinItemDeptByIds(deptCds);
   }

   public int deleteClinItemDeptById(String deptCd) {
      return this.clinItemDeptMapper.deleteClinItemDeptById(deptCd);
   }
}
