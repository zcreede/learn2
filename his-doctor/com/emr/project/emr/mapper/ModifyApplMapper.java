package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.ModifyAppl;
import com.emr.project.emr.domain.vo.ModifyApplVo;
import java.util.List;

public interface ModifyApplMapper {
   ModifyAppl selectModifyApplById(Long id);

   List selectModifyApplList(ModifyApplVo modifyApplVo);

   int insertModifyAppl(ModifyAppl modifyAppl);

   int updateModifyAppl(ModifyAppl modifyAppl);

   void updateModifyApplByIds(ModifyApplVo modifyApplVo);

   int deleteModifyApplById(Long id);

   int deleteModifyApplByIds(Long[] ids);

   List selectModifyApplByIds(List ids);

   ModifyAppl selectModifyApplByEndDate(ModifyAppl modifyAppl);

   List selectList(ModifyAppl modifyAppl);

   List selectModifyAppl(ModifyAppl modifyAppl);

   List selectSubFileAppls(ModifyApplVo modifyApplVo);

   ModifyAppl selectModifyApplByAppl(ModifyApplVo modifyParam);

   void updateModifyApplVo(ModifyApplVo modifyApplVo);
}
