package com.emr.project.emr.service;

import com.emr.project.emr.domain.ModifyAppl;
import com.emr.project.emr.domain.vo.ModifyApplVo;
import java.util.List;

public interface IModifyApplService {
   ModifyAppl selectModifyApplById(Long id);

   List selectModifyApplByIds(List ids);

   List selectModifyApplList(ModifyApplVo modifyApplVo) throws Exception;

   List selectModifyAppl(ModifyAppl modifyAppl) throws Exception;

   List selectUnRecordApplList(ModifyApplVo modifyApplVo) throws Exception;

   List selectRecordApplList(ModifyApplVo modifyApplVo);

   List selectUnWriteApplList(ModifyApplVo modifyApplVo);

   int insertModifyAppl(ModifyAppl modifyAppl) throws Exception;

   int updateModifyAppl(ModifyAppl modifyAppl);

   int deleteModifyApplByIds(Long[] ids);

   int deleteModifyApplById(Long id);

   void doApproved(ModifyAppl modifyAppl) throws Exception;

   void doApproveds(ModifyApplVo modifyApplVo) throws Exception;

   ModifyAppl selectModifyApplByEndDate(Long appReasonCd, String conState, Long mrFileId, String appDocCd, String appDeptCd);

   List selectList(ModifyAppl modifyAppl) throws Exception;

   List selectSubFileAppls(ModifyApplVo modifyApplVo) throws Exception;

   ModifyAppl selectModifyApplByAppl(ModifyAppl modifyAppl) throws Exception;

   void updateModifyApplVo(ModifyApplVo modifyApplVo);
}
