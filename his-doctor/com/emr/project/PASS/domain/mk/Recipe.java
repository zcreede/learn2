package com.emr.project.PASS.domain.mk;

public class Recipe {
   private String RecipeNo;
   private String RecipeTypeCode;
   private String RecipeTypeName;
   private String RecipeRouteCode;
   private String RecipeRouteName;
   private String RecipeFreq;
   private String RecipeDosage;
   private String RecipeMark;
   private String IsChronicDisease;
   private String ChmNDayPereach;

   public String getRecipeNo() {
      return this.RecipeNo;
   }

   public void setRecipeNo(String recipeNo) {
      this.RecipeNo = recipeNo;
   }

   public String getRecipeTypeCode() {
      return this.RecipeTypeCode;
   }

   public void setRecipeTypeCode(String recipeTypeCode) {
      this.RecipeTypeCode = recipeTypeCode;
   }

   public String getRecipeTypeName() {
      return this.RecipeTypeName;
   }

   public void setRecipeTypeName(String recipeTypeName) {
      this.RecipeTypeName = recipeTypeName;
   }

   public String getRecipeRouteCode() {
      return this.RecipeRouteCode;
   }

   public void setRecipeRouteCode(String recipeRouteCode) {
      this.RecipeRouteCode = recipeRouteCode;
   }

   public String getRecipeRouteName() {
      return this.RecipeRouteName;
   }

   public void setRecipeRouteName(String recipeRouteName) {
      this.RecipeRouteName = recipeRouteName;
   }

   public String getRecipeFreq() {
      return this.RecipeFreq;
   }

   public void setRecipeFreq(String recipeFreq) {
      this.RecipeFreq = recipeFreq;
   }

   public String getRecipeDosage() {
      return this.RecipeDosage;
   }

   public void setRecipeDosage(String recipeDosage) {
      this.RecipeDosage = recipeDosage;
   }

   public String getRecipeMark() {
      return this.RecipeMark;
   }

   public void setRecipeMark(String recipeMark) {
      this.RecipeMark = recipeMark;
   }

   public String getIsChronicDisease() {
      return this.IsChronicDisease;
   }

   public void setIsChronicDisease(String isChronicDisease) {
      this.IsChronicDisease = isChronicDisease;
   }

   public String getChmNDayPereach() {
      return this.ChmNDayPereach;
   }

   public void setChmNDayPereach(String chmNDayPereach) {
      this.ChmNDayPereach = chmNDayPereach;
   }
}
