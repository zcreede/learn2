package com.emr.project.mrhp.domain.mris;

import com.emr.common.constant.CommonConstants;
import com.emr.project.pat.domain.Transinfo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(
   value = "TdCmHpVo",
   description = "病案详情信息"
)
public class TdCmHpLineVo {
   @ApiModelProperty(
      value = "提取标志:1已提取、0未提取",
      required = true
   )
   private String extractFlag;
   @ApiModelProperty("诊断信息-中医")
   private List tdCmDiagZyList = new ArrayList();
   @ApiModelProperty("诊断信息-西医")
   private List tdCmDiagXyList = new ArrayList();
   @ApiModelProperty("手术信息")
   private List tdCmOperList = new ArrayList();
   @ApiModelProperty("重症信息")
   private List tdCmIcuList = new ArrayList();
   private List tmCmQcLists;
   private Boolean obsDeptFlag;
   private String weight;
   private String signPic;
   private List transinfoList;
   private Long visit_id;
   private List emrQcListList;
   @ApiModelProperty("病案id")
   private String record_id;
   @ApiModelProperty("病案号")
   private String record_no;
   private String patient_id;
   @ApiModelProperty("住院号")
   private String inp_no;
   @ApiModelProperty("住院次数")
   private Integer visit_num;
   private Integer visit_times;
   @ApiModelProperty("XY：西医病案；ZY：中医病案")
   private String mr_type;
   @ApiModelProperty("医疗机构代码")
   private String org_cd;
   @ApiModelProperty("医疗机构名称")
   private String org_name;
   @ApiModelProperty("医疗费用支付方式代码")
   private String pay_type_cd;
   @ApiModelProperty("医疗费用支付方式名称")
   private String pay_type_name;
   @ApiModelProperty("健康卡号")
   private String hc_no;
   @ApiModelProperty("患者姓名")
   private String patient_name;
   @ApiModelProperty("患者性别代码")
   private String sex_cd;
   @ApiModelProperty("患者性别")
   private String sex_name;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @ApiModelProperty("出生日期")
   private Date bir_date;
   @ApiModelProperty("年龄岁")
   private Integer age_y;
   @ApiModelProperty("年龄月")
   private Integer age_m;
   @ApiModelProperty("年龄日")
   private Integer age_d;
   @ApiModelProperty("年龄时")
   private Integer age_h;
   @ApiModelProperty("年龄分")
   private Integer age_mi;
   @ApiModelProperty("年龄")
   private String age;
   @ApiModelProperty("新生儿出生体重(g)")
   private Long baby1_bir_weight;
   @ApiModelProperty("宝二出生体重")
   private Long baby2_bir_weight;
   @ApiModelProperty("宝三出生体重")
   private Long baby3_bir_weight;
   @ApiModelProperty("宝四出生体重")
   private Long baby4_bir_weight;
   @ApiModelProperty("新生儿入院体重(g)")
   private Long inhos_weight;
   @ApiModelProperty("出生地-省（自治区、直辖市）")
   private String bir_addr_pro;
   @ApiModelProperty("出生地-市（地区、州）")
   private String bir_addr_plagty;
   @ApiModelProperty("出生地-县（区）")
   private String bir_addr_cou;
   @ApiModelProperty("籍贯-省（自治区、直辖市）")
   private String ap_addr_pro;
   @ApiModelProperty("籍贯-市（地区、州）")
   private String ap_addr_plagty;
   @ApiModelProperty("籍贯")
   private String ap_addr;
   @ApiModelProperty("国籍")
   private String citizenship;
   @ApiModelProperty("国籍代码")
   private String citizenship_cd;
   @ApiModelProperty("民族")
   private String nation;
   @ApiModelProperty("民族代码")
   private String nation_cd;
   @ApiModelProperty("证件类型名称")
   private String card_type_name;
   @ApiModelProperty("证件类型代码")
   private String card_type_cd;
   @ApiModelProperty("身份证件类型编号")
   private String card_type_no;
   @ApiModelProperty("职业类别代码")
   private String pro_type_cd;
   @ApiModelProperty("职业类别名称")
   private String pro_type_name;
   @ApiModelProperty("职业说明")
   private String pro_type_rem;
   @ApiModelProperty("婚姻状况")
   private String mar_sta;
   @ApiModelProperty("婚姻状况代码")
   private String mar_sta_cd;
   @ApiModelProperty("现住址-省（自治区、直辖市）")
   private String now_addr_pro;
   @ApiModelProperty("现住址-市（地区、州）")
   private String now_addr_flagty;
   @ApiModelProperty("现住址-县（区）")
   private String now_addr_cou;
   @ApiModelProperty("现住址")
   private String now_addr;
   @ApiModelProperty("现住址电话")
   private String now_addr_tel;
   @ApiModelProperty("现住址-邮政编码")
   private String now_addr_post;
   @ApiModelProperty(" 户口地址-省（自治区、直辖市）")
   private String rpr_addr_pro;
   @ApiModelProperty("户口地址-市（地区、州）")
   private String rpr_addr_flagty;
   @ApiModelProperty("户口地址-县（区）")
   private String rpr_addr_cou;
   @ApiModelProperty("户口地址")
   private String rpr_addr;
   @ApiModelProperty("户口地址-邮政编码")
   private String prp_addr_post;
   @ApiModelProperty("工作单位-省（自治区、直辖市）")
   private String work_addr_pro;
   @ApiModelProperty("工作单位-市（地区、州）")
   private String work_addr_flagty;
   @ApiModelProperty("工作单位-县（区）")
   private String work_addr_cou;
   @ApiModelProperty("工作单位及地址")
   private String work_name_addr;
   @ApiModelProperty("工作单位电话")
   private String work_tel;
   @ApiModelProperty("工作单位邮编")
   private String work_post;
   @ApiModelProperty("联系人姓名")
   private String cont_name;
   @ApiModelProperty("联系人与患者的关系代码")
   private String con_rela_cd;
   @ApiModelProperty("联系人与患者的关系名称")
   private String con_rela_name;
   @ApiModelProperty("联系人与患者的关系补充说明")
   private String con_rela_rem;
   @ApiModelProperty("联系人地址-省（自治区、直辖市）")
   private String cont_addr_pro;
   @ApiModelProperty("联系人地址-市（地区、州）")
   private String cont_addr_flagty;
   @ApiModelProperty("联系人地址-县（区）")
   private String cont_addr_cou;
   @ApiModelProperty("联系人地址")
   private String cont_addr;
   @ApiModelProperty("联系人电话号码")
   private String cont_tel;
   @ApiModelProperty("入院途径:1急诊；2门诊；3其他医疗机构转入；9其他")
   private String in_way;
   @ApiModelProperty("转诊医疗机构（来源）")
   private String ref_org_name;
   @ApiModelProperty("治疗类别(1.中医(1.1中医，1.2民族医) ；2.中西医；3.西医)")
   private String treat_type;
   @ApiModelProperty("入院日期时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date inhos_time;
   @ApiModelProperty("入院科室代码")
   private String in_dept_cd;
   @ApiModelProperty("入院科室名称")
   private String in_dept_name;
   @ApiModelProperty("入院病房")
   private String in_room_no;
   private String ch_dept_no;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @ApiModelProperty("出院日期")
   private Date out_time;
   @ApiModelProperty("科室编码")
   private String out_dept_cd;
   @ApiModelProperty("科室")
   private String out_dept_name;
   @ApiModelProperty("出院病房")
   private String out_room_no;
   @ApiModelProperty("实际住院天数")
   private Long real_inhos_days;
   @ApiModelProperty("门（急）诊诊断（中医诊断）名称")
   private String os_chd_name;
   @ApiModelProperty("门（急）诊诊断（中医诊断）病名编码")
   private String os_chd_cd;
   @ApiModelProperty("门（急）诊诊断（西医诊断）名称")
   private String os_wmd_name;
   @ApiModelProperty("门（急）诊诊断（西医诊断）疾病编码")
   private String os_wmd_cd;
   @ApiModelProperty("损伤中毒的外部原因")
   private String harm_or;
   @ApiModelProperty("损伤中毒的外部原因疾病编码")
   private String harm_or_cd;
   @ApiModelProperty("实施临床路径类别（1.中医；2.西医；3.否）")
   private String cp_type;
   @ApiModelProperty("使用医疗机构中药制剂标志")
   private String med_prep_flag;
   @ApiModelProperty("使用中医诊疗设备标志")
   private String cm_equi_flag;
   @ApiModelProperty("使用中医诊疗技术标志")
   private String cm_tech_flag;
   @ApiModelProperty("辩证施护标志")
   private String plead_flag;
   @ApiModelProperty("药物过敏标志")
   private String drug_alle_flag;
   @ApiModelProperty("过敏药物")
   private String alle_drug;
   @ApiModelProperty("死亡患者尸检标志")
   private Long test_flag;
   @ApiModelProperty("ABO血型")
   private String abo_name;
   @ApiModelProperty("ABO血型代码")
   private String abo_cd;
   @ApiModelProperty("RH血型")
   private String rh_name;
   @ApiModelProperty("Rh血型代码")
   private String rh_cd;
   @ApiModelProperty("科主任编码")
   private String hd_cd;
   @ApiModelProperty("科主任姓名")
   private String hd_name;
   @ApiModelProperty("主任医师编码")
   private String arc_doc_cd;
   @ApiModelProperty("主任医师姓名")
   private String arc_doc_name;
   @ApiModelProperty("主治医师编码")
   private String att_doc_cd;
   @ApiModelProperty("科室")
   private String att_doc_name;
   @ApiModelProperty("住院医师编码")
   private String res_doc_cd;
   @ApiModelProperty("住院医师姓名")
   private String res_doc_name;
   @ApiModelProperty("责任护士编码")
   private String duty_nur_name;
   @ApiModelProperty("责任护士姓名")
   private String duty_nur_cd;
   @ApiModelProperty("进修医师编码")
   private String stu_doc_cd;
   @ApiModelProperty("进修医师签名")
   private String stu_doc_name;
   @ApiModelProperty("实习医师编码")
   private String int_doc_cd;
   @ApiModelProperty("实习医师姓名")
   private String int_doc_name;
   @ApiModelProperty("编码员编码")
   private String coder_cd;
   @ApiModelProperty("编码员姓名")
   private String coder_name;
   @ApiModelProperty("病案质量代码")
   private String mrq_cd;
   @ApiModelProperty("质控医师编码")
   private String qcon_doc_cd;
   @ApiModelProperty("质控医师姓名")
   private String qcon_doc_name;
   @ApiModelProperty("质控护士编码")
   private String qcon_nur_cd;
   @ApiModelProperty("质控日期")
   private String qcon_nur_name;
   @ApiModelProperty("质控日期")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date qcon_date;
   @ApiModelProperty("离院方式代码（1.医嘱离院 2.医嘱转院 3.医嘱转社区卫生服务机构/乡镇卫生院 4.非医嘱离院 5.死亡 9.其他)")
   private String leave_way_cd;
   @ApiModelProperty("转入医疗机构名称")
   private String in_org_name;
   @ApiModelProperty("出院31天内再住院标志")
   private String out_in_flag;
   @ApiModelProperty("出院31天内再住院目的")
   private String out_in_aim;
   @ApiModelProperty("颅脑损伤患者入院前昏迷时间的天数")
   private Long ci_bef_day;
   @ApiModelProperty("颅脑损伤患者入院前昏迷时间的小时数")
   private Long ci_bef_hour;
   @ApiModelProperty("颅脑损伤患者入院前昏迷时间的分钟数")
   private Long ci_bef_min;
   @ApiModelProperty("颅脑损伤患者入院后昏迷时间的天数")
   private Long ci_aft_day;
   @ApiModelProperty("颅脑损伤患者入院后昏迷时间的小时数")
   private Long ci_aft_hour;
   @ApiModelProperty("颅脑损伤患者入院后昏迷时间的分钟数")
   private Long ci_aft_min;
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
   @ApiModelProperty("转科科别名称（内一→普外→耳鼻喉）")
   private String ch_dept_name;
   @ApiModelProperty("患者性别")
   private String sex;
   @ApiModelProperty("病案状态(01:暂存；02:保存；03：签名）")
   private String mrState;
   @ApiModelProperty("转科记录id")
   private String ch_dept_id;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date last_finish_time;
   @ApiModelProperty("最迟完成时间对应的时效规则ID")
   private Long last_finish_ruleid;
   public String no_card_reason;
   @ApiModelProperty("门（急）诊诊断（中医诊断）名称——临床医师")
   private String os_chd_clin_cd;
   @ApiModelProperty("门（急）诊诊断（中医诊断）病名编码——临床医师")
   private String os_chd_clin_name;
   @ApiModelProperty("门（急）诊诊断（西医诊断）疾病编码——临床医师")
   private String os_wmd_clin_cd;
   @ApiModelProperty("门（急）诊诊断（西医诊断）名称")
   private String os_wmd_clin_name;
   @ApiModelProperty("入院诊断编码")
   private String ad_diag_cd;
   @ApiModelProperty("入院诊断名称")
   private String ad_diag_name;
   @ApiModelProperty("入院诊断- -临床医师")
   private String ad_diag_clin_cd;
   @ApiModelProperty("入院诊断名称- -临床医师")
   private String ad_diag_clin_name;
   @ApiModelProperty("入院诊断编码-中医")
   private String ad_diag_tcm_cd;
   @ApiModelProperty("入院诊断名称-中医")
   private String ad_diag_tcm_name;
   @ApiModelProperty("入院诊断- -临床医师-中医")
   private String ad_diag_tcm_clin_cd;
   @ApiModelProperty("入院诊断名称- -临床医师-中医")
   private String ad_diag_tcm_clin_name;
   @ApiModelProperty("外部损伤和中毒原因代码")
   private String injury_cause_cd;
   @ApiModelProperty("外部损伤和中毒原因名称")
   private String injury_cause_name;
   @ApiModelProperty("外部损伤和中毒原因代码——临床医师")
   private String injury_cause_clin_cd;
   @ApiModelProperty("外部损伤和中毒原因名称——临床医师")
   private String injury_cause_clin_name;
   @ApiModelProperty("病理诊断代码")
   private String patho_dia_cd;
   @ApiModelProperty("病理诊断名称")
   private String patho_dia_name;
   @ApiModelProperty("病理诊断代码——临床医师")
   private String patho_dia_clin_cd;
   @ApiModelProperty("病理诊断名称——临床医师")
   private String patho_dia_clin_name;
   @ApiModelProperty("病理号")
   private String patho_no;
   @ApiModelProperty("录入客户端IP地址")
   private String client_ip;
   @ApiModelProperty("提取时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date extract_date;
   @ApiModelProperty("归档时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date file_time;
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
   @ApiModelProperty("呼吸机使用时间--天")
   private Long ventilator_d;
   @ApiModelProperty("呼吸机使用时间--时")
   private Long ventilator_h;
   @ApiModelProperty("呼吸机使用时间--分")
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
   @ApiModelProperty("总费用")
   private BigDecimal zfy;
   @ApiModelProperty("自付金额")
   private BigDecimal zfje;
   @ApiModelProperty("一般医疗服务费")
   private BigDecimal ybylfwf;
   @ApiModelProperty("一般治疗操作费")
   private BigDecimal ybzlczf;
   @ApiModelProperty("护理费")
   private BigDecimal hlf;
   @ApiModelProperty("其他费用")
   private BigDecimal qtfy;
   @ApiModelProperty("病理诊断费")
   private BigDecimal blzdf;
   @ApiModelProperty("实验室诊断费")
   private BigDecimal syszdf;
   @ApiModelProperty("影像学诊断费")
   private BigDecimal yxxzdf;
   @ApiModelProperty("临床诊断项目费")
   private BigDecimal lczdxmf;
   @ApiModelProperty("非手术治疗项目费")
   private BigDecimal fsszlxmf;
   @ApiModelProperty("手术治疗费")
   private BigDecimal sszlf;
   @ApiModelProperty("康复费")
   private BigDecimal kff;
   @ApiModelProperty("中医治疗费")
   private BigDecimal zyzlf;
   @ApiModelProperty("中医诊断")
   private BigDecimal zyzd;
   @ApiModelProperty("中医其他")
   private BigDecimal zyqt;
   @ApiModelProperty("西药费")
   private BigDecimal xyf;
   @ApiModelProperty("中成药费")
   private BigDecimal zcyf;
   @ApiModelProperty("中草药费")
   private BigDecimal zcyf_zcy;
   @ApiModelProperty("血费")
   private BigDecimal xf;
   @ApiModelProperty("白蛋白类制品费")
   private BigDecimal bdblzpf;
   @ApiModelProperty("球蛋白类制品费")
   private BigDecimal qdblzpf;
   @ApiModelProperty("凝血因子类制品费")
   private BigDecimal nxyzlzpf;
   @ApiModelProperty("细胞因子类制品费")
   private BigDecimal xbyzlzpf;
   @ApiModelProperty("检查用一次性医用材料费")
   private BigDecimal jcyycxyyclf;
   @ApiModelProperty("治疗用一次性医用材料费")
   private BigDecimal zlyycxyyclf;
   @ApiModelProperty("手术用一次性医用材料费")
   private BigDecimal ssyycxyyclf;
   @ApiModelProperty("其他费")
   private BigDecimal qtf;
   @ApiModelProperty("中医辨证论治费")
   private BigDecimal zybzlzf;
   @ApiModelProperty("中医辨证论治会诊费")
   private BigDecimal zybzlzhzf;
   @ApiModelProperty("临床物理治疗费")
   private BigDecimal lcwlzlf;
   @ApiModelProperty("麻醉费")
   private BigDecimal mzf;
   @ApiModelProperty("手术费")
   private BigDecimal ssf;
   @ApiModelProperty("中医外治")
   private BigDecimal zywz;
   @ApiModelProperty("中医骨伤")
   private BigDecimal zygs;
   @ApiModelProperty("针刺与灸法")
   private BigDecimal zcyjf;
   @ApiModelProperty("中医推拿治疗")
   private BigDecimal zytzl;
   @ApiModelProperty("中医肛肠治疗")
   private BigDecimal zygczl;
   @ApiModelProperty("中医特殊治疗")
   private BigDecimal zytszl;
   @ApiModelProperty("中药特殊调配加工")
   private BigDecimal zytstpjg;
   @ApiModelProperty("辨证施膳")
   private BigDecimal bzss;
   @ApiModelProperty("抗菌药物费用")
   private BigDecimal kjywfy;
   @ApiModelProperty("医疗机构中药制剂费")
   private BigDecimal yljgzyzjf;
   @ApiModelProperty("详细出生地")
   private String bir_addr_detail;
   @ApiModelProperty("详细籍贯")
   private String ap_addr_detail;
   @ApiModelProperty("详细现住址")
   private String now_addr_detail;
   @ApiModelProperty("详细户口地址")
   private String rpr_addr_detail;
   @ApiModelProperty("详细工作单位地址")
   private String work_addr_detail;
   @ApiModelProperty("详细联系人地址")
   private String cont_addr_detail;

   public TdCmHpLineVo() {
      this.obsDeptFlag = CommonConstants.BOOL_FALSE;
      this.transinfoList = new ArrayList();
      this.zfy = BigDecimal.ZERO;
      this.zfje = BigDecimal.ZERO;
      this.ybylfwf = BigDecimal.ZERO;
      this.ybzlczf = BigDecimal.ZERO;
      this.hlf = BigDecimal.ZERO;
      this.qtfy = BigDecimal.ZERO;
      this.blzdf = BigDecimal.ZERO;
      this.syszdf = BigDecimal.ZERO;
      this.yxxzdf = BigDecimal.ZERO;
      this.lczdxmf = BigDecimal.ZERO;
      this.fsszlxmf = BigDecimal.ZERO;
      this.sszlf = BigDecimal.ZERO;
      this.kff = BigDecimal.ZERO;
      this.zyzlf = BigDecimal.ZERO;
      this.zyzd = BigDecimal.ZERO;
      this.zyqt = BigDecimal.ZERO;
      this.xyf = BigDecimal.ZERO;
      this.zcyf = BigDecimal.ZERO;
      this.zcyf_zcy = BigDecimal.ZERO;
      this.xf = BigDecimal.ZERO;
      this.bdblzpf = BigDecimal.ZERO;
      this.qdblzpf = BigDecimal.ZERO;
      this.nxyzlzpf = BigDecimal.ZERO;
      this.xbyzlzpf = BigDecimal.ZERO;
      this.jcyycxyyclf = BigDecimal.ZERO;
      this.zlyycxyyclf = BigDecimal.ZERO;
      this.ssyycxyyclf = BigDecimal.ZERO;
      this.qtf = BigDecimal.ZERO;
      this.zybzlzf = BigDecimal.ZERO;
      this.zybzlzhzf = BigDecimal.ZERO;
      this.lcwlzlf = BigDecimal.ZERO;
      this.mzf = BigDecimal.ZERO;
      this.ssf = BigDecimal.ZERO;
      this.zywz = BigDecimal.ZERO;
      this.zygs = BigDecimal.ZERO;
      this.zcyjf = BigDecimal.ZERO;
      this.zytzl = BigDecimal.ZERO;
      this.zygczl = BigDecimal.ZERO;
      this.zytszl = BigDecimal.ZERO;
      this.zytstpjg = BigDecimal.ZERO;
      this.bzss = BigDecimal.ZERO;
      this.kjywfy = BigDecimal.ZERO;
      this.yljgzyzjf = BigDecimal.ZERO;
   }

   public String getAd_diag_tcm_cd() {
      return this.ad_diag_tcm_cd;
   }

   public void setAd_diag_tcm_cd(String ad_diag_tcm_cd) {
      this.ad_diag_tcm_cd = ad_diag_tcm_cd;
   }

   public String getAd_diag_tcm_name() {
      return this.ad_diag_tcm_name;
   }

   public void setAd_diag_tcm_name(String ad_diag_tcm_name) {
      this.ad_diag_tcm_name = ad_diag_tcm_name;
   }

   public String getAd_diag_tcm_clin_cd() {
      return this.ad_diag_tcm_clin_cd;
   }

   public void setAd_diag_tcm_clin_cd(String ad_diag_tcm_clin_cd) {
      this.ad_diag_tcm_clin_cd = ad_diag_tcm_clin_cd;
   }

   public String getAd_diag_tcm_clin_name() {
      return this.ad_diag_tcm_clin_name;
   }

   public void setAd_diag_tcm_clin_name(String ad_diag_tcm_clin_name) {
      this.ad_diag_tcm_clin_name = ad_diag_tcm_clin_name;
   }

   public String getBir_addr_detail() {
      return this.bir_addr_detail;
   }

   public void setBir_addr_detail(String bir_addr_detail) {
      this.bir_addr_detail = bir_addr_detail;
   }

   public String getAp_addr_detail() {
      return this.ap_addr_detail;
   }

   public void setAp_addr_detail(String ap_addr_detail) {
      this.ap_addr_detail = ap_addr_detail;
   }

   public String getNow_addr_detail() {
      return this.now_addr_detail;
   }

   public void setNow_addr_detail(String now_addr_detail) {
      this.now_addr_detail = now_addr_detail;
   }

   public String getRpr_addr_detail() {
      return this.rpr_addr_detail;
   }

   public void setRpr_addr_detail(String rpr_addr_detail) {
      this.rpr_addr_detail = rpr_addr_detail;
   }

   public String getWork_addr_detail() {
      return this.work_addr_detail;
   }

   public void setWork_addr_detail(String work_addr_detail) {
      this.work_addr_detail = work_addr_detail;
   }

   public String getCont_addr_detail() {
      return this.cont_addr_detail;
   }

   public void setCont_addr_detail(String cont_addr_detail) {
      this.cont_addr_detail = cont_addr_detail;
   }

   public BigDecimal getZfy() {
      return this.zfy;
   }

   public void setZfy(BigDecimal zfy) {
      this.zfy = zfy;
   }

   public BigDecimal getZfje() {
      return this.zfje;
   }

   public void setZfje(BigDecimal zfje) {
      this.zfje = zfje;
   }

   public BigDecimal getYbylfwf() {
      return this.ybylfwf;
   }

   public void setYbylfwf(BigDecimal ybylfwf) {
      this.ybylfwf = ybylfwf;
   }

   public BigDecimal getYbzlczf() {
      return this.ybzlczf;
   }

   public void setYbzlczf(BigDecimal ybzlczf) {
      this.ybzlczf = ybzlczf;
   }

   public BigDecimal getHlf() {
      return this.hlf;
   }

   public void setHlf(BigDecimal hlf) {
      this.hlf = hlf;
   }

   public BigDecimal getQtfy() {
      return this.qtfy;
   }

   public void setQtfy(BigDecimal qtfy) {
      this.qtfy = qtfy;
   }

   public BigDecimal getBlzdf() {
      return this.blzdf;
   }

   public void setBlzdf(BigDecimal blzdf) {
      this.blzdf = blzdf;
   }

   public BigDecimal getSyszdf() {
      return this.syszdf;
   }

   public void setSyszdf(BigDecimal syszdf) {
      this.syszdf = syszdf;
   }

   public BigDecimal getYxxzdf() {
      return this.yxxzdf;
   }

   public void setYxxzdf(BigDecimal yxxzdf) {
      this.yxxzdf = yxxzdf;
   }

   public BigDecimal getLczdxmf() {
      return this.lczdxmf;
   }

   public void setLczdxmf(BigDecimal lczdxmf) {
      this.lczdxmf = lczdxmf;
   }

   public BigDecimal getFsszlxmf() {
      return this.fsszlxmf;
   }

   public void setFsszlxmf(BigDecimal fsszlxmf) {
      this.fsszlxmf = fsszlxmf;
   }

   public BigDecimal getSszlf() {
      return this.sszlf;
   }

   public void setSszlf(BigDecimal sszlf) {
      this.sszlf = sszlf;
   }

   public BigDecimal getKff() {
      return this.kff;
   }

   public void setKff(BigDecimal kff) {
      this.kff = kff;
   }

   public BigDecimal getZyzlf() {
      return this.zyzlf;
   }

   public void setZyzlf(BigDecimal zyzlf) {
      this.zyzlf = zyzlf;
   }

   public BigDecimal getZyzd() {
      return this.zyzd;
   }

   public void setZyzd(BigDecimal zyzd) {
      this.zyzd = zyzd;
   }

   public BigDecimal getZyqt() {
      return this.zyqt;
   }

   public void setZyqt(BigDecimal zyqt) {
      this.zyqt = zyqt;
   }

   public BigDecimal getXyf() {
      return this.xyf;
   }

   public void setXyf(BigDecimal xyf) {
      this.xyf = xyf;
   }

   public BigDecimal getZcyf() {
      return this.zcyf;
   }

   public void setZcyf(BigDecimal zcyf) {
      this.zcyf = zcyf;
   }

   public BigDecimal getZcyf_zcy() {
      return this.zcyf_zcy;
   }

   public void setZcyf_zcy(BigDecimal zcyf_zcy) {
      this.zcyf_zcy = zcyf_zcy;
   }

   public BigDecimal getXf() {
      return this.xf;
   }

   public void setXf(BigDecimal xf) {
      this.xf = xf;
   }

   public BigDecimal getBdblzpf() {
      return this.bdblzpf;
   }

   public void setBdblzpf(BigDecimal bdblzpf) {
      this.bdblzpf = bdblzpf;
   }

   public BigDecimal getQdblzpf() {
      return this.qdblzpf;
   }

   public void setQdblzpf(BigDecimal qdblzpf) {
      this.qdblzpf = qdblzpf;
   }

   public BigDecimal getNxyzlzpf() {
      return this.nxyzlzpf;
   }

   public void setNxyzlzpf(BigDecimal nxyzlzpf) {
      this.nxyzlzpf = nxyzlzpf;
   }

   public BigDecimal getXbyzlzpf() {
      return this.xbyzlzpf;
   }

   public void setXbyzlzpf(BigDecimal xbyzlzpf) {
      this.xbyzlzpf = xbyzlzpf;
   }

   public BigDecimal getJcyycxyyclf() {
      return this.jcyycxyyclf;
   }

   public void setJcyycxyyclf(BigDecimal jcyycxyyclf) {
      this.jcyycxyyclf = jcyycxyyclf;
   }

   public BigDecimal getZlyycxyyclf() {
      return this.zlyycxyyclf;
   }

   public void setZlyycxyyclf(BigDecimal zlyycxyyclf) {
      this.zlyycxyyclf = zlyycxyyclf;
   }

   public BigDecimal getSsyycxyyclf() {
      return this.ssyycxyyclf;
   }

   public void setSsyycxyyclf(BigDecimal ssyycxyyclf) {
      this.ssyycxyyclf = ssyycxyyclf;
   }

   public BigDecimal getQtf() {
      return this.qtf;
   }

   public void setQtf(BigDecimal qtf) {
      this.qtf = qtf;
   }

   public BigDecimal getZybzlzf() {
      return this.zybzlzf;
   }

   public void setZybzlzf(BigDecimal zybzlzf) {
      this.zybzlzf = zybzlzf;
   }

   public BigDecimal getZybzlzhzf() {
      return this.zybzlzhzf;
   }

   public void setZybzlzhzf(BigDecimal zybzlzhzf) {
      this.zybzlzhzf = zybzlzhzf;
   }

   public BigDecimal getLcwlzlf() {
      return this.lcwlzlf;
   }

   public void setLcwlzlf(BigDecimal lcwlzlf) {
      this.lcwlzlf = lcwlzlf;
   }

   public BigDecimal getMzf() {
      return this.mzf;
   }

   public void setMzf(BigDecimal mzf) {
      this.mzf = mzf;
   }

   public BigDecimal getSsf() {
      return this.ssf;
   }

   public void setSsf(BigDecimal ssf) {
      this.ssf = ssf;
   }

   public BigDecimal getZywz() {
      return this.zywz;
   }

   public void setZywz(BigDecimal zywz) {
      this.zywz = zywz;
   }

   public BigDecimal getZygs() {
      return this.zygs;
   }

   public void setZygs(BigDecimal zygs) {
      this.zygs = zygs;
   }

   public BigDecimal getZcyjf() {
      return this.zcyjf;
   }

   public void setZcyjf(BigDecimal zcyjf) {
      this.zcyjf = zcyjf;
   }

   public BigDecimal getZytzl() {
      return this.zytzl;
   }

   public void setZytzl(BigDecimal zytzl) {
      this.zytzl = zytzl;
   }

   public BigDecimal getZygczl() {
      return this.zygczl;
   }

   public void setZygczl(BigDecimal zygczl) {
      this.zygczl = zygczl;
   }

   public BigDecimal getZytszl() {
      return this.zytszl;
   }

   public void setZytszl(BigDecimal zytszl) {
      this.zytszl = zytszl;
   }

   public BigDecimal getZytstpjg() {
      return this.zytstpjg;
   }

   public void setZytstpjg(BigDecimal zytstpjg) {
      this.zytstpjg = zytstpjg;
   }

   public BigDecimal getBzss() {
      return this.bzss;
   }

   public void setBzss(BigDecimal bzss) {
      this.bzss = bzss;
   }

   public BigDecimal getKjywfy() {
      return this.kjywfy;
   }

   public void setKjywfy(BigDecimal kjywfy) {
      this.kjywfy = kjywfy;
   }

   public BigDecimal getYljgzyzjf() {
      return this.yljgzyzjf;
   }

   public void setYljgzyzjf(BigDecimal yljgzyzjf) {
      this.yljgzyzjf = yljgzyzjf;
   }

   public List getTdCmDiagZyList() {
      return this.tdCmDiagZyList;
   }

   public void setTdCmDiagZyList(List tdCmDiagZyList) {
      this.tdCmDiagZyList = tdCmDiagZyList;
   }

   public List getTdCmDiagXyList() {
      return this.tdCmDiagXyList;
   }

   public void setTdCmDiagXyList(List tdCmDiagXyList) {
      this.tdCmDiagXyList = tdCmDiagXyList;
   }

   public List getTdCmOperList() {
      return this.tdCmOperList;
   }

   public void setTdCmOperList(List tdCmOperList) {
      this.tdCmOperList = tdCmOperList;
   }

   public List getTdCmIcuList() {
      return this.tdCmIcuList;
   }

   public void setTdCmIcuList(List tdCmIcuList) {
      this.tdCmIcuList = tdCmIcuList;
   }

   public String getRecord_id() {
      return this.record_id;
   }

   public void setRecord_id(String record_id) {
      this.record_id = record_id;
   }

   public String getRecord_no() {
      return this.record_no;
   }

   public void setRecord_no(String record_no) {
      this.record_no = record_no;
   }

   public String getPatient_id() {
      return this.patient_id;
   }

   public void setPatient_id(String patient_id) {
      this.patient_id = patient_id;
   }

   public String getInp_no() {
      return this.inp_no;
   }

   public void setInp_no(String inp_no) {
      this.inp_no = inp_no;
   }

   public Integer getVisit_num() {
      return this.visit_num;
   }

   public void setVisit_num(Integer visit_num) {
      this.visit_num = visit_num;
   }

   public Integer getVisit_times() {
      return this.visit_times;
   }

   public void setVisit_times(Integer visit_times) {
      this.visit_times = visit_times;
   }

   public String getMr_type() {
      return this.mr_type;
   }

   public void setMr_type(String mr_type) {
      this.mr_type = mr_type;
   }

   public String getOrg_cd() {
      return this.org_cd;
   }

   public void setOrg_cd(String org_cd) {
      this.org_cd = org_cd;
   }

   public String getOrg_name() {
      return this.org_name;
   }

   public void setOrg_name(String org_name) {
      this.org_name = org_name;
   }

   public String getPay_type_cd() {
      return this.pay_type_cd;
   }

   public void setPay_type_cd(String pay_type_cd) {
      this.pay_type_cd = pay_type_cd;
   }

   public String getPay_type_name() {
      return this.pay_type_name;
   }

   public void setPay_type_name(String pay_type_name) {
      this.pay_type_name = pay_type_name;
   }

   public String getHc_no() {
      return this.hc_no;
   }

   public void setHc_no(String hc_no) {
      this.hc_no = hc_no;
   }

   public String getPatient_name() {
      return this.patient_name;
   }

   public void setPatient_name(String patient_name) {
      this.patient_name = patient_name;
   }

   public String getSex_cd() {
      return this.sex_cd;
   }

   public void setSex_cd(String sex_cd) {
      this.sex_cd = sex_cd;
   }

   public String getSex_name() {
      return this.sex_name;
   }

   public void setSex_name(String sex_name) {
      this.sex_name = sex_name;
   }

   public Date getBir_date() {
      return this.bir_date;
   }

   public void setBir_date(Date bir_date) {
      this.bir_date = bir_date;
   }

   public Integer getAge_y() {
      return this.age_y;
   }

   public void setAge_y(Integer age_y) {
      this.age_y = age_y;
   }

   public Integer getAge_m() {
      return this.age_m;
   }

   public void setAge_m(Integer age_m) {
      this.age_m = age_m;
   }

   public Integer getAge_d() {
      return this.age_d;
   }

   public void setAge_d(Integer age_d) {
      this.age_d = age_d;
   }

   public Integer getAge_h() {
      return this.age_h;
   }

   public void setAge_h(Integer age_h) {
      this.age_h = age_h;
   }

   public Integer getAge_mi() {
      return this.age_mi;
   }

   public void setAge_mi(Integer age_mi) {
      this.age_mi = age_mi;
   }

   public Long getBaby1_bir_weight() {
      return this.baby1_bir_weight;
   }

   public void setBaby1_bir_weight(Long baby1_bir_weight) {
      this.baby1_bir_weight = baby1_bir_weight;
   }

   public Long getBaby2_bir_weight() {
      return this.baby2_bir_weight;
   }

   public void setBaby2_bir_weight(Long baby2_bir_weight) {
      this.baby2_bir_weight = baby2_bir_weight;
   }

   public Long getBaby3_bir_weight() {
      return this.baby3_bir_weight;
   }

   public void setBaby3_bir_weight(Long baby3_bir_weight) {
      this.baby3_bir_weight = baby3_bir_weight;
   }

   public Long getBaby4_bir_weight() {
      return this.baby4_bir_weight;
   }

   public void setBaby4_bir_weight(Long baby4_bir_weight) {
      this.baby4_bir_weight = baby4_bir_weight;
   }

   public Long getInhos_weight() {
      return this.inhos_weight;
   }

   public void setInhos_weight(Long inhos_weight) {
      this.inhos_weight = inhos_weight;
   }

   public String getBir_addr_pro() {
      return this.bir_addr_pro;
   }

   public void setBir_addr_pro(String bir_addr_pro) {
      this.bir_addr_pro = bir_addr_pro;
   }

   public String getBir_addr_plagty() {
      return this.bir_addr_plagty;
   }

   public void setBir_addr_plagty(String bir_addr_plagty) {
      this.bir_addr_plagty = bir_addr_plagty;
   }

   public String getBir_addr_cou() {
      return this.bir_addr_cou;
   }

   public void setBir_addr_cou(String bir_addr_cou) {
      this.bir_addr_cou = bir_addr_cou;
   }

   public String getAp_addr_pro() {
      return this.ap_addr_pro;
   }

   public void setAp_addr_pro(String ap_addr_pro) {
      this.ap_addr_pro = ap_addr_pro;
   }

   public String getAp_addr_plagty() {
      return this.ap_addr_plagty;
   }

   public void setAp_addr_plagty(String ap_addr_plagty) {
      this.ap_addr_plagty = ap_addr_plagty;
   }

   public String getCitizenship() {
      return this.citizenship;
   }

   public void setCitizenship(String citizenship) {
      this.citizenship = citizenship;
   }

   public String getCitizenship_cd() {
      return this.citizenship_cd;
   }

   public void setCitizenship_cd(String citizenship_cd) {
      this.citizenship_cd = citizenship_cd;
   }

   public String getNation() {
      return this.nation;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getNation_cd() {
      return this.nation_cd;
   }

   public void setNation_cd(String nation_cd) {
      this.nation_cd = nation_cd;
   }

   public String getCard_type_name() {
      return this.card_type_name;
   }

   public void setCard_type_name(String card_type_name) {
      this.card_type_name = card_type_name;
   }

   public String getCard_type_cd() {
      return this.card_type_cd;
   }

   public void setCard_type_cd(String card_type_cd) {
      this.card_type_cd = card_type_cd;
   }

   public String getCard_type_no() {
      return this.card_type_no;
   }

   public void setCard_type_no(String card_type_no) {
      this.card_type_no = card_type_no;
   }

   public String getPro_type_cd() {
      return this.pro_type_cd;
   }

   public void setPro_type_cd(String pro_type_cd) {
      this.pro_type_cd = pro_type_cd;
   }

   public String getPro_type_name() {
      return this.pro_type_name;
   }

   public void setPro_type_name(String pro_type_name) {
      this.pro_type_name = pro_type_name;
   }

   public String getPro_type_rem() {
      return this.pro_type_rem;
   }

   public void setPro_type_rem(String pro_type_rem) {
      this.pro_type_rem = pro_type_rem;
   }

   public String getMar_sta() {
      return this.mar_sta;
   }

   public void setMar_sta(String mar_sta) {
      this.mar_sta = mar_sta;
   }

   public String getMar_sta_cd() {
      return this.mar_sta_cd;
   }

   public void setMar_sta_cd(String mar_sta_cd) {
      this.mar_sta_cd = mar_sta_cd;
   }

   public String getNow_addr_pro() {
      return this.now_addr_pro;
   }

   public void setNow_addr_pro(String now_addr_pro) {
      this.now_addr_pro = now_addr_pro;
   }

   public String getNow_addr_flagty() {
      return this.now_addr_flagty;
   }

   public void setNow_addr_flagty(String now_addr_flagty) {
      this.now_addr_flagty = now_addr_flagty;
   }

   public String getNow_addr_cou() {
      return this.now_addr_cou;
   }

   public void setNow_addr_cou(String now_addr_cou) {
      this.now_addr_cou = now_addr_cou;
   }

   public String getNow_addr() {
      return this.now_addr;
   }

   public void setNow_addr(String now_addr) {
      this.now_addr = now_addr;
   }

   public String getNow_addr_tel() {
      return this.now_addr_tel;
   }

   public void setNow_addr_tel(String now_addr_tel) {
      this.now_addr_tel = now_addr_tel;
   }

   public String getNow_addr_post() {
      return this.now_addr_post;
   }

   public void setNow_addr_post(String now_addr_post) {
      this.now_addr_post = now_addr_post;
   }

   public String getRpr_addr_pro() {
      return this.rpr_addr_pro;
   }

   public void setRpr_addr_pro(String rpr_addr_pro) {
      this.rpr_addr_pro = rpr_addr_pro;
   }

   public String getRpr_addr_flagty() {
      return this.rpr_addr_flagty;
   }

   public void setRpr_addr_flagty(String rpr_addr_flagty) {
      this.rpr_addr_flagty = rpr_addr_flagty;
   }

   public String getRpr_addr_cou() {
      return this.rpr_addr_cou;
   }

   public void setRpr_addr_cou(String rpr_addr_cou) {
      this.rpr_addr_cou = rpr_addr_cou;
   }

   public String getRpr_addr() {
      return this.rpr_addr;
   }

   public void setRpr_addr(String rpr_addr) {
      this.rpr_addr = rpr_addr;
   }

   public String getPrp_addr_post() {
      return this.prp_addr_post;
   }

   public void setPrp_addr_post(String prp_addr_post) {
      this.prp_addr_post = prp_addr_post;
   }

   public String getWork_addr_pro() {
      return this.work_addr_pro;
   }

   public void setWork_addr_pro(String work_addr_pro) {
      this.work_addr_pro = work_addr_pro;
   }

   public String getWork_addr_flagty() {
      return this.work_addr_flagty;
   }

   public void setWork_addr_flagty(String work_addr_flagty) {
      this.work_addr_flagty = work_addr_flagty;
   }

   public String getWork_addr_cou() {
      return this.work_addr_cou;
   }

   public void setWork_addr_cou(String work_addr_cou) {
      this.work_addr_cou = work_addr_cou;
   }

   public String getWork_name_addr() {
      return this.work_name_addr;
   }

   public void setWork_name_addr(String work_name_addr) {
      this.work_name_addr = work_name_addr;
   }

   public String getWork_tel() {
      return this.work_tel;
   }

   public void setWork_tel(String work_tel) {
      this.work_tel = work_tel;
   }

   public String getWork_post() {
      return this.work_post;
   }

   public void setWork_post(String work_post) {
      this.work_post = work_post;
   }

   public String getCont_name() {
      return this.cont_name;
   }

   public void setCont_name(String cont_name) {
      this.cont_name = cont_name;
   }

   public String getCon_rela_cd() {
      return this.con_rela_cd;
   }

   public void setCon_rela_cd(String con_rela_cd) {
      this.con_rela_cd = con_rela_cd;
   }

   public String getCon_rela_name() {
      return this.con_rela_name;
   }

   public void setCon_rela_name(String con_rela_name) {
      this.con_rela_name = con_rela_name;
   }

   public String getCon_rela_rem() {
      return this.con_rela_rem;
   }

   public void setCon_rela_rem(String con_rela_rem) {
      this.con_rela_rem = con_rela_rem;
   }

   public String getCont_addr_pro() {
      return this.cont_addr_pro;
   }

   public void setCont_addr_pro(String cont_addr_pro) {
      this.cont_addr_pro = cont_addr_pro;
   }

   public String getCont_addr_flagty() {
      return this.cont_addr_flagty;
   }

   public void setCont_addr_flagty(String cont_addr_flagty) {
      this.cont_addr_flagty = cont_addr_flagty;
   }

   public String getCont_addr_cou() {
      return this.cont_addr_cou;
   }

   public void setCont_addr_cou(String cont_addr_cou) {
      this.cont_addr_cou = cont_addr_cou;
   }

   public String getCont_addr() {
      return this.cont_addr;
   }

   public void setCont_addr(String cont_addr) {
      this.cont_addr = cont_addr;
   }

   public String getCont_tel() {
      return this.cont_tel;
   }

   public void setCont_tel(String cont_tel) {
      this.cont_tel = cont_tel;
   }

   public String getIn_way() {
      return this.in_way;
   }

   public void setIn_way(String in_way) {
      this.in_way = in_way;
   }

   public String getRef_org_name() {
      return this.ref_org_name;
   }

   public void setRef_org_name(String ref_org_name) {
      this.ref_org_name = ref_org_name;
   }

   public String getTreat_type() {
      return this.treat_type;
   }

   public void setTreat_type(String treat_type) {
      this.treat_type = treat_type;
   }

   public Date getInhos_time() {
      return this.inhos_time;
   }

   public void setInhos_time(Date inhos_time) {
      this.inhos_time = inhos_time;
   }

   public String getIn_dept_cd() {
      return this.in_dept_cd;
   }

   public void setIn_dept_cd(String in_dept_cd) {
      this.in_dept_cd = in_dept_cd;
   }

   public String getIn_dept_name() {
      return this.in_dept_name;
   }

   public void setIn_dept_name(String in_dept_name) {
      this.in_dept_name = in_dept_name;
   }

   public String getIn_room_no() {
      return this.in_room_no;
   }

   public void setIn_room_no(String in_room_no) {
      this.in_room_no = in_room_no;
   }

   public String getCh_dept_no() {
      return this.ch_dept_no;
   }

   public void setCh_dept_no(String ch_dept_no) {
      this.ch_dept_no = ch_dept_no;
   }

   public Date getOut_time() {
      return this.out_time;
   }

   public void setOut_time(Date out_time) {
      this.out_time = out_time;
   }

   public String getOut_dept_cd() {
      return this.out_dept_cd;
   }

   public void setOut_dept_cd(String out_dept_cd) {
      this.out_dept_cd = out_dept_cd;
   }

   public String getOut_dept_name() {
      return this.out_dept_name;
   }

   public void setOut_dept_name(String out_dept_name) {
      this.out_dept_name = out_dept_name;
   }

   public String getOut_room_no() {
      return this.out_room_no;
   }

   public void setOut_room_no(String out_room_no) {
      this.out_room_no = out_room_no;
   }

   public Long getReal_inhos_days() {
      return this.real_inhos_days;
   }

   public void setReal_inhos_days(Long real_inhos_days) {
      this.real_inhos_days = real_inhos_days;
   }

   public String getOs_chd_name() {
      return this.os_chd_name;
   }

   public void setOs_chd_name(String os_chd_name) {
      this.os_chd_name = os_chd_name;
   }

   public String getOs_chd_cd() {
      return this.os_chd_cd;
   }

   public void setOs_chd_cd(String os_chd_cd) {
      this.os_chd_cd = os_chd_cd;
   }

   public String getOs_wmd_name() {
      return this.os_wmd_name;
   }

   public void setOs_wmd_name(String os_wmd_name) {
      this.os_wmd_name = os_wmd_name;
   }

   public String getOs_wmd_cd() {
      return this.os_wmd_cd;
   }

   public void setOs_wmd_cd(String os_wmd_cd) {
      this.os_wmd_cd = os_wmd_cd;
   }

   public String getHarm_or() {
      return this.harm_or;
   }

   public void setHarm_or(String harm_or) {
      this.harm_or = harm_or;
   }

   public String getHarm_or_cd() {
      return this.harm_or_cd;
   }

   public void setHarm_or_cd(String harm_or_cd) {
      this.harm_or_cd = harm_or_cd;
   }

   public String getCp_type() {
      return this.cp_type;
   }

   public void setCp_type(String cp_type) {
      this.cp_type = cp_type;
   }

   public String getMed_prep_flag() {
      return this.med_prep_flag;
   }

   public void setMed_prep_flag(String med_prep_flag) {
      this.med_prep_flag = med_prep_flag;
   }

   public String getCm_equi_flag() {
      return this.cm_equi_flag;
   }

   public void setCm_equi_flag(String cm_equi_flag) {
      this.cm_equi_flag = cm_equi_flag;
   }

   public String getCm_tech_flag() {
      return this.cm_tech_flag;
   }

   public void setCm_tech_flag(String cm_tech_flag) {
      this.cm_tech_flag = cm_tech_flag;
   }

   public String getPlead_flag() {
      return this.plead_flag;
   }

   public void setPlead_flag(String plead_flag) {
      this.plead_flag = plead_flag;
   }

   public String getDrug_alle_flag() {
      return this.drug_alle_flag;
   }

   public void setDrug_alle_flag(String drug_alle_flag) {
      this.drug_alle_flag = drug_alle_flag;
   }

   public String getAlle_drug() {
      return this.alle_drug;
   }

   public void setAlle_drug(String alle_drug) {
      this.alle_drug = alle_drug;
   }

   public Long getTest_flag() {
      return this.test_flag;
   }

   public void setTest_flag(Long test_flag) {
      this.test_flag = test_flag;
   }

   public String getAbo_name() {
      return this.abo_name;
   }

   public void setAbo_name(String abo_name) {
      this.abo_name = abo_name;
   }

   public String getAbo_cd() {
      return this.abo_cd;
   }

   public void setAbo_cd(String abo_cd) {
      this.abo_cd = abo_cd;
   }

   public String getRh_name() {
      return this.rh_name;
   }

   public void setRh_name(String rh_name) {
      this.rh_name = rh_name;
   }

   public String getRh_cd() {
      return this.rh_cd;
   }

   public void setRh_cd(String rh_cd) {
      this.rh_cd = rh_cd;
   }

   public String getHd_cd() {
      return this.hd_cd;
   }

   public void setHd_cd(String hd_cd) {
      this.hd_cd = hd_cd;
   }

   public String getHd_name() {
      return this.hd_name;
   }

   public void setHd_name(String hd_name) {
      this.hd_name = hd_name;
   }

   public String getArc_doc_cd() {
      return this.arc_doc_cd;
   }

   public void setArc_doc_cd(String arc_doc_cd) {
      this.arc_doc_cd = arc_doc_cd;
   }

   public String getArc_doc_name() {
      return this.arc_doc_name;
   }

   public void setArc_doc_name(String arc_doc_name) {
      this.arc_doc_name = arc_doc_name;
   }

   public String getAtt_doc_cd() {
      return this.att_doc_cd;
   }

   public void setAtt_doc_cd(String att_doc_cd) {
      this.att_doc_cd = att_doc_cd;
   }

   public String getAtt_doc_name() {
      return this.att_doc_name;
   }

   public void setAtt_doc_name(String att_doc_name) {
      this.att_doc_name = att_doc_name;
   }

   public String getRes_doc_cd() {
      return this.res_doc_cd;
   }

   public void setRes_doc_cd(String res_doc_cd) {
      this.res_doc_cd = res_doc_cd;
   }

   public String getRes_doc_name() {
      return this.res_doc_name;
   }

   public void setRes_doc_name(String res_doc_name) {
      this.res_doc_name = res_doc_name;
   }

   public String getDuty_nur_name() {
      return this.duty_nur_name;
   }

   public void setDuty_nur_name(String duty_nur_name) {
      this.duty_nur_name = duty_nur_name;
   }

   public String getDuty_nur_cd() {
      return this.duty_nur_cd;
   }

   public void setDuty_nur_cd(String duty_nur_cd) {
      this.duty_nur_cd = duty_nur_cd;
   }

   public String getStu_doc_cd() {
      return this.stu_doc_cd;
   }

   public void setStu_doc_cd(String stu_doc_cd) {
      this.stu_doc_cd = stu_doc_cd;
   }

   public String getStu_doc_name() {
      return this.stu_doc_name;
   }

   public void setStu_doc_name(String stu_doc_name) {
      this.stu_doc_name = stu_doc_name;
   }

   public String getInt_doc_cd() {
      return this.int_doc_cd;
   }

   public void setInt_doc_cd(String int_doc_cd) {
      this.int_doc_cd = int_doc_cd;
   }

   public String getInt_doc_name() {
      return this.int_doc_name;
   }

   public void setInt_doc_name(String int_doc_name) {
      this.int_doc_name = int_doc_name;
   }

   public String getCoder_cd() {
      return this.coder_cd;
   }

   public void setCoder_cd(String coder_cd) {
      this.coder_cd = coder_cd;
   }

   public String getCoder_name() {
      return this.coder_name;
   }

   public void setCoder_name(String coder_name) {
      this.coder_name = coder_name;
   }

   public String getMrq_cd() {
      return this.mrq_cd;
   }

   public void setMrq_cd(String mrq_cd) {
      this.mrq_cd = mrq_cd;
   }

   public String getQcon_doc_cd() {
      return this.qcon_doc_cd;
   }

   public void setQcon_doc_cd(String qcon_doc_cd) {
      this.qcon_doc_cd = qcon_doc_cd;
   }

   public String getQcon_doc_name() {
      return this.qcon_doc_name;
   }

   public void setQcon_doc_name(String qcon_doc_name) {
      this.qcon_doc_name = qcon_doc_name;
   }

   public String getQcon_nur_cd() {
      return this.qcon_nur_cd;
   }

   public void setQcon_nur_cd(String qcon_nur_cd) {
      this.qcon_nur_cd = qcon_nur_cd;
   }

   public String getQcon_nur_name() {
      return this.qcon_nur_name;
   }

   public void setQcon_nur_name(String qcon_nur_name) {
      this.qcon_nur_name = qcon_nur_name;
   }

   public Date getQcon_date() {
      return this.qcon_date;
   }

   public void setQcon_date(Date qcon_date) {
      this.qcon_date = qcon_date;
   }

   public String getLeave_way_cd() {
      return this.leave_way_cd;
   }

   public void setLeave_way_cd(String leave_way_cd) {
      this.leave_way_cd = leave_way_cd;
   }

   public String getIn_org_name() {
      return this.in_org_name;
   }

   public void setIn_org_name(String in_org_name) {
      this.in_org_name = in_org_name;
   }

   public String getOut_in_flag() {
      return this.out_in_flag;
   }

   public void setOut_in_flag(String out_in_flag) {
      this.out_in_flag = out_in_flag;
   }

   public String getOut_in_aim() {
      return this.out_in_aim;
   }

   public void setOut_in_aim(String out_in_aim) {
      this.out_in_aim = out_in_aim;
   }

   public Long getCi_bef_day() {
      return this.ci_bef_day;
   }

   public void setCi_bef_day(Long ci_bef_day) {
      this.ci_bef_day = ci_bef_day;
   }

   public Long getCi_bef_hour() {
      return this.ci_bef_hour;
   }

   public void setCi_bef_hour(Long ci_bef_hour) {
      this.ci_bef_hour = ci_bef_hour;
   }

   public Long getCi_bef_min() {
      return this.ci_bef_min;
   }

   public void setCi_bef_min(Long ci_bef_min) {
      this.ci_bef_min = ci_bef_min;
   }

   public Long getCi_aft_day() {
      return this.ci_aft_day;
   }

   public void setCi_aft_day(Long ci_aft_day) {
      this.ci_aft_day = ci_aft_day;
   }

   public Long getCi_aft_hour() {
      return this.ci_aft_hour;
   }

   public void setCi_aft_hour(Long ci_aft_hour) {
      this.ci_aft_hour = ci_aft_hour;
   }

   public Long getCi_aft_min() {
      return this.ci_aft_min;
   }

   public void setCi_aft_min(Long ci_aft_min) {
      this.ci_aft_min = ci_aft_min;
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

   public String getCh_dept_name() {
      return this.ch_dept_name;
   }

   public void setCh_dept_name(String ch_dept_name) {
      this.ch_dept_name = ch_dept_name;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getCh_dept_id() {
      return this.ch_dept_id;
   }

   public void setCh_dept_id(String ch_dept_id) {
      this.ch_dept_id = ch_dept_id;
   }

   public Date getLast_finish_time() {
      return this.last_finish_time;
   }

   public void setLast_finish_time(Date last_finish_time) {
      this.last_finish_time = last_finish_time;
   }

   public Long getLast_finish_ruleid() {
      return this.last_finish_ruleid;
   }

   public void setLast_finish_ruleid(Long last_finish_ruleid) {
      this.last_finish_ruleid = last_finish_ruleid;
   }

   public String getNo_card_reason() {
      return this.no_card_reason;
   }

   public void setNo_card_reason(String no_card_reason) {
      this.no_card_reason = no_card_reason;
   }

   public String getOs_chd_clin_cd() {
      return this.os_chd_clin_cd;
   }

   public void setOs_chd_clin_cd(String os_chd_clin_cd) {
      this.os_chd_clin_cd = os_chd_clin_cd;
   }

   public String getOs_chd_clin_name() {
      return this.os_chd_clin_name;
   }

   public void setOs_chd_clin_name(String os_chd_clin_name) {
      this.os_chd_clin_name = os_chd_clin_name;
   }

   public String getOs_wmd_clin_cd() {
      return this.os_wmd_clin_cd;
   }

   public void setOs_wmd_clin_cd(String os_wmd_clin_cd) {
      this.os_wmd_clin_cd = os_wmd_clin_cd;
   }

   public String getOs_wmd_clin_name() {
      return this.os_wmd_clin_name;
   }

   public void setOs_wmd_clin_name(String os_wmd_clin_name) {
      this.os_wmd_clin_name = os_wmd_clin_name;
   }

   public String getAd_diag_cd() {
      return this.ad_diag_cd;
   }

   public void setAd_diag_cd(String ad_diag_cd) {
      this.ad_diag_cd = ad_diag_cd;
   }

   public String getAd_diag_name() {
      return this.ad_diag_name;
   }

   public void setAd_diag_name(String ad_diag_name) {
      this.ad_diag_name = ad_diag_name;
   }

   public String getAd_diag_clin_cd() {
      return this.ad_diag_clin_cd;
   }

   public void setAd_diag_clin_cd(String ad_diag_clin_cd) {
      this.ad_diag_clin_cd = ad_diag_clin_cd;
   }

   public String getAd_diag_clin_name() {
      return this.ad_diag_clin_name;
   }

   public void setAd_diag_clin_name(String ad_diag_clin_name) {
      this.ad_diag_clin_name = ad_diag_clin_name;
   }

   public String getInjury_cause_cd() {
      return this.injury_cause_cd;
   }

   public void setInjury_cause_cd(String injury_cause_cd) {
      this.injury_cause_cd = injury_cause_cd;
   }

   public String getInjury_cause_name() {
      return this.injury_cause_name;
   }

   public void setInjury_cause_name(String injury_cause_name) {
      this.injury_cause_name = injury_cause_name;
   }

   public String getInjury_cause_clin_cd() {
      return this.injury_cause_clin_cd;
   }

   public void setInjury_cause_clin_cd(String injury_cause_clin_cd) {
      this.injury_cause_clin_cd = injury_cause_clin_cd;
   }

   public String getInjury_cause_clin_name() {
      return this.injury_cause_clin_name;
   }

   public void setInjury_cause_clin_name(String injury_cause_clin_name) {
      this.injury_cause_clin_name = injury_cause_clin_name;
   }

   public String getPatho_dia_cd() {
      return this.patho_dia_cd;
   }

   public void setPatho_dia_cd(String patho_dia_cd) {
      this.patho_dia_cd = patho_dia_cd;
   }

   public String getPatho_dia_name() {
      return this.patho_dia_name;
   }

   public void setPatho_dia_name(String patho_dia_name) {
      this.patho_dia_name = patho_dia_name;
   }

   public String getPatho_dia_clin_cd() {
      return this.patho_dia_clin_cd;
   }

   public void setPatho_dia_clin_cd(String patho_dia_clin_cd) {
      this.patho_dia_clin_cd = patho_dia_clin_cd;
   }

   public String getPatho_dia_clin_name() {
      return this.patho_dia_clin_name;
   }

   public void setPatho_dia_clin_name(String patho_dia_clin_name) {
      this.patho_dia_clin_name = patho_dia_clin_name;
   }

   public String getPatho_no() {
      return this.patho_no;
   }

   public void setPatho_no(String patho_no) {
      this.patho_no = patho_no;
   }

   public String getClient_ip() {
      return this.client_ip;
   }

   public void setClient_ip(String client_ip) {
      this.client_ip = client_ip;
   }

   public Date getExtract_date() {
      return this.extract_date;
   }

   public void setExtract_date(Date extract_date) {
      this.extract_date = extract_date;
   }

   public Date getFile_time() {
      return this.file_time;
   }

   public void setFile_time(Date file_time) {
      this.file_time = file_time;
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

   public List getTmCmQcLists() {
      return this.tmCmQcLists;
   }

   public void setTmCmQcLists(List tmCmQcLists) {
      this.tmCmQcLists = tmCmQcLists;
   }

   public String getExtractFlag() {
      return this.extractFlag;
   }

   public void setExtractFlag(String extractFlag) {
      this.extractFlag = extractFlag;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getAp_addr() {
      return this.ap_addr;
   }

   public void setAp_addr(String ap_addr) {
      this.ap_addr = ap_addr;
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

   public Boolean getObsDeptFlag() {
      return this.obsDeptFlag;
   }

   public void setObsDeptFlag(Boolean obsDeptFlag) {
      this.obsDeptFlag = obsDeptFlag;
   }

   public String getWeight() {
      return this.weight;
   }

   public void setWeight(String weight) {
      this.weight = weight;
   }

   public String getSignPic() {
      return this.signPic;
   }

   public void setSignPic(String signPic) {
      this.signPic = signPic;
   }

   public List getTransinfoList() {
      return this.transinfoList;
   }

   public void setTransinfoList(List transinfoList) {
      this.transinfoList = transinfoList;
   }

   public Long getVisit_id() {
      return this.visit_id;
   }

   public void setVisit_id(Long visit_id) {
      this.visit_id = visit_id;
   }

   public List getEmrQcListList() {
      return this.emrQcListList;
   }

   public void setEmrQcListList(List emrQcListList) {
      this.emrQcListList = emrQcListList;
   }
}
