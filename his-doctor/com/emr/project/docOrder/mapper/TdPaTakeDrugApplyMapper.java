package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaTakeDrugApply;
import com.emr.project.docOrder.domain.vo.TdPaTakeDrugApplyVo;
import java.util.List;

public interface TdPaTakeDrugApplyMapper {
   TdPaTakeDrugApply selectTdPaTakeDrugApplyById(Long id);

   List selectTdPaTakeDrugApplyList(TdPaTakeDrugApply tdPaTakeDrugApply);

   int insertTdPaTakeDrugApply(TdPaTakeDrugApply tdPaTakeDrugApply);

   int updateTdPaTakeDrugApply(TdPaTakeDrugApply tdPaTakeDrugApply);

   int deleteTdPaTakeDrugApplyById(Long id);

   int deleteTdPaTakeDrugApplyByIds(Long[] ids);

   TdPaTakeDrugApply selectLastApply(TdPaTakeDrugApplyVo tdPaTakeDrugApplyVo);
}
