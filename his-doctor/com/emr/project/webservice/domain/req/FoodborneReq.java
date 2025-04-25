package com.emr.project.webservice.domain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class FoodborneReq {
   private String patientId;
   private String diagnosisName;
   @JsonFormat(
      pattern = "YYYY-MM-DD HH:mm",
      timezone = "GMT+8"
   )
   private Date diseaseDate;
   @JsonFormat(
      pattern = "YYYY-MM-DD HH:mm",
      timezone = "GMT+8"
   )
   private Date diseaseTreattime;
   private String outPatientNumber;
   private String patientName;
   private Integer diseaseSex;
   private String guarderName;
   private Integer diseaseIsreexam;
   private Integer diseaseIspaint;
   private String diseaseHospitalno;
   private String identityCard;
   private Date diseaseBirthday;
   private String phoneNumber;
   private String workUnit;
   @JsonFormat(
      pattern = "YYYY-MM-DD HH:mm",
      timezone = "GMT+8"
   )
   private Date deathDate;
   private String fillingDoctorName;
   private String medicalInstiSeHospital;
   private String diseaseHometown;
   private String diseaseProvince;
   private String diseaseCity;
   private String diseaseDistrict;
   private String diseaseAddress;
   private String diseaseOccupation;
   private String uName;
   private String token;

   public String getDiagnosisName() {
      return this.diagnosisName;
   }

   public void setDiagnosisName(String diagnosisName) {
      this.diagnosisName = diagnosisName;
   }

   public Date getDiseaseDate() {
      return this.diseaseDate;
   }

   public void setDiseaseDate(Date diseaseDate) {
      this.diseaseDate = diseaseDate;
   }

   public Date getDiseaseTreattime() {
      return this.diseaseTreattime;
   }

   public void setDiseaseTreattime(Date diseaseTreattime) {
      this.diseaseTreattime = diseaseTreattime;
   }

   public String getOutPatientNumber() {
      return this.outPatientNumber;
   }

   public void setOutPatientNumber(String outPatientNumber) {
      this.outPatientNumber = outPatientNumber;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public Integer getDiseaseSex() {
      return this.diseaseSex;
   }

   public void setDiseaseSex(Integer diseaseSex) {
      this.diseaseSex = diseaseSex;
   }

   public String getGuarderName() {
      return this.guarderName;
   }

   public void setGuarderName(String guarderName) {
      this.guarderName = guarderName;
   }

   public Integer getDiseaseIsreexam() {
      return this.diseaseIsreexam;
   }

   public void setDiseaseIsreexam(Integer diseaseIsreexam) {
      this.diseaseIsreexam = diseaseIsreexam;
   }

   public Integer getDiseaseIspaint() {
      return this.diseaseIspaint;
   }

   public void setDiseaseIspaint(Integer diseaseIspaint) {
      this.diseaseIspaint = diseaseIspaint;
   }

   public String getDiseaseHospitalno() {
      return this.diseaseHospitalno;
   }

   public void setDiseaseHospitalno(String diseaseHospitalno) {
      this.diseaseHospitalno = diseaseHospitalno;
   }

   public String getIdentityCard() {
      return this.identityCard;
   }

   public void setIdentityCard(String identityCard) {
      this.identityCard = identityCard;
   }

   public Date getDiseaseBirthday() {
      return this.diseaseBirthday;
   }

   public void setDiseaseBirthday(Date diseaseBirthday) {
      this.diseaseBirthday = diseaseBirthday;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getWorkUnit() {
      return this.workUnit;
   }

   public void setWorkUnit(String workUnit) {
      this.workUnit = workUnit;
   }

   public Date getDeathDate() {
      return this.deathDate;
   }

   public void setDeathDate(Date deathDate) {
      this.deathDate = deathDate;
   }

   public String getFillingDoctorName() {
      return this.fillingDoctorName;
   }

   public void setFillingDoctorName(String fillingDoctorName) {
      this.fillingDoctorName = fillingDoctorName;
   }

   public String getMedicalInstiSeHospital() {
      return this.medicalInstiSeHospital;
   }

   public void setMedicalInstiSeHospital(String medicalInstiSeHospital) {
      this.medicalInstiSeHospital = medicalInstiSeHospital;
   }

   public String getDiseaseHometown() {
      return this.diseaseHometown;
   }

   public void setDiseaseHometown(String diseaseHometown) {
      this.diseaseHometown = diseaseHometown;
   }

   public String getDiseaseProvince() {
      return this.diseaseProvince;
   }

   public void setDiseaseProvince(String diseaseProvince) {
      this.diseaseProvince = diseaseProvince;
   }

   public String getDiseaseCity() {
      return this.diseaseCity;
   }

   public void setDiseaseCity(String diseaseCity) {
      this.diseaseCity = diseaseCity;
   }

   public String getDiseaseDistrict() {
      return this.diseaseDistrict;
   }

   public void setDiseaseDistrict(String diseaseDistrict) {
      this.diseaseDistrict = diseaseDistrict;
   }

   public String getDiseaseAddress() {
      return this.diseaseAddress;
   }

   public void setDiseaseAddress(String diseaseAddress) {
      this.diseaseAddress = diseaseAddress;
   }

   public String getDiseaseOccupation() {
      return this.diseaseOccupation;
   }

   public void setDiseaseOccupation(String diseaseOccupation) {
      this.diseaseOccupation = diseaseOccupation;
   }

   public String getuName() {
      return this.uName;
   }

   public void setuName(String uName) {
      this.uName = uName;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }
}
