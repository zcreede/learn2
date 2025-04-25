package com.emr.project.docOrder.domain.resp;

import com.emr.project.docOrder.domain.vo.TdPaTakeDrugListVO;

public class TdPaTakeDrugListDetailPrint extends TdPaTakeDrugListVO {
   private String deptName;
   private String bedid;
   private String printName;
   private String dept;

   public String getDept() {
      return this.dept;
   }

   public void setDept(String dept) {
      this.dept = dept;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getPrintName() {
      return this.printName;
   }

   public void setPrintName(String printName) {
      this.printName = printName;
   }
}
