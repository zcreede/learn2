package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaTakeDrugApply;
import java.util.Date;
import java.util.List;

public interface ITdPaTakeDrugApplyService {
   TdPaTakeDrugApply selectTdPaTakeDrugApplyById(Long id);

   List selectTdPaTakeDrugApplyList(TdPaTakeDrugApply tdPaTakeDrugApply);

   int insertTdPaTakeDrugApply(TdPaTakeDrugApply tdPaTakeDrugApply);

   int updateTdPaTakeDrugApply(TdPaTakeDrugApply tdPaTakeDrugApply);

   int deleteTdPaTakeDrugApplyByIds(Long[] ids);

   int deleteTdPaTakeDrugApplyById(Long id);

   TdPaTakeDrugApply selectLastApply(String orgCode, String deptCode, Date currDate) throws Exception;
}
