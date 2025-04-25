package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ClinItemDept extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String deptCd;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   private String clinItemCd;
   @Excel(
      name = "临床项目名称"
   )
   private String clinItemName;

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setClinItemCd(String clinItemCd) {
      this.clinItemCd = clinItemCd;
   }

   public String getClinItemCd() {
      return this.clinItemCd;
   }

   public void setClinItemName(String clinItemName) {
      this.clinItemName = clinItemName;
   }

   public String getClinItemName() {
      return this.clinItemName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("clinItemCd", this.getClinItemCd()).append("clinItemName", this.getClinItemName()).toString();
   }
}
