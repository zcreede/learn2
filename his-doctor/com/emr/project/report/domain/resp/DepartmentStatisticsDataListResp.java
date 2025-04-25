package com.emr.project.report.domain.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepartmentStatisticsDataListResp implements Serializable {
   private List dataList = new ArrayList();
   private String entryName;
   private String totalAmount;

   public String getEntryName() {
      return this.entryName;
   }

   public void setEntryName(String entryName) {
      this.entryName = entryName;
   }

   public List getDataList() {
      return this.dataList;
   }

   public void setDataList(List dataList) {
      this.dataList = dataList;
   }

   public String getTotalAmount() {
      return this.totalAmount;
   }

   public void setTotalAmount(String totalAmount) {
      this.totalAmount = totalAmount;
   }
}
