package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpCheckSet extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "结构编码"
   )
   private String orgCd;
   @Excel(
      name = "规则编码"
   )
   private String checkNo;
   @Excel(
      name = "规则名称"
   )
   private String checkName;
   @Excel(
      name = "质控数据首页信息分类"
   )
   private String checkMrhpClassNo;
   @Excel(
      name = "质控数据首页信息分类名称"
   )
   private String checkMrhpClassName;
   @Excel(
      name = "质控规则分类编码"
   )
   private String checkClassNo;
   @Excel(
      name = "质控规则分类名称"
   )
   private String checkClassName;
   @Excel(
      name = "质控规则类型编码"
   )
   private String checkTypeNo;
   @Excel(
      name = "质控规则类型名称"
   )
   private String checkTypeName;
   @Excel(
      name = "作用域"
   )
   private String checkActMrType;
   @Excel(
      name = "触发环节"
   )
   private String checkEventNo;
   @Excel(
      name = "严重级别"
   )
   private String checkLevel;
   @Excel(
      name = "是否启用"
   )
   private String checkEnable;
   @Excel(
      name = "规则提示信息"
   )
   private String checkTips;
   @Excel(
      name = "规则说明"
   )
   private String checkComm;
   @Excel(
      name = "校验表名"
   )
   private String checkTable1;
   @Excel(
      name = "校验字段"
   )
   private String checkColumn1;
   @Excel(
      name = "校验表达式"
   )
   private String checkExpression1;
   @Excel(
      name = "关联运算符"
   )
   private String checkRelopeRator;
   @Excel(
      name = "校验关联表名"
   )
   private String checkTable2;
   @Excel(
      name = "校验关联字段"
   )
   private String checkColumn2;
   @Excel(
      name = "校验关联表达式"
   )
   private String checkExpression2;
   @Excel(
      name = "是否校验控制(1：校验；0：不校验)"
   )
   private String checkNull;
   @Excel(
      name = "保护标志。系统预制规则不允许在前台修改"
   )
   private String protectFlag;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
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
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date altDate;
   @Excel(
      name = "校验结果返回 true/false时错误"
   )
   private String checkResult;
   @Excel(
      name = "规则引擎文件名称"
   )
   private String checkFileName;
   @Excel(
      name = "触发环节名称"
   )
   private String checkEventName;
   private String checkHasConditions;

   public String getCheckEventName() {
      return this.checkEventName;
   }

   public void setCheckEventName(String checkEventName) {
      this.checkEventName = checkEventName;
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

   public void setCheckNo(String checkNo) {
      this.checkNo = checkNo;
   }

   public String getCheckNo() {
      return this.checkNo;
   }

   public void setCheckName(String checkName) {
      this.checkName = checkName;
   }

   public String getCheckName() {
      return this.checkName;
   }

   public void setCheckMrhpClassNo(String checkMrhpClassNo) {
      this.checkMrhpClassNo = checkMrhpClassNo;
   }

   public String getCheckMrhpClassNo() {
      return this.checkMrhpClassNo;
   }

   public void setCheckMrhpClassName(String checkMrhpClassName) {
      this.checkMrhpClassName = checkMrhpClassName;
   }

   public String getCheckMrhpClassName() {
      return this.checkMrhpClassName;
   }

   public void setCheckClassNo(String checkClassNo) {
      this.checkClassNo = checkClassNo;
   }

   public String getCheckClassNo() {
      return this.checkClassNo;
   }

   public void setCheckClassName(String checkClassName) {
      this.checkClassName = checkClassName;
   }

   public String getCheckClassName() {
      return this.checkClassName;
   }

   public void setCheckTypeNo(String checkTypeNo) {
      this.checkTypeNo = checkTypeNo;
   }

   public String getCheckTypeNo() {
      return this.checkTypeNo;
   }

   public void setCheckTypeName(String checkTypeName) {
      this.checkTypeName = checkTypeName;
   }

   public String getCheckTypeName() {
      return this.checkTypeName;
   }

   public void setCheckActMrType(String checkActMrType) {
      this.checkActMrType = checkActMrType;
   }

   public String getCheckActMrType() {
      return this.checkActMrType;
   }

   public void setCheckEventNo(String checkEventNo) {
      this.checkEventNo = checkEventNo;
   }

   public String getCheckEventNo() {
      return this.checkEventNo;
   }

   public void setCheckLevel(String checkLevel) {
      this.checkLevel = checkLevel;
   }

   public String getCheckLevel() {
      return this.checkLevel;
   }

   public void setCheckEnable(String checkEnable) {
      this.checkEnable = checkEnable;
   }

   public String getCheckEnable() {
      return this.checkEnable;
   }

   public void setCheckTips(String checkTips) {
      this.checkTips = checkTips;
   }

   public String getCheckTips() {
      return this.checkTips;
   }

   public void setCheckComm(String checkComm) {
      this.checkComm = checkComm;
   }

   public String getCheckComm() {
      return this.checkComm;
   }

   public void setCheckTable1(String checkTable1) {
      this.checkTable1 = checkTable1;
   }

   public String getCheckTable1() {
      return this.checkTable1;
   }

   public void setCheckColumn1(String checkColumn1) {
      this.checkColumn1 = checkColumn1;
   }

   public String getCheckColumn1() {
      return this.checkColumn1;
   }

   public void setCheckExpression1(String checkExpression1) {
      this.checkExpression1 = checkExpression1;
   }

   public String getCheckExpression1() {
      return this.checkExpression1;
   }

   public void setCheckRelopeRator(String checkRelopeRator) {
      this.checkRelopeRator = checkRelopeRator;
   }

   public String getCheckRelopeRator() {
      return this.checkRelopeRator;
   }

   public void setCheckTable2(String checkTable2) {
      this.checkTable2 = checkTable2;
   }

   public String getCheckTable2() {
      return this.checkTable2;
   }

   public void setCheckColumn2(String checkColumn2) {
      this.checkColumn2 = checkColumn2;
   }

   public String getCheckColumn2() {
      return this.checkColumn2;
   }

   public void setCheckExpression2(String checkExpression2) {
      this.checkExpression2 = checkExpression2;
   }

   public String getCheckExpression2() {
      return this.checkExpression2;
   }

   public void setCheckNull(String checkNull) {
      this.checkNull = checkNull;
   }

   public String getCheckNull() {
      return this.checkNull;
   }

   public void setProtectFlag(String protectFlag) {
      this.protectFlag = protectFlag;
   }

   public String getProtectFlag() {
      return this.protectFlag;
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

   public void setCheckResult(String checkResult) {
      this.checkResult = checkResult;
   }

   public String getCheckResult() {
      return this.checkResult;
   }

   public String getCheckFileName() {
      return this.checkFileName;
   }

   public void setCheckFileName(String checkFileName) {
      this.checkFileName = checkFileName;
   }

   public String getCheckHasConditions() {
      return this.checkHasConditions;
   }

   public void setCheckHasConditions(String checkHasConditions) {
      this.checkHasConditions = checkHasConditions;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("checkNo", this.getCheckNo()).append("checkName", this.getCheckName()).append("checkMrhpClassNo", this.getCheckMrhpClassNo()).append("checkMrhpClassName", this.getCheckMrhpClassName()).append("checkClassNo", this.getCheckClassNo()).append("checkClassName", this.getCheckClassName()).append("checkTypeNo", this.getCheckTypeNo()).append("checkTypeName", this.getCheckTypeName()).append("checkActMrType", this.getCheckActMrType()).append("checkEventNo", this.getCheckEventNo()).append("checkLevel", this.getCheckLevel()).append("checkEnable", this.getCheckEnable()).append("checkTips", this.getCheckTips()).append("checkComm", this.getCheckComm()).append("checkTable1", this.getCheckTable1()).append("checkColumn1", this.getCheckColumn1()).append("checkExpression1", this.getCheckExpression1()).append("checkRelopeRator", this.getCheckRelopeRator()).append("checkTable2", this.getCheckTable2()).append("checkColumn2", this.getCheckColumn2()).append("checkExpression2", this.getCheckExpression2()).append("checkNull", this.getCheckNull()).append("protectFlag", this.getProtectFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("checkResult", this.getCheckResult()).append("checkFileName", this.getCheckFileName()).append("checkEventName", this.getCheckEventName()).toString();
   }
}
