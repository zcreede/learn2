package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.vo.PatEventVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PatEventMapper {
   PatEvent selectPatEventById(Long id);

   List selectPatEventList(PatEvent patEvent);

   int insertPatEvent(PatEvent patEvent);

   void insertList(List list);

   int updatePatEvent(PatEvent patEvent);

   int deletePatEventById(Long id);

   int deletePatEventByIds(Long[] ids);

   List selectByEventCodeAndBeginTime(PatEventVo patEventVo);

   List selectListByEventCodeAndBeginTime(PatEventVo patEventVo);

   List selectListByEventCode(PatEventVo patEventVo);

   List selectListByEventCodeNew(PatEventVo patEventVo);

   List selectListByPatient(PatEventVo patEventVo);

   List selectListByPatientAndEvent(List patientIdList, List eventCodeList);

   void delPatientEventByOrderNos(List list);

   List selectTestAlertList(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

   List selectExamAlertList(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

   List selectRescueOrderList(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

   List selectBloodOrderList(@Param("beginDate") String beginDate, @Param("endDate") String endDate);
}
