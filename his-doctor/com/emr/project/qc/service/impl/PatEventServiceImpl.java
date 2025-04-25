package com.emr.project.qc.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.mapper.PatEventMapper;
import com.emr.project.qc.service.IPatEventService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatEventServiceImpl implements IPatEventService {
   @Autowired
   private PatEventMapper patEventMapper;

   public PatEvent selectPatEventById(Long id) {
      return this.patEventMapper.selectPatEventById(id);
   }

   public List selectPatEventList(PatEvent patEvent) {
      return this.patEventMapper.selectPatEventList(patEvent);
   }

   public int insertPatEvent(PatEvent patEvent) {
      return this.patEventMapper.insertPatEvent(patEvent);
   }

   public void insertList(List list) {
      if (list != null && !list.isEmpty()) {
         this.patEventMapper.insertList(list);
      }

   }

   public int updatePatEvent(PatEvent patEvent) {
      return this.patEventMapper.updatePatEvent(patEvent);
   }

   public int deletePatEventByIds(Long[] ids) {
      return this.patEventMapper.deletePatEventByIds(ids);
   }

   public int deletePatEventById(Long id) {
      return this.patEventMapper.deletePatEventById(id);
   }

   public List selectByEventCodeAndBeginTime(String eventCode, Integer afterBeginHour) throws Exception {
      PatEventVo patEventVo = new PatEventVo();
      patEventVo.setEventCode(eventCode);
      patEventVo.setAfterBeginHour(afterBeginHour);
      return this.patEventMapper.selectByEventCodeAndBeginTime(patEventVo);
   }

   public List selectListByEventCodeAndBeginTime(PatEventVo patEventVo) throws Exception {
      return this.patEventMapper.selectListByEventCodeAndBeginTime(patEventVo);
   }

   public List selectListByEventCode(PatEventVo patEventVo) {
      return this.patEventMapper.selectListByEventCode(patEventVo);
   }

   public List selectListByEventCodeNew(PatEventVo patEventVo) {
      return this.patEventMapper.selectListByEventCodeNew(patEventVo);
   }

   public List selectListByPatient(PatEventVo patEventVo) throws Exception {
      return this.patEventMapper.selectListByPatient(patEventVo);
   }

   public void addPatientEvent(List list) throws Exception {
      List<String> patientIdList = (List)list.stream().map((t) -> t.getPatientId()).distinct().collect(Collectors.toList());
      List<String> eventCodeList = (List)list.stream().map((t) -> t.getEventCode()).distinct().collect(Collectors.toList());
      this.patEventMapper.selectListByPatientAndEvent(patientIdList, eventCodeList);
   }

   public void delPatientEventByOrderNos(List orderNoList) throws Exception {
      if (orderNoList != null && !orderNoList.isEmpty()) {
         this.patEventMapper.delPatientEventByOrderNos(orderNoList);
      }

   }

   public List selectTestAlertList(String beginDate, String endDate) throws Exception {
      List<PatEvent> patEvents = this.patEventMapper.selectTestAlertList(beginDate, endDate);
      if (!patEvents.isEmpty()) {
         for(PatEvent pat : patEvents) {
            pat.setId(SnowIdUtils.uniqueLong());
         }
      }

      return patEvents;
   }

   public List selectExamAlertList(String beginDate, String endDate) throws Exception {
      List<PatEvent> patEvents = this.patEventMapper.selectExamAlertList(beginDate, endDate);
      if (!patEvents.isEmpty()) {
         for(PatEvent pat : patEvents) {
            pat.setId(SnowIdUtils.uniqueLong());
         }
      }

      return patEvents;
   }

   public List selectOrderList(String beginDate, String endDate) throws Exception {
      List<PatEvent> orderList = new ArrayList();
      List<PatEvent> rescueOrderList = this.patEventMapper.selectRescueOrderList(beginDate, endDate);
      if (!rescueOrderList.isEmpty()) {
         for(PatEvent pat : rescueOrderList) {
            pat.setId(SnowIdUtils.uniqueLong());
            orderList.add(pat);
         }
      }

      List<PatEvent> bloodOrderList = this.patEventMapper.selectBloodOrderList(beginDate, endDate);
      if (!bloodOrderList.isEmpty()) {
         for(PatEvent pat : bloodOrderList) {
            pat.setId(SnowIdUtils.uniqueLong());
            orderList.add(pat);
         }
      }

      return orderList;
   }
}
