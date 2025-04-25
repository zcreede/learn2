package com.emr.project.dr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DrHandoverMain extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "科室编码"
   )
   private String deptCd;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @Excel(
      name = "交班日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date handoverDate;
   @Excel(
      name = "方案编码"
   )
   private Long schemeCd;
   @Excel(
      name = "方案名称"
   )
   private String schemeName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "开始时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date schemeBegin;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "结束时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date schemeEnd;
   @Excel(
      name = "交班医师编码"
   )
   private String shiftDocCd;
   @Excel(
      name = "交班者医师名称"
   )
   private String shiftDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "交班医师签名时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date shiftDocSignDate;
   @Excel(
      name = "接班医师编码"
   )
   private String joinDocCd;
   @Excel(
      name = "接班医师姓名"
   )
   private String joinDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "接班医师签名时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date joinDocSignDate;
   @Excel(
      name = "原有人数"
   )
   private Integer originalNum = 0;
   @Excel(
      name = "现有人数"
   )
   private Integer nowNum = 0;
   @Excel(
      name = "新入人数"
   )
   private Integer newNum = 0;
   @Excel(
      name = "转入人数"
   )
   private Integer tiNum = 0;
   @Excel(
      name = "转出人数"
   )
   private Integer toNum = 0;
   @Excel(
      name = "死亡人数"
   )
   private Integer deathNum = 0;
   @Excel(
      name = "危重人数"
   )
   private Integer criticalNum = 0;
   @Excel(
      name = "手术人数"
   )
   private Integer operatNum = 0;
   @Excel(
      name = "出院人数"
   )
   private Integer outHosNum = 0;
   @Excel(
      name = "危急值人数"
   )
   private Integer dangNum = 0;
   @Excel(
      name = "拟术人数"
   )
   private Integer planNum = 0;
   @Excel(
      name = "分娩人数"
   )
   private Integer partNum = 0;
   @Excel(
      name = "婴儿人数"
   )
   private Integer babyNum = 0;
   @Excel(
      name = "加床患者数"
   )
   private Integer addBedNum = 0;
   private String delFlag;
   @Excel(
      name = "状态"
   )
   private String state;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date altDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date creDate;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
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

   public Date getHandoverDate() {
      return this.handoverDate;
   }

   public void setHandoverDate(Date handoverDate) {
      this.handoverDate = handoverDate;
   }

   public void setSchemeCd(Long schemeCd) {
      this.schemeCd = schemeCd;
   }

   public Long getSchemeCd() {
      return this.schemeCd;
   }

   public void setSchemeName(String schemeName) {
      this.schemeName = schemeName;
   }

   public String getSchemeName() {
      return this.schemeName;
   }

   public void setSchemeBegin(Date schemeBegin) {
      this.schemeBegin = schemeBegin;
   }

   public Date getSchemeBegin() {
      return this.schemeBegin;
   }

   public void setSchemeEnd(Date schemeEnd) {
      this.schemeEnd = schemeEnd;
   }

   public Date getSchemeEnd() {
      return this.schemeEnd;
   }

   public void setShiftDocCd(String shiftDocCd) {
      this.shiftDocCd = shiftDocCd;
   }

   public String getShiftDocCd() {
      return this.shiftDocCd;
   }

   public void setShiftDocName(String shiftDocName) {
      this.shiftDocName = shiftDocName;
   }

   public String getShiftDocName() {
      return this.shiftDocName;
   }

   public void setShiftDocSignDate(Date shiftDocSignDate) {
      this.shiftDocSignDate = shiftDocSignDate;
   }

   public Date getShiftDocSignDate() {
      return this.shiftDocSignDate;
   }

   public void setJoinDocCd(String joinDocCd) {
      this.joinDocCd = joinDocCd;
   }

   public String getJoinDocCd() {
      return this.joinDocCd;
   }

   public void setJoinDocName(String joinDocName) {
      this.joinDocName = joinDocName;
   }

   public String getJoinDocName() {
      return this.joinDocName;
   }

   public void setJoinDocSignDate(Date joinDocSignDate) {
      this.joinDocSignDate = joinDocSignDate;
   }

   public Date getJoinDocSignDate() {
      return this.joinDocSignDate;
   }

   public Integer getOriginalNum() {
      return this.originalNum;
   }

   public void setOriginalNum(Integer originalNum) {
      this.originalNum = originalNum;
   }

   public Integer getNowNum() {
      return this.nowNum;
   }

   public void setNowNum(Integer nowNum) {
      this.nowNum = nowNum;
   }

   public Integer getNewNum() {
      return this.newNum;
   }

   public void setNewNum(Integer newNum) {
      this.newNum = newNum;
   }

   public Integer getTiNum() {
      return this.tiNum;
   }

   public void setTiNum(Integer tiNum) {
      this.tiNum = tiNum;
   }

   public Integer getToNum() {
      return this.toNum;
   }

   public void setToNum(Integer toNum) {
      this.toNum = toNum;
   }

   public Integer getDeathNum() {
      return this.deathNum;
   }

   public void setDeathNum(Integer deathNum) {
      this.deathNum = deathNum;
   }

   public Integer getCriticalNum() {
      return this.criticalNum;
   }

   public void setCriticalNum(Integer criticalNum) {
      this.criticalNum = criticalNum;
   }

   public Integer getOperatNum() {
      return this.operatNum;
   }

   public void setOperatNum(Integer operatNum) {
      this.operatNum = operatNum;
   }

   public Integer getOutHosNum() {
      return this.outHosNum;
   }

   public void setOutHosNum(Integer outHosNum) {
      this.outHosNum = outHosNum;
   }

   public Integer getDangNum() {
      return this.dangNum;
   }

   public void setDangNum(Integer dangNum) {
      this.dangNum = dangNum;
   }

   public Integer getPlanNum() {
      return this.planNum;
   }

   public void setPlanNum(Integer planNum) {
      this.planNum = planNum;
   }

   public Integer getPartNum() {
      return this.partNum;
   }

   public void setPartNum(Integer partNum) {
      this.partNum = partNum;
   }

   public Integer getBabyNum() {
      return this.babyNum;
   }

   public void setBabyNum(Integer babyNum) {
      this.babyNum = babyNum;
   }

   public Integer getAddBedNum() {
      return this.addBedNum;
   }

   public void setAddBedNum(Integer addBedNum) {
      this.addBedNum = addBedNum;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getState() {
      return this.state;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("schemeCd", this.getSchemeCd()).append("schemeName", this.getSchemeName()).append("schemeBegin", this.getSchemeBegin()).append("schemeEnd", this.getSchemeEnd()).append("shiftDocCd", this.getShiftDocCd()).append("shiftDocName", this.getShiftDocName()).append("shiftDocSignDate", this.getShiftDocSignDate()).append("joinDocCd", this.getJoinDocCd()).append("joinDocName", this.getJoinDocName()).append("joinDocSignDate", this.getJoinDocSignDate()).append("originalNum", this.getOriginalNum()).append("nowNum", this.getNowNum()).append("newNum", this.getNewNum()).append("tiNum", this.getTiNum()).append("toNum", this.getToNum()).append("deathNum", this.getDeathNum()).append("criticalNum", this.getCriticalNum()).append("operatNum", this.getOperatNum()).append("outHosNum", this.getOutHosNum()).append("dangNum", this.getDangNum()).append("planNum", this.getPlanNum()).append("partNum", this.getPartNum()).append("babyNum", this.getBabyNum()).append("delFlag", this.getDelFlag()).append("state", this.getState()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
