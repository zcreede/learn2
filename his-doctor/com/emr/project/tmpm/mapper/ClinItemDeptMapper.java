package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.ClinItemDept;
import java.util.List;

public interface ClinItemDeptMapper {
   ClinItemDept selectClinItemDeptById(String deptCd);

   List selectClinItemDeptList(ClinItemDept clinItemDept);

   int insertClinItemDept(ClinItemDept clinItemDept);

   int updateClinItemDept(ClinItemDept clinItemDept);

   int deleteClinItemDeptById(String deptCd);

   int deleteClinItemDeptByIds(String[] deptCds);
}
