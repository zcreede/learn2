package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcScoreItem;
import java.util.ArrayList;
import java.util.List;

public class QcScoreItemVo extends QcScoreItem {
   private int dedDetailNum;
   private Double itemDedScore;
   private Integer itemClassSort;
   private List qcScoreDedDetailVoList = new ArrayList();

   public Integer getItemClassSort() {
      return this.itemClassSort;
   }

   public void setItemClassSort(Integer itemClassSort) {
      this.itemClassSort = itemClassSort;
   }

   public List getQcScoreDedDetailVoList() {
      return this.qcScoreDedDetailVoList;
   }

   public void setQcScoreDedDetailVoList(List qcScoreDedDetailVoList) {
      this.qcScoreDedDetailVoList = qcScoreDedDetailVoList;
   }

   public Double getItemDedScore() {
      return this.itemDedScore;
   }

   public void setItemDedScore(Double itemDedScore) {
      this.itemDedScore = itemDedScore;
   }

   public int getDedDetailNum() {
      return this.dedDetailNum;
   }

   public void setDedDetailNum(int dedDetailNum) {
      this.dedDetailNum = dedDetailNum;
   }
}
