package com.emr.project.PASS.domain.vo.mk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.bean.BeanUtils;
import com.emr.project.PASS.domain.mk.Allergen;
import com.emr.project.PASS.domain.mk.Drugs;
import com.emr.project.PASS.domain.mk.Exam;
import com.emr.project.PASS.domain.mk.Lab;
import com.emr.project.PASS.domain.mk.MedCond;
import com.emr.project.PASS.domain.mk.Operation;
import com.emr.project.PASS.domain.mk.Patient;
import com.emr.project.PASS.domain.mk.Recipe;
import java.util.List;

public class CheckInfoVo {
   private Patient patientO;
   private List drugsListO;
   private List recipeListO;
   private List medCondListO;
   private List allergenListO;
   private List operationListO;
   private List examListO;
   private List labListO;
   private JSONObject patient;
   private JSONArray drugsList;
   private JSONArray recipeList;
   private JSONArray medCondList;
   private JSONArray allergenList;
   private JSONArray operationList;
   private JSONArray examList;
   private JSONArray labList;

   public CheckInfoVo toJsonInfo() throws Exception {
      CheckInfoVo vo = new CheckInfoVo();
      JSONObject patientJO = BeanUtils.getObjectJsonObject(this.patientO);
      vo.setPatient(patientJO);
      JSONArray drugsListJA = BeanUtils.getObjectJsonArr(this.drugsListO);
      vo.setDrugsList(drugsListJA);
      JSONArray recipeListJA = BeanUtils.getObjectJsonArr(this.recipeListO);
      vo.setRecipeList(recipeListJA);
      JSONArray medCondListJA = BeanUtils.getObjectJsonArr(this.medCondListO);
      vo.setMedCondList(medCondListJA);
      JSONArray allergenListJA = BeanUtils.getObjectJsonArr(this.allergenListO);
      vo.setAllergenList(allergenListJA);
      JSONArray operationListJA = BeanUtils.getObjectJsonArr(this.operationListO);
      vo.setOperationList(operationListJA);
      JSONArray examListJA = BeanUtils.getObjectJsonArr(this.examListO);
      vo.setExamList(examListJA);
      JSONArray labListJA = BeanUtils.getObjectJsonArr(this.labListO);
      vo.setLabList(labListJA);
      return vo;
   }

   public Patient getPatientO() {
      return this.patientO;
   }

   public void setPatientO(Patient patientO) {
      this.patientO = patientO;
   }

   public List getDrugsListO() {
      return this.drugsListO;
   }

   public void setDrugsListO(List drugsListO) {
      this.drugsListO = drugsListO;
   }

   public List getRecipeListO() {
      return this.recipeListO;
   }

   public void setRecipeListO(List recipeListO) {
      this.recipeListO = recipeListO;
   }

   public List getMedCondListO() {
      return this.medCondListO;
   }

   public void setMedCondListO(List medCondListO) {
      this.medCondListO = medCondListO;
   }

   public List getAllergenListO() {
      return this.allergenListO;
   }

   public void setAllergenListO(List allergenListO) {
      this.allergenListO = allergenListO;
   }

   public List getOperationListO() {
      return this.operationListO;
   }

   public void setOperationListO(List operationListO) {
      this.operationListO = operationListO;
   }

   public List getExamListO() {
      return this.examListO;
   }

   public void setExamListO(List examListO) {
      this.examListO = examListO;
   }

   public List getLabListO() {
      return this.labListO;
   }

   public void setLabListO(List labListO) {
      this.labListO = labListO;
   }

   public JSONObject getPatient() {
      return this.patient;
   }

   public void setPatient(JSONObject patient) {
      this.patient = patient;
   }

   public JSONArray getDrugsList() {
      return this.drugsList;
   }

   public void setDrugsList(JSONArray drugsList) {
      this.drugsList = drugsList;
   }

   public JSONArray getRecipeList() {
      return this.recipeList;
   }

   public void setRecipeList(JSONArray recipeList) {
      this.recipeList = recipeList;
   }

   public JSONArray getMedCondList() {
      return this.medCondList;
   }

   public void setMedCondList(JSONArray medCondList) {
      this.medCondList = medCondList;
   }

   public JSONArray getAllergenList() {
      return this.allergenList;
   }

   public void setAllergenList(JSONArray allergenList) {
      this.allergenList = allergenList;
   }

   public JSONArray getOperationList() {
      return this.operationList;
   }

   public void setOperationList(JSONArray operationList) {
      this.operationList = operationList;
   }

   public JSONArray getExamList() {
      return this.examList;
   }

   public void setExamList(JSONArray examList) {
      this.examList = examList;
   }

   public JSONArray getLabList() {
      return this.labList;
   }

   public void setLabList(JSONArray labList) {
      this.labList = labList;
   }
}
