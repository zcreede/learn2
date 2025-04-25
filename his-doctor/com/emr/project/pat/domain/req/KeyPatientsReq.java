package com.emr.project.pat.domain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class KeyPatientsReq {
   private String flag;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date startDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date endDate;
   private String deptCode;
   private String fieldName;
   private List deptList;

   public List getDeptList() {
      return this.deptList;
   }

   public void setDeptList(List deptList) {
      this.deptList = deptList;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getFieldName() {
      return this.fieldName;
   }

   public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
   }

   public String getFlag() {
      return this.flag;
   }

   public void setFlag(String flag) {
      this.flag = flag;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }
}
