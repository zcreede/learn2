package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.EmrQcFlowScore;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmrQcFlowScoreVo extends EmrQcFlowScore {
   private String scoreDoc;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date scoreDate;
   private Double itemTotalScore;
   private Double totalScore;
   private String levelCode;
   private String levelName;
   private String deptCode;
   private Long recordId;
   private String dedDesc;
   private String hosName;
   private Integer itemSort;
   private Double itemScore;
   private Integer itemClassSort;
   private Integer dedSort;
   private List qcStateList;
   List qcScoreItemVoList = new ArrayList();

   public Integer getDedSort() {
      return this.dedSort;
   }

   public void setDedSort(Integer dedSort) {
      this.dedSort = dedSort;
   }

   public Integer getItemClassSort() {
      return this.itemClassSort;
   }

   public void setItemClassSort(Integer itemClassSort) {
      this.itemClassSort = itemClassSort;
   }

   public Integer getItemSort() {
      return this.itemSort;
   }

   public void setItemSort(Integer itemSort) {
      this.itemSort = itemSort;
   }

   public Double getItemScore() {
      return this.itemScore;
   }

   public void setItemScore(Double itemScore) {
      this.itemScore = itemScore;
   }

   public String getHosName() {
      return this.hosName;
   }

   public void setHosName(String hosName) {
      this.hosName = hosName;
   }

   public List getQcScoreItemVoList() {
      return this.qcScoreItemVoList;
   }

   public String getDedDesc() {
      return this.dedDesc;
   }

   public void setDedDesc(String dedDesc) {
      this.dedDesc = dedDesc;
   }

   public void setQcScoreItemVoList(List qcScoreItemVoList) {
      this.qcScoreItemVoList = qcScoreItemVoList;
   }

   public Long getRecordId() {
      return this.recordId;
   }

   public void setRecordId(Long recordId) {
      this.recordId = recordId;
   }

   public String getScoreDoc() {
      return this.scoreDoc;
   }

   public void setScoreDoc(String scoreDoc) {
      this.scoreDoc = scoreDoc;
   }

   public Date getScoreDate() {
      return this.scoreDate;
   }

   public void setScoreDate(Date scoreDate) {
      this.scoreDate = scoreDate;
   }

   public Double getItemTotalScore() {
      return this.itemTotalScore;
   }

   public void setItemTotalScore(Double itemTotalScore) {
      this.itemTotalScore = itemTotalScore;
   }

   public Double getTotalScore() {
      return this.totalScore;
   }

   public void setTotalScore(Double totalScore) {
      this.totalScore = totalScore;
   }

   public String getLevelName() {
      return this.levelName;
   }

   public void setLevelName(String levelName) {
      this.levelName = levelName;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public List getQcStateList() {
      return this.qcStateList;
   }

   public void setQcStateList(List qcStateList) {
      this.qcStateList = qcStateList;
   }

   public String getLevelCode() {
      return this.levelCode;
   }

   public void setLevelCode(String levelCode) {
      this.levelCode = levelCode;
   }
}
