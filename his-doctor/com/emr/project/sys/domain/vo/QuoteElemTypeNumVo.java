package com.emr.project.sys.domain.vo;

public class QuoteElemTypeNumVo {
   private String tempType;
   private String tempTypeName;
   private Integer tempTypeNum;
   private Integer fromTypeNum;

   public String getTempType() {
      return this.tempType;
   }

   public void setTempType(String tempType) {
      this.tempType = tempType;
   }

   public String getTempTypeName() {
      return this.tempTypeName;
   }

   public void setTempTypeName(String tempTypeName) {
      this.tempTypeName = tempTypeName;
   }

   public Integer getTempTypeNum() {
      return this.tempTypeNum;
   }

   public void setTempTypeNum(Integer tempTypeNum) {
      this.tempTypeNum = tempTypeNum;
   }

   public Integer getFromTypeNum() {
      return this.fromTypeNum;
   }

   public void setFromTypeNum(Integer fromTypeNum) {
      this.fromTypeNum = fromTypeNum;
   }
}
