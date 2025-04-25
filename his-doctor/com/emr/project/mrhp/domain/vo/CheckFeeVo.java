package com.emr.project.mrhp.domain.vo;

public class CheckFeeVo {
   private String feeTypeCode;
   private String feeTypeName;
   private Double offsetValue;
   private String isOffset;

   public String getFeeTypeCode() {
      return this.feeTypeCode;
   }

   public void setFeeTypeCode(String feeTypeCode) {
      this.feeTypeCode = feeTypeCode;
   }

   public String getFeeTypeName() {
      return this.feeTypeName;
   }

   public void setFeeTypeName(String feeTypeName) {
      this.feeTypeName = feeTypeName;
   }

   public Double getOffsetValue() {
      return this.offsetValue;
   }

   public void setOffsetValue(Double offsetValue) {
      this.offsetValue = offsetValue;
   }

   public String getIsOffset() {
      return this.isOffset;
   }

   public void setIsOffset(String isOffset) {
      this.isOffset = isOffset;
   }
}
