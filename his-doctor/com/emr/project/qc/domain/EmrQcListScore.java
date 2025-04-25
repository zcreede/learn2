package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcListScore extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "缺陷id"
   )
   private Long qcListId;
   @Excel(
      name = "归属评分项目编码"
   )
   private String itemCd;
   @Excel(
      name = "归属评分项目名称"
   )
   private String itemName;
   @Excel(
      name = "扣分类型 1每次 2每项"
   )
   private String dedType;
   @Excel(
      name = "单次扣费值"
   )
   private Double dedScoreSingle;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人姓名"
   )
   private String crePerName;
   private Long dedId;
   private String dedScoreDesc;

   public String getDedScoreDesc() {
      return this.dedScoreDesc;
   }

   public void setDedScoreDesc(String dedScoreDesc) {
      this.dedScoreDesc = dedScoreDesc;
   }

   public Long getDedId() {
      return this.dedId;
   }

   public void setDedId(Long dedId) {
      this.dedId = dedId;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setQcListId(Long qcListId) {
      this.qcListId = qcListId;
   }

   public Long getQcListId() {
      return this.qcListId;
   }

   public void setItemCd(String itemCd) {
      this.itemCd = itemCd;
   }

   public String getItemCd() {
      return this.itemCd;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setDedType(String dedType) {
      this.dedType = dedType;
   }

   public String getDedType() {
      return this.dedType;
   }

   public void setDedScoreSingle(Double dedScoreSingle) {
      this.dedScoreSingle = dedScoreSingle;
   }

   public Double getDedScoreSingle() {
      return this.dedScoreSingle;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("qcListId", this.getQcListId()).append("itemCd", this.getItemCd()).append("itemName", this.getItemName()).append("dedType", this.getDedType()).append("dedScoreSingle", this.getDedScoreSingle()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("dedId", this.getDedId()).toString();
   }
}
