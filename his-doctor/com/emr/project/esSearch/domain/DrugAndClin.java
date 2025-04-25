package com.emr.project.esSearch.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import java.math.BigDecimal;
import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.annotation.ESMetaData;
import org.zxp.esclientrhl.enums.Analyzer;
import org.zxp.esclientrhl.enums.DataType;

@ESMetaData(
   indexName = "drugandclin_zy",
   number_of_shards = 1,
   number_of_replicas = 0,
   printLog = true,
   autoCreateIndex = false
)
public class DrugAndClin {
   @ESID
   private String id;
   private String hospitalCode;
   @ESMapping(
      datatype = DataType.text_type
   )
   private String cpNo;
   @ESMapping(
      datatype = DataType.keyword_type,
      analyzer = Analyzer.ik_max_word,
      search_analyzer = Analyzer.ik_smart
   )
   private String cpName;
   @ESMapping(
      datatype = DataType.keyword_type
   )
   private String orderClassCode;
   private String orderClassName;
   private String standard;
   @ESMapping(
      datatype = DataType.text_type
   )
   private String inputstrphtc;
   private BigDecimal price;
   private String performDepCode;
   private String performDepName;
   private String jbywbz;
   private String sccj;
   private BigDecimal sl;
   private BigDecimal xcsl;
   private String unit;
   private String dlcgbz;
   private String outEnabled;
   private String inEnabled;
   private String documentTypeNo;
   private String examMachineCd;
   private String examMachineName;
   private String examPartCd;
   private String examPartName;
   private String specCd;
   private String specName;
   private String orderItemType;
   private String freqFlag;
   private String priceFlag;
   private String itemFlagCd;
   private String itemFlagName;
   private String barcodeClassCd;
   private String barcodeClassName;
   private String execTerm;
   private String examSex;
   private String indication;
   private String examNote;
   private String specCollectionReq;
   private String examSign;
   private String supportDiag;
   private String exclusionDiag;
   private String orderItemFlag;
   private String executorFlag;
   private String emrEventCd;
   private String drugForm;
   private String psfl;
   private String highRiskFlag;
   private String stockNo;
   private String ylxs;
   private String usageUnit;
   private String dwxs;
   private String yongfa;
   private String yongfaName;
   private String yongfaShowName;
   private String orderFreq;
   private String zfbl;
   private String ybsx;
   private String ybsm;
   private BigDecimal num;
   private Boolean collectFlag;
   private String orderFlag;
   private Integer usageTimes;
   private String herbalFlag;
   private String drugType;
   private String drugTypeName;
   @Excel(
      name = "实际用量"
   )
   private BigDecimal orderActualUsage;
   @Excel(
      name = "总量"
   )
   private BigDecimal orderTotalDose;
   @Excel(
      name = "医师说明"
   )
   private String doctorInstructions;
   @Excel(
      name = "金额"
   )
   private BigDecimal orderTotal;
   @Excel(
      name = "医保编码"
   )
   private String insuranceCode;
   private String patientId;
   private String changeFlag;
   private Integer ageStart;
   private Integer ageEnd;
   private String notice;
   private String preAuth;
   private String drugTumor;
   private String controlClass;
   private String controlClassName;
   private String drugAnti;
   private String minUnit;
   private String deptUnits;
   private Integer deptUnitRatio;
   private BigDecimal purPrice;

   public String getChangeFlag() {
      return this.changeFlag;
   }

   public void setChangeFlag(String changeFlag) {
      this.changeFlag = changeFlag;
   }

   public String getNotice() {
      return this.notice;
   }

