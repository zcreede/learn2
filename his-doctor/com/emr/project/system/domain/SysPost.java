package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysPost extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "岗位序号",
      cellType = Excel.ColumnType.NUMERIC
   )
   private Long postId;
   @Excel(
      name = "岗位编码"
   )
   private String postCode;
   @Excel(
      name = "岗位名称"
   )
   private String postName;
   @Excel(
      name = "岗位排序"
   )
   private String postSort;
   @Excel(
      name = "状态",
      readConverterExp = "0=正常,1=停用"
   )
   private String status;
   private boolean flag = false;

   public Long getPostId() {
      return this.postId;
   }

   public void setPostId(Long postId) {
      this.postId = postId;
   }

   public @NotBlank(
   message = "岗位编码不能为空"
) @Size(
   min = 0,
   max = 64,
   message = "岗位编码长度不能超过64个字符"
) String getPostCode() {
      return this.postCode;
   }

   public void setPostCode(String postCode) {
      this.postCode = postCode;
   }

   public @NotBlank(
   message = "岗位名称不能为空"
) @Size(
   min = 0,
   max = 50,
   message = "岗位名称长度不能超过50个字符"
) String getPostName() {
      return this.postName;
   }

   public void setPostName(String postName) {
      this.postName = postName;
   }

   public @NotBlank(
   message = "显示顺序不能为空"
) String getPostSort() {
      return this.postSort;
   }

   public void setPostSort(String postSort) {
      this.postSort = postSort;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public boolean isFlag() {
      return this.flag;
   }

   public void setFlag(boolean flag) {
      this.flag = flag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("postId", this.getPostId()).append("postCode", this.getPostCode()).append("postName", this.getPostName()).append("postSort", this.getPostSort()).append("status", this.getStatus()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
