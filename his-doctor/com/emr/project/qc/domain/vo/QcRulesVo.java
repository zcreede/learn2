package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.domain.QcRules;
import java.util.List;

public class QcRulesVo extends QcRules {
   private String defeLevelName;
   private List qcCheckElemList;
   private String checkItems;
   private String dedId;
   private String dedDesc;
   private String itemCd;
   private String itemName;
   private String dedType;
   private Double dedScoreSingle;
   private String dedScoreDesc;
   private String qcSection;

   public String getQcSection() {
      return this.qcSection;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }

   public String getDedId() {
      return this.dedId;
   }

   public void setDedId(String dedId) {
      this.dedId = dedId;
   }

   public String getDedDesc() {
      return this.dedDesc;
   }

   public void setDedDesc(String dedDesc) {
      this.dedDesc = dedDesc;
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

   public Double getDedScoreSingle() {
      return this.dedScoreSingle;
   }

   public void setDedScoreSingle(Double dedScoreSingle) {
      this.dedScoreSingle = dedScoreSingle;
   }

   public String getDedScoreDesc() {
      return this.dedScoreDesc;
   }

   public void setDedScoreDesc(String dedScoreDesc) {
      this.dedScoreDesc = dedScoreDesc;
   }

   public String getCheckItems() {
      return this.checkItems;
   }

   public void setCheckItems(String checkItems) {
      this.checkItems = checkItems;
   }

   public String getDefeLevelName() {
      return this.defeLevelName;
   }

   public void setDefeLevelName(String defeLevelName) {
      this.defeLevelName = defeLevelName;
   }

   public List getQcCheckElemList() {
      return this.qcCheckElemList;
   }

   public void setQcCheckElemList(List qcCheckElemList) {
      this.qcCheckElemList = qcCheckElemList;
   }
}
