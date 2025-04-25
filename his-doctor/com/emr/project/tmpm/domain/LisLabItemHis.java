package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LisLabItemHis extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "HIS项目编码"
   )
   private String hisItemCd;
   @Excel(
      name = "HIS项目名称"
   )
   private String hisItemName;
   @Excel(
      name = "LIS项目编码"
   )
   private String lisItemCd;
   @Excel(
      name = "lIS项目名称"
   )
   private String lisItemName;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setHisItemCd(String hisItemCd) {
      this.hisItemCd = hisItemCd;
   }

   public String getHisItemCd() {
      return this.hisItemCd;
   }

   public void setHisItemName(String hisItemName) {
      this.hisItemName = hisItemName;
   }

   public String getHisItemName() {
      return this.hisItemName;
   }

   public void setLisItemCd(String lisItemCd) {
      this.lisItemCd = lisItemCd;
   }

   public String getLisItemCd() {
      return this.lisItemCd;
   }

   public void setLisItemName(String lisItemName) {
      this.lisItemName = lisItemName;
   }

   public String getLisItemName() {
      return this.lisItemName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("hisItemCd", this.getHisItemCd()).append("hisItemName", this.getHisItemName()).append("lisItemCd", this.getLisItemCd()).append("lisItemName", this.getLisItemName()).toString();
   }
}
