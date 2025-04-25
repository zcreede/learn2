package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcScoreScheDed;
import java.util.List;

public class QcScoreScheDedVo extends QcScoreScheDed {
   private String dedType;
   private String dedDesc;
   private String dedNum;
   private String ruleNum;
   private String dedScoreDesc;
   private String itemClassCd;
   private String itemClassName;
   private String itemDesc;
   private Double dedScoreSingle;
   private Double dedScoreMax;
   private Double schetotalscore;
   private String deptCode;
   private String orgCode;
   private String appSeg;
   private Integer itemClassSort;
   private Integer detailSort;
   List qcScoreScheDedList;
   List qcScoreScheItemList;

   public String getOrgCode() {
      return this.orgCode;
   }

   public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
   }

   public Integer getDetailSort() {
      return this.detailSort;
   }

   public void setDetailSort(Integer detailSort) {
      this.detailSort = detailSort;
   }

   public Integer getItemClassSort() {
      return this.itemClassSort;
   }

   public void setItemClassSort(Integer itemClassSort) {
      this.itemClassSort = itemClassSort;
   }

   public Double getDedScoreMax() {
      return this.dedScoreMax;
   }

   public void setDedScoreMax(Double dedScoreMax) {
      this.dedScoreMax = dedScoreMax;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getAppSeg() {
      return this.appSeg;
   }

   public void setAppSeg(String appSeg) {
      this.appSeg = appSeg;
   }

   public Double getDedScoreSingle() {
      return this.dedScoreSingle;
   }

   public void setDedScoreSingle(Double dedScoreSingle) {
      this.dedScoreSingle = dedScoreSingle;
   }

   public Double getSchetotalscore() {
      return this.schetotalscore;
   }

   public void setSchetotalscore(Double schetotalscore) {
      this.schetotalscore = schetotalscore;
   }

   public String getItemDesc() {
      return this.itemDesc;
   }

   public void setItemDesc(String itemDesc) {
      this.itemDesc = itemDesc;
   }

   public List getQcScoreScheItemList() {
      return this.qcScoreScheItemList;
   }

   public void setQcScoreScheItemList(List qcScoreScheItemList) {
      this.qcScoreScheItemList = qcScoreScheItemList;
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

   public String getDedScoreDesc() {
      return this.dedScoreDesc;
   }

   public void setDedScoreDesc(String dedScoreDesc) {
      this.dedScoreDesc = dedScoreDesc;
   }

   public List getQcScoreScheDedList() {
      return this.qcScoreScheDedList;
   }

   public void setQcScoreScheDedList(List qcScoreScheDedList) {
      this.qcScoreScheDedList = qcScoreScheDedList;
   }

   public String getRuleNum() {
      return this.ruleNum;
   }

   public void setRuleNum(String ruleNum) {
      this.ruleNum = ruleNum;
   }

   public String getDedNum() {
      return this.dedNum;
   }

   public void setDedNum(String dedNum) {
      this.dedNum = dedNum;
   }

   public String getDedType() {
      return this.dedType;
   }

   public void setDedType(String dedType) {
      this.dedType = dedType;
   }

   public String getDedDesc() {
      return this.dedDesc;
   }

   public void setDedDesc(String dedDesc) {
      this.dedDesc = dedDesc;
   }
}
