package com.emr.project.mrhp.domain.mris;

import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(
   value = "TdCmAttachSave",
   description = "病案附页"
)
public class TdCmAttachSave extends BaseEntity {
   @ApiModelProperty("病案ID")
   private String record_id;
   private String patient_id;
   private String is_cri_record;
   @ApiModelProperty("入院病情代码")
   private String inhos_cond_cd;
   @ApiModelProperty("入院时病情情况(危急、病重、一般）")
   private String inhos_cond;
   @ApiModelProperty("确诊日期")
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date dia_date;
   @ApiModelProperty("门诊诊断与出院诊断符合情况")
   private String out_dis_dia;
   @ApiModelProperty("入院与出院诊断符合情况")
   private String adm_dis_dia;
   @ApiModelProperty("术前与术后诊断符合情况")
   private String pre_pos_dia;
   @ApiModelProperty("临床诊断与病理诊断符合情况")
   private String clin_path_dia;
   @ApiModelProperty("医学影像与病理诊断符合情况")
   private String med_path_dia;
   @ApiModelProperty("恶性肿瘤术前诊断与病理诊断符合情况")
   private String pre_path_dia;
   @ApiModelProperty("主诊断治疗效果")
   private String dia_eff;
   @ApiModelProperty("主要诊断依据名称")
   private String dia_bas_name;
   @ApiModelProperty("是否1-31天内同一病再入院  0.当天   1.15天内     2.31天内    3.否")
   private String readm_flag;
   @ApiModelProperty("无创呼吸机使用时间--天")
   private Long ventilator_d;
   @ApiModelProperty("无创呼吸机使用时间--时")
   private Long ventilator_h;
   @ApiModelProperty("无创呼吸机使用时间--分")
   private Long ventilator_mi;
   @ApiModelProperty("有创呼吸机使用时间--天")
   private Long invasive_ventilator_d;
   @ApiModelProperty("有创呼吸机使用时间--时")
   private Long invasive_ventilator_h;
   @ApiModelProperty("有创呼吸机使用时间--分")
   private Long invasive_ventilator_mi;
   @ApiModelProperty("日常生活能力评定量表得分（入院）")
   private Long adl_inhos;
   @ApiModelProperty("日常生活能力评定量表得分(出院)")
   private Long adl_outhos;
   @ApiModelProperty("抢救次数")
   private Long save_times;
   @ApiModelProperty("抢救成功次数")
   private Long save_success_times;
   @ApiModelProperty("是否临床路径病种（1.是 2.否）")
   private String iscp;
   @ApiModelProperty("肿瘤分期_T(0.0期  1. Ⅰ期 2.Ⅱ期 3.Ⅲ期 4.Ⅳ期 5.不详)")
   private String staging_t;
   @ApiModelProperty("肿瘤分期_N(0.0期  1. Ⅰ期 2.Ⅱ期 3.Ⅲ期 4.Ⅳ期 5.不详)")
   private String staging_n;
   @ApiModelProperty("肿瘤分期_M(0.0期  1. Ⅰ期 2.Ⅱ期 3.Ⅲ期 4.Ⅳ期 5.不详)")
   private String staging_m;
   @ApiModelProperty("肿瘤分期(0.0期  1. Ⅰ期 2.Ⅱ期 3.Ⅲ期 4.Ⅳ期 5.不详)")
   private String staging;
   @ApiModelProperty("术前住院天数")
   private Long preoper_hosp_d;
   @ApiModelProperty("是否发生术后并发症")
   private String is_postoper_comp;
   @ApiModelProperty("是否发生压疮")
   private String is_pressure;
   @ApiModelProperty("压疮等级")
   private String pressure_grade;
   @ApiModelProperty("压疮原因")
   private String pressure_caus;
   @ApiModelProperty("压疮部位")
   private String pressure_site;
   @ApiModelProperty("是否跌倒/坠床")
   private String is_fall;
   @ApiModelProperty("跌倒等级")
   private String fall_grade;
   @ApiModelProperty("跌倒原因")
   private String fall_caus;
   @ApiModelProperty("输液情况")
   private String is_infuse;
   @ApiModelProperty("输液反应")
   private String is_infuse_reac;
   @ApiModelProperty("是否随诊")
   private String is_follow_up;
   @ApiModelProperty("随诊期限-年")
   private String follow_interval_y;
   @ApiModelProperty("随诊期限-月")
   private String follow_interval_m;
   @ApiModelProperty("随诊期限-天")
   private String follow_interval_d;
   @ApiModelProperty("使用抗菌药物")
   private String is_use_anti;
   @ApiModelProperty("抗菌药物用药目的(1治疗性用药、2预防性用药）")
   private String use_anti_purp;
   @ApiModelProperty("抗菌药物用药方案")
   private String use_anti_sche;
   @ApiModelProperty("抗菌药物使用天数")
   private Long use_anti_days;
   @ApiModelProperty("特级护理天数")
   private Long spr_care_d;
   @ApiModelProperty("一级护理天")
   private Long pri_care_d;
   @ApiModelProperty("二级护理天数")
   private Long sec_care_d;
   @ApiModelProperty("三级护理天数")
   private Long ter_care_d;
   @ApiModelProperty("疑难病历")
   private String is_diff_record;
   @ApiModelProperty("急症病历")
   private String is_eme_record;
   @ApiModelProperty("科研病历")
   private String is_res_record;
   @ApiModelProperty("主要诊断依据编码")
   private String dia_bas_code;
   @ApiModelProperty("分化程度代码")
   private String diff_pro_code;
   @ApiModelProperty("分化程度名称")
   private String diff_pro_name;
   @ApiModelProperty("手术类型 0.非手术患者  1.急诊手术   2.择期手术")
   private String ope_type;
   @ApiModelProperty("本院第一例")
   private String first_record;
   @ApiModelProperty("HBsAg")
   private String hbsag;
   @ApiModelProperty("HCV-Ab")
   private String hcv_ab;
   @ApiModelProperty("HIV-Ab")
   private String hiv_ab;
   @ApiModelProperty("输血反应1.无  2.有")
   private String transfusion_reaction;
   @ApiModelProperty("输红细胞量(单位)")
   private BigDecimal red_blood_cell;
   @ApiModelProperty("输血小板量(袋)")
   private BigDecimal platelet;
   @ApiModelProperty("输血浆量(单位)")
   private BigDecimal plasma;
   @ApiModelProperty("输全血量(单位)")
   private BigDecimal whole_blood;
   @ApiModelProperty("自体回收(ml)")
   private BigDecimal self_bolld;
   @ApiModelProperty("输其它血制品量(ml)")
   private BigDecimal other_blood;
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

