package com.emr.project.emr.domain.resp;

import io.swagger.annotations.ApiModelProperty;

public class TertiaryIndexResp {
   @ApiModelProperty("病历索引id")
   private Long id;
   @ApiModelProperty("病历模板名称")
   private String mrFileShowName;
   @ApiModelProperty("指标编码")
   private String elemCd;
   @ApiModelProperty("指标名称")
   private String elemName;
   @ApiModelProperty("指标内容")
   private String elemCon;
   @ApiModelProperty("患者标识")
   private String patientId;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getMrFileShowName() {
      return this.mrFileShowName;
   }

   public void setMrFileShowName(String mrFileShowName) {
      this.mrFileShowName = mrFileShowName;
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

   public String getElemCon() {
      return this.elemCon;
   }

   public void setElemCon(String elemCon) {
      this.elemCon = elemCon;
   }
}
