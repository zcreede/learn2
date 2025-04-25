package com.emr.project.tmpm.domain.vo;

import com.emr.project.tmpm.domain.ClinItemDetail;
import java.math.BigDecimal;
import java.util.List;

public class ClinItemDetailVo extends ClinItemDetail {
   private List itemCdList;
   private String itemClassCode;
   private String chsCode;
   private String chsName;
   private String chsType;
   private BigDecimal selfRatio;
   private String chsDesc;

   public String getItemClassCode() {
      return this.itemClassCode;
   }

   public void setItemClassCode(String itemClassCode) {
      this.itemClassCode = itemClassCode;
   }

   public List getItemCdList() {
      return this.itemCdList;
   }

   public void setItemCdList(List itemCdList) {
      this.itemCdList = itemCdList;
   }

   public String getChsCode() {
      return this.chsCode;
   }

   public void setChsCode(String chsCode) {
      this.chsCode = chsCode;
   }

   public String getChsName() {
      return this.chsName;
   }

   public void setChsName(String chsName) {
      this.chsName = chsName;
   }

   public String getChsType() {
      return this.chsType;
   }

   public void setChsType(String chsType) {
      this.chsType = chsType;
   }

   public BigDecimal getSelfRatio() {
      return this.selfRatio;
   }

   public void setSelfRatio(BigDecimal selfRatio) {
      this.selfRatio = selfRatio;
   }

   public String getChsDesc() {
      return this.chsDesc;
   }

   public void setChsDesc(String chsDesc) {
      this.chsDesc = chsDesc;
   }
}
