package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrSearchHistory;
import com.emr.project.emr.domain.EmrSearchHistoryDetail;
import java.util.List;

public class EmrSearchHistoryVo extends EmrSearchHistory {
   private List detailList;
   private String descStr;

   public String getDescStr() {
      return this.descStr;
   }

   public void setDescStr(String descStr) {
      this.descStr = descStr;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }
}
