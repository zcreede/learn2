package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcScoreDedDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "机构编码"
   )
   private String orgCd;
   @Excel(
      name = "扣分细则编码"
   )
   private String dedCd;
   @Excel(
      name = "扣分细则名称"
   )
   private String dedName;
   @Excel(
      name = "扣分细则描述"
   )
   private String dedDesc;
   @Excel(
      name = "归属评分项目编码"
   )
   private String itemCd;
   @Excel(
      name = "归属评分项目名称"
   )
   private String itemName;
   @Excel(
      name = "扣分类型"
   )
   private String dedType;
   @Excel(
      name = "单次扣费值"
   )
   private Double dedScoreSingle;
   @Excel(
      name = "本细则最大扣分值"
   )
   private Double dedScoreMax;
   @Excel(
      name = "本细则扣分描述"
   )
   private String dedScoreDesc;
   @Excel(
      name = "排序"
   )
   private Integer sort;
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
   private String delFlag;
   private String scheId;
   private Double itemTotalScore;
   private Integer itemSort;

   public Double getItemTotalScore() {
      return this.itemTotalScore;
   }

   public void setItemTotalScore(Double itemTotalScore) {
      this.itemTotalScore = itemTotalScore;
   }

   public Integer getItemSort() {
      return this.itemSort;
   }

   public void setItemSort(Integer itemSort) {
      this.itemSort = itemSort;
   }

   public String getScheId() {
      return this.scheId;
   }

   public void setScheId(String scheId) {
      this.scheId = scheId;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

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

   public void setDedCd(String dedCd) {
      this.dedCd = dedCd;
   }

   public String getDedCd() {
      return this.dedCd;
   }

   public void setDedName(String dedName) {
      this.dedName = dedName;
   }

   public String getDedName() {
      return this.dedName;
   }

   public void setDedDesc(String dedDesc) {
      this.dedDesc = dedDesc;
   }

   public String getDedDesc() {
      return this.dedDesc;
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

   public Double getDedScoreSingle() {
      return this.dedScoreSingle;
   }

   public void setDedScoreSingle(Double dedScoreSingle) {
      this.dedScoreSingle = dedScoreSingle;
   }

   public void setDedScoreMax(Double dedScoreMax) {
      this.dedScoreMax = dedScoreMax;
   }

   public Double getDedScoreMax() {
      return this.dedScoreMax;
   }

   public void setDedScoreDesc(String dedScoreDesc) {
      this.dedScoreDesc = dedScoreDesc;
   }

   public String getDedScoreDesc() {
      return this.dedScoreDesc;
   }

   public void setSort(Integer sort) {
      this.sort = sort;
   }

   public Integer getSort() {
      return this.sort;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("dedCd", this.getDedCd()).append("dedName", this.getDedName()).append("dedDesc", this.getDedDesc()).append("itemCd", this.getItemCd()).append("itemName", this.getItemName()).append("dedType", this.getDedType()).append("dedScoreSigle", this.getDedScoreSingle()).append("dedScoreMax", this.getDedScoreMax()).append("dedScoreDesc", this.getDedScoreDesc()).append("sort", this.getSort()).append("validFlag", this.getValidFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
