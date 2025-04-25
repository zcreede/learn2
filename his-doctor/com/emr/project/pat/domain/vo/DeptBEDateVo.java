package com.emr.project.pat.domain.vo;

import java.util.Date;

public class DeptBEDateVo {
   private Long id;
   private String deptCode;
   private String deptName;
   private String bedNo;
   private Date beginDate;
   private Date endDate;

   public DeptBEDateVo() {
   }

   public DeptBEDateVo(Long id, String deptCode, String deptName, String bedNo, Date beginDate, Date endDate) {
      this.id = id;
      this.deptCode = deptCode;
      this.deptName = deptName;
      this.bedNo = bedNo;
      this.beginDate = beginDate;
      this.endDate = endDate;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public Date getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(Date beginDate) {
      this.beginDate = beginDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }
}
