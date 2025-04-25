package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderItem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String orderNo;
   @Excel(
      name = "医嘱类型"
   )
   private String orderType;
   @Excel(
      name = "医嘱类别"
   )
   private String orderClassCode;
   private String orderSortNumber;
   @Excel(
      name = "组号"
   )
   private Integer orderGroupNo;
   @Excel(
      name = "临床项目编号"
   )
   private String cpNo;
   @Excel(
      name = "临床项目名称"
   )
   private String cpName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "医嘱开始时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date orderStartTime;
   @Excel(
      name = "医生站 开立医嘱写入  "
   )
   private String orderStartDoc;
   @Excel(
      name = "开嘱医师名称"
   )
   private String orderStartDocName;
   @Excel(
      name = "开立签名标志(0：未签名；1：已签名)"
   )
   private String orderDoctorSignFlag;
   @Excel(
      name = "计价标志(0：未计价；1：已计价)"
   )
   private String priceFlag;
   @Excel(
      name = "单据类型编号"
   )
   private String documentTypeNo;
   @Excel(
      name = "用药时间"
   )
   private String takeDrugTime;
   @Excel(
      name = "打印次数"
   )
   private Integer printTimes;
   @Excel(
      name = "1：出院带药；2：特殊用药；3：自备药品；4：备用药品"
   )
   private String outHavaDrugFlag;
   @Excel(
      name = "1：当天领药；2：提前一天"
   )
   private String takeDrugMode;
   @Excel(
      name = "补录标志(0：非补录；1：补录)"
   )
   private String additionalFlag;
   @Excel(
      name = "转科出院标志(0：非转科出院；1：转科出院)"
   )
   private String transferOutFlag;
   @Excel(
      name = "预约标志(0：未预约；1：已预约)"
   )
   private Integer precontractFlag;
   @Excel(
      name = "持续时间"
   )
   private BigDecimal orderDuration;
   @Excel(
      name = "持续单位"
   )
   private String orderDurationUnit;
   @Excel(
      name = "项目类型(1：普通项目 ；2：主从项目；3：按小时记账)"
   )
   private String orderItemType;
   @Excel(
      name = "项目执行人标志(0：护士执行；1：医生执行；)"
   )
   private String performStaffFlag;
   @Excel(
      name = "提交医师内码"
   )
   private String submitDoctorCode;
   @Excel(
      name = "提交医师编码"
   )
   private String submitDoctorNo;
   @Excel(
      name = "提交医师姓名"
   )
   private String submitDoctorName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "医嘱提交时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date submitTime;
   @Excel(
      name = "项目数量"
   )
   private BigDecimal orderDoseItem;
   @Excel(
      name = "项目单位"
   )
   private String unitItem;
   @Excel(
      name = "项目单价"
   )
   private BigDecimal priceItem;
   @Excel(
      name = "项目金额"
   )
   private BigDecimal orderTotalItem;
   @Excel(
      name = "用法"
   )
   private String itemOrderUsageWay;
   @Excel(
      name = "用法名称"
   )
   private String itemOrderUsageWayName;
   @Excel(
      name = "频率"
   )
   private String itemOrderFreq;
   @Excel(
      name = "频率名称"
   )
   private String itemOrderFreqName;
   @Excel(
      name = "执行科室"
   )
   private String executorDptNo;
   @Excel(
      name = "医嘱状态"
   )
   private String orderStatus;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "护士站 审核医嘱写入",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date orderAuditTime;
   @Excel(
      name = "护士站 审核医嘱写入"
   )
   private String orderAuditDoc;
   @Excel(
      name = "审核护士姓名"
   )
   private String orderAuditDocName;
   @Excel(
      name = "护士站 处理医嘱写入",
      readConverterExp = "对=于长期医嘱，每天都要更新"
   )
   private Date orderDealTime;
   @Excel(
      name = "护士站 处理医嘱写入"
   )
   private String orderDealDoc;
   @Excel(
      name = "处理护士姓名"
   )
   private String orderDealDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "护士站 执行医嘱写入",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date orderExecuteTime;
   @Excel(
      name = "护士站 执行医嘱写入"
   )
   private String orderExecuteDoc;
   @Excel(
      name = "执行护士姓名"
   )
   private String orderExecuteDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "医生站 停止医嘱写入",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date orderStopTime;
   @Excel(
      name = "医生站 停止医嘱写入"
   )
   private String orderStopDoc;
   @Excel(
      name = "停止医师姓名"
   )
   private String orderStopDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "医生站 取消医嘱写入",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date orderCancelTime;
   @Excel(
      name = "医生站 取消医嘱写入"
   )
   private String orderCancelDoc;
   @Excel(
      name = "取消医师姓名"
   )
   private String orderCancelDocName;
   @Excel(
      name = "医嘱标志编码(出院医嘱、转科医嘱、术后医嘱等）"
   )
   private String orderFlagCd;
   @Excel(
      name = "医嘱标志名称(出院医嘱、转科医嘱、术后医嘱等）"
   )
   private String orderFlagName;
   @Excel(
      name = "是否加急",
      readConverterExp = "0=不加急，1加急"
   )
   private String urgentFlag;
   @Excel(
      name = "协定处方",
      readConverterExp = "套=餐"
   )
   private String prescribeNo;
   @Excel(
      name = "协定处方",
      readConverterExp = "套=餐"
   )
   private String prescribeName;
   @Excel(
      name = "是否草药(0：否；1：是)"
   )
   private String herbalFlag;
   @Excel(
      name = "草药剂数"
   )
   private BigDecimal herbalDose;
   @Excel(
      name = "草药引子"
   )
   private String herbalIntr;
   @Excel(
      name = "煎药方式",
      readConverterExp = "1=自煎；2代煎-人工煎药；3代煎-煎药机煎药"
   )
   private String decoctMethod;
   @Excel(
      name = "医嘱说明"
   )
   private String oederDesc;
   @Excel(
      name = "是否膏药"
   )
   private String plasterFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "修改编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "医嘱重整序号"
   )
   private Integer reOrganizationNo;
   @Excel(
      name = "原医嘱编号(医嘱复制和医嘱重整存原来饿医嘱编码)"
   )
   private String sourceOrderNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStopAuditTime;
   private String orderStopAuditDoc;
   private String orderStopAuditDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderCancelAuditTime;
   private String orderCancelAuditDoc;
   private String orderCancelAuditDocName;
   private String cpStageCode;
   private String cpItemCode;
   private String lcljCpNo;
   private String lcljCpName;

   public TdPaOrderItem() {
   }

   public TdPaOrderItem(String orderType, String orderStatus, String patientId) {
      this.orderType = orderType;
      this.orderStatus = orderStatus;
      this.patientId = patientId;
   }

   public TdPaOrderItem(String patientId, String orderFlagCd) {
      this.patientId = patientId;
      this.orderFlagCd = orderFlagCd;
   }

   public Date getOrderCancelAuditTime() {
      return this.orderCancelAuditTime;
   }

   public void setOrderCancelAuditTime(Date orderCancelAuditTime) {
      this.orderCancelAuditTime = orderCancelAuditTime;
   }

   public String getOrderCancelAuditDoc() {
      return this.orderCancelAuditDoc;
   }

   public void setOrderCancelAuditDoc(String orderCancelAuditDoc) {
      this.orderCancelAuditDoc = orderCancelAuditDoc;
   }

   public String getOrderCancelAuditDocName() {
      return this.orderCancelAuditDocName;
   }

   public void setOrderCancelAuditDocName(String orderCancelAuditDocName) {
      this.orderCancelAuditDocName = orderCancelAuditDocName;
   }

   public Date getOrderStopAuditTime() {
      return this.orderStopAuditTime;
   }

   public void setOrderStopAuditTime(Date orderStopAuditTime) {
      this.orderStopAuditTime = orderStopAuditTime;
   }

   public String getOrderStopAuditDoc() {
      return this.orderStopAuditDoc;
   }

   public void setOrderStopAuditDoc(String orderStopAuditDoc) {
      this.orderStopAuditDoc = orderStopAuditDoc;
   }

   public String getOrderStopAuditDocName() {
      return this.orderStopAuditDocName;
   }

   public void setOrderStopAuditDocName(String orderStopAuditDocName) {
      this.orderStopAuditDocName = orderStopAuditDocName;
   }

   public Integer getReOrganizationNo() {
      return this.reOrganizationNo;
   }

   public void setReOrganizationNo(Integer reOrganizationNo) {
      this.reOrganizationNo = reOrganizationNo;
   }

   public String getSourceOrderNo() {
      return this.sourceOrderNo;
   }

   public void setSourceOrderNo(String sourceOrderNo) {
      this.sourceOrderNo = sourceOrderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
   }

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartDoc(String orderStartDoc) {
      this.orderStartDoc = orderStartDoc;
   }

   public String getOrderStartDoc() {
      return this.orderStartDoc;
   }

   public void setOrderStartDocName(String orderStartDocName) {
      this.orderStartDocName = orderStartDocName;
   }

   public String getOrderStartDocName() {
      return this.orderStartDocName;
   }

   public void setOrderDoctorSignFlag(String orderDoctorSignFlag) {
      this.orderDoctorSignFlag = orderDoctorSignFlag;
   }

   public String getOrderDoctorSignFlag() {
      return this.orderDoctorSignFlag;
   }

   public void setPriceFlag(String priceFlag) {
      this.priceFlag = priceFlag;
   }

   public String getPriceFlag() {
      return this.priceFlag;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setTakeDrugTime(String takeDrugTime) {
      this.takeDrugTime = takeDrugTime;
   }

   public String getTakeDrugTime() {
      return this.takeDrugTime;
   }

   public void setPrintTimes(Integer printTimes) {
      this.printTimes = printTimes;
   }

   public Integer getPrintTimes() {
      return this.printTimes;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setTakeDrugMode(String takeDrugMode) {
      this.takeDrugMode = takeDrugMode;
   }

   public String getTakeDrugMode() {
      return this.takeDrugMode;
   }

   public void setAdditionalFlag(String additionalFlag) {
      this.additionalFlag = additionalFlag;
   }

   public String getAdditionalFlag() {
      return this.additionalFlag;
   }

   public void setTransferOutFlag(String transferOutFlag) {
      this.transferOutFlag = transferOutFlag;
   }

   public String getTransferOutFlag() {
      return this.transferOutFlag;
   }

   public void setPrecontractFlag(Integer precontractFlag) {
      this.precontractFlag = precontractFlag;
   }

   public Integer getPrecontractFlag() {
      return this.precontractFlag;
   }

   public void setOrderDuration(BigDecimal orderDuration) {
      this.orderDuration = orderDuration;
   }

   public BigDecimal getOrderDuration() {
      return this.orderDuration;
   }

   public void setOrderDurationUnit(String orderDurationUnit) {
      this.orderDurationUnit = orderDurationUnit;
   }

   public String getOrderDurationUnit() {
      return this.orderDurationUnit;
   }

   public void setOrderItemType(String orderItemType) {
      this.orderItemType = orderItemType;
   }

   public String getOrderItemType() {
      return this.orderItemType;
   }

   public void setPerformStaffFlag(String performStaffFlag) {
      this.performStaffFlag = performStaffFlag;
   }

   public String getPerformStaffFlag() {
      return this.performStaffFlag;
   }

   public void setSubmitDoctorCode(String submitDoctorCode) {
      this.submitDoctorCode = submitDoctorCode;
   }

   public String getSubmitDoctorCode() {
      return this.submitDoctorCode;
   }

   public void setSubmitDoctorNo(String submitDoctorNo) {
      this.submitDoctorNo = submitDoctorNo;
   }

   public String getSubmitDoctorNo() {
      return this.submitDoctorNo;
   }

   public void setSubmitDoctorName(String submitDoctorName) {
      this.submitDoctorName = submitDoctorName;
   }

   public String getSubmitDoctorName() {
      return this.submitDoctorName;
   }

   public void setSubmitTime(Date submitTime) {
      this.submitTime = submitTime;
   }

   public Date getSubmitTime() {
      return this.submitTime;
   }

   public void setOrderDoseItem(BigDecimal orderDoseItem) {
      this.orderDoseItem = orderDoseItem;
   }

   public BigDecimal getOrderDoseItem() {
      return this.orderDoseItem;
   }

   public void setUnitItem(String unitItem) {
      this.unitItem = unitItem;
   }

   public String getUnitItem() {
      return this.unitItem;
   }

   public void setPriceItem(BigDecimal priceItem) {
      this.priceItem = priceItem;
   }

   public BigDecimal getPriceItem() {
      return this.priceItem;
   }

   public void setOrderTotalItem(BigDecimal orderTotalItem) {
      this.orderTotalItem = orderTotalItem;
   }

   public BigDecimal getOrderTotalItem() {
      return this.orderTotalItem;
   }

   public void setItemOrderUsageWay(String itemOrderUsageWay) {
      this.itemOrderUsageWay = itemOrderUsageWay;
   }

   public String getItemOrderUsageWay() {
      return this.itemOrderUsageWay;
   }

   public void setItemOrderUsageWayName(String itemOrderUsageWayName) {
      this.itemOrderUsageWayName = itemOrderUsageWayName;
   }

   public String getItemOrderUsageWayName() {
      return this.itemOrderUsageWayName;
   }

   public void setItemOrderFreq(String itemOrderFreq) {
      this.itemOrderFreq = itemOrderFreq;
   }

   public String getItemOrderFreq() {
      return this.itemOrderFreq;
   }

   public void setItemOrderFreqName(String itemOrderFreqName) {
      this.itemOrderFreqName = itemOrderFreqName;
   }

   public String getItemOrderFreqName() {
      return this.itemOrderFreqName;
   }

   public void setExecutorDptNo(String executorDptNo) {
      this.executorDptNo = executorDptNo;
   }

   public String getExecutorDptNo() {
      return this.executorDptNo;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }

   public String getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderAuditTime(Date orderAuditTime) {
      this.orderAuditTime = orderAuditTime;
   }

   public Date getOrderAuditTime() {
      return this.orderAuditTime;
   }

   public void setOrderAuditDoc(String orderAuditDoc) {
      this.orderAuditDoc = orderAuditDoc;
   }

   public String getOrderAuditDoc() {
      return this.orderAuditDoc;
   }

   public void setOrderAuditDocName(String orderAuditDocName) {
      this.orderAuditDocName = orderAuditDocName;
   }

   public String getOrderAuditDocName() {
      return this.orderAuditDocName;
   }

   public void setOrderDealTime(Date orderDealTime) {
      this.orderDealTime = orderDealTime;
   }

   public Date getOrderDealTime() {
      return this.orderDealTime;
   }

   public void setOrderDealDoc(String orderDealDoc) {
      this.orderDealDoc = orderDealDoc;
   }

   public String getOrderDealDoc() {
      return this.orderDealDoc;
   }

   public void setOrderDealDocName(String orderDealDocName) {
      this.orderDealDocName = orderDealDocName;
   }

   public String getOrderDealDocName() {
      return this.orderDealDocName;
   }

   public void setOrderExecuteTime(Date orderExecuteTime) {
      this.orderExecuteTime = orderExecuteTime;
   }

   public Date getOrderExecuteTime() {
      return this.orderExecuteTime;
   }

   public void setOrderExecuteDoc(String orderExecuteDoc) {
      this.orderExecuteDoc = orderExecuteDoc;
   }

   public String getOrderExecuteDoc() {
      return this.orderExecuteDoc;
   }

   public void setOrderExecuteDocName(String orderExecuteDocName) {
      this.orderExecuteDocName = orderExecuteDocName;
   }

   public String getOrderExecuteDocName() {
      return this.orderExecuteDocName;
   }

   public void setOrderStopTime(Date orderStopTime) {
      this.orderStopTime = orderStopTime;
   }

   public Date getOrderStopTime() {
      return this.orderStopTime;
   }

   public void setOrderStopDoc(String orderStopDoc) {
      this.orderStopDoc = orderStopDoc;
   }

   public String getOrderStopDoc() {
      return this.orderStopDoc;
   }

   public void setOrderStopDocName(String orderStopDocName) {
      this.orderStopDocName = orderStopDocName;
   }

   public String getOrderStopDocName() {
      return this.orderStopDocName;
   }

   public void setOrderCancelTime(Date orderCancelTime) {
      this.orderCancelTime = orderCancelTime;
   }

   public Date getOrderCancelTime() {
      return this.orderCancelTime;
   }

   public void setOrderCancelDoc(String orderCancelDoc) {
      this.orderCancelDoc = orderCancelDoc;
   }

   public String getOrderCancelDoc() {
      return this.orderCancelDoc;
   }

   public void setOrderCancelDocName(String orderCancelDocName) {
      this.orderCancelDocName = orderCancelDocName;
   }

   public String getOrderCancelDocName() {
      return this.orderCancelDocName;
   }

   public void setOrderFlagCd(String orderFlagCd) {
      this.orderFlagCd = orderFlagCd;
   }

   public String getOrderFlagCd() {
      return this.orderFlagCd;
   }

   public void setOrderFlagName(String orderFlagName) {
      this.orderFlagName = orderFlagName;
   }

   public String getOrderFlagName() {
      return this.orderFlagName;
   }

   public void setUrgentFlag(String urgentFlag) {
      this.urgentFlag = urgentFlag;
   }

   public String getUrgentFlag() {
      return this.urgentFlag;
   }

   public void setPrescribeNo(String prescribeNo) {
      this.prescribeNo = prescribeNo;
   }

   public String getPrescribeNo() {
      return this.prescribeNo;
   }

   public void setPrescribeName(String prescribeName) {
      this.prescribeName = prescribeName;
   }

   public String getPrescribeName() {
      return this.prescribeName;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalIntr(String herbalIntr) {
      this.herbalIntr = herbalIntr;
   }

   public String getHerbalIntr() {
      return this.herbalIntr;
   }

   public void setDecoctMethod(String decoctMethod) {
      this.decoctMethod = decoctMethod;
   }

   public String getDecoctMethod() {
      return this.decoctMethod;
   }

   public void setOederDesc(String oederDesc) {
      this.oederDesc = oederDesc;
   }

   public String getOederDesc() {
      return this.oederDesc;
   }

   public void setPlasterFlag(String plasterFlag) {
      this.plasterFlag = plasterFlag;
   }

   public String getPlasterFlag() {
      return this.plasterFlag;
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

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getCpStageCode() {
      return this.cpStageCode;
   }

   public void setCpStageCode(String cpStageCode) {
      this.cpStageCode = cpStageCode;
   }

   public String getCpItemCode() {
      return this.cpItemCode;
   }

   public void setCpItemCode(String cpItemCode) {
      this.cpItemCode = cpItemCode;
   }

   public String getLcljCpNo() {
      return this.lcljCpNo;
   }

   public void setLcljCpNo(String lcljCpNo) {
      this.lcljCpNo = lcljCpNo;
   }

   public String getLcljCpName() {
      return this.lcljCpName;
   }

   public void setLcljCpName(String lcljCpName) {
      this.lcljCpName = lcljCpName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("orderNo", this.getOrderNo()).append("orderType", this.getOrderType()).append("orderClassCode", this.getOrderClassCode()).append("orderSortNumber", this.getOrderSortNumber()).append("orderGroupNo", this.getOrderGroupNo()).append("cpNo", this.getCpNo()).append("cpName", this.getCpName()).append("orderStartTime", this.getOrderStartTime()).append("orderStartDoc", this.getOrderStartDoc()).append("orderStartDocName", this.getOrderStartDocName()).append("orderDoctorSignFlag", this.getOrderDoctorSignFlag()).append("priceFlag", this.getPriceFlag()).append("documentTypeNo", this.getDocumentTypeNo()).append("takeDrugTime", this.getTakeDrugTime()).append("printTimes", this.getPrintTimes()).append("outHavaDrugFlag", this.getOutHavaDrugFlag()).append("takeDrugMode", this.getTakeDrugMode()).append("additionalFlag", this.getAdditionalFlag()).append("transferOutFlag", this.getTransferOutFlag()).append("precontractFlag", this.getPrecontractFlag()).append("orderDuration", this.getOrderDuration()).append("orderDurationUnit", this.getOrderDurationUnit()).append("orderItemType", this.getOrderItemType()).append("performStaffFlag", this.getPerformStaffFlag()).append("submitDoctorCode", this.getSubmitDoctorCode()).append("submitDoctorNo", this.getSubmitDoctorNo()).append("submitDoctorName", this.getSubmitDoctorName()).append("submitTime", this.getSubmitTime()).append("orderDoseItem", this.getOrderDoseItem()).append("unitItem", this.getUnitItem()).append("priceItem", this.getPriceItem()).append("orderTotalItem", this.getOrderTotalItem()).append("itemOrderUsageWay", this.getItemOrderUsageWay()).append("itemOrderUsageWayName", this.getItemOrderUsageWayName()).append("itemOrderFreq", this.getItemOrderFreq()).append("itemOrderFreqName", this.getItemOrderFreqName()).append("executorDptNo", this.getExecutorDptNo()).append("orderStatus", this.getOrderStatus()).append("orderAuditTime", this.getOrderAuditTime()).append("orderAuditDoc", this.getOrderAuditDoc()).append("orderAuditDocName", this.getOrderAuditDocName()).append("orderDealTime", this.getOrderDealTime()).append("orderDealDoc", this.getOrderDealDoc()).append("orderDealDocName", this.getOrderDealDocName()).append("orderExecuteTime", this.getOrderExecuteTime()).append("orderExecuteDoc", this.getOrderExecuteDoc()).append("orderExecuteDocName", this.getOrderExecuteDocName()).append("orderStopTime", this.getOrderStopTime()).append("orderStopDoc", this.getOrderStopDoc()).append("orderStopDocName", this.getOrderStopDocName()).append("orderCancelTime", this.getOrderCancelTime()).append("orderCancelDoc", this.getOrderCancelDoc()).append("orderCancelDocName", this.getOrderCancelDocName()).append("orderFlagCd", this.getOrderFlagCd()).append("orderFlagName", this.getOrderFlagName()).append("urgentFlag", this.getUrgentFlag()).append("prescribeNo", this.getPrescribeNo()).append("prescribeName", this.getPrescribeName()).append("herbalFlag", this.getHerbalFlag()).append("herbalDose", this.getHerbalDose()).append("herbalIntr", this.getHerbalIntr()).append("decoctMethod", this.getDecoctMethod()).append("oederDesc", this.getOederDesc()).append("plasterFlag", this.getPlasterFlag()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).toString();
   }
}
