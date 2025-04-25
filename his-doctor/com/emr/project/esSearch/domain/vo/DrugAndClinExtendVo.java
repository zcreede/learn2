package com.emr.project.esSearch.domain.vo;

import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.tmpm.domain.vo.ClinItemDetailVo;
import java.math.BigDecimal;
import java.util.List;

public class DrugAndClinExtendVo extends DrugAndClin {
   private String chsCode;
   private String chsName;
   private String chsType;
   private BigDecimal selfRatio;
   private String chsDesc;
   private List clinItemDetailVoList;
   private List setDetailList;

   public List getSetDetailList() {
      return this.setDetailList;
   }

   public void setSetDetailList(List setDetailList) {
      this.setDetailList = setDetailList;
   }

   public List getClinItemDetailVoList() {
      return this.clinItemDetailVoList;
   }

   public void setClinItemDetailVoList(List clinItemDetailVoList) {
      this.clinItemDetailVoList = clinItemDetailVoList;
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
