package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcScoreDedDetail;
import java.util.ArrayList;
import java.util.List;

public class QcScoreDedDetailVo extends QcScoreDedDetail {
   private Integer dedRuleNum;
   private List ruleIdList;
   private String scoreDesc;
   private Long itemId;
   private Integer scoreNum;
   private Double dedScore;
   private String itemClassCd;
   private String itemClassName;
   private String itemDesc;
   private String dedItemDesc;
   private List emrQcFlowScoreListVos = new ArrayList();
   private List itemList;

   public List getItemList() {
      return this.itemList;
   }

   public void setItemList(List itemList) {
      this.itemList = itemList;
   }

   public String getDedItemDesc() {
      return this.dedItemDesc;
   }

   public void setDedItemDesc(String dedItemDesc) {
      this.dedItemDesc = dedItemDesc;
   }

   public List getEmrQcFlowScoreListVos() {
      return this.emrQcFlowScoreListVos;
   }

   public void setEmrQcFlowScoreListVos(List emrQcFlowScoreListVos) {
      this.emrQcFlowScoreListVos = emrQcFlowScoreListVos;
   }

   public String getItemClassCd() {
      return this.itemClassCd;
   }

   public void setItemClassCd(String itemClassCd) {
      this.itemClassCd = itemClassCd;
   }

   public String getItemClassName() {
      return this.itemClassName;
   }

   public void setItemClassName(String itemClassName) {
      this.itemClassName = itemClassName;
   }

   public String getItemDesc() {
      return this.itemDesc;
   }

   public void setItemDesc(String itemDesc) {
      this.itemDesc = itemDesc;
   }

   public Double getDedScore() {
      return this.dedScore;
   }

   public void setDedScore(Double dedScore) {
      this.dedScore = dedScore;
   }

   public Integer getScoreNum() {
      return this.scoreNum;
   }

   public void setScoreNum(Integer scoreNum) {
      this.scoreNum = scoreNum;
   }

   public Long getItemId() {
      return this.itemId;
   }

   public void setItemId(Long itemId) {
      this.itemId = itemId;
   }

   public String getScoreDesc() {
      return this.scoreDesc;
   }

   public void setScoreDesc(String scoreDesc) {
      this.scoreDesc = scoreDesc;
   }

   public List getRuleIdList() {
      return this.ruleIdList;
   }

   public void setRuleIdList(List ruleIdList) {
      this.ruleIdList = ruleIdList;
   }

   public Integer getDedRuleNum() {
      return this.dedRuleNum;
   }

   public void setDedRuleNum(Integer dedRuleNum) {
      this.dedRuleNum = dedRuleNum;
   }
}
