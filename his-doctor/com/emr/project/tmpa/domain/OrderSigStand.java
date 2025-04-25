package com.emr.project.tmpa.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderSigStand extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "标准用法编号"
   )
   private String standCode;
   @Excel(
      name = "标准用法名称"
   )
   private String standName;

   public void setStandCode(String standCode) {
      this.standCode = standCode;
   }

   public String getStandCode() {
      return this.standCode;
   }

   public void setStandName(String standName) {
      this.standName = standName;
   }

   public String getStandName() {
      return this.standName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("standCode", this.getStandCode()).append("standName", this.getStandName()).append("createTime", this.getCreateTime()).append("updateTime", this.getUpdateTime()).toString();
   }
}
