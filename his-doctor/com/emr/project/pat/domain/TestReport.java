package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestReport extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String id;
   @Excel(
      name = "检验报告单编号"
   )
   private String testRepNo;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "报告单元名称",
      readConverterExp = "血=球仪、尿沉渣等"
   )
   private String repUnitName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "采样时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date samTime;
   @Excel(
      name = "检验标本号"
   )
   private String specNo;
   @Excel(
      name = "检验标本名称"
   )
   private String specName;
   @Excel(
      name = "检验标本代码"
   )
   private String specCd;
   @Excel(
      name = "标本备注"
   )
   private String specRemark;
   @Excel(
      name = "电子申请单编号"
   )
   private String appCd;
   @Excel(
      name = "检验申请机构名称"
   )
   private String appOrgName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date appTime;
   @Excel(
      name = "申请医生编码"
   )
   private String appDocCd;
   @Excel(
      name = "申请医生名称"
   )
   private String appDocName;
   @Excel(
      name = "申请科室编码"
   )
   private String appDeptCd;
   @Excel(
      name = "申请科室名称"
   )
   private String appDeptName;
   @Excel(
      name = "检验类别"
   )
   private String testType;
   @Excel(
      name = "检验方法名称"
   )
   private String testWayName;
   @Excel(
      name = "检验报告科室名称"
   )
   private String repDeptName;
   @Excel(
      name = "检验报告机构名称"
   )
   private String repOrgName;
   @Excel(
      name = "检验技师编码"
   )
   private String testTecCd;
   @Excel(
      name = "检验技师签名"
   )
   private String testTecName;
   @Excel(
      name = "检验医师编码"
   )
   private String testDocCd;
   @Excel(
      name = "检验医师签名"
   )
   private String testDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "审核时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date conDate;
   @Excel(
      name = "审核医师编码"
   )
   private String conDocCd;
   @Excel(
      name = "审核医师签名"
   )
   private String conDocName;
   @Excel(
      name = "实验室意见"
   )
   private String labAdvice;
   @Excel(
      name = "检验报告科室编码"
   )
   private String repDeptCd;
   @Excel(
      name = "报告医师代码"
   )
   private String repDocCd;
   @Excel(
      name = "报告医师签名"
   )
   private String repDocName;
   @Excel(
      name = "发布人编码"
   )
   private String issCd;
   @Excel(
      name = "发布人姓名"
   )
   private String issName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "发布时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date issTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "最后修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date lastAltTime;
   @Excel(
      name = "来源",
      readConverterExp = "院=内、院外"
   )
   private String source;
   @Excel(
      name = "标识",
      readConverterExp = "住=院、门诊"
   )
   private String identify;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "检验报告日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date repDate;
   @Excel(
      name = "检验目的"
   )
   private String testAim;
   @Excel(
      name = "修改标志"
   )
   private String modFlag;
   @Excel(
      name = "报告后修改标志"
   )
   private String reportModFlag;
   @Excel(
      name = "细菌标志"
   )
   private String bactFlag;
   @Excel(
      name = "检验报告备注"
   )
   private String testRemark;
   @Excel(
      name = "危急值报告标志",
      readConverterExp = "0=：非危急；1：危急"
   )
   private String alterFlag;
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
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
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
   @Excel(
      name = "临床项目编号"
   )
   private String clinItemCd;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "接收日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date recDate;
   private String delFlag;
   @Excel(
      name = "临床项目名称"
   )
   private String clinItemName;
   private String recDocCd;

   public String getRecDocCd() {
      return this.recDocCd;
   }

   public void setRecDocCd(String recDocCd) {
      this.recDocCd = recDocCd;
   }

   public String getClinItemName() {
      return this.clinItemName;
   }

   public void setClinItemName(String clinItemName) {
      this.clinItemName = clinItemName;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return this.id;
   }

   public void setTestRepNo(String testRepNo) {
      this.testRepNo = testRepNo;
   }

   public String getTestRepNo() {
      return this.testRepNo;
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

   public void setRepUnitName(String repUnitName) {
      this.repUnitName = repUnitName;
   }

   public String getRepUnitName() {
      return this.repUnitName;
   }

   public void setSamTime(Date samTime) {
      this.samTime = samTime;
   }

   public Date getSamTime() {
      return this.samTime;
   }

   public void setSpecNo(String specNo) {
      this.specNo = specNo;
   }

   public String getSpecNo() {
      return this.specNo;
   }

   public void setSpecName(String specName) {
      this.specName = specName;
   }

   public String getSpecName() {
      return this.specName;
   }

   public void setSpecCd(String specCd) {
      this.specCd = specCd;
   }

   public String getSpecCd() {
      return this.specCd;
   }

   public void setSpecRemark(String specRemark) {
      this.specRemark = specRemark;
   }

   public String getSpecRemark() {
      return this.specRemark;
   }

   public void setAppCd(String appCd) {
      this.appCd = appCd;
   }

   public String getAppCd() {
      return this.appCd;
   }

   public void setAppOrgName(String appOrgName) {
      this.appOrgName = appOrgName;
   }

   public String getAppOrgName() {
      return this.appOrgName;
   }

   public void setAppTime(Date appTime) {
      this.appTime = appTime;
   }

   public Date getAppTime() {
      return this.appTime;
   }

   public void setAppDocCd(String appDocCd) {
      this.appDocCd = appDocCd;
   }

   public String getAppDocCd() {
      return this.appDocCd;
   }

   public void setAppDocName(String appDocName) {
      this.appDocName = appDocName;
   }

   public String getAppDocName() {
      return this.appDocName;
   }

   public void setAppDeptCd(String appDeptCd) {
      this.appDeptCd = appDeptCd;
   }

   public String getAppDeptCd() {
      return this.appDeptCd;
   }

   public void setAppDeptName(String appDeptName) {
      this.appDeptName = appDeptName;
   }

   public String getAppDeptName() {
      return this.appDeptName;
   }

   public void setTestType(String testType) {
      this.testType = testType;
   }

   public String getTestType() {
      return this.testType;
   }

   public void setTestWayName(String testWayName) {
      this.testWayName = testWayName;
   }

   public String getTestWayName() {
      return this.testWayName;
   }

   public void setRepDeptName(String repDeptName) {
      this.repDeptName = repDeptName;
   }

   public String getRepDeptName() {
      return this.repDeptName;
   }

   public void setRepOrgName(String repOrgName) {
      this.repOrgName = repOrgName;
   }

   public String getRepOrgName() {
      return this.repOrgName;
   }

   public void setTestTecCd(String testTecCd) {
      this.testTecCd = testTecCd;
   }

   public String getTestTecCd() {
      return this.testTecCd;
   }

   public void setTestTecName(String testTecName) {
      this.testTecName = testTecName;
   }

   public String getTestTecName() {
      return this.testTecName;
   }

   public void setTestDocCd(String testDocCd) {
      this.testDocCd = testDocCd;
   }

   public String getTestDocCd() {
      return this.testDocCd;
   }

   public void setTestDocName(String testDocName) {
      this.testDocName = testDocName;
   }

   public String getTestDocName() {
      return this.testDocName;
   }

   public void setConDate(Date conDate) {
      this.conDate = conDate;
   }

   public Date getConDate() {
      return this.conDate;
   }

   public void setConDocCd(String conDocCd) {
      this.conDocCd = conDocCd;
   }

   public String getConDocCd() {
      return this.conDocCd;
   }

   public void setConDocName(String conDocName) {
      this.conDocName = conDocName;
   }

   public String getConDocName() {
      return this.conDocName;
   }

   public void setLabAdvice(String labAdvice) {
      this.labAdvice = labAdvice;
   }

   public String getLabAdvice() {
      return this.labAdvice;
   }

   public void setRepDeptCd(String repDeptCd) {
      this.repDeptCd = repDeptCd;
   }

   public String getRepDeptCd() {
      return this.repDeptCd;
   }

   public void setRepDocCd(String repDocCd) {
      this.repDocCd = repDocCd;
   }

   public String getRepDocCd() {
      return this.repDocCd;
   }

   public void setRepDocName(String repDocName) {
      this.repDocName = repDocName;
   }

   public String getRepDocName() {
      return this.repDocName;
   }

   public void setIssCd(String issCd) {
      this.issCd = issCd;
   }

   public String getIssCd() {
      return this.issCd;
   }

   public void setIssName(String issName) {
      this.issName = issName;
   }

   public String getIssName() {
      return this.issName;
   }

   public void setIssTime(Date issTime) {
      this.issTime = issTime;
   }

   public Date getIssTime() {
      return this.issTime;
   }

   public void setLastAltTime(Date lastAltTime) {
      this.lastAltTime = lastAltTime;
   }

   public Date getLastAltTime() {
      return this.lastAltTime;
   }

   public void setSource(String source) {
      this.source = source;
   }

   public String getSource() {
      return this.source;
   }

   public void setIdentify(String identify) {
      this.identify = identify;
   }

   public String getIdentify() {
      return this.identify;
   }

   public void setRepDate(Date repDate) {
      this.repDate = repDate;
   }

   public Date getRepDate() {
      return this.repDate;
   }

   public void setTestAim(String testAim) {
      this.testAim = testAim;
   }

   public String getTestAim() {
      return this.testAim;
   }

   public void setModFlag(String modFlag) {
      this.modFlag = modFlag;
   }

   public String getModFlag() {
      return this.modFlag;
   }

   public void setReportModFlag(String reportModFlag) {
      this.reportModFlag = reportModFlag;
   }

   public String getReportModFlag() {
      return this.reportModFlag;
   }

   public void setBactFlag(String bactFlag) {
      this.bactFlag = bactFlag;
   }

   public String getBactFlag() {
      return this.bactFlag;
   }

   public void setTestRemark(String testRemark) {
      this.testRemark = testRemark;
   }

   public String getTestRemark() {
      return this.testRemark;
   }

   public void setAlterFlag(String alterFlag) {
      this.alterFlag = alterFlag;
   }

   public String getAlterFlag() {
      return this.alterFlag;
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

   public void setClinItemCd(String clinItemCd) {
      this.clinItemCd = clinItemCd;
   }

   public String getClinItemCd() {
      return this.clinItemCd;
   }

   public void setRecDate(Date recDate) {
      this.recDate = recDate;
   }

   public Date getRecDate() {
      return this.recDate;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("testRepNo", this.getTestRepNo()).append("orgCd", this.getOrgCd()).append("patientId", this.getPatientId()).append("repUnitName", this.getRepUnitName()).append("samTime", this.getSamTime()).append("specNo", this.getSpecNo()).append("specName", this.getSpecName()).append("specCd", this.getSpecCd()).append("specRemark", this.getSpecRemark()).append("appCd", this.getAppCd()).append("appOrgName", this.getAppOrgName()).append("appTime", this.getAppTime()).append("appDocCd", this.getAppDocCd()).append("appDocName", this.getAppDocName()).append("appDeptCd", this.getAppDeptCd()).append("appDeptName", this.getAppDeptName()).append("testType", this.getTestType()).append("testWayName", this.getTestWayName()).append("repDeptName", this.getRepDeptName()).append("repOrgName", this.getRepOrgName()).append("testTecCd", this.getTestTecCd()).append("testTecName", this.getTestTecName()).append("testDocCd", this.getTestDocCd()).append("testDocName", this.getTestDocName()).append("conDate", this.getConDate()).append("conDocCd", this.getConDocCd()).append("conDocName", this.getConDocName()).append("labAdvice", this.getLabAdvice()).append("repDeptCd", this.getRepDeptCd()).append("repDocCd", this.getRepDocCd()).append("repDocName", this.getRepDocName()).append("issCd", this.getIssCd()).append("issName", this.getIssName()).append("issTime", this.getIssTime()).append("lastAltTime", this.getLastAltTime()).append("source", this.getSource()).append("identify", this.getIdentify()).append("repDate", this.getRepDate()).append("testAim", this.getTestAim()).append("modFlag", this.getModFlag()).append("reportModFlag", this.getReportModFlag()).append("bactFlag", this.getBactFlag()).append("testRemark", this.getTestRemark()).append("alterFlag", this.getAlterFlag()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("clinItemCd", this.getClinItemCd()).append("recDate", this.getRecDate()).append("delFlag", this.getDelFlag()).append("clinItemName", this.getClinItemName()).toString();
   }
}
