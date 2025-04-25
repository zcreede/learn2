package com.emr.project.sys.domain.vo;

import com.emr.project.sys.domain.BusIntegMenu;
import java.util.List;

public class BusIntegMenuTreeVo extends BusIntegMenu {
   List children;
   private String hisInhosTimeStr;
   private String patientId;
   private String visitNo;
   private String inpNo;
   private Integer visitId;
   private String mrType;
   private String colorFlag;
   private String openFlag;
   private String indexType;
   private String emrTypeCode;
   private String emrTypeName;
   private String consFlag;
   private String isGoldenFlag;
   private String mzh;
   private Long proofId;

   public Long getProofId() {
      return this.proofId;
   }

   public void setProofId(Long proofId) {
      this.proofId = proofId;
   }

   public String getMzh() {
      return this.mzh;
   }

   public void setMzh(String mzh) {
      this.mzh = mzh;
   }

   public String getIsGoldenFlag() {
      return this.isGoldenFlag;
   }

   public void setIsGoldenFlag(String isGoldenFlag) {
      this.isGoldenFlag = isGoldenFlag;
   }

   public String getConsFlag() {
      return this.consFlag;
   }

   public void setConsFlag(String consFlag) {
      this.consFlag = consFlag;
   }

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getIndexType() {
      return this.indexType;
   }

   public void setIndexType(String indexType) {
      this.indexType = indexType;
   }

   public String getOpenFlag() {
      return this.openFlag;
   }

   public void setOpenFlag(String openFlag) {
      this.openFlag = openFlag;
   }

   public BusIntegMenuTreeVo() {
   }

   public String getVisitNo() {
      return this.visitNo;
   }

   public void setVisitNo(String visitNo) {
      this.visitNo = visitNo;
   }

   public BusIntegMenuTreeVo(BusIntegMenu busIntegMenu) {
      super.setId(busIntegMenu.getId());
      super.setCode(busIntegMenu.getCode());
      super.setName(busIntegMenu.getName());
      super.setTypeCode(busIntegMenu.getTypeCode());
      super.setLoadMode(busIntegMenu.getLoadMode());
      super.setRoutePath(busIntegMenu.getRoutePath());
      super.setIsValid(busIntegMenu.getIsValid());
      super.setCrePerCode(busIntegMenu.getCrePerCode());
      super.setCrePerName(busIntegMenu.getCrePerName());
      super.setCreDate(busIntegMenu.getCreDate());
      super.setAltPerCode(busIntegMenu.getAltPerCode());
      super.setAltPerName(busIntegMenu.getAltPerName());
      super.setAltDate(busIntegMenu.getAltDate());
      super.setMenuClass(busIntegMenu.getMenuClass());
      super.setMenuOrder(busIntegMenu.getMenuOrder());
      super.setParentId(busIntegMenu.getParentId());
      super.setBrowserType(busIntegMenu.getBrowserType());
      super.setBrowserTypeName(busIntegMenu.getBrowserTypeName());
   }

   public String getColorFlag() {
      return this.colorFlag;
   }

   public void setColorFlag(String colorFlag) {
      this.colorFlag = colorFlag;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public String getHisInhosTimeStr() {
      return this.hisInhosTimeStr;
   }

   public void setHisInhosTimeStr(String hisInhosTimeStr) {
      this.hisInhosTimeStr = hisInhosTimeStr;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Integer getVisitId() {
      return this.visitId;
   }

   public void setVisitId(Integer visitId) {
      this.visitId = visitId;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }
}
