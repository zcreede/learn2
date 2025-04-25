package com.emr.project.webservice.domain.req;

import io.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "Request"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class EcgReq {
   @ApiModelProperty("申请单号")
   @XmlElement(
      name = "ApplyNo"
   )
   private String applyNo;
   @ApiModelProperty("患者姓名")
   @XmlElement(
      name = "PatientName"
   )
   private String patientName;
   @ApiModelProperty("住院号")
   @XmlElement(
      name = "InpatientNo"
   )
   private String inpatientNo;
   @ApiModelProperty("门诊号")
   @XmlElement(
      name = "OutpatientNo"
   )
   private String outpatientNo;
   @ApiModelProperty("消息 id，用于关联危急值处理结果")
   @XmlElement(
      name = "CriticalId"
   )
   private String criticalId;
   @ApiModelProperty("上报危急值消息的内容")
   @XmlElement(
      name = "CriticalContent"
   )
   private String criticalContent;
   @ApiModelProperty("上报医生名字")
   @XmlElement(
      name = "SendDoctor"
   )
   private String sendDoctor;
   @ApiModelProperty("上报医生的 his 工号")
   @XmlElement(
      name = "SendDoctorId"
   )
   private String sendDoctorId;
   @ApiModelProperty("上报时间")
   @XmlElement(
      name = "SendDate"
   )
   private String sendDate;
   @ApiModelProperty("检查项目名称")
   @XmlElement(
      name = "ExamItem"
   )
   private String examItem;

   public String getApplyNo() {
      return this.applyNo;
   }

   public void setApplyNo(String applyNo) {
      this.applyNo = applyNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getInpatientNo() {
      return this.inpatientNo;
   }

   public void setInpatientNo(String inpatientNo) {
      this.inpatientNo = inpatientNo;
   }

   public String getOutpatientNo() {
      return this.outpatientNo;
   }

   public void setOutpatientNo(String outpatientNo) {
      this.outpatientNo = outpatientNo;
   }

   public String getCriticalId() {
      return this.criticalId;
   }

   public void setCriticalId(String criticalId) {
      this.criticalId = criticalId;
   }

   public String getCriticalContent() {
      return this.criticalContent;
   }

   public void setCriticalContent(String criticalContent) {
      this.criticalContent = criticalContent;
   }

   public String getSendDoctor() {
      return this.sendDoctor;
   }

   public void setSendDoctor(String sendDoctor) {
      this.sendDoctor = sendDoctor;
   }

   public String getSendDoctorId() {
      return this.sendDoctorId;
   }

   public void setSendDoctorId(String sendDoctorId) {
      this.sendDoctorId = sendDoctorId;
   }

   public String getSendDate() {
      return this.sendDate;
   }

   public void setSendDate(String sendDate) {
      this.sendDate = sendDate;
   }

   public String getExamItem() {
      return this.examItem;
   }

   public void setExamItem(String examItem) {
      this.examItem = examItem;
   }
}