   public void setNotice(String notice) {
      this.notice = notice;
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

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getInsuranceCode() {
      return this.insuranceCode;
   }

   public void setInsuranceCode(String insuranceCode) {
      this.insuranceCode = insuranceCode;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }

   public BigDecimal getOrderActualUsage() {
      return this.orderActualUsage;
   }

   public void setOrderActualUsage(BigDecimal orderActualUsage) {
      this.orderActualUsage = orderActualUsage;
   }

   public BigDecimal getOrderTotalDose() {
      return this.orderTotalDose;
   }

   public void setOrderTotalDose(BigDecimal orderTotalDose) {
      this.orderTotalDose = orderTotalDose;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

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

   public String getDrugType() {
      return this.drugType;
   }

   public void setDrugType(String drugType) {
      this.drugType = drugType;
   }

   public String getDrugTypeName() {
      return this.drugTypeName;
   }

   public void setDrugTypeName(String drugTypeName) {
      this.drugTypeName = drugTypeName;
   }

   public Integer getUsageTimes() {
      return this.usageTimes;
   }

   public void setUsageTimes(Integer usageTimes) {
      this.usageTimes = usageTimes;
   }

   public Boolean getCollectFlag() {
      return this.collectFlag;
   }

   public void setCollectFlag(Boolean collectFlag) {
      this.collectFlag = collectFlag;
   }

   public BigDecimal getNum() {
      return this.num;
   }

   public void setNum(BigDecimal num) {
      this.num = num;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getOrderClassName() {
      return this.orderClassName;
   }

   public void setOrderClassName(String orderClassName) {
      this.orderClassName = orderClassName;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getPerformDepName() {
      return this.performDepName;
   }

   public void setPerformDepName(String performDepName) {
      this.performDepName = performDepName;
   }

   public String getJbywbz() {
      return this.jbywbz;
   }

   public void setJbywbz(String jbywbz) {
      this.jbywbz = jbywbz;
   }

   public String getSccj() {
      return this.sccj;
   }

   public void setSccj(String sccj) {
      this.sccj = sccj;
   }

   public BigDecimal getSl() {
      return this.sl;
   }

   public void setSl(BigDecimal sl) {
      this.sl = sl;
   }

   public BigDecimal getXcsl() {
      return this.xcsl;
   }

   public void setXcsl(BigDecimal xcsl) {
      this.xcsl = xcsl;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getDlcgbz() {
      return this.dlcgbz;
   }

   public void setDlcgbz(String dlcgbz) {
      this.dlcgbz = dlcgbz;
   }

   public String getOutEnabled() {
      return this.outEnabled;
   }

   public void setOutEnabled(String outEnabled) {
      this.outEnabled = outEnabled;
   }

   public String getInEnabled() {
      return this.inEnabled;
   }

   public void setInEnabled(String inEnabled) {
      this.inEnabled = inEnabled;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getExamMachineCd() {
      return this.examMachineCd;
   }

   public void setExamMachineCd(String examMachineCd) {
      this.examMachineCd = examMachineCd;
   }

   public String getExamMachineName() {
      return this.examMachineName;
   }

   public void setExamMachineName(String examMachineName) {
      this.examMachineName = examMachineName;
   }

   public String getExamPartCd() {
      return this.examPartCd;
   }

   public void setExamPartCd(String examPartCd) {
      this.examPartCd = examPartCd;
   }

   public String getExamPartName() {
      return this.examPartName;
   }

   public void setExamPartName(String examPartName) {
      this.examPartName = examPartName;
   }

   public String getSpecCd() {
      return this.specCd;
   }

   public void setSpecCd(String specCd) {
      this.specCd = specCd;
   }

   public String getSpecName() {
      return this.specName;
   }

   public void setSpecName(String specName) {
      this.specName = specName;
   }

   public String getOrderItemType() {
      return this.orderItemType;
   }

   public void setOrderItemType(String orderItemType) {
      this.orderItemType = orderItemType;
   }

   public String getFreqFlag() {
      return this.freqFlag;
   }

   public void setFreqFlag(String freqFlag) {
      this.freqFlag = freqFlag;
   }

   public String getPriceFlag() {
      return this.priceFlag;
   }

   public void setPriceFlag(String priceFlag) {
      this.priceFlag = priceFlag;
   }

   public String getItemFlagCd() {
      return this.itemFlagCd;
   }

   public void setItemFlagCd(String itemFlagCd) {
      this.itemFlagCd = itemFlagCd;
   }

   public String getItemFlagName() {
      return this.itemFlagName;
   }

   public void setItemFlagName(String itemFlagName) {
      this.itemFlagName = itemFlagName;
   }

   public String getBarcodeClassCd() {
      return this.barcodeClassCd;
   }

   public void setBarcodeClassCd(String barcodeClassCd) {
      this.barcodeClassCd = barcodeClassCd;
   }

   public String getBarcodeClassName() {
      return this.barcodeClassName;
   }

   public void setBarcodeClassName(String barcodeClassName) {
      this.barcodeClassName = barcodeClassName;
   }

   public String getExecTerm() {
      return this.execTerm;
   }

   public void setExecTerm(String execTerm) {
      this.execTerm = execTerm;
   }

   public String getExamSex() {
      return this.examSex;
   }

   public void setExamSex(String examSex) {
      this.examSex = examSex;
   }

   public String getIndication() {
      return this.indication;
   }

   public void setIndication(String indication) {
      this.indication = indication;
   }

   public String getExamNote() {
      return this.examNote;
   }

   public void setExamNote(String examNote) {
      this.examNote = examNote;
   }

   public String getSpecCollectionReq() {
      return this.specCollectionReq;
   }

   public void setSpecCollectionReq(String specCollectionReq) {
      this.specCollectionReq = specCollectionReq;
   }

   public String getExamSign() {
      return this.examSign;
   }

   public void setExamSign(String examSign) {
      this.examSign = examSign;
   }

   public String getSupportDiag() {
      return this.supportDiag;
   }

   public void setSupportDiag(String supportDiag) {
      this.supportDiag = supportDiag;
   }

   public String getExclusionDiag() {
      return this.exclusionDiag;
   }

   public void setExclusionDiag(String exclusionDiag) {
      this.exclusionDiag = exclusionDiag;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getPsfl() {
      return this.psfl;
   }

   public void setPsfl(String psfl) {
      this.psfl = psfl;
   }

   public String getHighRiskFlag() {
      return this.highRiskFlag;
   }

   public void setHighRiskFlag(String highRiskFlag) {
      this.highRiskFlag = highRiskFlag;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public String getYlxs() {
      return this.ylxs;
   }

   public void setYlxs(String ylxs) {
      this.ylxs = ylxs;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public String getDwxs() {
      return this.dwxs;
   }

   public void setDwxs(String dwxs) {
      this.dwxs = dwxs;
   }

   public String getYongfa() {
      return this.yongfa;
   }

   public void setYongfa(String yongfa) {
      this.yongfa = yongfa;
   }

   public String getYongfaName() {
      return this.yongfaName;
   }

   public void setYongfaName(String yongfaName) {
      this.yongfaName = yongfaName;
   }

   public String getYongfaShowName() {
      return this.yongfaShowName;
   }

   public void setYongfaShowName(String yongfaShowName) {
      this.yongfaShowName = yongfaShowName;
   }

   public String getOrderFreq() {
      return this.orderFreq;
   }

   public void setOrderFreq(String orderFreq) {
      this.orderFreq = orderFreq;
   }

   public String getZfbl() {
      return this.zfbl;
   }

   public void setZfbl(String zfbl) {
      this.zfbl = zfbl;
   }

   public String getYbsx() {
      return this.ybsx;
   }

   public void setYbsx(String ybsx) {
      this.ybsx = ybsx;
   }

   public String getYbsm() {
      return this.ybsm;
   }

   public void setYbsm(String ybsm) {
      this.ybsm = ybsm;
   }

   public String getOrderItemFlag() {
      return this.orderItemFlag;
   }

   public void setOrderItemFlag(String orderItemFlag) {
      this.orderItemFlag = orderItemFlag;
   }

   public String getOrderFlag() {
      return this.orderFlag;
   }

   public void setOrderFlag(String orderFlag) {
      this.orderFlag = orderFlag;
   }

   public String getPreAuth() {
      return this.preAuth;
   }

   public void setPreAuth(String preAuth) {
      this.preAuth = preAuth;
   }

   public String getDrugTumor() {
      return this.drugTumor;
   }

   public void setDrugTumor(String drugTumor) {
      this.drugTumor = drugTumor;
   }

   public String getControlClass() {
      return this.controlClass;
   }

   public void setControlClass(String controlClass) {
      this.controlClass = controlClass;
   }

   public String getDrugAnti() {
      return this.drugAnti;
   }

   public void setDrugAnti(String drugAnti) {
      this.drugAnti = drugAnti;
   }

   public String getControlClassName() {
      return this.controlClassName;
   }

   public void setControlClassName(String controlClassName) {
      this.controlClassName = controlClassName;
   }

   public String getMinUnit() {
      return this.minUnit;
   }

   public void setMinUnit(String minUnit) {
      this.minUnit = minUnit;
   }

   public String getDeptUnits() {
      return this.deptUnits;
   }

   public void setDeptUnits(String deptUnits) {
      this.deptUnits = deptUnits;
   }

   public Integer getDeptUnitRatio() {
      return this.deptUnitRatio;
   }

   public void setDeptUnitRatio(Integer deptUnitRatio) {
      this.deptUnitRatio = deptUnitRatio;
   }

   public BigDecimal getPurPrice() {
      return this.purPrice;
   }

   public void setPurPrice(BigDecimal purPrice) {
      this.purPrice = purPrice;
   }
}
