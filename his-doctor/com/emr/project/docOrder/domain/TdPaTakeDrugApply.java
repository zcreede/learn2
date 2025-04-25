package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaTakeDrugApply extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "发药申请院区编码"
   )
   private String orgCode;
   @Excel(
      name = "发药申请科室编码"
   )
   private String deptCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "发药申请日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date applyDate;
   @Excel(
      name = "发药申请序号"
   )
   private Integer applyNum;
   @Excel(
      name = "发药申请流水号(申请院区编码+发药申请科室编码+发药申请日期+发药申请序号)"
   )
   private String applyNo;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
   }

   public String getOrgCode() {
      return this.orgCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setApplyDate(Date applyDate) {
      this.applyDate = applyDate;
   }

   public Date getApplyDate() {
      return this.applyDate;
   }

   public void setApplyNum(Integer applyNum) {
      this.applyNum = applyNum;
   }

   public Integer getApplyNum() {
      return this.applyNum;
   }

   public void setApplyNo(String applyNo) {
      this.applyNo = applyNo;
   }

   public String getApplyNo() {
      return this.applyNo;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCode", this.getOrgCode()).append("deptCode", this.getDeptCode()).append("applyDate", this.getApplyDate()).append("applyNum", this.getApplyNum()).append("applyNo", this.getApplyNo()).toString();
   }
}
