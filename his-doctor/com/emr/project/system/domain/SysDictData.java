package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysDictData extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "字典编码",
      cellType = Excel.ColumnType.STRING
   )
   private Long dictCode;
   @Excel(
      name = "字典排序",
      cellType = Excel.ColumnType.NUMERIC
   )
   private Long dictSort;
   @Excel(
      name = "字典标签"
   )
   private String dictLabel;
   private String inputstrphtc;
   @Excel(
      name = "字典键值"
   )
   private String dictValue;
   @Excel(
      name = "字典类型"
   )
   private String dictType;
   private String cssClass;
   private String listClass;
   @Excel(
      name = "是否默认",
      readConverterExp = "Y=是,N=否"
   )
   private String isDefault;
   @Excel(
      name = "状态",
      readConverterExp = "0=正常,1=停用"
   )
   private String status;
   @Excel(
      name = "是否预置",
      readConverterExp = "1=是, 0=否"
   )
   private String isPreset;
   List children;
   private List dataValList;

   public String getIsPreset() {
      return this.isPreset;
   }

   public void setIsPreset(String isPreset) {
      this.isPreset = isPreset;
   }

   public List getDataValList() {
      return this.dataValList;
   }

   public void setDataValList(List dataValList) {
      this.dataValList = dataValList;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public Long getDictCode() {
      return this.dictCode;
   }

   public void setDictCode(Long dictCode) {
      this.dictCode = dictCode;
   }

   public Long getDictSort() {
      return this.dictSort;
   }

   public void setDictSort(Long dictSort) {
      this.dictSort = dictSort;
   }

   public @NotBlank(
   message = "字典标签不能为空"
) @Size(
   min = 0,
   max = 100,
   message = "字典标签长度不能超过100个字符"
) String getDictLabel() {
      return this.dictLabel;
   }

   public void setDictLabel(String dictLabel) {
      this.dictLabel = dictLabel;
   }

   public @NotBlank(
   message = "字典键值不能为空"
) @Size(
   min = 0,
   max = 100,
   message = "字典键值长度不能超过100个字符"
) String getDictValue() {
      return this.dictValue;
   }

   public void setDictValue(String dictValue) {
      this.dictValue = dictValue;
   }

   public @NotBlank(
   message = "字典类型不能为空"
) @Size(
   min = 0,
   max = 100,
   message = "字典类型长度不能超过100个字符"
) String getDictType() {
      return this.dictType;
   }

   public void setDictType(String dictType) {
      this.dictType = dictType;
   }

   public @Size(
   min = 0,
   max = 100,
   message = "样式属性长度不能超过100个字符"
) String getCssClass() {
      return this.cssClass;
   }

   public void setCssClass(String cssClass) {
      this.cssClass = cssClass;
   }

   public String getListClass() {
      return this.listClass;
   }

   public void setListClass(String listClass) {
      this.listClass = listClass;
   }

   public boolean getDefault() {
      return "Y".equals(this.isDefault);
   }

   public String getIsDefault() {
      return this.isDefault;
   }

   public void setIsDefault(String isDefault) {
      this.isDefault = isDefault;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("dictCode", this.getDictCode()).append("dictSort", this.getDictSort()).append("dictLabel", this.getDictLabel()).append("dictValue", this.getDictValue()).append("dictType", this.getDictType()).append("cssClass", this.getCssClass()).append("listClass", this.getListClass()).append("isDefault", this.getIsDefault()).append("status", this.getStatus()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
