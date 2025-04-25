package com.emr.project.docOrder.domain.vo;

import com.emr.project.esSearch.domain.DrugAndClin;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class DrugAndClinPatientVo extends DrugAndClin {
   private String patientId;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStartTime;
   private List orderSearchVoList;

   public List getOrderSearchVoList() {
      return this.orderSearchVoList;
   }

   public void setOrderSearchVoList(List orderSearchVoList) {
      this.orderSearchVoList = orderSearchVoList;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
   }
}
