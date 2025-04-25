package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class WorkLoadOther extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构编码"
   )
   private String orgCd;
   @Excel(
      name = "上报主表id"
   )
   private Long mainId;
   @Excel(
      name = "项目名称"
   )
   private String itemName;
   @Excel(
      name = "项目编码"
   )
   private String itemCode;
   @Excel(
      name = "项目人数"
   )
   private Integer itemNum;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemNum(Integer itemNum) {
      this.itemNum = itemNum;
   }

   public Integer getItemNum() {
      return this.itemNum;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("mainId", this.getMainId()).append("itemName", this.getItemName()).append("itemCode", this.getItemCode()).append("itemNum", this.getItemNum()).toString();
   }
}
