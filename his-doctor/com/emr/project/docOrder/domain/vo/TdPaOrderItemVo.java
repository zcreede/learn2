package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import java.util.List;

public class TdPaOrderItemVo extends TdPaOrderItem {
   private String hospitalCode;
   private List orderNoList;
   private List stateList;
   private List drugAndClinList;
   private String performDepCode;
   private String performDepName;
   private String babyAdmissionNo;
   private String operationType;
   private String operationTypeName;
   private VisitinfoVo visitinfoVo;
   private List detailList;
   private List cpNoList;
   private String tableName;
   private String chargeNo;

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public VisitinfoVo getVisitinfoVo() {
      return this.visitinfoVo;
   }

   public void setVisitinfoVo(VisitinfoVo visitinfoVo) {
      this.visitinfoVo = visitinfoVo;
   }

   public String getOperationType() {
      return this.operationType;
   }

   public void setOperationType(String operationType) {
      this.operationType = operationType;
   }

   public String getOperationTypeName() {
      return this.operationTypeName;
   }

   public void setOperationTypeName(String operationTypeName) {
      this.operationTypeName = operationTypeName;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getPerformDepName() {
      return this.performDepName;
   }

   public void setPerformDepName(String performDepName) {
      this.performDepName = performDepName;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public List getDrugAndClinList() {
      return this.drugAndClinList;
   }

   public void setDrugAndClinList(List drugAndClinList) {
      this.drugAndClinList = drugAndClinList;
   }

   public List getStateList() {
      return this.stateList;
   }

   public void setStateList(List stateList) {
      this.stateList = stateList;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }

   public List getOrderNoList() {
      return this.orderNoList;
   }

   public void setOrderNoList(List orderNoList) {
      this.orderNoList = orderNoList;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public List getCpNoList() {
      return this.cpNoList;
   }

   public void setCpNoList(List cpNoList) {
      this.cpNoList = cpNoList;
   }

   public String getTableName() {
      return this.tableName;
   }

   public void setTableName(String tableName) {
      this.tableName = tableName;
   }
}
