package com.emr.project.mrhp.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class PrintZyDiagInfoVo implements Serializable {
   @ApiModelProperty(
      hidden = true
   )
   private String diagZyClassName;
   @ApiModelProperty(
      hidden = true
   )
   private String diagZyName;
   @ApiModelProperty(
      hidden = true
   )
   private String diagZyCode;
   @ApiModelProperty(
      hidden = true
   )
   private String inHosCondZyCd;

   public String getDiagZyClassName() {
      return this.diagZyClassName;
   }

   public void setDiagZyClassName(String diagZyClassName) {
      this.diagZyClassName = diagZyClassName;
   }

   public String getDiagZyName() {
      return this.diagZyName;
   }

   public void setDiagZyName(String diagZyName) {
      this.diagZyName = diagZyName;
   }

   public String getDiagZyCode() {
      return this.diagZyCode;
   }

   public void setDiagZyCode(String diagZyCode) {
      this.diagZyCode = diagZyCode;
   }

   public String getInHosCondZyCd() {
      return this.inHosCondZyCd;
   }

   public void setInHosCondZyCd(String inHosCondZyCd) {
      this.inHosCondZyCd = inHosCondZyCd;
   }
}
