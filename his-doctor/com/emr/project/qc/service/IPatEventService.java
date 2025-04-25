package com.emr.project.qc.service;

import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.vo.PatEventVo;
import java.util.List;

public interface IPatEventService {
   PatEvent selectPatEventById(Long id);

   List selectPatEventList(PatEvent patEvent);

   int insertPatEvent(PatEvent patEvent);

   void insertList(List list);

   int updatePatEvent(PatEvent patEvent);

   int deletePatEventByIds(Long[] ids);

   int deletePatEventById(Long id);

   List selectByEventCodeAndBeginTime(String eventCode, Integer afterBeginHour) throws Exception;

   List selectListByEventCodeAndBeginTime(PatEventVo patEventVo) throws Exception;

   List selectListByEventCode(PatEventVo patEventVo) throws Exception;

   List selectListByEventCodeNew(PatEventVo patEventVo) throws Exception;

   List selectListByPatient(PatEventVo patEventVo) throws Exception;

   void addPatientEvent(List list) throws Exception;

   void delPatientEventByOrderNos(List orderNoList) throws Exception;

   List selectTestAlertList(String beginDate, String endDate) throws Exception;

   List selectExamAlertList(String beginDate, String endDate) throws Exception;

   List selectOrderList(String beginDate, String endDate) throws Exception;
}
