package com.emr.project.operation.domain;

public class TmPmHsxm {
   private Integer id;
   private String code;
   private String name;
   private Integer sort;
   private String threeLevelCode;
   private String threeLevelName;
   private String drugFlag;
   private String firstTypeStatis;
   private String firstCode;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getFirstTypeStatis() {
      return this.firstTypeStatis;
   }

   public void setFirstTypeStatis(String firstTypeStatis) {
      this.firstTypeStatis = firstTypeStatis;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code == null ? null : code.trim();
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name == null ? null : name.trim();
   }

   public Integer getSort() {
      return this.sort;
   }

   public void setSort(Integer sort) {
      this.sort = sort;
   }

   public String getThreeLevelCode() {
      return this.threeLevelCode;
   }

   public void setThreeLevelCode(String threeLevelCode) {
      this.threeLevelCode = threeLevelCode == null ? null : threeLevelCode.trim();
   }

   public String getThreeLevelName() {
      return this.threeLevelName;
   }

   public void setThreeLevelName(String threeLevelName) {
      this.threeLevelName = threeLevelName == null ? null : threeLevelName.trim();
   }

   public String getDrugFlag() {
      return this.drugFlag;
   }

   public void setDrugFlag(String drugFlag) {
      this.drugFlag = drugFlag == null ? null : drugFlag.trim();
   }

   public String getFirstCode() {
      return this.firstCode;
   }

   public void setFirstCode(String firstCode) {
      this.firstCode = firstCode;
   }
}
