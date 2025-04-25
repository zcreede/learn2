package com.emr.project.CDSS.domain.vo.xyt;

import com.emr.project.CDSS.domain.xyt.Anaphylaxis;
import com.emr.project.CDSS.domain.xyt.Diagnoses;
import com.emr.project.CDSS.domain.xyt.ExamApplication;
import com.emr.project.CDSS.domain.xyt.ExamResult;
import com.emr.project.CDSS.domain.xyt.MedRecordInfo;
import com.emr.project.CDSS.domain.xyt.MedicalOrderMain;
import com.emr.project.CDSS.domain.xyt.OperationApplication;
import com.emr.project.CDSS.domain.xyt.PatientInfo;
import com.emr.project.CDSS.domain.xyt.TestsApplication;
import com.emr.project.CDSS.domain.xyt.TestsResult;
import com.emr.project.CDSS.domain.xyt.TreatmentOrder;
import com.emr.project.CDSS.domain.xyt.VitalSigns;
import java.util.List;

public class ReqParamVo {
   private PatientInfo patientInfo;
   private List diagnoses;
   private List medRecordInfo;
   private List ExamApplication;
   private List ExamResult;
   private List TestsApplication;
   private List TestsResult;
   private List Anaphylaxis;
   private List VitalSigns;
   private List MedicalOrder;
   private List OperationApplication;
   private List TreatmentOrder;

   public PatientInfo getPatientInfo() {
      return this.patientInfo;
   }

   public void setPatientInfo(PatientInfo patientInfo) {
      this.patientInfo = patientInfo;
   }

   public List getDiagnoses() {
      return this.diagnoses;
   }

   public void setDiagnoses(List diagnoses) {
      this.diagnoses = diagnoses;
   }

   public List getMedRecordInfo() {
      return this.medRecordInfo;
   }

   public void setMedRecordInfo(List medRecordInfo) {
      this.medRecordInfo = medRecordInfo;
   }

   public List getExamApplication() {
      return this.ExamApplication;
   }

   public void setExamApplication(List examApplication) {
      this.ExamApplication = examApplication;
   }

   public List getExamResult() {
      return this.ExamResult;
   }

   public void setExamResult(List examResult) {
      this.ExamResult = examResult;
   }

   public List getTestsApplication() {
      return this.TestsApplication;
   }

   public void setTestsApplication(List testsApplication) {
      this.TestsApplication = testsApplication;
   }

   public List getTestsResult() {
      return this.TestsResult;
   }

   public void setTestsResult(List testsResult) {
      this.TestsResult = testsResult;
   }

   public List getAnaphylaxis() {
      return this.Anaphylaxis;
   }

   public void setAnaphylaxis(List anaphylaxis) {
      this.Anaphylaxis = anaphylaxis;
   }

   public List getVitalSigns() {
      return this.VitalSigns;
   }

   public void setVitalSigns(List vitalSigns) {
      this.VitalSigns = vitalSigns;
   }

   public List getMedicalOrder() {
      return this.MedicalOrder;
   }

   public void setMedicalOrder(List medicalOrder) {
      this.MedicalOrder = medicalOrder;
   }

   public List getOperationApplication() {
      return this.OperationApplication;
   }

   public void setOperationApplication(List operationApplication) {
      this.OperationApplication = operationApplication;
   }

   public List getTreatmentOrder() {
      return this.TreatmentOrder;
   }

   public void setTreatmentOrder(List treatmentOrder) {
      this.TreatmentOrder = treatmentOrder;
   }
}
