package com.emr.project.sys.domain.vo;

import java.util.List;

public class BusIntegMenuSearchVo {
   private String patientId;
   private String visitNo;
   private List indexStateList;
   private String indexNofinishQcRedColorFlag;
   private String indexOpenFlag;
   private List mrHpStateList;
   private Long recordId;
   private String qcSection;

   public String getVisitNo() {
      return this.visitNo;
   }

   public void setVisitNo(String visitNo) {
      this.visitNo = visitNo;
   }

   public String getQcSection() {
      return this.qcSection;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }

   public Long getRecordId() {
      return this.recordId;
   }

   public void setRecordId(Long recordId) {
      this.recordId = recordId;
   }

   public List getMrHpStateList() {
      return this.mrHpStateList;
   }

   public void setMrHpStateList(List mrHpStateList) {
      this.mrHpStateList = mrHpStateList;
   }

   public String getIndexOpenFlag() {
      return this.indexOpenFlag;
   }

   public void setIndexOpenFlag(String indexOpenFlag) {
      this.indexOpenFlag = indexOpenFlag;
   }

   public BusIntegMenuSearchVo() {
   }

   public BusIntegMenuSearchVo(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public List getIndexStateList() {
      return this.indexStateList;
   }

   public void setIndexStateList(List indexStateList) {
      this.indexStateList = indexStateList;
   }

   public String getIndexNofinishQcRedColorFlag() {
      return this.indexNofinishQcRedColorFlag;
   }

   public void setIndexNofinishQcRedColorFlag(String indexNofinishQcRedColorFlag) {
      this.indexNofinishQcRedColorFlag = indexNofinishQcRedColorFlag;
   }
}
