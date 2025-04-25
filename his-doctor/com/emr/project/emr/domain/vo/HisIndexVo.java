package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.Index;
import java.util.List;

public class HisIndexVo extends Index {
   List children;
   private String hisInhosTimeStr;
   private Integer visitId;
   private String code;
   private String name;

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public String getHisInhosTimeStr() {
      return this.hisInhosTimeStr;
   }

   public void setHisInhosTimeStr(String hisInhosTimeStr) {
      this.hisInhosTimeStr = hisInhosTimeStr;
   }

   public Integer getVisitId() {
      return this.visitId;
   }

   public void setVisitId(Integer visitId) {
      this.visitId = visitId;
   }
}
