package com.emr.project.mrhp.domain.vo;

import com.emr.framework.web.domain.BaseEntity;

public class MrHpDrawMainTableVo extends BaseEntity {
   private String fieldCd;
   private String fieldName;

   public String getFieldCd() {
      return this.fieldCd;
   }

   public void setFieldCd(String fieldCd) {
      this.fieldCd = fieldCd;
   }

   public String getFieldName() {
      return this.fieldName;
   }

   public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
   }
}
