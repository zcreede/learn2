package com.emr.project.mrhp.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class PicInfoVo implements Serializable {
   @ApiModelProperty("病案质量代码")
   private String mrq_cd;
   @ApiModelProperty("科主任")
   private String hd_img;
   @ApiModelProperty("主任医师姓名")
   private String arc_doc_img;
   @ApiModelProperty("科室")
   private String att_doc_img;
   @ApiModelProperty("住院医师编码")
   private String res_doc_img;
   @ApiModelProperty("责任护士编码")
   private String duty_nur_img;
   @ApiModelProperty("进修医师编码")
   private String stu_doc_img;
   @ApiModelProperty("实习医师编码")
   private String int_doc_img;
   @ApiModelProperty("编码员编码")
   private String coder_img;
   @ApiModelProperty("质控医师编码")
   private String qcon_doc_img;
   @ApiModelProperty("质控护士编码")
   private String qcon_nur_img;
   @ApiModelProperty("质控日期")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date qcon_date;

   public String getMrq_cd() {
      return this.mrq_cd;
   }

   public void setMrq_cd(String mrq_cd) {
      this.mrq_cd = mrq_cd;
   }

   public String getHd_img() {
      return this.hd_img;
   }

   public void setHd_img(String hd_img) {
      this.hd_img = hd_img;
   }

   public String getArc_doc_img() {
      return this.arc_doc_img;
   }

   public void setArc_doc_img(String arc_doc_img) {
      this.arc_doc_img = arc_doc_img;
   }

   public String getAtt_doc_img() {
      return this.att_doc_img;
   }

   public void setAtt_doc_img(String att_doc_img) {
      this.att_doc_img = att_doc_img;
   }

   public String getRes_doc_img() {
      return this.res_doc_img;
   }

   public void setRes_doc_img(String res_doc_img) {
      this.res_doc_img = res_doc_img;
   }

   public String getDuty_nur_img() {
      return this.duty_nur_img;
   }

   public void setDuty_nur_img(String duty_nur_img) {
      this.duty_nur_img = duty_nur_img;
   }

   public String getStu_doc_img() {
      return this.stu_doc_img;
   }

   public void setStu_doc_img(String stu_doc_img) {
      this.stu_doc_img = stu_doc_img;
   }

   public String getInt_doc_img() {
      return this.int_doc_img;
   }

   public void setInt_doc_img(String int_doc_img) {
      this.int_doc_img = int_doc_img;
   }

   public String getCoder_img() {
      return this.coder_img;
   }

   public void setCoder_img(String coder_img) {
      this.coder_img = coder_img;
   }

   public String getQcon_doc_img() {
      return this.qcon_doc_img;
   }

   public void setQcon_doc_img(String qcon_doc_img) {
      this.qcon_doc_img = qcon_doc_img;
   }

   public String getQcon_nur_img() {
      return this.qcon_nur_img;
   }

   public void setQcon_nur_img(String qcon_nur_img) {
      this.qcon_nur_img = qcon_nur_img;
   }

   public Date getQcon_date() {
      return this.qcon_date;
   }

   public void setQcon_date(Date qcon_date) {
      this.qcon_date = qcon_date;
   }
}
