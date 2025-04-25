package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpDrawField extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "提取配置主键ID"
   )
   private Long mainId;
   @Excel(
      name = "返回字段名"
   )
   private String field;
   @Excel(
      name = "对应his表字段名"
   )
   private String hisDbField;
   @Excel(
      name = "对应his表字段名名称"
   )
   private String hisDbFieldName;
   @Excel(
      name = "对照字典来源id"
   )
   private Long defineId;
   @Excel(
      name = "对照字典类型编码"
   )
   private String dictTypeCode;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setField(String field) {
      this.field = field;
   }

   public String getField() {
      return this.field;
   }

   public void setHisDbField(String hisDbField) {
      this.hisDbField = hisDbField;
   }

   public String getHisDbField() {
      return this.hisDbField;
   }

   public void setHisDbFieldName(String hisDbFieldName) {
      this.hisDbFieldName = hisDbFieldName;
   }

   public String getHisDbFieldName() {
      return this.hisDbFieldName;
   }

   public void setDefineId(Long defineId) {
      this.defineId = defineId;
   }

   public Long getDefineId() {
      return this.defineId;
   }

   public void setDictTypeCode(String dictTypeCode) {
      this.dictTypeCode = dictTypeCode;
   }

   public String getDictTypeCode() {
      return this.dictTypeCode;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("mainId", this.getMainId()).append("field", this.getField()).append("hisDbField", this.getHisDbField()).append("hisDbFieldName", this.getHisDbFieldName()).append("defineId", this.getDefineId()).append("dictTypeCode", this.getDictTypeCode()).toString();
   }
}
