package com.emr.project.mrhp.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class PrintXyDiagInfoVo implements Serializable {
   @ApiModelProperty(
      hidden = true
   )
   private String diagXyClassName;
   @ApiModelProperty(
      hidden = true
   )
   private String diagXyName;
   @ApiModelProperty(
      hidden = true
   )
   private String diagXyCode;
   @ApiModelProperty(
      hidden = true
   )
   private String inHosCondXyCd;

   public String getDiagXyClassName() {
      return this.diagXyClassName;
   }

   public void setDiagXyClassName(String diagXyClassName) {
      this.diagXyClassName = diagXyClassName;
   }

   public String getDiagXyName() {
      return this.diagXyName;
   }

   public void setDiagXyName(String diagXyName) {
      this.diagXyName = diagXyName;
   }

   public String getDiagXyCode() {
      return this.diagXyCode;
   }

   public void setDiagXyCode(String diagXyCode) {
      this.diagXyCode = diagXyCode;
   }

   public String getInHosCondXyCd() {
      return this.inHosCondXyCd;
   }

   public void setInHosCondXyCd(String inHosCondXyCd) {
      this.inHosCondXyCd = inHosCondXyCd;
   }
}
