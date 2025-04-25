package com.emr.project.report.domain.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FeeWorkloadListResp implements Serializable {
   private List list = new ArrayList();
   private String staffCode;
   private String staffName;
   private String total;

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public String getStaffCode() {
      return this.staffCode;
   }

   public void setStaffCode(String staffCode) {
      this.staffCode = staffCode;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }

   public String getTotal() {
      return this.total;
   }

   public void setTotal(String total) {
      this.total = total;
   }
}
