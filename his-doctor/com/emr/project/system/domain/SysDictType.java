package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysDictType extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "字典主键",
      cellType = Excel.ColumnType.NUMERIC
   )
   private Long dictId;
   @Excel(
      name = "字典名称"
   )
   private String dictName;
   @Excel(
      name = "字典类型"
   )
   private String dictType;
   @Excel(
      name = "状态",
      readConverterExp = "0=正常,1=停用"
   )
   private String status;
   List children;

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public Long getDictId() {
      return this.dictId;
   }

   public void setDictId(Long dictId) {
      this.dictId = dictId;
   }

   public @NotBlank(
   message = "字典名称不能为空"
) @Size(
   min = 0,
   max = 100,
   message = "字典类型名称长度不能超过100个字符"
) String getDictName() {
      return this.dictName;
   }

   public void setDictName(String dictName) {
      this.dictName = dictName;
   }

   public @NotBlank(
   message = "字典类型不能为空"
) @Size(
   min = 0,
   max = 100,
   message = "字典类型类型长度不能超过100个字符"
) String getDictType() {
      return this.dictType;
   }

   public void setDictType(String dictType) {
      this.dictType = dictType;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("dictId", this.getDictId()).append("dictName", this.getDictName()).append("dictType", this.getDictType()).append("status", this.getStatus()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