   public String getIs_cri_record() {
      return this.is_cri_record;
   }

   public void setIs_cri_record(String is_cri_record) {
      this.is_cri_record = is_cri_record;
   }

   public String getInhos_cond_cd() {
      return this.inhos_cond_cd;
   }

   public void setInhos_cond_cd(String inhos_cond_cd) {
      this.inhos_cond_cd = inhos_cond_cd;
   }

   public String getInhos_cond() {
      return this.inhos_cond;
   }

   public void setInhos_cond(String inhos_cond) {
      this.inhos_cond = inhos_cond;
   }

   public Date getDia_date() {
      return this.dia_date;
   }

   public void setDia_date(Date dia_date) {
      this.dia_date = dia_date;
   }

   public String getOut_dis_dia() {
      return this.out_dis_dia;
   }

   public void setOut_dis_dia(String out_dis_dia) {
      this.out_dis_dia = out_dis_dia;
   }

   public String getAdm_dis_dia() {
      return this.adm_dis_dia;
   }

   public void setAdm_dis_dia(String adm_dis_dia) {
      this.adm_dis_dia = adm_dis_dia;
   }

   public String getPre_pos_dia() {
      return this.pre_pos_dia;
   }

   public void setPre_pos_dia(String pre_pos_dia) {
      this.pre_pos_dia = pre_pos_dia;
   }

   public String getClin_path_dia() {
      return this.clin_path_dia;
   }

   public void setClin_path_dia(String clin_path_dia) {
      this.clin_path_dia = clin_path_dia;
   }

   public String getMed_path_dia() {
      return this.med_path_dia;
   }

   public void setMed_path_dia(String med_path_dia) {
      this.med_path_dia = med_path_dia;
   }

   public String getPre_path_dia() {
      return this.pre_path_dia;
   }

   public void setPre_path_dia(String pre_path_dia) {
      this.pre_path_dia = pre_path_dia;
   }

   public String getDia_eff() {
      return this.dia_eff;
   }

   public void setDia_eff(String dia_eff) {
      this.dia_eff = dia_eff;
   }

   public String getDia_bas_name() {
      return this.dia_bas_name;
   }

   public void setDia_bas_name(String dia_bas_name) {
      this.dia_bas_name = dia_bas_name;
   }

   public String getReadm_flag() {
      return this.readm_flag;
   }

   public void setReadm_flag(String readm_flag) {
      this.readm_flag = readm_flag;
   }

   public Long getVentilator_d() {
      return this.ventilator_d;
   }

   public void setVentilator_d(Long ventilator_d) {
      this.ventilator_d = ventilator_d;
   }

   public Long getVentilator_h() {
      return this.ventilator_h;
   }

   public void setVentilator_h(Long ventilator_h) {
      this.ventilator_h = ventilator_h;
   }

   public Long getVentilator_mi() {
      return this.ventilator_mi;
   }

   public void setVentilator_mi(Long ventilator_mi) {
      this.ventilator_mi = ventilator_mi;
   }

