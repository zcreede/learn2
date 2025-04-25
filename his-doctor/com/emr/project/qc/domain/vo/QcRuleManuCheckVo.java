package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRuleManuCheck;
import java.util.List;

public class QcRuleManuCheckVo extends QcRuleManuCheck {
   private List typeCodeList;
   private List elemList;
   private String defeLevelName;
   private List elemVoList;
   private Long elemId;
   private Long mrFileId;
   private String itemCd;
   private String itemName;
   private String dedType;
   private String dedTypeName;
   private Double dedScoreSingle;
   private String appSeg;

   public String getAppSeg() {
      return this.appSeg;
   }

   public void setAppSeg(String appSeg) {
      this.appSeg = appSeg;
   }

   public String getItemCd() {
      return this.itemCd;
   }

   public void setItemCd(String itemCd) {
      this.itemCd = itemCd;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getDedType() {
      return this.dedType;
   }

   public void setDedType(String dedType) {
      this.dedType = dedType;
   }

   public String getDedTypeName() {
      return this.dedTypeName;
   }

   public void setDedTypeName(String dedTypeName) {
      this.dedTypeName = dedTypeName;
   }

   public Double getDedScoreSingle() {
      return this.dedScoreSingle;
   }

   public void setDedScoreSingle(Double dedScoreSingle) {
      this.dedScoreSingle = dedScoreSingle;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
   }

   public List getElemVoList() {
      return this.elemVoList;
   }

   public void setElemVoList(List elemVoList) {
      this.elemVoList = elemVoList;
   }

   public String getDefeLevelName() {
      return this.defeLevelName;
   }

   public void setDefeLevelName(String defeLevelName) {
      this.defeLevelName = defeLevelName;
   }

   public List getTypeCodeList() {
      return this.typeCodeList;
   }

   public void setTypeCodeList(List typeCodeList) {
      this.typeCodeList = typeCodeList;
   }

   public List getElemList() {
      return this.elemList;
   }

   public void setElemList(List elemList) {
      this.elemList = elemList;
   }
}
