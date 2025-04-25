package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderPerform extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构代码"
   )
   private String hospitalCode;
   @Excel(
      name = "医嘱执行病区编码"
   )
   private String performWardNo;
   @Excel(
      name = "医嘱执行科室编码"
   )
   private String performDepCode;
   @Excel(
      name = "病案号"
   )
   private String caseNo;
   private String performListNo;
   @Excel(
      name = "医嘱编号"
   )
   private String orderNo;
   @Excel(
      name = "医嘱类型"
   )
   private String orderType;
   @Excel(
      name = "医嘱序号"
   )
   private String orderSortNumber;
   @Excel(
      name = "医嘱类别编号"
   )
   private String orderClassCode;
   @Excel(
      name = "住院号"
   )
   private String admissionNo;
   @Excel(
      name = "入院次数"
   )
   private Integer hospitalizedCount;
   @Excel(
      name = "开单医师内码"
   )
   private String orderDoctorCode;
   @Excel(
      name = "开单医师编码"
   )
   private String orderDoctorNo;
   @Excel(
      name = "开单医师所在病区编码"
   )
   private String orderDoctorWardNo;
   @Excel(
      name = "开单医师所在科室编码"
   )
   private String orderDoctorDepCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "医嘱开始时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date orderStartTime;
   @Excel(
      name = "单据类型编号"
   )
   private String documentTypeNo;
   @Excel(
      name = "执行单状态：0未执行 1已执行 2作废 3库存不足 4欠费"
   )
   private String performListStatus;
   @Excel(
      name = "临床项目编号"
   )
   private String cpNo;
   @Excel(
      name = "临床项目名称"
   )
   private String cpName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "处理时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date handleTime;
   @Excel(
      name = "处理护士内码"
   )
   private String handleNurseCode;
   @Excel(
      name = "处理护士编码"
   )
   private String handleNurseNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "执行时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date performTime;
   @Excel(
      name = "执行护士内码"
   )
   private String performNurseCode;
   @Excel(
      name = "执行护士编码"
   )
   private String performNurseNo;
   @Excel(
      name = "出院带药标志(0：不带；1：带)"
   )
   private String outHavaDrugFlag;
   @Excel(
      name = "项目类型(1：普通项目 ；2：主从项目；3：按小时记账)"
   )
   private Integer orderItemType;
   @Excel(
      name = "首次翻倍标志(1：当日；2：次日)"
   )
   private Integer firstDoubleFlag;
   @Excel(
      name = "领药方式"
   )
   private String takeDrugMode;
   @Excel(
      name = "科室编号"
   )
   private String depCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "计划用药时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date planUsageTime;
   @Excel(
      name = "婴儿住院号"
   )
   private String babyAdmissionNo;
   @Excel(
      name = "首瓶标志(1：第一瓶；2：加一瓶；3：普通)"
   )
   private Integer firstBottleFlag;
   @Excel(
      name = "机器编码"
   )
   private String performComputerNo;
   @Excel(
      name = "IP地址"
   )
   private String performComputerIp;
   private Integer performListSortNumber;
   @Excel(
      name = "执行科室编号"
   )
   private String detailPerformDepCode;
   @Excel(
      name = "执行科病区编号"
   )
   private String detailPerformWardNo;
   @Excel(
      name = "pc或移动端执行标志(1 pc 2 移动端)"
   )
   private Integer pcPdaFlag;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private BigDecimal orderDoseItem;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String unitItem;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private BigDecimal priceItem;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private BigDecimal orderTotalItem;
   private String performFirstGenFlag;

   public String getPerformFirstGenFlag() {
      return this.performFirstGenFlag;
   }

   public void setPerformFirstGenFlag(String performFirstGenFlag) {
      this.performFirstGenFlag = performFirstGenFlag;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setPerformWardNo(String performWardNo) {
      this.performWardNo = performWardNo;
   }

   public String getPerformWardNo() {
      return this.performWardNo;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getPerformListNo() {
      return this.performListNo;
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

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorNo(String orderDoctorNo) {
      this.orderDoctorNo = orderDoctorNo;
   }

   public String getOrderDoctorNo() {
      return this.orderDoctorNo;
   }

   public void setOrderDoctorWardNo(String orderDoctorWardNo) {
      this.orderDoctorWardNo = orderDoctorWardNo;
   }

   public String getOrderDoctorWardNo() {
      return this.orderDoctorWardNo;
   }

   public void setOrderDoctorDepCode(String orderDoctorDepCode) {
      this.orderDoctorDepCode = orderDoctorDepCode;
   }

   public String getOrderDoctorDepCode() {
      return this.orderDoctorDepCode;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
   }

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setPerformListStatus(String performListStatus) {
      this.performListStatus = performListStatus;
   }

   public String getPerformListStatus() {
      return this.performListStatus;
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

   public void setHandleTime(Date handleTime) {
      this.handleTime = handleTime;
   }

   public Date getHandleTime() {
      return this.handleTime;
   }

   public void setHandleNurseCode(String handleNurseCode) {
      this.handleNurseCode = handleNurseCode;
   }

   public String getHandleNurseCode() {
      return this.handleNurseCode;
   }

   public void setHandleNurseNo(String handleNurseNo) {
      this.handleNurseNo = handleNurseNo;
   }

   public String getHandleNurseNo() {
      return this.handleNurseNo;
   }

   public void setPerformTime(Date performTime) {
      this.performTime = performTime;
   }

   public Date getPerformTime() {
      return this.performTime;
   }

   public void setPerformNurseCode(String performNurseCode) {
      this.performNurseCode = performNurseCode;
   }

   public String getPerformNurseCode() {
      return this.performNurseCode;
   }

   public void setPerformNurseNo(String performNurseNo) {
      this.performNurseNo = performNurseNo;
   }

   public String getPerformNurseNo() {
      return this.performNurseNo;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOrderItemType(Integer orderItemType) {
      this.orderItemType = orderItemType;
   }

   public Integer getOrderItemType() {
      return this.orderItemType;
   }

   public void setFirstDoubleFlag(Integer firstDoubleFlag) {
      this.firstDoubleFlag = firstDoubleFlag;
   }

   public Integer getFirstDoubleFlag() {
      return this.firstDoubleFlag;
   }

   public void setTakeDrugMode(String takeDrugMode) {
      this.takeDrugMode = takeDrugMode;
   }

   public String getTakeDrugMode() {
      return this.takeDrugMode;
   }

   public void setDepCode(String depCode) {
      this.depCode = depCode;
   }

   public String getDepCode() {
      return this.depCode;
   }

   public void setPlanUsageTime(Date planUsageTime) {
      this.planUsageTime = planUsageTime;
   }

   public Date getPlanUsageTime() {
      return this.planUsageTime;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setFirstBottleFlag(Integer firstBottleFlag) {
      this.firstBottleFlag = firstBottleFlag;
   }

   public Integer getFirstBottleFlag() {
      return this.firstBottleFlag;
   }

   public void setPerformComputerNo(String performComputerNo) {
      this.performComputerNo = performComputerNo;
   }

   public String getPerformComputerNo() {
      return this.performComputerNo;
   }

   public void setPerformComputerIp(String performComputerIp) {
      this.performComputerIp = performComputerIp;
   }

   public String getPerformComputerIp() {
      return this.performComputerIp;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setDetailPerformDepCode(String detailPerformDepCode) {
      this.detailPerformDepCode = detailPerformDepCode;
   }

   public String getDetailPerformDepCode() {
      return this.detailPerformDepCode;
   }

   public void setDetailPerformWardNo(String detailPerformWardNo) {
      this.detailPerformWardNo = detailPerformWardNo;
   }

   public String getDetailPerformWardNo() {
      return this.detailPerformWardNo;
   }

   public void setPcPdaFlag(Integer pcPdaFlag) {
      this.pcPdaFlag = pcPdaFlag;
   }

   public Integer getPcPdaFlag() {
      return this.pcPdaFlag;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("performWardNo", this.getPerformWardNo()).append("performDepCode", this.getPerformDepCode()).append("caseNo", this.getCaseNo()).append("performListNo", this.getPerformListNo()).append("orderNo", this.getOrderNo()).append("orderType", this.getOrderType()).append("orderSortNumber", this.getOrderSortNumber()).append("orderClassCode", this.getOrderClassCode()).append("admissionNo", this.getAdmissionNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("orderDoctorCode", this.getOrderDoctorCode()).append("orderDoctorNo", this.getOrderDoctorNo()).append("orderDoctorWardNo", this.getOrderDoctorWardNo()).append("orderDoctorDepCode", this.getOrderDoctorDepCode()).append("orderStartTime", this.getOrderStartTime()).append("documentTypeNo", this.getDocumentTypeNo()).append("performListStatus", this.getPerformListStatus()).append("cpNo", this.getCpNo()).append("cpName", this.getCpName()).append("handleTime", this.getHandleTime()).append("handleNurseCode", this.getHandleNurseCode()).append("handleNurseNo", this.getHandleNurseNo()).append("performTime", this.getPerformTime()).append("performNurseCode", this.getPerformNurseCode()).append("performNurseNo", this.getPerformNurseNo()).append("outHavaDrugFlag", this.getOutHavaDrugFlag()).append("orderItemType", this.getOrderItemType()).append("firstDoubleFlag", this.getFirstDoubleFlag()).append("takeDrugMode", this.getTakeDrugMode()).append("depCode", this.getDepCode()).append("planUsageTime", this.getPlanUsageTime()).append("babyAdmissionNo", this.getBabyAdmissionNo()).append("firstBottleFlag", this.getFirstBottleFlag()).append("performComputerNo", this.getPerformComputerNo()).append("performComputerIp", this.getPerformComputerIp()).append("performListSortNumber", this.getPerformListSortNumber()).append("detailPerformDepCode", this.getDetailPerformDepCode()).append("detailPerformWardNo", this.getDetailPerformWardNo()).append("pcPdaFlag", this.getPcPdaFlag()).append("orderDoseItem", this.getOrderDoseItem()).append("unitItem", this.getUnitItem()).append("priceItem", this.getPriceItem()).append("orderTotalItem", this.getOrderTotalItem()).toString();
   }
}
