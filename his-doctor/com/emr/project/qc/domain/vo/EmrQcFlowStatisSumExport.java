package com.emr.project.qc.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;

public class EmrQcFlowStatisSumExport {
   @Excel(
      name = "所属科室",
      sort = 3,
      height = (double)20.0F
   )
   private String deptName;
   @Excel(
      name = "用户工号",
      sort = 1,
      height = (double)20.0F
   )
   private String doctCd;
   @Excel(
      name = "责任医师",
      sort = 2,
      height = (double)20.0F
   )
   private String doctName;
   @Excel(
      name = "超时病历总数",
      sort = 4,
      height = (double)20.0F
   )
   private Integer valueTotal;
   @Excel(
      name = "超时已书写病历数",
      sort = 5,
      height = (double)20.0F
   )
   private Integer flow1;
   @Excel(
      name = "超时未书写病历数",
      sort = 6,
      height = (double)20.0F
   )
   private Integer flow2;

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDoctCd() {
      return this.doctCd;
   }

   public void setDoctCd(String doctCd) {
      this.doctCd = doctCd;
   }

   public String getDoctName() {
      return this.doctName;
   }

   public void setDoctName(String doctName) {
      this.doctName = doctName;
   }

   public Integer getValueTotal() {
      return this.valueTotal;
   }

   public void setValueTotal(Integer valueTotal) {
      this.valueTotal = valueTotal;
   }

   public Integer getFlow1() {
      return this.flow1;
   }

   public void setFlow1(Integer flow1) {
      this.flow1 = flow1;
   }

   public Integer getFlow2() {
      return this.flow2;
   }

   public void setFlow2(Integer flow2) {
      this.flow2 = flow2;
   }
}
