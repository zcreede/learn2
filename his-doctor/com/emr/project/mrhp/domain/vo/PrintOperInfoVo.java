package com.emr.project.mrhp.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class PrintOperInfoVo implements Serializable {
   @ApiModelProperty("手术及操作编码")
   private String operIcd;
   @ApiModelProperty("手术及操作名称")
   private String operName;
   @ApiModelProperty("手术及操作开始日期时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operBeginDt;
   @ApiModelProperty("手术级别名称")
   private String operLevelName;
   @ApiModelProperty("主刀医师姓名")
   private String operDocName;
   @ApiModelProperty("Ⅰ助姓名")
   private String aid1Name;
   @ApiModelProperty("Ⅱ助姓名")
   private String aid2Name;
   @ApiModelProperty("手术切口等级名称")
   private String operInci;
   @ApiModelProperty("麻醉方法名称")
   private String anestMethName;
   @ApiModelProperty("麻醉医师姓名")
   private String anestName;

   public String getOperIcd() {
      return this.operIcd;
   }

   public void setOperIcd(String operIcd) {
      this.operIcd = operIcd;
   }

   public String getOperName() {
      return this.operName;
   }

   public void setOperName(String operName) {
      this.operName = operName;
   }

   public Date getOperBeginDt() {
      return this.operBeginDt;
   }

   public void setOperBeginDt(Date operBeginDt) {
      this.operBeginDt = operBeginDt;
   }

   public String getOperLevelName() {
      return this.operLevelName;
   }

   public void setOperLevelName(String operLevelName) {
      this.operLevelName = operLevelName;
   }

   public String getOperDocName() {
      return this.operDocName;
   }

   public void setOperDocName(String operDocName) {
      this.operDocName = operDocName;
   }

   public String getAid1Name() {
      return this.aid1Name;
   }

   public void setAid1Name(String aid1Name) {
      this.aid1Name = aid1Name;
   }

   public String getAid2Name() {
      return this.aid2Name;
   }

   public void setAid2Name(String aid2Name) {
      this.aid2Name = aid2Name;
   }

   public String getOperInci() {
      return this.operInci;
   }

   public void setOperInci(String operInci) {
      this.operInci = operInci;
   }

   public String getAnestMethName() {
      return this.anestMethName;
   }

   public void setAnestMethName(String anestMethName) {
      this.anestMethName = anestMethName;
   }

   public String getAnestName() {
      return this.anestName;
   }

   public void setAnestName(String anestName) {
      this.anestName = anestName;
   }
}
