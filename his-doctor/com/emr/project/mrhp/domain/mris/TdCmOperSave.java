package com.emr.project.mrhp.domain.mris;

import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(
   value = "TdCmOperSave",
   description = "病案首页-手术"
)
public class TdCmOperSave extends BaseEntity {
   @ApiModelProperty("病案ID")
   private String record_id;
   private String patient_id;
   private String patient_name;
   @ApiModelProperty("手术序号")
   private String oper_seq;
   @ApiModelProperty("主键id")
   private String oper_id;
   @ApiModelProperty("主手术（1：主手术，2：次手术）")
   private String oper_main;
   @ApiModelProperty("手术及操作开始日期时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date oper_begin_dt;
   @ApiModelProperty("手术及操作结束日期时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date oper_end_dt;
   @ApiModelProperty("手术及操作编码")
   private String oper_icd;
   @ApiModelProperty("手术及操作名称")
   private String oper_name;
   @ApiModelProperty("手术级别代码")
   private String oper_level_code;
   @ApiModelProperty("手术级别名称")
   private String oper_level_name;
   @ApiModelProperty("手术类型（1.手术；2：诊断性操作；3：治疗性操作 4：介入治疗)")
   private String oper_type;
   @ApiModelProperty("主刀医师编码")
   private String oper_doc_code;
   @ApiModelProperty("主刀医师姓名")
   private String oper_doc_name;
   @ApiModelProperty("Ⅰ助编码")
   private String aid1_code;
   @ApiModelProperty("Ⅰ助姓名")
   private String aid1_name;
   @ApiModelProperty("Ⅱ助编码")
   private String aid2_code;
   @ApiModelProperty("Ⅱ助姓名")
   private String aid2_name;
   @ApiModelProperty("手术切口类别代码")
   private String oper_inci_code;
   @ApiModelProperty("手术切口等级名称")
   private String oper_inci_name;
   @ApiModelProperty("手术愈合等级代码")
   private String oper_heal_code;
   @ApiModelProperty("手术愈合等级名称")
   private String oper_heal_name;
   @ApiModelProperty("麻醉方法代码")
   private String anest_meth_code;
   @ApiModelProperty("麻醉方法名称")
   private String anest_meth_name;
   @ApiModelProperty("麻醉医师姓名")
   private String anest_name;
   @ApiModelProperty("麻醉医师编号")
   private String anest_code;
   @ApiModelProperty("麻醉风险等级ASA（P1：正常的患者；P2：患者有轻微的临床症状；P3：患者有明显的系统临床症状；P4：患者有明显的系统临床症状，且危及生命；P5：如果不手术患者将不能存活；P6：脑死亡的患者）")
   private String anest_asa;
   @ApiModelProperty("手术部位代码")
   private String oper_site_code;
   @ApiModelProperty("手术部位名称")
   private String oper_site_name;
   @ApiModelProperty("手术风险等级代码“手术风险分级标准（NNIS）”将手术分为四级，即NNIS0级、NNIS1级、NNIS2级和NNIS3级")
   private String oper_nnis;
   @ApiModelProperty("麻醉开始时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date anest_begin_time;
   @ApiModelProperty("麻醉结束时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date anest_end_time;
   @ApiModelProperty("医师下的手术及操作编码")
   private String oper_icd_doc;
   @ApiModelProperty("医师下的手术及操作名称")
   private String oper_name_doc;
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

   public String getOper_seq() {
      return this.oper_seq;
   }

   public void setOper_seq(String oper_seq) {
      this.oper_seq = oper_seq;
   }

   public String getOper_id() {
      return this.oper_id;
   }

   public void setOper_id(String oper_id) {
      this.oper_id = oper_id;
   }

   public String getOper_main() {
      return this.oper_main;
   }

   public void setOper_main(String oper_main) {
      this.oper_main = oper_main;
   }

   public Date getOper_begin_dt() {
      return this.oper_begin_dt;
   }

   public void setOper_begin_dt(Date oper_begin_dt) {
      this.oper_begin_dt = oper_begin_dt;
   }

   public Date getOper_end_dt() {
      return this.oper_end_dt;
   }

   public void setOper_end_dt(Date oper_end_dt) {
      this.oper_end_dt = oper_end_dt;
   }

   public String getOper_icd() {
      return this.oper_icd;
   }

   public void setOper_icd(String oper_icd) {
      this.oper_icd = oper_icd;
   }

   public String getOper_name() {
      return this.oper_name;
   }

   public void setOper_name(String oper_name) {
      this.oper_name = oper_name;
   }

   public String getOper_level_code() {
      return this.oper_level_code;
   }

   public void setOper_level_code(String oper_level_code) {
      this.oper_level_code = oper_level_code;
   }

   public String getOper_level_name() {
      return this.oper_level_name;
   }

   public void setOper_level_name(String oper_level_name) {
      this.oper_level_name = oper_level_name;
   }

   public String getOper_type() {
      return this.oper_type;
   }

   public void setOper_type(String oper_type) {
      this.oper_type = oper_type;
   }

   public String getOper_doc_code() {
      return this.oper_doc_code;
   }

   public void setOper_doc_code(String oper_doc_code) {
      this.oper_doc_code = oper_doc_code;
   }

   public String getOper_doc_name() {
      return this.oper_doc_name;
   }

   public void setOper_doc_name(String oper_doc_name) {
      this.oper_doc_name = oper_doc_name;
   }

   public String getAid1_code() {
      return this.aid1_code;
   }

   public void setAid1_code(String aid1_code) {
      this.aid1_code = aid1_code;
   }

   public String getAid1_name() {
      return this.aid1_name;
   }

   public void setAid1_name(String aid1_name) {
      this.aid1_name = aid1_name;
   }

   public String getAid2_code() {
      return this.aid2_code;
   }

   public void setAid2_code(String aid2_code) {
      this.aid2_code = aid2_code;
   }

   public String getAid2_name() {
      return this.aid2_name;
   }

   public void setAid2_name(String aid2_name) {
      this.aid2_name = aid2_name;
   }

   public String getOper_inci_code() {
      return this.oper_inci_code;
   }

   public void setOper_inci_code(String oper_inci_code) {
      this.oper_inci_code = oper_inci_code;
   }

   public String getOper_inci_name() {
      return this.oper_inci_name;
   }

   public void setOper_inci_name(String oper_inci_name) {
      this.oper_inci_name = oper_inci_name;
   }

   public String getOper_heal_code() {
      return this.oper_heal_code;
   }

   public void setOper_heal_code(String oper_heal_code) {
      this.oper_heal_code = oper_heal_code;
   }

   public String getOper_heal_name() {
      return this.oper_heal_name;
   }

   public void setOper_heal_name(String oper_heal_name) {
      this.oper_heal_name = oper_heal_name;
   }

   public String getAnest_meth_code() {
      return this.anest_meth_code;
   }

   public void setAnest_meth_code(String anest_meth_code) {
      this.anest_meth_code = anest_meth_code;
   }

   public String getAnest_meth_name() {
      return this.anest_meth_name;
   }

   public void setAnest_meth_name(String anest_meth_name) {
      this.anest_meth_name = anest_meth_name;
   }

   public String getAnest_name() {
      return this.anest_name;
   }

   public void setAnest_name(String anest_name) {
      this.anest_name = anest_name;
   }

   public String getAnest_code() {
      return this.anest_code;
   }

   public void setAnest_code(String anest_code) {
      this.anest_code = anest_code;
   }

   public String getAnest_asa() {
      return this.anest_asa;
   }

   public void setAnest_asa(String anest_asa) {
      this.anest_asa = anest_asa;
   }

   public String getOper_site_code() {
      return this.oper_site_code;
   }

   public void setOper_site_code(String oper_site_code) {
      this.oper_site_code = oper_site_code;
   }

   public String getOper_site_name() {
      return this.oper_site_name;
   }

   public void setOper_site_name(String oper_site_name) {
      this.oper_site_name = oper_site_name;
   }

   public String getOper_nnis() {
      return this.oper_nnis;
   }

   public void setOper_nnis(String oper_nnis) {
      this.oper_nnis = oper_nnis;
   }

   public Date getAnest_begin_time() {
      return this.anest_begin_time;
   }

   public void setAnest_begin_time(Date anest_begin_time) {
      this.anest_begin_time = anest_begin_time;
   }

   public Date getAnest_end_time() {
      return this.anest_end_time;
   }

   public void setAnest_end_time(Date anest_end_time) {
      this.anest_end_time = anest_end_time;
   }

   public String getOper_icd_doc() {
      return this.oper_icd_doc;
   }

   public void setOper_icd_doc(String oper_icd_doc) {
      this.oper_icd_doc = oper_icd_doc;
   }

   public String getOper_name_doc() {
      return this.oper_name_doc;
   }

   public void setOper_name_doc(String oper_name_doc) {
      this.oper_name_doc = oper_name_doc;
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
