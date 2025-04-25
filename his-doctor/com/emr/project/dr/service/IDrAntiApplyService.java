package com.emr.project.dr.service;

import com.emr.project.dr.domain.DrAntiApply;
import com.emr.project.dr.domain.vo.DrAntiApplyVo;
import java.util.List;

public interface IDrAntiApplyService {
   DrAntiApply selectDrAntiApplyById(Long id);

   List selectDrAntiApplyList(DrAntiApplyVo drAntiApplyVo) throws Exception;

   int insertDrAntiApply(DrAntiApply drAntiApply) throws Exception;

   int updateDrAntiApply(DrAntiApply drAntiApply) throws Exception;

   int deleteDrAntiApplyByIds(Long[] ids);

   int deleteDrAntiApplyById(Long id) throws Exception;

   List selectDrAntiDrugVoList(DrAntiApplyVo drAntiApplyVo) throws Exception;

   void saveDrAntiApply(DrAntiApply drAntiApply) throws Exception;

   List selectAuditList(DrAntiApplyVo drAntiApplyVo, Boolean flag) throws Exception;

   void updateAuditState(DrAntiApplyVo drAntiApplyVo) throws Exception;

   List selectYetAuditList(DrAntiApplyVo drAntiApplyVo, Boolean flag) throws Exception;

   List selectByPatientAndDrugCode(DrAntiApply drAntiApply) throws Exception;

   List selectByPatientAndDrugCodes(String patientId, List drugCodeList) throws Exception;
}
