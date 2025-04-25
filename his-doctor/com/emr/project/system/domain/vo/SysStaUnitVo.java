package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.SysStaUnit;

public class SysStaUnitVo extends SysStaUnit {
   private Long elemId;
   private String elemCd;
   private String elemName;
   private String elemInputstrphtc;
   private String isParagraph;
   private String contType;
   private String elemQua;
   private String typeFlag;
   private String sourFlag;
   private String validFlag;

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemInputstrphtc() {
      return this.elemInputstrphtc;
   }

   public void setElemInputstrphtc(String elemInputstrphtc) {
      this.elemInputstrphtc = elemInputstrphtc;
   }

   public String getIsParagraph() {
      return this.isParagraph;
   }

   public void setIsParagraph(String isParagraph) {
      this.isParagraph = isParagraph;
   }

   public String getContType() {
      return this.contType;
   }

   public void setContType(String contType) {
      this.contType = contType;
   }

   public String getElemQua() {
      return this.elemQua;
   }

   public void setElemQua(String elemQua) {
      this.elemQua = elemQua;
   }

   public String getTypeFlag() {
      return this.typeFlag;
   }

   public void setTypeFlag(String typeFlag) {
      this.typeFlag = typeFlag;
   }

   public String getSourFlag() {
      return this.sourFlag;
   }

   public void setSourFlag(String sourFlag) {
      this.sourFlag = sourFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }
}