   public Long getAdl_inhos() {
      return this.adl_inhos;
   }

   public void setAdl_inhos(Long adl_inhos) {
      this.adl_inhos = adl_inhos;
   }

   public Long getAdl_outhos() {
      return this.adl_outhos;
   }

   public void setAdl_outhos(Long adl_outhos) {
      this.adl_outhos = adl_outhos;
   }

   public Long getSave_times() {
      return this.save_times;
   }

   public void setSave_times(Long save_times) {
      this.save_times = save_times;
   }

   public Long getSave_success_times() {
      return this.save_success_times;
   }

   public void setSave_success_times(Long save_success_times) {
      this.save_success_times = save_success_times;
   }

   public String getIscp() {
      return this.iscp;
   }

   public void setIscp(String iscp) {
      this.iscp = iscp;
   }

   public String getStaging_t() {
      return this.staging_t;
   }

   public void setStaging_t(String staging_t) {
      this.staging_t = staging_t;
   }

   public String getStaging_n() {
      return this.staging_n;
   }

   public void setStaging_n(String staging_n) {
      this.staging_n = staging_n;
   }

   public String getStaging_m() {
      return this.staging_m;
   }

   public void setStaging_m(String staging_m) {
      this.staging_m = staging_m;
   }

   public String getStaging() {
      return this.staging;
   }

   public void setStaging(String staging) {
      this.staging = staging;
   }

   public Long getPreoper_hosp_d() {
      return this.preoper_hosp_d;
   }

   public void setPreoper_hosp_d(Long preoper_hosp_d) {
      this.preoper_hosp_d = preoper_hosp_d;
   }

   public String getIs_postoper_comp() {
      return this.is_postoper_comp;
   }

   public void setIs_postoper_comp(String is_postoper_comp) {
      this.is_postoper_comp = is_postoper_comp;
   }

   public String getIs_pressure() {
      return this.is_pressure;
   }

   public void setIs_pressure(String is_pressure) {
      this.is_pressure = is_pressure;
   }

   public String getPressure_grade() {
      return this.pressure_grade;
   }

   public void setPressure_grade(String pressure_grade) {
      this.pressure_grade = pressure_grade;
   }

   public String getPressure_caus() {
      return this.pressure_caus;
   }

   public void setPressure_caus(String pressure_caus) {
      this.pressure_caus = pressure_caus;
   }

   public String getPressure_site() {
      return this.pressure_site;
   }

   public void setPressure_site(String pressure_site) {
      this.pressure_site = pressure_site;
   }

   public String getIs_fall() {
      return this.is_fall;
   }

   public void setIs_fall(String is_fall) {
      this.is_fall = is_fall;
   }

   public String getFall_grade() {
      return this.fall_grade;
   }

   public void setFall_grade(String fall_grade) {
      this.fall_grade = fall_grade;
   }

   public String getFall_caus() {
      return this.fall_caus;
   }

   public void setFall_caus(String fall_caus) {
      this.fall_caus = fall_caus;
   }

   public String getIs_infuse() {
      return this.is_infuse;
   }

   public void setIs_infuse(String is_infuse) {
      this.is_infuse = is_infuse;
   }

   public String getIs_infuse_reac() {
      return this.is_infuse_reac;
   }

   public void setIs_infuse_reac(String is_infuse_reac) {
      this.is_infuse_reac = is_infuse_reac;
   }

   public String getIs_follow_up() {
      return this.is_follow_up;
   }

   public void setIs_follow_up(String is_follow_up) {
      this.is_follow_up = is_follow_up;
   }

   public String getFollow_interval_y() {
      return this.follow_interval_y;
   }

   public void setFollow_interval_y(String follow_interval_y) {
      this.follow_interval_y = follow_interval_y;
   }

   public String getFollow_interval_m() {
      return this.follow_interval_m;
   }

   public void setFollow_interval_m(String follow_interval_m) {
      this.follow_interval_m = follow_interval_m;
   }

   public String getFollow_interval_d() {
      return this.follow_interval_d;
   }

   public void setFollow_interval_d(String follow_interval_d) {
      this.follow_interval_d = follow_interval_d;
   }

   public String getIs_use_anti() {
      return this.is_use_anti;
   }

   public void setIs_use_anti(String is_use_anti) {
      this.is_use_anti = is_use_anti;
   }

   public String getUse_anti_purp() {
      return this.use_anti_purp;
   }

   public void setUse_anti_purp(String use_anti_purp) {
      this.use_anti_purp = use_anti_purp;
   }

   public String getUse_anti_sche() {
      return this.use_anti_sche;
   }

   public void setUse_anti_sche(String use_anti_sche) {
      this.use_anti_sche = use_anti_sche;
   }

   public Long getUse_anti_days() {
      return this.use_anti_days;
   }

