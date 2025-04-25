package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrder extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构代码"
   )
   private String hospitalCode;
   private String orderNo;
   @Excel(
      name = "医嘱类型"
   )
   private String orderType;
   @Excel(
      name = "医嘱类别"
   )
   private String orderClassCode;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "病案号"
   )
   private String caseNo;
   @Excel(
      name = "住院号"
   )
   private String admissionNo;
   @Excel(
      name = "入院次数"
   )
   private Integer hospitalizedCount;
   @Excel(
      name = "患者所在病区编码"
   )
   private String patientWardNo;
   @Excel(
      name = "患者所在科室编码"
   )
   private String patientDepCode;
   @Excel(
      name = "患者所在科室名称"
   )
   private String patientDepName;
   @Excel(
      name = "开单医师内码"
   )
   private String orderDoctorCode;
   @Excel(
      name = "开单医师编码"
   )
   private String orderDoctorNo;
   @Excel(
      name = "开单医师姓名"
   )
   private String orderDoctorName;
   @Excel(
      name = "开单医师所在病区编码"
   )
   private String orderDoctorWardNo;
   @Excel(
      name = "开单医师所在科室编码"
   )
   private String orderDoctorDepCode;
   @Excel(
      name = "开单医师所在科室"
   )
   private String orderDoctorDepName;
   @Excel(
      name = "婴儿住院号"
   )
   private String babyAdmissionNo;
   @Excel(
      name = "住院医师内码"
   )
   private String residentDoctorCode;
   @Excel(
      name = "住院医师编码"
   )
   private String residentDoctorNo;
   @Excel(
      name = "住院医师姓名"
   )
   private String residentDoctorName;
   @Excel(
      name = "责任护士内码"
   )
   private String respNurseCode;
   @Excel(
      name = "责任护士编码"
   )
   private String respNurseNo;
   @Excel(
      name = "责任护士姓名"
   )
   private String respNurseName;
   @Excel(
      name = "医嘱执行病区编码"
   )
   private String performWardNo;
   @Excel(
      name = "医嘱执行科室编码"
   )
   private String performDepCode;
   @Excel(
      name = "医嘱执行科室名称"
   )
   private String performDepName;
   @Excel(
      name = "医嘱执行标志(0：本科医师和本科病人，正常开立医嘱;1：病人所在科护士审核，医嘱执行科发送和执行；2：病人所在科护士审核和发送，医嘱执行科执行)；3：医嘱执行科审核、处理、执行"
   )
   private String orderPerformFlag;
   @Excel(
      name = "操作员内码"
   )
   private String operatorCode;
   @Excel(
      name = "操作员编码"
   )
   private String operatorNo;
   @Excel(
      name = "操作员姓名"
   )
   private String operatorName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "录入时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date operationTime;
   @Excel(
      name = "机器编码"
   )
   private String performComputerNo;
   @Excel(
      name = "IP地址"
   )
   private String performComputerIp;
   @Excel(
      name = "申请单编号",
      readConverterExp = "检=查检验、手术、输血、会诊"
   )
   private String applyFormNo;
   @Excel(
      name = "床号",
      readConverterExp = "床号"
   )
   private String bedNo;

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
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

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getCaseNo() {
      return this.caseNo;
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

   public void setPatientWardNo(String patientWardNo) {
      this.patientWardNo = patientWardNo;
   }

   public String getPatientWardNo() {
      return this.patientWardNo;
   }

   public void setPatientDepCode(String patientDepCode) {
      this.patientDepCode = patientDepCode;
   }

   public String getPatientDepCode() {
      return this.patientDepCode;
   }

   public void setPatientDepName(String patientDepName) {
      this.patientDepName = patientDepName;
   }

   public String getPatientDepName() {
      return this.patientDepName;
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

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
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

   public void setOrderDoctorDepName(String orderDoctorDepName) {
      this.orderDoctorDepName = orderDoctorDepName;
   }

   public String getOrderDoctorDepName() {
      return this.orderDoctorDepName;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setResidentDoctorCode(String residentDoctorCode) {
      this.residentDoctorCode = residentDoctorCode;
   }

   public String getResidentDoctorCode() {
      return this.residentDoctorCode;
   }

   public void setResidentDoctorNo(String residentDoctorNo) {
      this.residentDoctorNo = residentDoctorNo;
   }

   public String getResidentDoctorNo() {
      return this.residentDoctorNo;
   }

   public void setResidentDoctorName(String residentDoctorName) {
      this.residentDoctorName = residentDoctorName;
   }

   public String getResidentDoctorName() {
      return this.residentDoctorName;
   }

   public void setRespNurseCode(String respNurseCode) {
      this.respNurseCode = respNurseCode;
   }

   public String getRespNurseCode() {
      return this.respNurseCode;
   }

   public void setRespNurseNo(String respNurseNo) {
      this.respNurseNo = respNurseNo;
   }

   public String getRespNurseNo() {
      return this.respNurseNo;
   }

   public void setRespNurseName(String respNurseName) {
      this.respNurseName = respNurseName;
   }

   public String getRespNurseName() {
      return this.respNurseName;
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

   public void setPerformDepName(String performDepName) {
      this.performDepName = performDepName;
   }

   public String getPerformDepName() {
      return this.performDepName;
   }

   public void setOrderPerformFlag(String orderPerformFlag) {
      this.orderPerformFlag = orderPerformFlag;
   }

   public String getOrderPerformFlag() {
      return this.orderPerformFlag;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo;
   }

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperationTime(Date operationTime) {
      this.operationTime = operationTime;
   }

   public Date getOperationTime() {
      return this.operationTime;
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

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("orderNo", this.getOrderNo()).append("orderType", this.getOrderType()).append("orderClassCode", this.getOrderClassCode()).append("patientId", this.getPatientId()).append("caseNo", this.getCaseNo()).append("admissionNo", this.getAdmissionNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("patientWardNo", this.getPatientWardNo()).append("patientDepCode", this.getPatientDepCode()).append("patientDepName", this.getPatientDepName()).append("orderDoctorCode", this.getOrderDoctorCode()).append("orderDoctorNo", this.getOrderDoctorNo()).append("orderDoctorName", this.getOrderDoctorName()).append("orderDoctorWardNo", this.getOrderDoctorWardNo()).append("orderDoctorDepCode", this.getOrderDoctorDepCode()).append("orderDoctorDepName", this.getOrderDoctorDepName()).append("babyAdmissionNo", this.getBabyAdmissionNo()).append("residentDoctorCode", this.getResidentDoctorCode()).append("residentDoctorNo", this.getResidentDoctorNo()).append("residentDoctorName", this.getResidentDoctorName()).append("respNurseCode", this.getRespNurseCode()).append("respNurseNo", this.getRespNurseNo()).append("respNurseName", this.getRespNurseName()).append("performWardNo", this.getPerformWardNo()).append("performDepCode", this.getPerformDepCode()).append("performDepName", this.getPerformDepName()).append("orderPerformFlag", this.getOrderPerformFlag()).append("operatorCode", this.getOperatorCode()).append("operatorNo", this.getOperatorNo()).append("operatorName", this.getOperatorName()).append("operationTime", this.getOperationTime()).append("performComputerNo", this.getPerformComputerNo()).append("performComputerIp", this.getPerformComputerIp()).append("applyFormNo", this.getApplyFormNo()).append("bedNo", this.getBedNo()).toString();
   }
}
