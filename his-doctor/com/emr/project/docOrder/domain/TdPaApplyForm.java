package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaApplyForm extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构编码 "
   )
   private String hospitalCode;
   private String applyFormNo;
   @Excel(
      name = "申请单据类别",
      readConverterExp = "0=2检查03检验"
   )
   private String applyFormClassCode;
   @Excel(
      name = "申请单据类型编号"
   )
   private String applyFormTypeCode;
   @Excel(
      name = "医嘱编号"
   )
   private String orderNo;
   @Excel(
      name = "医嘱序号"
   )
   private String orderSortNumber;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "病案号"
   )
   private String caseNo;
   @Excel(
      name = "患者住院号"
   )
   private String admissionNo;
   @Excel(
      name = "入院次数"
   )
   private Integer hospitalizedCount;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "婴儿住院号"
   )
   private String babyNo;
   @Excel(
      name = "患者科室编号"
   )
   private String patientDepCode;
   @Excel(
      name = "患者科室名称"
   )
   private String patientDepName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date applyTime;
   @Excel(
      name = "开单医生编号"
   )
   private String orderDoctorCode;
   @Excel(
      name = "开单医师姓名"
   )
   private String orderDoctorName;
   @Excel(
      name = "开单科室编号"
   )
   private String physicianDptNo;
   @Excel(
      name = "开单科室名称"
   )
   private String physicianDptName;
   @Excel(
      name = "执行科室编号"
   )
   private String performDepCode;
   @Excel(
      name = "执行科室名称"
   )
   private String performDepName;
   @Excel(
      name = "临床诊断编码"
   )
   private String diagnosisCode;
   @Excel(
      name = "临床诊断名称"
   )
   private String diagnosisName;
   @Excel(
      name = "主诉"
   )
   private String complaint;
   @Excel(
      name = "病例摘要"
   )
   private String medicalRecordDigest;
   @Excel(
      name = "送检目的"
   )
   private String purposeExamination;
   @Excel(
      name = "检查类别"
   )
   private String examCategory;
   @Excel(
      name = "检查部位"
   )
   private String examPart;
   @Excel(
      name = "标本编号"
   )
   private String sampleNo;
   @Excel(
      name = "标本名称"
   )
   private String sampleName;
   @Excel(
      name = "检查方法"
   )
   private String examMethodName;
   @Excel(
      name = "申请单状态0:开立；1：已提交；2：已撤销；30:未记账；31：已记账；:4：已欠费；5：已申请退费；6:已退费；7：医技科室确认；8：上机确认；9：报告完成；）"
   )
   private String applyFormStatus;
   @Excel(
      name = "操作员编码"
   )
   private String operatorCode;
   @Excel(
      name = "影像学诊断"
   )
   private String imagingDiag;
   @Excel(
      name = "操作员姓名"
   )
   private String operatorName;
   @Excel(
      name = "既往病理诊断"
   )
   private String pathologicDiagH;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "肿瘤确诊日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date tumorFindDate;
   @Excel(
      name = "转移部位"
   )
   private String tumorMovePart;
   @Excel(
      name = "是否加急(0:普通；1：加急；2：平急；3：特急）"
   )
   private String urgentFlag;
   @Excel(
      name = "打印标记"
   )
   private String printFlag;
   @Excel(
      name = "放射及药物治疗情况"
   )
   private String treatment;
   @Excel(
      name = "手术所见"
   )
   private String surgicalDescr;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm"
   )
   @Excel(
      name = "记账时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date settleAccountDate;
   @Excel(
      name = "记账人内码"
   )
   private String settleAccountCode;
   @Excel(
      name = "肿瘤形态"
   )
   private String tumorShape;
   @Excel(
      name = "记账人编码"
   )
   private String settleAccountNo;
   @Excel(
      name = "肿瘤大小"
   )
   private String tumorSize;
   @Excel(
      name = "记账人姓名"
   )
   private String settleAccountName;
   @Excel(
      name = "肿瘤颜色"
   )
   private String tumorColor;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "报告日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date reportDate;
   @Excel(
      name = "与周边组织关系"
   )
   private String tumorAroundTissuesRelation;
   @Excel(
      name = "报告医生"
   )
   private String reportDoctor;
   @Excel(
      name = "标本固定液"
   )
   private String specFixative;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date altDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "标本离体时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date specInVitroDate;
   @Excel(
      name = "修改编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "标本固定时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date specFixationDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "标本交接时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date specHandoverDate;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "传染病标志（0否，1是）"
   )
   private String infectiousFlag;
   @Excel(
      name = "检查方法编号"
   )
   private String examMethodCode;
   private String physiologicalCycle;

   public String getPhysiologicalCycle() {
      return this.physiologicalCycle;
   }

   public void setPhysiologicalCycle(String physiologicalCycle) {
      this.physiologicalCycle = physiologicalCycle;
   }

   public String getExamMethodCode() {
      return this.examMethodCode;
   }

   public void setExamMethodCode(String examMethodCode) {
      this.examMethodCode = examMethodCode;
   }

   public String getInfectiousFlag() {
      return this.infectiousFlag;
   }

   public void setInfectiousFlag(String infectiousFlag) {
      this.infectiousFlag = infectiousFlag;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormClassCode(String applyFormClassCode) {
      this.applyFormClassCode = applyFormClassCode;
   }

   public String getApplyFormClassCode() {
      return this.applyFormClassCode;
   }

   public void setApplyFormTypeCode(String applyFormTypeCode) {
      this.applyFormTypeCode = applyFormTypeCode;
   }

   public String getApplyFormTypeCode() {
      return this.applyFormTypeCode;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
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

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setBabyNo(String babyNo) {
      this.babyNo = babyNo;
   }

   public String getBabyNo() {
      return this.babyNo;
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

   public void setApplyTime(Date applyTime) {
      this.applyTime = applyTime;
   }

   public Date getApplyTime() {
      return this.applyTime;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setPhysicianDptNo(String physicianDptNo) {
      this.physicianDptNo = physicianDptNo;
   }

   public String getPhysicianDptNo() {
      return this.physicianDptNo;
   }

   public void setPhysicianDptName(String physicianDptName) {
      this.physicianDptName = physicianDptName;
   }

   public String getPhysicianDptName() {
      return this.physicianDptName;
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

   public void setDiagnosisCode(String diagnosisCode) {
      this.diagnosisCode = diagnosisCode;
   }

   public String getDiagnosisCode() {
      return this.diagnosisCode;
   }

   public void setDiagnosisName(String diagnosisName) {
      this.diagnosisName = diagnosisName;
   }

   public String getDiagnosisName() {
      return this.diagnosisName;
   }

   public void setComplaint(String complaint) {
      this.complaint = complaint;
   }

   public String getComplaint() {
      return this.complaint;
   }

   public void setMedicalRecordDigest(String medicalRecordDigest) {
      this.medicalRecordDigest = medicalRecordDigest;
   }

   public String getMedicalRecordDigest() {
      return this.medicalRecordDigest;
   }

   public void setPurposeExamination(String purposeExamination) {
      this.purposeExamination = purposeExamination;
   }

   public String getPurposeExamination() {
      return this.purposeExamination;
   }

   public void setExamCategory(String examCategory) {
      this.examCategory = examCategory;
   }

   public String getExamCategory() {
      return this.examCategory;
   }

   public void setExamPart(String examPart) {
      this.examPart = examPart;
   }

   public String getExamPart() {
      return this.examPart;
   }

   public void setSampleNo(String sampleNo) {
      this.sampleNo = sampleNo;
   }

   public String getSampleNo() {
      return this.sampleNo;
   }

   public void setSampleName(String sampleName) {
      this.sampleName = sampleName;
   }

   public String getSampleName() {
      return this.sampleName;
   }

   public void setExamMethodName(String examMethodName) {
      this.examMethodName = examMethodName;
   }

   public String getExamMethodName() {
      return this.examMethodName;
   }

   public void setApplyFormStatus(String applyFormStatus) {
      this.applyFormStatus = applyFormStatus;
   }

   public String getApplyFormStatus() {
      return this.applyFormStatus;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setImagingDiag(String imagingDiag) {
      this.imagingDiag = imagingDiag;
   }

   public String getImagingDiag() {
      return this.imagingDiag;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setPathologicDiagH(String pathologicDiagH) {
      this.pathologicDiagH = pathologicDiagH;
   }

   public String getPathologicDiagH() {
      return this.pathologicDiagH;
   }

   public void setTumorFindDate(Date tumorFindDate) {
      this.tumorFindDate = tumorFindDate;
   }

   public Date getTumorFindDate() {
      return this.tumorFindDate;
   }

   public void setTumorMovePart(String tumorMovePart) {
      this.tumorMovePart = tumorMovePart;
   }

   public String getTumorMovePart() {
      return this.tumorMovePart;
   }

   public void setUrgentFlag(String urgentFlag) {
      this.urgentFlag = urgentFlag;
   }

   public String getUrgentFlag() {
      return this.urgentFlag;
   }

   public void setPrintFlag(String printFlag) {
      this.printFlag = printFlag;
   }

   public String getPrintFlag() {
      return this.printFlag;
   }

   public void setTreatment(String treatment) {
      this.treatment = treatment;
   }

   public String getTreatment() {
      return this.treatment;
   }

   public void setSurgicalDescr(String surgicalDescr) {
      this.surgicalDescr = surgicalDescr;
   }

   public String getSurgicalDescr() {
      return this.surgicalDescr;
   }

   public void setSettleAccountDate(Date settleAccountDate) {
      this.settleAccountDate = settleAccountDate;
   }

   public Date getSettleAccountDate() {
      return this.settleAccountDate;
   }

   public void setSettleAccountCode(String settleAccountCode) {
      this.settleAccountCode = settleAccountCode;
   }

   public String getSettleAccountCode() {
      return this.settleAccountCode;
   }

   public void setTumorShape(String tumorShape) {
      this.tumorShape = tumorShape;
   }

   public String getTumorShape() {
      return this.tumorShape;
   }

   public void setSettleAccountNo(String settleAccountNo) {
      this.settleAccountNo = settleAccountNo;
   }

   public String getSettleAccountNo() {
      return this.settleAccountNo;
   }

   public void setTumorSize(String tumorSize) {
      this.tumorSize = tumorSize;
   }

   public String getTumorSize() {
      return this.tumorSize;
   }

   public void setSettleAccountName(String settleAccountName) {
      this.settleAccountName = settleAccountName;
   }

   public String getSettleAccountName() {
      return this.settleAccountName;
   }

   public void setTumorColor(String tumorColor) {
      this.tumorColor = tumorColor;
   }

   public String getTumorColor() {
      return this.tumorColor;
   }

   public void setReportDate(Date reportDate) {
      this.reportDate = reportDate;
   }

   public Date getReportDate() {
      return this.reportDate;
   }

   public void setTumorAroundTissuesRelation(String tumorAroundTissuesRelation) {
      this.tumorAroundTissuesRelation = tumorAroundTissuesRelation;
   }

   public String getTumorAroundTissuesRelation() {
      return this.tumorAroundTissuesRelation;
   }

   public void setReportDoctor(String reportDoctor) {
      this.reportDoctor = reportDoctor;
   }

   public String getReportDoctor() {
      return this.reportDoctor;
   }

   public void setSpecFixative(String specFixative) {
      this.specFixative = specFixative;
   }

   public String getSpecFixative() {
      return this.specFixative;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setSpecInVitroDate(Date specInVitroDate) {
      this.specInVitroDate = specInVitroDate;
   }

   public Date getSpecInVitroDate() {
      return this.specInVitroDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setSpecFixationDate(Date specFixationDate) {
      this.specFixationDate = specFixationDate;
   }

   public Date getSpecFixationDate() {
      return this.specFixationDate;
   }

   public void setSpecHandoverDate(Date specHandoverDate) {
      this.specHandoverDate = specHandoverDate;
   }

   public Date getSpecHandoverDate() {
      return this.specHandoverDate;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("applyFormNo", this.getApplyFormNo()).append("applyFormClassCode", this.getApplyFormClassCode()).append("applyFormTypeCode", this.getApplyFormTypeCode()).append("orderNo", this.getOrderNo()).append("orderSortNumber", this.getOrderSortNumber()).append("patientId", this.getPatientId()).append("caseNo", this.getCaseNo()).append("admissionNo", this.getAdmissionNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("patientName", this.getPatientName()).append("babyNo", this.getBabyNo()).append("patientDepCode", this.getPatientDepCode()).append("patientDepName", this.getPatientDepName()).append("applyTime", this.getApplyTime()).append("orderDoctorCode", this.getOrderDoctorCode()).append("orderDoctorName", this.getOrderDoctorName()).append("physicianDptNo", this.getPhysicianDptNo()).append("physicianDptName", this.getPhysicianDptName()).append("performDepCode", this.getPerformDepCode()).append("performDepName", this.getPerformDepName()).append("diagnosisCode", this.getDiagnosisCode()).append("diagnosisName", this.getDiagnosisName()).append("complaint", this.getComplaint()).append("medicalRecordDigest", this.getMedicalRecordDigest()).append("purposeExamination", this.getPurposeExamination()).append("examCategory", this.getExamCategory()).append("examPart", this.getExamPart()).append("sampleNo", this.getSampleNo()).append("sampleName", this.getSampleName()).append("examMethodName", this.getExamMethodName()).append("applyFormStatus", this.getApplyFormStatus()).append("operatorCode", this.getOperatorCode()).append("imagingDiag", this.getImagingDiag()).append("operatorName", this.getOperatorName()).append("pathologicDiagH", this.getPathologicDiagH()).append("remark", this.getRemark()).append("tumorFindDate", this.getTumorFindDate()).append("tumorMovePart", this.getTumorMovePart()).append("urgentFlag", this.getUrgentFlag()).append("printFlag", this.getPrintFlag()).append("treatment", this.getTreatment()).append("surgicalDescr", this.getSurgicalDescr()).append("settleAccountDate", this.getSettleAccountDate()).append("settleAccountCode", this.getSettleAccountCode()).append("tumorShape", this.getTumorShape()).append("settleAccountNo", this.getSettleAccountNo()).append("tumorSize", this.getTumorSize()).append("settleAccountName", this.getSettleAccountName()).append("tumorColor", this.getTumorColor()).append("reportDate", this.getReportDate()).append("tumorAroundTissuesRelation", this.getTumorAroundTissuesRelation()).append("reportDoctor", this.getReportDoctor()).append("specFixative", this.getSpecFixative()).append("altDate", this.getAltDate()).append("specInVitroDate", this.getSpecInVitroDate()).append("altPerCode", this.getAltPerCode()).append("specFixationDate", this.getSpecFixationDate()).append("specHandoverDate", this.getSpecHandoverDate()).append("altPerName", this.getAltPerName()).append("infectiousFlag", this.getInfectiousFlag()).append("examMethodCode", this.getExamMethodCode()).toString();
   }
}