   public void setUse_anti_days(Long use_anti_days) {
      this.use_anti_days = use_anti_days;
   }

   public Long getSpr_care_d() {
      return this.spr_care_d;
   }

   public void setSpr_care_d(Long spr_care_d) {
      this.spr_care_d = spr_care_d;
   }

   public Long getPri_care_d() {
      return this.pri_care_d;
   }

   public void setPri_care_d(Long pri_care_d) {
      this.pri_care_d = pri_care_d;
   }

   public Long getSec_care_d() {
      return this.sec_care_d;
   }

   public void setSec_care_d(Long sec_care_d) {
      this.sec_care_d = sec_care_d;
   }

   public Long getTer_care_d() {
      return this.ter_care_d;
   }

   public void setTer_care_d(Long ter_care_d) {
      this.ter_care_d = ter_care_d;
   }

   public String getIs_diff_record() {
      return this.is_diff_record;
   }

   public void setIs_diff_record(String is_diff_record) {
      this.is_diff_record = is_diff_record;
   }

   public String getIs_eme_record() {
      return this.is_eme_record;
   }

   public void setIs_eme_record(String is_eme_record) {
      this.is_eme_record = is_eme_record;
   }

   public String getIs_res_record() {
      return this.is_res_record;
   }

   public void setIs_res_record(String is_res_record) {
      this.is_res_record = is_res_record;
   }

   public String getDia_bas_code() {
      return this.dia_bas_code;
   }

   public void setDia_bas_code(String dia_bas_code) {
      this.dia_bas_code = dia_bas_code;
   }

   public String getDiff_pro_code() {
      return this.diff_pro_code;
   }

   public void setDiff_pro_code(String diff_pro_code) {
      this.diff_pro_code = diff_pro_code;
   }

   public String getDiff_pro_name() {
      return this.diff_pro_name;
   }

   public void setDiff_pro_name(String diff_pro_name) {
      this.diff_pro_name = diff_pro_name;
   }

   public String getOpe_type() {
      return this.ope_type;
   }

   public void setOpe_type(String ope_type) {
      this.ope_type = ope_type;
   }

   public String getFirst_record() {
      return this.first_record;
   }

   public void setFirst_record(String first_record) {
      this.first_record = first_record;
   }

   public String getHbsag() {
      return this.hbsag;
   }

   public void setHbsag(String hbsag) {
      this.hbsag = hbsag;
   }

   public String getHcv_ab() {
      return this.hcv_ab;
   }

   public void setHcv_ab(String hcv_ab) {
      this.hcv_ab = hcv_ab;
   }

   public String getHiv_ab() {
      return this.hiv_ab;
   }

   public void setHiv_ab(String hiv_ab) {
      this.hiv_ab = hiv_ab;
   }

   public String getTransfusion_reaction() {
      return this.transfusion_reaction;
   }

   public void setTransfusion_reaction(String transfusion_reaction) {
      this.transfusion_reaction = transfusion_reaction;
   }

   public Long getInvasive_ventilator_d() {
      return this.invasive_ventilator_d;
   }

   public void setInvasive_ventilator_d(Long invasive_ventilator_d) {
      this.invasive_ventilator_d = invasive_ventilator_d;
   }

   public Long getInvasive_ventilator_h() {
      return this.invasive_ventilator_h;
   }

   public void setInvasive_ventilator_h(Long invasive_ventilator_h) {
      this.invasive_ventilator_h = invasive_ventilator_h;
   }

   public Long getInvasive_ventilator_mi() {
      return this.invasive_ventilator_mi;
   }

   public void setInvasive_ventilator_mi(Long invasive_ventilator_mi) {
      this.invasive_ventilator_mi = invasive_ventilator_mi;
   }

   public BigDecimal getRed_blood_cell() {
      return this.red_blood_cell;
   }

   public void setRed_blood_cell(BigDecimal red_blood_cell) {
      this.red_blood_cell = red_blood_cell;
   }

   public BigDecimal getPlatelet() {
      return this.platelet;
   }

   public void setPlatelet(BigDecimal platelet) {
      this.platelet = platelet;
   }

   public BigDecimal getPlasma() {
      return this.plasma;
   }

   public void setPlasma(BigDecimal plasma) {
      this.plasma = plasma;
   }

   public BigDecimal getWhole_blood() {
      return this.whole_blood;
   }

   public void setWhole_blood(BigDecimal whole_blood) {
      this.whole_blood = whole_blood;
   }

   public BigDecimal getSelf_bolld() {
      return this.self_bolld;
   }

   public void setSelf_bolld(BigDecimal self_bolld) {
      this.self_bolld = self_bolld;
   }

   public BigDecimal getOther_blood() {
      return this.other_blood;
   }

   public void setOther_blood(BigDecimal other_blood) {
      this.other_blood = other_blood;
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
