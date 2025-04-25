package com.emr.project.system.domain;

import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmBsStaffOperlevel extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String staffCode;
   private String operGradeCode;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getStaffCode() {
      return this.staffCode;
   }

   public void setStaffCode(String staffCode) {
      this.staffCode = staffCode;
   }

   public String getOperGradeCode() {
      return this.operGradeCode;
   }

   public void setOperGradeCode(String operGradeCode) {
      this.operGradeCode = operGradeCode;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("staffCode", this.getStaffCode()).append("operGradeCode", this.getOperGradeCode()).toString();
   }
}
