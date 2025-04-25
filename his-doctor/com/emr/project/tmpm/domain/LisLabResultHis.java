package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LisLabResultHis extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "LIS项目编码"
   )
   private String lisItemCd;
   @Excel(
      name = "LIS结果编码"
   )
   private String lisResultCd;
   @Excel(
      name = "LIS结果名称"
   )
   private String lisResultName;
   @Excel(
      name = "对应HIS结果编码"
   )
   private String hisResultCd;
   @Excel(
      name = "对应HIS结果名称"
   )
   private String hisResultName;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setLisItemCd(String lisItemCd) {
      this.lisItemCd = lisItemCd;
   }

   public String getLisItemCd() {
      return this.lisItemCd;
   }

   public void setLisResultCd(String lisResultCd) {
      this.lisResultCd = lisResultCd;
   }

   public String getLisResultCd() {
      return this.lisResultCd;
   }

   public void setLisResultName(String lisResultName) {
      this.lisResultName = lisResultName;
   }

   public String getLisResultName() {
      return this.lisResultName;
   }

   public void setHisResultCd(String hisResultCd) {
      this.hisResultCd = hisResultCd;
   }

   public String getHisResultCd() {
      return this.hisResultCd;
   }

   public void setHisResultName(String hisResultName) {
      this.hisResultName = hisResultName;
   }

   public String getHisResultName() {
      return this.hisResultName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("lisItemCd", this.getLisItemCd()).append("lisResultCd", this.getLisResultCd()).append("lisResultName", this.getLisResultName()).append("hisResultCd", this.getHisResultCd()).append("hisResultName", this.getHisResultName()).toString();
   }
}
