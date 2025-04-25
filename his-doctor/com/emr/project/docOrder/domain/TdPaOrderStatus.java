package com.emr.project.docOrder.domain;

import com.emr.common.utils.SecurityUtils;
import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.emr.project.system.domain.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderStatus extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "病区编码"
   )
   private String patientWardNo;
   @Excel(
      name = "科室编码"
   )
   private String patientDepCode;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "病案号"
   )
   private String caseNo;
   @Excel(
      name = "入院次数"
   )
   private Integer hospitalizedCount;
   @Excel(
      name = "住院号"
   )
   private String admissionNo;
   private String orderNo;
   private Integer orderGroupNo;
   private String orderSortNumber;
   @Excel(
      name = "医嘱类别"
   )
   private String orderClassCode;
   private String operationType;
   @Excel(
      name = "操作类型名称"
   )
   private String operationTypeName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "操作时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date operationTime;
   @Excel(
      name = "操作人类型(1,护士; 2,医师)"
   )
   private String operatorType;
   @Excel(
      name = "操作人内码"
   )
   private String operatorCode;
   @Excel(
      name = "操作人编码"
   )
   private String operatorNo;
   @Excel(
      name = "操作人姓名"
   )
   private String operatorName;
   @Excel(
      name = "操作人签名标志(0：未签名；1：已签名)"
   )
   private String operatorSignFlag;
   @Excel(
      name = "操作说明信息(取消原因等)"
   )
   private String operatorDesc;
   @Excel(
      name = "签名记录id"
   )
   private Long signId;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
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

   public void setOperationType(String operationType) {
      this.operationType = operationType;
   }

   public String getOperationType() {
      return this.operationType;
   }

   public void setOperationTypeName(String operationTypeName) {
      this.operationTypeName = operationTypeName;
   }

   public String getOperationTypeName() {
      return this.operationTypeName;
   }

   public void setOperationTime(Date operationTime) {
      this.operationTime = operationTime;
   }

   public Date getOperationTime() {
      return this.operationTime;
   }

   public void setOperatorType(String operatorType) {
      this.operatorType = operatorType;
   }

   public String getOperatorType() {
      return this.operatorType;
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

   public void setOperatorSignFlag(String operatorSignFlag) {
      this.operatorSignFlag = operatorSignFlag;
   }

   public String getOperatorSignFlag() {
      return this.operatorSignFlag;
   }

   public void setOperatorDesc(String operatorDesc) {
      this.operatorDesc = operatorDesc;
   }

   public String getOperatorDesc() {
      return this.operatorDesc;
   }

   public void setSignId(Long signId) {
      this.signId = signId;
   }

   public Long getSignId() {
      return this.signId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("patientWardNo", this.getPatientWardNo()).append("patientDepCode", this.getPatientDepCode()).append("patientId", this.getPatientId()).append("caseNo", this.getCaseNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("admissionNo", this.getAdmissionNo()).append("orderNo", this.getOrderNo()).append("orderGroupNo", this.getOrderGroupNo()).append("orderSortNumber", this.getOrderSortNumber()).append("orderClassCode", this.getOrderClassCode()).append("operationType", this.getOperationType()).append("operationTypeName", this.getOperationTypeName()).append("operationTime", this.getOperationTime()).append("operatorType", this.getOperatorType()).append("operatorCode", this.getOperatorCode()).append("operatorNo", this.getOperatorNo()).append("operatorName", this.getOperatorName()).append("operatorSignFlag", this.getOperatorSignFlag()).append("operatorDesc", this.getOperatorDesc()).append("signId", this.getSignId()).toString();
   }

   public TdPaOrderStatus() {
   }

   public TdPaOrderStatus(Date operationTime, String patientWardNo, String patientDepCode, String caseNo, String admission, int hospitalCount, String orderNo, String orderSortNumber, String orderGroupNo, Integer status, String statusName, Integer operatorSignFlag, String operatorDesc, Integer operationType, String orderClassCode) {
      this.setPatientWardNo(patientWardNo);
      this.setPatientDepCode(patientDepCode);
      this.setCaseNo(caseNo);
      this.setAdmissionNo(admission);
      this.setHospitalizedCount(hospitalCount);
      this.setOrderNo(orderNo);
      this.setOrderSortNumber(orderSortNumber);
      this.setOrderGroupNo(Integer.parseInt(orderGroupNo));
      this.setOperationType(status.toString());
      this.setOperationTypeName(statusName);
      this.setOperationTime(operationTime);
      SysUser user = SecurityUtils.getLoginUser().getUser();
      if ("c".equals(user.getAuthorLevel())) {
         this.setOperatorType(String.valueOf(2));
      }

      if ("d".equals(user.getStaffType())) {
         this.setOperatorType(String.valueOf(1));
      }

      this.setOperatorNo(user.getUserName());
      this.setOperatorName(user.getNickName());
      this.setOperatorCode(user.getUserName());
      if (operatorSignFlag != null) {
         this.setOperatorSignFlag(operatorSignFlag.toString());
      }

      this.setOperatorDesc(operatorDesc);
      this.setOrderClassCode(orderClassCode);
   }
}
