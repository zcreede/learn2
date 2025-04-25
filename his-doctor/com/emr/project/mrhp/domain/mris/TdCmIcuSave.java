package com.emr.project.mrhp.domain.mris;

import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(
   value = "TdCmIcuSave",
   description = "病案附页-重症监护室"
)
public class TdCmIcuSave extends BaseEntity {
   @ApiModelProperty("病案ID")
   private String record_id;
   private String patient_id;
   private String patient_name;
   @ApiModelProperty("主键ID")
   private String icu_id;
   @ApiModelProperty("序号")
   private Long icu_seq;
   @ApiModelProperty("ICU科室编码")
   private String icu_code;
   @ApiModelProperty("ICU科室名称")
   private String icu_name;
   @ApiModelProperty("进重症监护室时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date icu_in_time;
   @ApiModelProperty("出重症监护室时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date icu_out_time;
   @ApiModelProperty("时长（小时）")
   private BigDecimal icu_hour;
   @ApiModelProperty("创建人编码")
   private String cre_per_code;
   @ApiModelProperty("创建人名称")
   private String cre_per_name;
   @ApiModelProperty("创建日期")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date cre_date;
   @ApiModelProperty("修改人编码")
   private String alt_per_code;
   @ApiModelProperty("修改人姓名")
   private String alt_per_name;
   @ApiModelProperty("修改日期")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date alt_date;

   public String getRecord_id() {
      return this.record_id;
   }

   public void setRecord_id(String record_id) {
      this.record_id = record_id;
   }

   public String getPatient_id() {
      return this.patient_id;
   }

   public void setPatient_id(String patient_id) {
      this.patient_id = patient_id;
   }

   public String getPatient_name() {
      return this.patient_name;
   }

   public void setPatient_name(String patient_name) {
      this.patient_name = patient_name;
   }

   public String getIcu_id() {
      return this.icu_id;
   }

   public void setIcu_id(String icu_id) {
      this.icu_id = icu_id;
   }

   public Long getIcu_seq() {
      return this.icu_seq;
   }

   public void setIcu_seq(Long icu_seq) {
      this.icu_seq = icu_seq;
   }

   public String getIcu_code() {
      return this.icu_code;
   }

   public void setIcu_code(String icu_code) {
      this.icu_code = icu_code;
   }

   public String getIcu_name() {
      return this.icu_name;
   }

   public void setIcu_name(String icu_name) {
      this.icu_name = icu_name;
   }

   public Date getIcu_in_time() {
      return this.icu_in_time;
   }

   public void setIcu_in_time(Date icu_in_time) {
      this.icu_in_time = icu_in_time;
   }

   public Date getIcu_out_time() {
      return this.icu_out_time;
   }

   public void setIcu_out_time(Date icu_out_time) {
      this.icu_out_time = icu_out_time;
   }

   public BigDecimal getIcu_hour() {
      return this.icu_hour;
   }

   public void setIcu_hour(BigDecimal icu_hour) {
      this.icu_hour = icu_hour;
   }

   public String getCre_per_code() {
      return this.cre_per_code;
   }

   public void setCre_per_code(String cre_per_code) {
      this.cre_per_code = cre_per_code;
   }

   public String getCre_per_name() {
      return this.cre_per_name;
   }

   public void setCre_per_name(String cre_per_name) {
      this.cre_per_name = cre_per_name;
   }

   public Date getCre_date() {
      return this.cre_date;
   }

   public void setCre_date(Date cre_date) {
      this.cre_date = cre_date;
   }

   public String getAlt_per_code() {
      return this.alt_per_code;
   }

   public void setAlt_per_code(String alt_per_code) {
      this.alt_per_code = alt_per_code;
   }

   public String getAlt_per_name() {
      return this.alt_per_name;
   }

   public void setAlt_per_name(String alt_per_name) {
      this.alt_per_name = alt_per_name;
   }

   public Date getAlt_date() {
      return this.alt_date;
   }

   public void setAlt_date(Date alt_date) {
      this.alt_date = alt_date;
   }
}
