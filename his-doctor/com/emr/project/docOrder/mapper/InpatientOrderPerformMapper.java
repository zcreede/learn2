package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.vo.InpatientOrderPerformVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InpatientOrderPerformMapper {
   List selectInpatientOrderPerform(InpatientOrderPerformVo inpatientOrderPerformVo);

   List selectSkinTestResultByPatient(String admissionNo);

   List selectHisPatientOrderPerformList(@Param("deptList") List deptList);

   int deletePatientOrderPerformByPerformListNos(@Param("orderPerformList") List orderPerformList);
}
