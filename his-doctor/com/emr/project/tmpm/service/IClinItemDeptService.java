package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.ClinItemDept;
import java.util.List;

public interface IClinItemDeptService {
   ClinItemDept selectClinItemDeptById(String deptCd);

   List selectClinItemDeptList(ClinItemDept clinItemDept);

   int insertClinItemDept(ClinItemDept clinItemDept);

   int updateClinItemDept(ClinItemDept clinItemDept);

   int deleteClinItemDeptByIds(String[] deptCds);

   int deleteClinItemDeptById(String deptCd);
}
