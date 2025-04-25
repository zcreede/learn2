package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcAgiEven extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "事件编号"
   )
   private String evenCode;
   @Excel(
      name = "事件名称"
   )
   private String evenName;
   @Excel(
      name = "事件发生频次 1 单次 2 重复 3 持续"
   )
   private String evenFreq;
   @Excel(
      name = "事件描述"
   )
   private String desc;
   @Excel(
      name = "添加方式  1 ：系统预置  2：人工添加"
   )
   private String addMeth;
   private String delFlag;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setEvenCode(String evenCode) {
      this.evenCode = evenCode;
   }

   public String getEvenCode() {
      return this.evenCode;
   }

   public void setEvenName(String evenName) {
      this.evenName = evenName;
   }

   public String getEvenName() {
      return this.evenName;
   }

   public void setEvenFreq(String evenFreq) {
      this.evenFreq = evenFreq;
   }

   public String getEvenFreq() {
      return this.evenFreq;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public String getDesc() {
      return this.desc;
   }

   public void setAddMeth(String addMeth) {
      this.addMeth = addMeth;
   }

   public String getAddMeth() {
      return this.addMeth;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("evenCode", this.getEvenCode()).append("evenName", this.getEvenName()).append("evenFreq", this.getEvenFreq()).append("desc", this.getDesc()).append("addMeth", this.getAddMeth()).append("delFlag", this.getDelFlag()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
