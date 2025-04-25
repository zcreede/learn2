package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdCaOperationApply;

public class TdCaOperWorkloadVo extends TdCaOperationApply {
   private Integer operNum;
   private Integer operType1Num;
   private Integer operType2Num;
   private Integer operType3Num;
   private Integer operType4Num;

   public Integer getOperNum() {
      return this.operNum;
   }

   public void setOperNum(Integer operNum) {
      this.operNum = operNum;
   }

   public Integer getOperType1Num() {
      return this.operType1Num;
   }

   public void setOperType1Num(Integer operType1Num) {
      this.operType1Num = operType1Num;
   }

   public Integer getOperType2Num() {
      return this.operType2Num;
   }

   public void setOperType2Num(Integer operType2Num) {
      this.operType2Num = operType2Num;
   }

   public Integer getOperType3Num() {
      return this.operType3Num;
   }

   public void setOperType3Num(Integer operType3Num) {
      this.operType3Num = operType3Num;
   }

   public Integer getOperType4Num() {
      return this.operType4Num;
   }

   public void setOperType4Num(Integer operType4Num) {
      this.operType4Num = operType4Num;
   }
}
