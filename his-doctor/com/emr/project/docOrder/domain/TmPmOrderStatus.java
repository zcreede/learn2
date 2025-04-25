package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmPmOrderStatus extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String codeNo;
   @Excel(
      name = "状态名称"
   )
   private String codeName;
   @Excel(
      name = "状态名称2"
   )
   private String statusName;

   public TmPmOrderStatus() {
   }

   public TmPmOrderStatus(String codeNo, String codeName, String statusName) {
      this.codeName = codeName;
      this.codeNo = codeNo;
      this.statusName = statusName;
   }

   public void setCodeNo(String codeNo) {
      this.codeNo = codeNo;
   }

   public String getCodeNo() {
      return this.codeNo;
   }

   public void setCodeName(String codeName) {
      this.codeName = codeName;
   }

   public String getCodeName() {
      return this.codeName;
   }

   public void setStatusName(String statusName) {
      this.statusName = statusName;
   }

   public String getStatusName() {
      return this.statusName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("codeNo", this.getCodeNo()).append("codeName", this.getCodeName()).append("remark", this.getRemark()).append("statusName", this.getStatusName()).toString();
   }
}
