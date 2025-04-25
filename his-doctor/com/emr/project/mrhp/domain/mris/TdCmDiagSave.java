package com.emr.project.mrhp.domain.mris;

import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(
   value = "TdCmDiagSave",
   description = "病案首页-出院诊断"
)
public class TdCmDiagSave extends BaseEntity {
   @ApiModelProperty("病案ID")
   private String record_id;
   private String patient_id;
   private String patient_name;
   @ApiModelProperty("诊断分类(XY：西医；ZY：中医）")
   private String diag_type;
   @ApiModelProperty("诊断序号")
   private String diag_seq;
   @ApiModelProperty("主键id")
   private String diag_id;
   @ApiModelProperty("诊断类别（01.主要诊断、02.其他诊断、11.主病、12.主证、13.其他主病、14.其他主证等）")
   private String diag_class;
   @ApiModelProperty("诊断类别--用于保存时判断诊断和类别是否对照一致")
   private String diag_class_old;
   @ApiModelProperty("疾病诊断编码")
   private String diag_cd;
   @ApiModelProperty("疾病诊断名称")
   private String diag_name;
   @ApiModelProperty("疾病诊断附加码")
   private String diag_ex_cd;
   @ApiModelProperty("入院病情情况")
   private String inhos_cond;
   @ApiModelProperty("入院病情情况编号")
   private String inhos_cond_cd;
   @ApiModelProperty("转归情况代码")
   private String outcome_cd;
   @ApiModelProperty("转归情况")
   private String outcome;
   @ApiModelProperty("诊断说明")
   private String diag_rem;
   @ApiModelProperty("医师诊断编码")
   private String diag_cd_doc;
   @ApiModelProperty("医师诊断名称")
   private String diag_name_doc;
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
   private String diag_item;
   @ApiModelProperty("修改日期")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date alt_date;

   public String getDiag_class_old() {
      return this.diag_class_old;
   }

   public void setDiag_class_old(String diag_class_old) {
      this.diag_class_old = diag_class_old;
   }

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

   public String getDiag_type() {
      return this.diag_type;
   }

   public void setDiag_type(String diag_type) {
      this.diag_type = diag_type;
   }

   public String getDiag_seq() {
      return this.diag_seq;
   }

   public void setDiag_seq(String diag_seq) {
      this.diag_seq = diag_seq;
   }

   public String getDiag_id() {
      return this.diag_id;
   }

   public void setDiag_id(String diag_id) {
      this.diag_id = diag_id;
   }

   public String getDiag_class() {
      return this.diag_class;
   }

   public void setDiag_class(String diag_class) {
      this.diag_class = diag_class;
   }

   public String getDiag_cd() {
      return this.diag_cd;
   }

   public void setDiag_cd(String diag_cd) {
      this.diag_cd = diag_cd;
   }

   public String getDiag_name() {
      return this.diag_name;
   }

   public void setDiag_name(String diag_name) {
      this.diag_name = diag_name;
   }

   public String getDiag_ex_cd() {
      return this.diag_ex_cd;
   }

   public void setDiag_ex_cd(String diag_ex_cd) {
      this.diag_ex_cd = diag_ex_cd;
   }

   public String getInhos_cond() {
      return this.inhos_cond;
   }

   public void setInhos_cond(String inhos_cond) {
      this.inhos_cond = inhos_cond;
   }

   public String getInhos_cond_cd() {
      return this.inhos_cond_cd;
   }

   public void setInhos_cond_cd(String inhos_cond_cd) {
      this.inhos_cond_cd = inhos_cond_cd;
   }

   public String getOutcome_cd() {
      return this.outcome_cd;
   }

   public void setOutcome_cd(String outcome_cd) {
      this.outcome_cd = outcome_cd;
   }

   public String getOutcome() {
      return this.outcome;
   }

   public void setOutcome(String outcome) {
      this.outcome = outcome;
   }

   public String getDiag_rem() {
      return this.diag_rem;
   }

   public void setDiag_rem(String diag_rem) {
      this.diag_rem = diag_rem;
   }

   public String getDiag_cd_doc() {
      return this.diag_cd_doc;
   }

   public void setDiag_cd_doc(String diag_cd_doc) {
      this.diag_cd_doc = diag_cd_doc;
   }

   public String getDiag_name_doc() {
      return this.diag_name_doc;
   }

   public void setDiag_name_doc(String diag_name_doc) {
      this.diag_name_doc = diag_name_doc;
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

   public String getDiag_item() {
      return this.diag_item;
   }

   public void setDiag_item(String diag_item) {
      this.diag_item = diag_item;
   }

   public Date getAlt_date() {
      return this.alt_date;
   }

   public void setAlt_date(Date alt_date) {
      this.alt_date = alt_date;
   }
}
