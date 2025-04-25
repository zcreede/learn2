package com.emr.project.system.domain;

import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmBsDeptTypeContrast extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String deptCode;
   private String deptType;

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptType(String deptType) {
      this.deptType = deptType;
   }

   public String getDeptType() {
      return this.deptType;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("deptCode", this.getDeptCode()).append("deptType", this.getDeptType()).append("createTime", this.getCreateTime()).append("updateTime", this.getUpdateTime()).toString();
   }
}
