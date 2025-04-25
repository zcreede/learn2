package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrSearchCaseDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "方案ID"
   )
   private Long caseId;
   @Excel(
      name = "查询条件编码 关键词，患者性别，科室，专科，病种，入院病情，入院方式，患者年龄，手术级别，患者病情，治疗类别，护理级别，出院时间"
   )
   private String detailCode;
   @Excel(
      name = "查询条件名称 关键词，患者性别，科室，专科，病种，入院病情，入院方式，患者年龄，手术级别，患者病情，治疗类别，护理级别，出院时间"
   )
   private String detailName;
   @Excel(
      name = "关键词类型名称"
   )
   private String keyWordName;
   @Excel(
      name = "关键词类型编码"
   )
   private String keyWordCode;
   @Excel(
      name = "查询条件内容"
   )
   private String text;
   @Excel(
      name = "查询条件内容编码"
   )
   private String textCode;
   @Excel(
      name = "数字最大值"
   )
   private Long numMax;
   @Excel(
      name = "数字最小值"
   )
   private Long numMin;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "时间最大值",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date dateMax;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "时间最小值",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date dateMin;
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
   private String keyWordCondition;
   private Integer keyWordOrder;
   private String queryStatus;

   public String getKeyWordCondition() {
      return this.keyWordCondition;
   }

   public void setKeyWordCondition(String keyWordCondition) {
      this.keyWordCondition = keyWordCondition;
   }

   public Integer getKeyWordOrder() {
      return this.keyWordOrder;
   }

   public void setKeyWordOrder(Integer keyWordOrder) {
      this.keyWordOrder = keyWordOrder;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setCaseId(Long caseId) {
      this.caseId = caseId;
   }

   public Long getCaseId() {
      return this.caseId;
   }

   public void setDetailCode(String detailCode) {
      this.detailCode = detailCode;
   }

   public String getDetailCode() {
      return this.detailCode;
   }

   public void setDetailName(String detailName) {
      this.detailName = detailName;
   }

   public String getDetailName() {
      return this.detailName;
   }

   public void setKeyWordName(String keyWordName) {
      this.keyWordName = keyWordName;
   }

   public String getKeyWordName() {
      return this.keyWordName;
   }

   public void setKeyWordCode(String keyWordCode) {
      this.keyWordCode = keyWordCode;
   }

   public String getKeyWordCode() {
      return this.keyWordCode;
   }

   public void setText(String text) {
      this.text = text;
   }

   public String getText() {
      return this.text;
   }

   public void setTextCode(String textCode) {
      this.textCode = textCode;
   }

   public String getTextCode() {
      return this.textCode;
   }

   public void setNumMax(Long numMax) {
      this.numMax = numMax;
   }

   public Long getNumMax() {
      return this.numMax;
   }

   public void setNumMin(Long numMin) {
      this.numMin = numMin;
   }

   public Long getNumMin() {
      return this.numMin;
   }

   public void setDateMax(Date dateMax) {
      this.dateMax = dateMax;
   }

   public Date getDateMax() {
      return this.dateMax;
   }

   public void setDateMin(Date dateMin) {
      this.dateMin = dateMin;
   }

   public Date getDateMin() {
      return this.dateMin;
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

   public String getQueryStatus() {
      return this.queryStatus;
   }

   public void setQueryStatus(String queryStatus) {
      this.queryStatus = queryStatus;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("caseId", this.getCaseId()).append("detailCode", this.getDetailCode()).append("detailName", this.getDetailName()).append("keyWordName", this.getKeyWordName()).append("keyWordCode", this.getKeyWordCode()).append("text", this.getText()).append("textCode", this.getTextCode()).append("numMax", this.getNumMax()).append("numMin", this.getNumMin()).append("dateMax", this.getDateMax()).append("dateMin", this.getDateMin()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("keyWordCondition", this.getKeyWordCondition()).append("keyWordOrder", this.getKeyWordOrder()).toString();
   }
}
