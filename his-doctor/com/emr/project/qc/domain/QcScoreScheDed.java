package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcScoreScheDed extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "评分方案id"
   )
   private Long scheId;
   @Excel(
      name = "评分方案名称"
   )
   private String scheName;
   @Excel(
      name = "评分项目id"
   )
   private Long itemId;
   @Excel(
      name = "评分项目名称"
   )
   private String itemName;
   @Excel(
      name = "评分项目总分"
   )
   private Double itemTotalScore;
   @Excel(
      name = "评分项目排序"
   )
   private Long itemSort;
   @Excel(
      name = "扣分细则ID"
   )
   private Long dedId;
   @Excel(
      name = "扣分细则名称"
   )
   private String dedName;
   @Excel(
      name = "扣分细则排序"
   )
   private Long dedSort;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setScheId(Long scheId) {
      this.scheId = scheId;
   }

   public Long getScheId() {
      return this.scheId;
   }

   public void setScheName(String scheName) {
      this.scheName = scheName;
   }

   public String getScheName() {
      return this.scheName;
   }

   public void setItemId(Long itemId) {
      this.itemId = itemId;
   }

   public Long getItemId() {
      return this.itemId;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemTotalScore(Double itemTotalScore) {
      this.itemTotalScore = itemTotalScore;
   }

   public Double getItemTotalScore() {
      return this.itemTotalScore;
   }

   public void setItemSort(Long itemSort) {
      this.itemSort = itemSort;
   }

   public Long getItemSort() {
      return this.itemSort;
   }

   public void setDedId(Long dedId) {
      this.dedId = dedId;
   }

   public Long getDedId() {
      return this.dedId;
   }

   public void setDedName(String dedName) {
      this.dedName = dedName;
   }

   public String getDedName() {
      return this.dedName;
   }

   public void setDedSort(Long dedSort) {
      this.dedSort = dedSort;
   }

   public Long getDedSort() {
      return this.dedSort;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
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

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("scheId", this.getScheId()).append("scheName", this.getScheName()).append("itemId", this.getItemId()).append("itemName", this.getItemName()).append("itemTotalScore", this.getItemTotalScore()).append("itemSort", this.getItemSort()).append("dedId", this.getDedId()).append("dedName", this.getDedName()).append("dedSort", this.getDedSort()).append("validFlag", this.getValidFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
