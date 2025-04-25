package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcFlowScore extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "患者所在科室编码"
   )
   private String deptCd;
   @Excel(
      name = "患者所在科室名称"
   )
   private String deptName;
   @Excel(
      name = "评分方案ID"
   )
   private String scheId;
   @Excel(
      name = "评分方案名称"
   )
   private String scheName;
   @Excel(
      name = "评分方案总分"
   )
   private Double scheScore;
   @Excel(
      name = "适用环节"
   )
   private String appSeg;
   @Excel(
      name = "评分细则编码"
   )
   private String dedCd;
   @Excel(
      name = "评分细则名称"
   )
   private String dedName;
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
      name = "扣分项描述"
   )
   private String dedScoreDesc;
   @Excel(
      name = "扣分次数"
   )
   private Long depNum;
   @Excel(
      name = "本项扣分总分"
   )
   private String dedScore;
   @Excel(
      name = "归属评分项目编码",
      readConverterExp = "与=QC_SCORE_ITEM对应"
   )
   private String itemCd;
   @Excel(
      name = "归属评分项目名称",
      readConverterExp = "与=QC_SCORE_ITEM对应"
   )
   private String itemName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "质控流水号"
   )
   private String qcSn;
   private String itemClassCd;
   private String itemClassName;
   private String itemDesc;

   public String getItemDesc() {
      return this.itemDesc;
   }

   public void setItemDesc(String itemDesc) {
      this.itemDesc = itemDesc;
   }

   public String getItemClassCd() {
      return this.itemClassCd;
   }

   public void setItemClassCd(String itemClassCd) {
      this.itemClassCd = itemClassCd;
   }

   public String getItemClassName() {
      return this.itemClassName;
   }

   public void setItemClassName(String itemClassName) {
      this.itemClassName = itemClassName;
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

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setScheId(String scheId) {
      this.scheId = scheId;
   }

   public String getScheId() {
      return this.scheId;
   }

   public void setScheName(String scheName) {
      this.scheName = scheName;
   }

   public String getScheName() {
      return this.scheName;
   }

   public void setAppSeg(String appSeg) {
      this.appSeg = appSeg;
   }

   public String getAppSeg() {
      return this.appSeg;
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

   public void setDedType(String dedType) {
      this.dedType = dedType;
   }

   public String getDedType() {
      return this.dedType;
   }

   public void setDedScoreDesc(String dedScoreDesc) {
      this.dedScoreDesc = dedScoreDesc;
   }

   public String getDedScoreDesc() {
      return this.dedScoreDesc;
   }

   public void setDepNum(Long depNum) {
      this.depNum = depNum;
   }

   public Long getDepNum() {
      return this.depNum;
   }

   public void setDedScore(String dedScore) {
      this.dedScore = dedScore;
   }

   public String getDedScore() {
      return this.dedScore;
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

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public Double getScheScore() {
      return this.scheScore;
   }

   public void setScheScore(Double scheScore) {
      this.scheScore = scheScore;
   }

   public Double getDedScoreSingle() {
      return this.dedScoreSingle;
   }

   public void setDedScoreSingle(Double dedScoreSingle) {
      this.dedScoreSingle = dedScoreSingle;
   }

   public Double getDedScoreMax() {
      return this.dedScoreMax;
   }

   public void setDedScoreMax(Double dedScoreMax) {
      this.dedScoreMax = dedScoreMax;
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

   public void setQcSn(String qcSn) {
      this.qcSn = qcSn;
   }

   public String getQcSn() {
      return this.qcSn;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("patientId", this.getPatientId()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("scheId", this.getScheId()).append("scheName", this.getScheName()).append("scheScore", this.getScheScore()).append("appSeg", this.getAppSeg()).append("dedCd", this.getDedCd()).append("dedName", this.getDedName()).append("dedType", this.getDedType()).append("dedScoreSingle", this.getDedScoreSingle()).append("dedScoreMax", this.getDedScoreMax()).append("dedScoreDesc", this.getDedScoreDesc()).append("depNum", this.getDepNum()).append("dedScore", this.getDedScore()).append("itemCd", this.getItemCd()).append("itemName", this.getItemName()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("qcSn", this.getQcSn()).append("itemClassCd", this.getItemClassCd()).append("itemClassName", this.getItemClassName()).append("itemDesc", this.getItemDesc()).toString();
   }
}
