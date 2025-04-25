package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ClinItemMain extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构编码 "
   )
   private String hospitalCode;
   private String itemCd;
   @Excel(
      name = "临床项目名称"
   )
   private String itemName;
   @Excel(
      name = "项目类别编码(处置、检查检验等)"
   )
   private String itemClassCd;
   @Excel(
      name = "项目类别名称"
   )
   private String itemClassName;
   @Excel(
      name = "规格"
   )
   private String standard;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "门诊启用标志"
   )
   private String outEnabled;
   @Excel(
      name = "住院启用标志"
   )
   private String inEnabled;
   @Excel(
      name = "单据类型编码"
   )
   private String documentTypeNo;
   @Excel(
      name = "检查设备编码"
   )
   private String examMachineCd;
   @Excel(
      name = "检查设备名称"
   )
   private String examMachineName;
   @Excel(
      name = "检查部位代码"
   )
   private String examPartCd;
   @Excel(
      name = "检查部位名称"
   )
   private String examPartName;
   @Excel(
      name = "标本代码"
   )
   private String specCd;
   @Excel(
      name = "标本名称"
   )
   private String specName;
   @Excel(
      name = "项目类型编码",
      readConverterExp = "1=普通项目；2主从项目；3.自动记账项目"
   )
   private String itemTypeCd;
   @Excel(
      name = "是否需要频次0-无需频次1-需要输入频次"
   )
   private String freqFlag;
   @Excel(
      name = "计价标志",
      readConverterExp = "0=不加价。1计价"
   )
   private String priceFlag;
   @Excel(
      name = "单价"
   )
   private Long price;
   @Excel(
      name = "执行科室编码"
   )
   private String execDeptCd;
   @Excel(
      name = "执行科室名称"
   )
   private String execDeptName;
   @Excel(
      name = "项目标识代码(出院、转科、手术等)"
   )
   private String itemFlagCd;
   @Excel(
      name = "项目标识名称"
   )
   private String itemFlagName;
   @Excel(
      name = "条码分类代码"
   )
   private String barcodeClassCd;
   @Excel(
      name = "条码分类名称"
   )
   private String barcodeClassName;
   @Excel(
      name = "执行终端(1PC端；2移动端）"
   )
   private String execTerm;
   @Excel(
      name = "检查性别"
   )
   private String examSex;
   @Excel(
      name = "适应症"
   )
   private String indication;
   @Excel(
      name = "检查注意事项"
   )
   private String examNote;
   @Excel(
      name = "标本采集要求"
   )
   private String specCollectionReq;
   @Excel(
      name = "检查意义"
   )
   private String examSign;
   @Excel(
      name = "支持诊断"
   )
   private String supportDiag;
   @Excel(
      name = "排除诊断"
   )
   private String exclusionDiag;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人项目"
   )
   private String crePerName;
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
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "执行人标志"
   )
   private String executorFlag;
   @Excel(
      name = "病历事件代码"
   )
   private String emrEventCd;
   private Integer numberLimit;
   private String itemNameShort;
   private String changeFlag;
   private Integer ageStart;
   private Integer ageEnd;

   public String getExecutorFlag() {
      return this.executorFlag;
   }

   public void setExecutorFlag(String executorFlag) {
      this.executorFlag = executorFlag;
   }

   public String getEmrEventCd() {
      return this.emrEventCd;
   }

   public void setEmrEventCd(String emrEventCd) {
      this.emrEventCd = emrEventCd;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
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

   public void setItemClassCd(String itemClassCd) {
      this.itemClassCd = itemClassCd;
   }

   public String getItemClassCd() {
      return this.itemClassCd;
   }

   public void setItemClassName(String itemClassName) {
      this.itemClassName = itemClassName;
   }

   public String getItemClassName() {
      return this.itemClassName;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setOutEnabled(String outEnabled) {
      this.outEnabled = outEnabled;
   }

   public String getOutEnabled() {
      return this.outEnabled;
   }

   public void setInEnabled(String inEnabled) {
      this.inEnabled = inEnabled;
   }

   public String getInEnabled() {
      return this.inEnabled;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setExamMachineCd(String examMachineCd) {
      this.examMachineCd = examMachineCd;
   }

   public String getExamMachineCd() {
      return this.examMachineCd;
   }

   public void setExamMachineName(String examMachineName) {
      this.examMachineName = examMachineName;
   }

   public String getExamMachineName() {
      return this.examMachineName;
   }

   public void setExamPartCd(String examPartCd) {
      this.examPartCd = examPartCd;
   }

   public String getExamPartCd() {
      return this.examPartCd;
   }

   public void setExamPartName(String examPartName) {
      this.examPartName = examPartName;
   }

   public String getExamPartName() {
      return this.examPartName;
   }

   public void setSpecCd(String specCd) {
      this.specCd = specCd;
   }

   public String getSpecCd() {
      return this.specCd;
   }

   public void setSpecName(String specName) {
      this.specName = specName;
   }

   public String getSpecName() {
      return this.specName;
   }

   public void setItemTypeCd(String itemTypeCd) {
      this.itemTypeCd = itemTypeCd;
   }

   public String getItemTypeCd() {
      return this.itemTypeCd;
   }

   public void setFreqFlag(String freqFlag) {
      this.freqFlag = freqFlag;
   }

   public String getFreqFlag() {
      return this.freqFlag;
   }

   public void setPriceFlag(String priceFlag) {
      this.priceFlag = priceFlag;
   }

   public String getPriceFlag() {
      return this.priceFlag;
   }

   public void setPrice(Long price) {
      this.price = price;
   }

   public Long getPrice() {
      return this.price;
   }

   public void setExecDeptCd(String execDeptCd) {
      this.execDeptCd = execDeptCd;
   }

   public String getExecDeptCd() {
      return this.execDeptCd;
   }

   public void setExecDeptName(String execDeptName) {
      this.execDeptName = execDeptName;
   }

   public String getExecDeptName() {
      return this.execDeptName;
   }

   public void setItemFlagCd(String itemFlagCd) {
      this.itemFlagCd = itemFlagCd;
   }

   public String getItemFlagCd() {
      return this.itemFlagCd;
   }

   public void setItemFlagName(String itemFlagName) {
      this.itemFlagName = itemFlagName;
   }

   public String getItemFlagName() {
      return this.itemFlagName;
   }

   public void setBarcodeClassCd(String barcodeClassCd) {
      this.barcodeClassCd = barcodeClassCd;
   }

   public String getBarcodeClassCd() {
      return this.barcodeClassCd;
   }

   public void setBarcodeClassName(String barcodeClassName) {
      this.barcodeClassName = barcodeClassName;
   }

   public String getBarcodeClassName() {
      return this.barcodeClassName;
   }

   public void setExecTerm(String execTerm) {
      this.execTerm = execTerm;
   }

   public String getExecTerm() {
      return this.execTerm;
   }

   public void setExamSex(String examSex) {
      this.examSex = examSex;
   }

   public String getExamSex() {
      return this.examSex;
   }

   public void setIndication(String indication) {
      this.indication = indication;
   }

   public String getIndication() {
      return this.indication;
   }

   public void setExamNote(String examNote) {
      this.examNote = examNote;
   }

   public String getExamNote() {
      return this.examNote;
   }

   public void setSpecCollectionReq(String specCollectionReq) {
      this.specCollectionReq = specCollectionReq;
   }

   public String getSpecCollectionReq() {
      return this.specCollectionReq;
   }

   public void setExamSign(String examSign) {
      this.examSign = examSign;
   }

   public String getExamSign() {
      return this.examSign;
   }

   public void setSupportDiag(String supportDiag) {
      this.supportDiag = supportDiag;
   }

   public String getSupportDiag() {
      return this.supportDiag;
   }

   public void setExclusionDiag(String exclusionDiag) {
      this.exclusionDiag = exclusionDiag;
   }

   public String getExclusionDiag() {
      return this.exclusionDiag;
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

   public Integer getNumberLimit() {
      return this.numberLimit;
   }

   public void setNumberLimit(Integer numberLimit) {
      this.numberLimit = numberLimit;
   }

   public String getItemNameShort() {
      return this.itemNameShort;
   }

   public void setItemNameShort(String itemNameShort) {
      this.itemNameShort = itemNameShort;
   }

   public String getChangeFlag() {
      return this.changeFlag;
   }

   public void setChangeFlag(String changeFlag) {
      this.changeFlag = changeFlag;
   }

   public Integer getAgeStart() {
      return this.ageStart;
   }

   public void setAgeStart(Integer ageStart) {
      this.ageStart = ageStart;
   }

   public Integer getAgeEnd() {
      return this.ageEnd;
   }

   public void setAgeEnd(Integer ageEnd) {
      this.ageEnd = ageEnd;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("itemCd", this.getItemCd()).append("itemName", this.getItemName()).append("itemClassCd", this.getItemClassCd()).append("itemClassName", this.getItemClassName()).append("standard", this.getStandard()).append("inputstrphtc", this.getInputstrphtc()).append("outEnabled", this.getOutEnabled()).append("inEnabled", this.getInEnabled()).append("documentTypeNo", this.getDocumentTypeNo()).append("examMachineCd", this.getExamMachineCd()).append("examMachineName", this.getExamMachineName()).append("examPartCd", this.getExamPartCd()).append("examPartName", this.getExamPartName()).append("specCd", this.getSpecCd()).append("specName", this.getSpecName()).append("itemTypeCd", this.getItemTypeCd()).append("freqFlag", this.getFreqFlag()).append("priceFlag", this.getPriceFlag()).append("price", this.getPrice()).append("execDeptCd", this.getExecDeptCd()).append("execDeptName", this.getExecDeptName()).append("itemFlagCd", this.getItemFlagCd()).append("itemFlagName", this.getItemFlagName()).append("barcodeClassCd", this.getBarcodeClassCd()).append("barcodeClassName", this.getBarcodeClassName()).append("execTerm", this.getExecTerm()).append("examSex", this.getExamSex()).append("indication", this.getIndication()).append("examNote", this.getExamNote()).append("specCollectionReq", this.getSpecCollectionReq()).append("examSign", this.getExamSign()).append("supportDiag", this.getSupportDiag()).append("exclusionDiag", this.getExclusionDiag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
