package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.DrAntiApply;
import com.emr.project.dr.domain.vo.DrAntiApplyVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DrAntiApplyMapper {
   DrAntiApply selectDrAntiApplyById(Long id);

   List selectDrAntiApplyList(DrAntiApplyVo drAntiApplyVo);

   int insertDrAntiApply(DrAntiApply drAntiApply);

   int updateDrAntiApply(DrAntiApply drAntiApply);

   int deleteDrAntiApplyById(Long id);

   int deleteDrAntiApplyByIds(Long[] ids);

   List selectSqlStrList(DrAntiApplyVo drAntiApplyVo);

   void updateAuditState(DrAntiApplyVo drAntiApplyVo);

   List selectByPatientAndDrugCode(DrAntiApply drAntiApply);

   List selectByPatientAndDrugCodes(@Param("patientId") String patientId, @Param("drugCodeList") List drugCodeList);
}
