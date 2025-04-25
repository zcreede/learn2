package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcFlowScoreList extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "质控流水号"
   )
   private String qcSn;
   @Excel(
      name = "评分细则编码"
   )
   private String dedCd;
   @Excel(
      name = "评分细则名称"
   )
   private String dedName;
   @Excel(
      name = "缺陷id",
      readConverterExp = "e=mr_qc_list表中的主键id"
   )
   private Long qcId;
   @Excel(
      name = "规则id"
   )
   private Long ruleId;
   @Excel(
      name = "规则名称"
   )
   private String ruleName;
   @Excel(
      name = "缺陷描述"
   )
   private String flawDesc;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   private Long mainId;

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setQcSn(String qcSn) {
      this.qcSn = qcSn;
   }

   public String getQcSn() {
      return this.qcSn;
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

   public void setQcId(Long qcId) {
      this.qcId = qcId;
   }

   public Long getQcId() {
      return this.qcId;
   }

   public void setRuleId(Long ruleId) {
      this.ruleId = ruleId;
   }

   public Long getRuleId() {
      return this.ruleId;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setFlawDesc(String flawDesc) {
      this.flawDesc = flawDesc;
   }

   public String getFlawDesc() {
      return this.flawDesc;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("qcSn", this.getQcSn()).append("dedCd", this.getDedCd()).append("dedName", this.getDedName()).append("qcId", this.getQcId()).append("ruleId", this.getRuleId()).append("ruleName", this.getRuleName()).append("flawDesc", this.getFlawDesc()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
