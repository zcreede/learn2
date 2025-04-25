package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import java.util.List;

public interface TdCaConsApplyMapper {
   TdCaConsApply selectTdCaConsApplyById(Long id);

   TdCaConsApply selectTdCaConsApplyByMrFileId(Long mrFileId);

   List selectTdCaConsApplyList(TdCaConsApplyVo tdCaConsApplyVo);

   List selectTdCaConsApplyStatisList(TdCaConsApplyVo tdCaConsApplyVo);

   void insertTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo);

   int updateTdCaConsApply(TdCaConsApply tdCaConsApply);

   int deleteTdCaConsApplyById(Long id);

   int deleteTdCaConsApplyByIds(Long[] ids);

   List selectUnFinishConsList(TdCaConsApplyVo tdCaConsApplyVo);

   List selectListByIds(List idList);

   void updateStatusByIds(List idList, String state);

   void updateToReturn(TdCaConsApply tdCaConsApply);
}
