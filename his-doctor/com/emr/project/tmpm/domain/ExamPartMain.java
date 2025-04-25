package com.emr.project.tmpm.domain;

public class ExamPartMain {
   private Long id;
   private String partClassCd;
   private String partClassName;
   private Integer sort;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPartClassCd() {
      return this.partClassCd;
   }

   public void setPartClassCd(String partClassCd) {
      this.partClassCd = partClassCd;
   }

   public String getPartClassName() {
      return this.partClassName;
   }

   public void setPartClassName(String partClassName) {
      this.partClassName = partClassName;
   }

   public Integer getSort() {
      return this.sort;
   }

   public void setSort(Integer sort) {
      this.sort = sort;
   }
}
