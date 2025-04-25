package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHp extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String recordId = "";
   @Excel(
      name = "病案号"
   )
   private String recordNo = "";
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId = "";
   @Excel(
      name = "住院号或门诊号"
   )
   private String inpNo = "";
   @Excel(
      name = "住院次数"
   )
   private Long visitId;
   @Excel(
      name = "XY：西医病案；ZY：中医病案"
   )
   private String mrType = "";
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd = "";
   @Excel(
      name = "医疗机构名称"
   )
   private String orgName = "";
   @Excel(
      name = "医疗费用支付方式代码"
   )
   private String payTypeCd = "";
   @Excel(
      name = "医疗费用支付方式名称"
   )
   private String payTypeName = "";
   @Excel(
      name = "健康卡号"
   )
   private String hcNo = "";
   @Excel(
      name = "患者姓名"
   )
   private String patientName = "";
   @Excel(
      name = "患者性别"
   )
   private String sex = "";
   @Excel(
      name = "患者性别代码"
   )
   private String sexCd = "";
   @JsonFormat(
      shape = Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出生日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date birDate;
   @Excel(
      name = "年龄岁"
   )
   private Long ageY;
   @Excel(
      name = "年龄月"
   )
   private Long ageM;
   @Excel(
      name = "年龄日"
   )
   private Long ageD;
   @Excel(
      name = "年龄时"
   )
   private Long ageH;
   @Excel(
      name = "年龄分"
   )
   private Long ageMi;
   @Excel(
      name = "新生儿出生体重(g)"
   )
   private Long baby1BirWeight;
   @Excel(
      name = "宝二出生体重"
   )
   private Long baby2BirWeight;
   @Excel(
      name = "宝三出生体重"
   )
   private Long baby3BirWeight;
   @Excel(
      name = "宝四出生体重"
   )
   private Long baby4BirWeight;
   @Excel(
      name = "新生儿入院体重(g)"
   )
   private Long inhosWeight;
   @Excel(
      name = "出生地-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String birAddrPro = "";
   @Excel(
      name = "出生地-市",
      readConverterExp = "地=区、州"
   )
   private String birAddrPlagty = "";
   @Excel(
      name = "出生地-县",
      readConverterExp = "区="
   )
   private String birAddrCou = "";
   @Excel(
      name = "籍贯-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String apAddrPro = "";
   @Excel(
      name = "籍贯-市",
      readConverterExp = "地=区、州"
   )
   private String apAddrPlagty = "";
   @Excel(
      name = "国籍"
   )
   private String citizenship = "";
   @Excel(
      name = "国籍代码"
   )
   private String citizenshipCd = "";
   @Excel(
      name = "民族"
   )
   private String nation = "";
   @Excel(
      name = "民族代码"
   )
   private String nationCd = "";
   @Excel(
      name = "证件类型名称"
   )
   private String cardTypeName = "";
   @Excel(
      name = "证件类型代码"
   )
   private String cardTypeCd = "";
   @Excel(
      name = "身份证件类型编号"
   )
   private String cardTypeNo = "";
   @Excel(
      name = "职业类别代码"
   )
   private String proTypeCd = "";
   @Excel(
      name = "职业类别名称"
   )
   private String proTypeName = "";
   @Excel(
      name = "职业说明"
   )
   private String proTypeRem = "";
   @Excel(
      name = "婚姻状况"
   )
   private String marSta = "";
   @Excel(
      name = "婚姻状况代码"
   )
   private String marStaCd = "";
   @Excel(
      name = "现住址-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String nowAddrPro = "";
   @Excel(
      name = "现住址-市",
      readConverterExp = "地=区、州"
   )
   private String nowAddrFlagty = "";
   @Excel(
      name = "现住址-县",
      readConverterExp = "区="
   )
   private String nowAddrCou = "";
   @Excel(
      name = "现住址"
   )
   private String nowAddr = "";
   @Excel(
      name = "现住址电话"
   )
   private String nowAddrTel = "";
   @Excel(
      name = "现住址-邮政编码"
   )
   private String nowAddrPost = "";
   @Excel(
      name = "户口地址-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String rprAddrPro = "";
   @Excel(
      name = "户口地址-市",
      readConverterExp = "地=区、州"
   )
   private String rprAddrFlagty = "";
   @Excel(
      name = "户口地址-县",
      readConverterExp = "区="
   )
   private String rprAddrCou = "";
   @Excel(
      name = "户口地址"
   )
   private String rprAddr = "";
   @Excel(
      name = "户口地址-邮政编码"
   )
   private String prpAddrPost = "";
   @Excel(
      name = "工作单位-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String workAddrPro = "";
   @Excel(
      name = "工作单位-市",
      readConverterExp = "地=区、州"
   )
   private String workAddrFlagty = "";
   @Excel(
      name = "工作单位-县",
      readConverterExp = "区="
   )
   private String workAddrCou = "";
   @Excel(
      name = "工作单位及地址"
   )
   private String workNameAddr = "";
   @Excel(
      name = "工作单位电话"
   )
   private String workTel = "";
   @Excel(
      name = "工作单位邮编"
   )
   private String workPost = "";
   @Excel(
      name = "联系人姓名"
   )
   private String contName = "";
   @Excel(
      name = "联系人与患者的关系代码"
   )
   private String conRelaCd = "";
   @Excel(
      name = "联系人与患者的关系名称"
   )
   private String conRelaName = "";
   @Excel(
      name = "联系人与患者的关系补充说明"
   )
   private String conRelaRem = "";
   @Excel(
      name = "联系人地址-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String contAddrPro = "";
   @Excel(
      name = "联系人地址-市",
      readConverterExp = "地=区、州"
   )
   private String contAddrFlagty = "";
   @Excel(
      name = "联系人地址-县",
      readConverterExp = "区="
   )
   private String contAddrCou = "";
   @Excel(
      name = "联系人地址"
   )
   private String contAddr = "";
   @Excel(
      name = "联系人电话号码"
   )
   private String contTel = "";
   @Excel(
      name = "入院途径:1急诊；2门诊；3其他医疗机构转入；9其他"
   )
   private String inWay = "";
   @Excel(
      name = "治疗类别(1.中医(1.1中医，1.2民族医) ；2.中西医；3.西医)"
   )
   private String treatType = "";
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date inhosTime;
   @Excel(
      name = "入院科室代码"
   )
   private String inDeptCd = "";
   @Excel(
      name = "入院科室名称"
   )
   private String inDeptName = "";
   @Excel(
      name = "入院病房"
   )
   private String inRoomNo = "";
   @Excel(
      name = "转科科别",
      readConverterExp = "E=G:126→104→126"
   )
   private String chDeptNo = "";
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date outTime;
   @Excel(
      name = "出院科室代码"
   )
   private String outDeptCd = "";
   @Excel(
      name = "出院科室名称"
   )
   private String outDeptName = "";
   @Excel(
      name = "出院病房"
   )
   private String outRoomNo = "";
   @Excel(
      name = "实际住院天数"
   )
   private Integer realInhosDays;
   @Excel(
      name = "门",
      readConverterExp = "急="
   )
   private String osChdName = "";
   @Excel(
      name = "门",
      readConverterExp = "急="
   )
   private String osChdCd = "";
   @Excel(
      name = "门",
      readConverterExp = "急="
   )
   private String osWmdName = "";
   @Excel(
      name = "门",
      readConverterExp = "急="
   )
   private String osWmdCd = "";
   @Excel(
      name = "实施临床路径类别",
      readConverterExp = "1=.中医；2.西医；3.否"
   )
   private String cpType = "";
   @Excel(
      name = "使用医疗机构中药制剂标志"
   )
   private Long medPrepFlag;
   @Excel(
      name = "使用中医诊疗设备标志"
   )
   private Long cmEquiFlag;
   @Excel(
      name = "使用中医诊疗技术标志"
   )
   private Long cmTechFlag;
   @Excel(
      name = "辩证施护标志"
   )
   private Long pleadFlag;
   @Excel(
      name = "损伤中毒的外部原因"
   )
   private String harmOr = "";
   @Excel(
      name = "损伤中毒的外部原因疾病编码"
   )
   private String harmOrCd = "";
   @Excel(
      name = "病理诊断名称"
   )
   private String patDiaName = "";
   @Excel(
      name = "病理诊断疾病编码"
   )
   private String patDiaCd = "";
   @Excel(
      name = "病理号"
   )
   private String patNo = "";
   @Excel(
      name = "药物过敏标志"
   )
   private Long drugAlleFlag;
   @Excel(
      name = "过敏药物"
   )
   private String alleDrug;
   @Excel(
      name = "死亡患者尸检标志"
   )
   private Long testFlag;
   @Excel(
      name = "ABO血型"
   )
   private String aboName = "";
   @Excel(
      name = "ABO血型代码"
   )
   private String aboCd = "";
   @Excel(
      name = "RH血型"
   )
   private String rhName = "";
   @Excel(
      name = "Rh血型代码"
   )
   private String rhCd = "";
   @Excel(
      name = "科主任编码"
   )
   private String hdCd = "";
   @Excel(
      name = "科主任姓名"
   )
   private String hdName = "";
   @Excel(
      name = "主任医师编码"
   )
   private String arcDocCd = "";
   @Excel(
      name = "主任医师姓名"
   )
   private String arcDocName = "";
   @Excel(
      name = "主治医师编码"
   )
   private String attDocCd = "";
   @Excel(
      name = "主治医师姓名"
   )
   private String attDocName = "";
   @Excel(
      name = "住院医师编码"
   )
   private String resDocCd = "";
   @Excel(
      name = "住院医师姓名"
   )
   private String resDocName = "";
   @Excel(
      name = "责任护士姓名"
   )
   private String dutyNurName = "";
   @Excel(
      name = "责任护士编码"
   )
   private String dutyNurCd = "";
   @Excel(
      name = "进修医师编码"
   )
   private String stuDocCd = "";
   @Excel(
      name = "进修医师签名"
   )
   private String stuDocName = "";
   @Excel(
      name = "实习医师编码"
   )
   private String intDocCd = "";
   @Excel(
      name = "实习医师姓名"
   )
   private String intDocName = "";
   @Excel(
      name = "编码员编码"
   )
   private String coderCd = "";
   @Excel(
      name = "编码员姓名"
   )
   private String coderName = "";
   @Excel(
      name = "病案质量代码"
   )
   private String mrqCd = "";
   @Excel(
      name = "质控医师编码"
   )
   private String qconDocCd = "";
   @Excel(
      name = "质控医师姓名"
   )
   private String qconDocName = "";
   @Excel(
      name = "质控护士编码"
   )
   private String qconNurCd = "";
   @Excel(
      name = "质控护士姓名"
   )
   private String qconNurName = "";
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "质控日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date qconDate;
   @Excel(
      name = "离院方式代码",
      readConverterExp = "离院方式代码（1.医嘱离院 2.医嘱转院 3.医嘱转社区卫生服务机构/乡镇卫生院 4.非医嘱离院 5.死亡 9.其他)"
   )
   private String leaveWayCd = "";
   @Excel(
      name = "转入医疗机构名称"
   )
   private String inOrgName = "";
   @Excel(
      name = "出院31天内再住院标志"
   )
   private Long outInFlag;
   @Excel(
      name = "出院31天内再住院目的"
   )
   private String outInAim = "";
   @Excel(
      name = "颅脑损伤患者入院前昏迷时间的天数"
   )
   private Long ciBefDay;
   @Excel(
      name = "颅脑损伤患者入院前昏迷时间的小时数"
   )
   private Long ciBefHour;
   @Excel(
      name = "颅脑损伤患者入院前昏迷时间的分钟数"
   )
   private Long ciBefMin;
   @Excel(
      name = "颅脑损伤患者入院后昏迷时间的天数"
   )
   private Long ciAftDay;
   @Excel(
      name = "颅脑损伤患者入院后昏迷时间的小时数"
   )
   private Long ciAftHour;
   @Excel(
      name = "颅脑损伤患者入院后昏迷时间的分钟数"
   )
   private Long ciAftMin;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode = "";
   @Excel(
      name = "创建人名称"
   )
   private String crePerName = "";
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date creDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode = "";
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName = "";
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date altDate;
   @Excel(
      name = "病案状态(01:暂存；02:保存；03：提交）"
   )
   private String mrState = "";
   @Excel(
      name = "转科科别名称（内一→普外→耳鼻喉）"
   )
   private String chDeptName = "";
   @Excel(
      name = "转科记录id"
   )
   private String chDeptId = "";
   @Excel(
      name = "最迟完成时间"
   )
   private Date lastFinishTime;
   @Excel(
      name = "最迟完成时间对应的时效规则ID"
   )
   private Long lastFinishRuleId;

   public Date getLastFinishTime() {
      return this.lastFinishTime;
   }

   public void setLastFinishTime(Date lastFinishTime) {
      this.lastFinishTime = lastFinishTime;
   }

   public Long getLastFinishRuleId() {
      return this.lastFinishRuleId;
   }

   public void setLastFinishRuleId(Long lastFinishRuleId) {
      this.lastFinishRuleId = lastFinishRuleId;
   }

   public String getChDeptId() {
      return this.chDeptId;
   }

   public void setChDeptId(String chDeptId) {
      this.chDeptId = chDeptId;
   }

   public String getChDeptName() {
      return this.chDeptName;
   }

   public void setChDeptName(String chDeptName) {
      this.chDeptName = chDeptName;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setVisitId(Long visitId) {
      this.visitId = visitId;
   }

   public Long getVisitId() {
      return this.visitId;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public void setPayTypeCd(String payTypeCd) {
      this.payTypeCd = payTypeCd;
   }

   public String getPayTypeCd() {
      return this.payTypeCd;
   }

   public void setPayTypeName(String payTypeName) {
      this.payTypeName = payTypeName;
   }

   public String getPayTypeName() {
      return this.payTypeName;
   }

   public void setHcNo(String hcNo) {
      this.hcNo = hcNo;
   }

   public String getHcNo() {
      return this.hcNo;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setBirDate(Date birDate) {
      this.birDate = birDate;
   }

   public Date getBirDate() {
      return this.birDate;
   }

   public void setAgeY(Long ageY) {
      this.ageY = ageY;
   }

   public Long getAgeY() {
      return this.ageY;
   }

   public void setAgeM(Long ageM) {
      this.ageM = ageM;
   }

   public Long getAgeM() {
      return this.ageM;
   }

   public void setAgeD(Long ageD) {
      this.ageD = ageD;
   }

   public Long getAgeD() {
      return this.ageD;
   }

   public void setAgeH(Long ageH) {
      this.ageH = ageH;
   }

   public Long getAgeH() {
      return this.ageH;
   }

   public void setAgeMi(Long ageMi) {
      this.ageMi = ageMi;
   }

   public Long getAgeMi() {
      return this.ageMi;
   }

   public void setBaby1BirWeight(Long baby1BirWeight) {
      this.baby1BirWeight = baby1BirWeight;
   }

   public Long getBaby1BirWeight() {
      return this.baby1BirWeight;
   }

   public void setBaby2BirWeight(Long baby2BirWeight) {
      this.baby2BirWeight = baby2BirWeight;
   }

   public Long getBaby2BirWeight() {
      return this.baby2BirWeight;
   }

   public void setBaby3BirWeight(Long baby3BirWeight) {
      this.baby3BirWeight = baby3BirWeight;
   }

   public Long getBaby3BirWeight() {
      return this.baby3BirWeight;
   }

   public void setBaby4BirWeight(Long baby4BirWeight) {
      this.baby4BirWeight = baby4BirWeight;
   }

   public Long getBaby4BirWeight() {
      return this.baby4BirWeight;
   }

   public void setInhosWeight(Long inhosWeight) {
      this.inhosWeight = inhosWeight;
   }

   public Long getInhosWeight() {
      return this.inhosWeight;
   }

   public void setBirAddrPro(String birAddrPro) {
      this.birAddrPro = birAddrPro;
   }

   public String getBirAddrPro() {
      return this.birAddrPro;
   }

   public void setBirAddrPlagty(String birAddrPlagty) {
      this.birAddrPlagty = birAddrPlagty;
   }

   public String getBirAddrPlagty() {
      return this.birAddrPlagty;
   }

   public void setBirAddrCou(String birAddrCou) {
      this.birAddrCou = birAddrCou;
   }

   public String getBirAddrCou() {
      return this.birAddrCou;
   }

   public void setApAddrPro(String apAddrPro) {
      this.apAddrPro = apAddrPro;
   }

   public String getApAddrPro() {
      return this.apAddrPro;
   }

   public void setApAddrPlagty(String apAddrPlagty) {
      this.apAddrPlagty = apAddrPlagty;
   }

   public String getApAddrPlagty() {
      return this.apAddrPlagty;
   }

   public void setCitizenship(String citizenship) {
      this.citizenship = citizenship;
   }

   public String getCitizenship() {
      return this.citizenship;
   }

   public void setCitizenshipCd(String citizenshipCd) {
      this.citizenshipCd = citizenshipCd;
   }

   public String getCitizenshipCd() {
      return this.citizenshipCd;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getNation() {
      return this.nation;
   }

   public void setNationCd(String nationCd) {
      this.nationCd = nationCd;
   }

   public String getNationCd() {
      return this.nationCd;
   }

   public void setCardTypeName(String cardTypeName) {
      this.cardTypeName = cardTypeName;
   }

   public String getCardTypeName() {
      return this.cardTypeName;
   }

   public void setCardTypeCd(String cardTypeCd) {
      this.cardTypeCd = cardTypeCd;
   }

   public String getCardTypeCd() {
      return this.cardTypeCd;
   }

   public void setCardTypeNo(String cardTypeNo) {
      this.cardTypeNo = cardTypeNo;
   }

   public String getCardTypeNo() {
      return this.cardTypeNo;
   }

   public void setProTypeCd(String proTypeCd) {
      this.proTypeCd = proTypeCd;
   }

   public String getProTypeCd() {
      return this.proTypeCd;
   }

   public void setProTypeName(String proTypeName) {
      this.proTypeName = proTypeName;
   }

   public String getProTypeName() {
      return this.proTypeName;
   }

   public void setProTypeRem(String proTypeRem) {
      this.proTypeRem = proTypeRem;
   }

   public String getProTypeRem() {
      return this.proTypeRem;
   }

   public void setMarSta(String marSta) {
      this.marSta = marSta;
   }

   public String getMarSta() {
      return this.marSta;
   }

   public void setMarStaCd(String marStaCd) {
      this.marStaCd = marStaCd;
   }

   public String getMarStaCd() {
      return this.marStaCd;
   }

   public void setNowAddrPro(String nowAddrPro) {
      this.nowAddrPro = nowAddrPro;
   }

   public String getNowAddrPro() {
      return this.nowAddrPro;
   }

   public void setNowAddrFlagty(String nowAddrFlagty) {
      this.nowAddrFlagty = nowAddrFlagty;
   }

   public String getNowAddrFlagty() {
      return this.nowAddrFlagty;
   }

   public void setNowAddrCou(String nowAddrCou) {
      this.nowAddrCou = nowAddrCou;
   }

   public String getNowAddrCou() {
      return this.nowAddrCou;
   }

   public void setNowAddr(String nowAddr) {
      this.nowAddr = nowAddr;
   }

   public String getNowAddr() {
      return this.nowAddr;
   }

   public void setNowAddrTel(String nowAddrTel) {
      this.nowAddrTel = nowAddrTel;
   }

   public String getNowAddrTel() {
      return this.nowAddrTel;
   }

   public void setNowAddrPost(String nowAddrPost) {
      this.nowAddrPost = nowAddrPost;
   }

   public String getNowAddrPost() {
      return this.nowAddrPost;
   }

   public void setRprAddrPro(String rprAddrPro) {
      this.rprAddrPro = rprAddrPro;
   }

   public String getRprAddrPro() {
      return this.rprAddrPro;
   }

   public void setRprAddrFlagty(String rprAddrFlagty) {
      this.rprAddrFlagty = rprAddrFlagty;
   }

   public String getRprAddrFlagty() {
      return this.rprAddrFlagty;
   }

   public void setRprAddrCou(String rprAddrCou) {
      this.rprAddrCou = rprAddrCou;
   }

   public String getRprAddrCou() {
      return this.rprAddrCou;
   }

   public void setRprAddr(String rprAddr) {
      this.rprAddr = rprAddr;
   }

   public String getRprAddr() {
      return this.rprAddr;
   }

   public void setPrpAddrPost(String prpAddrPost) {
      this.prpAddrPost = prpAddrPost;
   }

   public String getPrpAddrPost() {
      return this.prpAddrPost;
   }

   public void setWorkAddrPro(String workAddrPro) {
      this.workAddrPro = workAddrPro;
   }

   public String getWorkAddrPro() {
      return this.workAddrPro;
   }

   public void setWorkAddrFlagty(String workAddrFlagty) {
      this.workAddrFlagty = workAddrFlagty;
   }

   public String getWorkAddrFlagty() {
      return this.workAddrFlagty;
   }

   public void setWorkAddrCou(String workAddrCou) {
      this.workAddrCou = workAddrCou;
   }

   public String getWorkAddrCou() {
      return this.workAddrCou;
   }

   public void setWorkNameAddr(String workNameAddr) {
      this.workNameAddr = workNameAddr;
   }

   public String getWorkNameAddr() {
      return this.workNameAddr;
   }

   public void setWorkTel(String workTel) {
      this.workTel = workTel;
   }

   public String getWorkTel() {
      return this.workTel;
   }

   public void setWorkPost(String workPost) {
      this.workPost = workPost;
   }

   public String getWorkPost() {
      return this.workPost;
   }

   public void setContName(String contName) {
      this.contName = contName;
   }

   public String getContName() {
      return this.contName;
   }

   public void setConRelaCd(String conRelaCd) {
      this.conRelaCd = conRelaCd;
   }

   public String getConRelaCd() {
      return this.conRelaCd;
   }

   public void setConRelaName(String conRelaName) {
      this.conRelaName = conRelaName;
   }

   public String getConRelaName() {
      return this.conRelaName;
   }

   public void setConRelaRem(String conRelaRem) {
      this.conRelaRem = conRelaRem;
   }

   public String getConRelaRem() {
      return this.conRelaRem;
   }

   public void setContAddrPro(String contAddrPro) {
      this.contAddrPro = contAddrPro;
   }

   public String getContAddrPro() {
      return this.contAddrPro;
   }

   public void setContAddrFlagty(String contAddrFlagty) {
      this.contAddrFlagty = contAddrFlagty;
   }

   public String getContAddrFlagty() {
      return this.contAddrFlagty;
   }

   public void setContAddrCou(String contAddrCou) {
      this.contAddrCou = contAddrCou;
   }

   public String getContAddrCou() {
      return this.contAddrCou;
   }

   public void setContAddr(String contAddr) {
      this.contAddr = contAddr;
   }

   public String getContAddr() {
      return this.contAddr;
   }

   public void setContTel(String contTel) {
      this.contTel = contTel;
   }

   public String getContTel() {
      return this.contTel;
   }

   public void setInWay(String inWay) {
      this.inWay = inWay;
   }

   public String getInWay() {
      return this.inWay;
   }

   public void setTreatType(String treatType) {
      this.treatType = treatType;
   }

   public String getTreatType() {
      return this.treatType;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setInDeptCd(String inDeptCd) {
      this.inDeptCd = inDeptCd;
   }

   public String getInDeptCd() {
      return this.inDeptCd;
   }

   public void setInDeptName(String inDeptName) {
      this.inDeptName = inDeptName;
   }

   public String getInDeptName() {
      return this.inDeptName;
   }

   public void setInRoomNo(String inRoomNo) {
      this.inRoomNo = inRoomNo;
   }

   public String getInRoomNo() {
      return this.inRoomNo;
   }

   public void setChDeptNo(String chDeptNo) {
      this.chDeptNo = chDeptNo;
   }

   public String getChDeptNo() {
      return this.chDeptNo;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutDeptCd(String outDeptCd) {
      this.outDeptCd = outDeptCd;
   }

   public String getOutDeptCd() {
      return this.outDeptCd;
   }

   public void setOutDeptName(String outDeptName) {
      this.outDeptName = outDeptName;
   }

   public String getOutDeptName() {
      return this.outDeptName;
   }

   public void setOutRoomNo(String outRoomNo) {
      this.outRoomNo = outRoomNo;
   }

   public String getOutRoomNo() {
      return this.outRoomNo;
   }

   public void setRealInhosDays(Integer realInhosDays) {
      this.realInhosDays = realInhosDays;
   }

   public Integer getRealInhosDays() {
      return this.realInhosDays;
   }

   public void setOsChdName(String osChdName) {
      this.osChdName = osChdName;
   }

   public String getOsChdName() {
      return this.osChdName;
   }

   public void setOsChdCd(String osChdCd) {
      this.osChdCd = osChdCd;
   }

   public String getOsChdCd() {
      return this.osChdCd;
   }

   public void setOsWmdName(String osWmdName) {
      this.osWmdName = osWmdName;
   }

   public String getOsWmdName() {
      return this.osWmdName;
   }

   public void setOsWmdCd(String osWmdCd) {
      this.osWmdCd = osWmdCd;
   }

   public String getOsWmdCd() {
      return this.osWmdCd;
   }

   public void setCpType(String cpType) {
      this.cpType = cpType;
   }

   public String getCpType() {
      return this.cpType;
   }

   public void setMedPrepFlag(Long medPrepFlag) {
      this.medPrepFlag = medPrepFlag;
   }

   public Long getMedPrepFlag() {
      return this.medPrepFlag;
   }

   public void setCmEquiFlag(Long cmEquiFlag) {
      this.cmEquiFlag = cmEquiFlag;
   }

   public Long getCmEquiFlag() {
      return this.cmEquiFlag;
   }

   public void setCmTechFlag(Long cmTechFlag) {
      this.cmTechFlag = cmTechFlag;
   }

   public Long getCmTechFlag() {
      return this.cmTechFlag;
   }

   public void setPleadFlag(Long pleadFlag) {
      this.pleadFlag = pleadFlag;
   }

   public Long getPleadFlag() {
      return this.pleadFlag;
   }

   public void setHarmOr(String harmOr) {
      this.harmOr = harmOr;
   }

   public String getHarmOr() {
      return this.harmOr;
   }

   public void setHarmOrCd(String harmOrCd) {
      this.harmOrCd = harmOrCd;
   }

   public String getHarmOrCd() {
      return this.harmOrCd;
   }

   public void setPatDiaName(String patDiaName) {
      this.patDiaName = patDiaName;
   }

   public String getPatDiaName() {
      return this.patDiaName;
   }

   public void setPatDiaCd(String patDiaCd) {
      this.patDiaCd = patDiaCd;
   }

   public String getPatDiaCd() {
      return this.patDiaCd;
   }

   public void setPatNo(String patNo) {
      this.patNo = patNo;
   }

   public String getPatNo() {
      return this.patNo;
   }

   public void setDrugAlleFlag(Long drugAlleFlag) {
      this.drugAlleFlag = drugAlleFlag;
   }

   public Long getDrugAlleFlag() {
      return this.drugAlleFlag;
   }

   public void setAlleDrug(String alleDrug) {
      this.alleDrug = alleDrug;
   }

   public String getAlleDrug() {
      return this.alleDrug;
   }

   public void setTestFlag(Long testFlag) {
      this.testFlag = testFlag;
   }

   public Long getTestFlag() {
      return this.testFlag;
   }

   public void setAboName(String aboName) {
      this.aboName = aboName;
   }

   public String getAboName() {
      return this.aboName;
   }

   public void setAboCd(String aboCd) {
      this.aboCd = aboCd;
   }

   public String getAboCd() {
      return this.aboCd;
   }

   public void setRhName(String rhName) {
      this.rhName = rhName;
   }

   public String getRhName() {
      return this.rhName;
   }

   public void setRhCd(String rhCd) {
      this.rhCd = rhCd;
   }

   public String getRhCd() {
      return this.rhCd;
   }

   public void setHdCd(String hdCd) {
      this.hdCd = hdCd;
   }

   public String getHdCd() {
      return this.hdCd;
   }

   public void setHdName(String hdName) {
      this.hdName = hdName;
   }

   public String getHdName() {
      return this.hdName;
   }

   public void setArcDocCd(String arcDocCd) {
      this.arcDocCd = arcDocCd;
   }

   public String getArcDocCd() {
      return this.arcDocCd;
   }

   public void setArcDocName(String arcDocName) {
      this.arcDocName = arcDocName;
   }

   public String getArcDocName() {
      return this.arcDocName;
   }

   public void setAttDocCd(String attDocCd) {
      this.attDocCd = attDocCd;
   }

   public String getAttDocCd() {
      return this.attDocCd;
   }

   public void setAttDocName(String attDocName) {
      this.attDocName = attDocName;
   }

   public String getAttDocName() {
      return this.attDocName;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setDutyNurName(String dutyNurName) {
      this.dutyNurName = dutyNurName;
   }

   public String getDutyNurName() {
      return this.dutyNurName;
   }

   public void setDutyNurCd(String dutyNurCd) {
      this.dutyNurCd = dutyNurCd;
   }

   public String getDutyNurCd() {
      return this.dutyNurCd;
   }

   public void setStuDocCd(String stuDocCd) {
      this.stuDocCd = stuDocCd;
   }

   public String getStuDocCd() {
      return this.stuDocCd;
   }

   public void setStuDocName(String stuDocName) {
      this.stuDocName = stuDocName;
   }

   public String getStuDocName() {
      return this.stuDocName;
   }

   public void setIntDocCd(String intDocCd) {
      this.intDocCd = intDocCd;
   }

   public String getIntDocCd() {
      return this.intDocCd;
   }

   public void setIntDocName(String intDocName) {
      this.intDocName = intDocName;
   }

   public String getIntDocName() {
      return this.intDocName;
   }

   public void setCoderCd(String coderCd) {
      this.coderCd = coderCd;
   }

   public String getCoderCd() {
      return this.coderCd;
   }

   public void setCoderName(String coderName) {
      this.coderName = coderName;
   }

   public String getCoderName() {
      return this.coderName;
   }

   public void setMrqCd(String mrqCd) {
      this.mrqCd = mrqCd;
   }

   public String getMrqCd() {
      return this.mrqCd;
   }

   public void setQconDocCd(String qconDocCd) {
      this.qconDocCd = qconDocCd;
   }

   public String getQconDocCd() {
      return this.qconDocCd;
   }

   public void setQconDocName(String qconDocName) {
      this.qconDocName = qconDocName;
   }

   public String getQconDocName() {
      return this.qconDocName;
   }

   public void setQconNurCd(String qconNurCd) {
      this.qconNurCd = qconNurCd;
   }

   public String getQconNurCd() {
      return this.qconNurCd;
   }

   public void setQconNurName(String qconNurName) {
      this.qconNurName = qconNurName;
   }

   public String getQconNurName() {
      return this.qconNurName;
   }

   public void setQconDate(Date qconDate) {
      this.qconDate = qconDate;
   }

   public Date getQconDate() {
      return this.qconDate;
   }

   public void setLeaveWayCd(String leaveWayCd) {
      this.leaveWayCd = leaveWayCd;
   }

   public String getLeaveWayCd() {
      return this.leaveWayCd;
   }

   public void setInOrgName(String inOrgName) {
      this.inOrgName = inOrgName;
   }

   public String getInOrgName() {
      return this.inOrgName;
   }

   public void setOutInFlag(Long outInFlag) {
      this.outInFlag = outInFlag;
   }

   public Long getOutInFlag() {
      return this.outInFlag;
   }

   public void setOutInAim(String outInAim) {
      this.outInAim = outInAim;
   }

   public String getOutInAim() {
      return this.outInAim;
   }

   public void setCiBefDay(Long ciBefDay) {
      this.ciBefDay = ciBefDay;
   }

   public Long getCiBefDay() {
      return this.ciBefDay;
   }

   public void setCiBefHour(Long ciBefHour) {
      this.ciBefHour = ciBefHour;
   }

   public Long getCiBefHour() {
      return this.ciBefHour;
   }

   public void setCiBefMin(Long ciBefMin) {
      this.ciBefMin = ciBefMin;
   }

   public Long getCiBefMin() {
      return this.ciBefMin;
   }

   public void setCiAftDay(Long ciAftDay) {
      this.ciAftDay = ciAftDay;
   }

   public Long getCiAftDay() {
      return this.ciAftDay;
   }

   public void setCiAftHour(Long ciAftHour) {
      this.ciAftHour = ciAftHour;
   }

   public Long getCiAftHour() {
      return this.ciAftHour;
   }

   public void setCiAftMin(Long ciAftMin) {
      this.ciAftMin = ciAftMin;
   }

   public Long getCiAftMin() {
      return this.ciAftMin;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getMrState() {
      return this.mrState;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("recordId", this.getRecordId()).append("recordNo", this.getRecordNo()).append("patientId", this.getPatientId()).append("inpNo", this.getInpNo()).append("visitId", this.getVisitId()).append("mrType", this.getMrType()).append("orgCd", this.getOrgCd()).append("orgName", this.getOrgName()).append("payTypeCd", this.getPayTypeCd()).append("payTypeName", this.getPayTypeName()).append("hcNo", this.getHcNo()).append("patientName", this.getPatientName()).append("sex", this.getSex()).append("sexCd", this.getSexCd()).append("birDate", this.getBirDate()).append("ageY", this.getAgeY()).append("ageM", this.getAgeM()).append("ageD", this.getAgeD()).append("ageH", this.getAgeH()).append("ageMi", this.getAgeMi()).append("baby1BirWeight", this.getBaby1BirWeight()).append("baby2BirWeight", this.getBaby2BirWeight()).append("baby3BirWeight", this.getBaby3BirWeight()).append("baby4BirWeight", this.getBaby4BirWeight()).append("inhosWeight", this.getInhosWeight()).append("birAddrPro", this.getBirAddrPro()).append("birAddrPlagty", this.getBirAddrPlagty()).append("birAddrCou", this.getBirAddrCou()).append("apAddrPro", this.getApAddrPro()).append("apAddrPlagty", this.getApAddrPlagty()).append("citizenship", this.getCitizenship()).append("citizenshipCd", this.getCitizenshipCd()).append("nation", this.getNation()).append("nationCd", this.getNationCd()).append("cardTypeName", this.getCardTypeName()).append("cardTypeCd", this.getCardTypeCd()).append("cardTypeNo", this.getCardTypeNo()).append("proTypeCd", this.getProTypeCd()).append("proTypeName", this.getProTypeName()).append("proTypeRem", this.getProTypeRem()).append("marSta", this.getMarSta()).append("marStaCd", this.getMarStaCd()).append("nowAddrPro", this.getNowAddrPro()).append("nowAddrFlagty", this.getNowAddrFlagty()).append("nowAddrCou", this.getNowAddrCou()).append("nowAddr", this.getNowAddr()).append("nowAddrTel", this.getNowAddrTel()).append("nowAddrPost", this.getNowAddrPost()).append("rprAddrPro", this.getRprAddrPro()).append("rprAddrFlagty", this.getRprAddrFlagty()).append("rprAddrCou", this.getRprAddrCou()).append("rprAddr", this.getRprAddr()).append("prpAddrPost", this.getPrpAddrPost()).append("workAddrPro", this.getWorkAddrPro()).append("workAddrFlagty", this.getWorkAddrFlagty()).append("workAddrCou", this.getWorkAddrCou()).append("workNameAddr", this.getWorkNameAddr()).append("workTel", this.getWorkTel()).append("workPost", this.getWorkPost()).append("contName", this.getContName()).append("conRelaCd", this.getConRelaCd()).append("conRelaName", this.getConRelaName()).append("conRelaRem", this.getConRelaRem()).append("contAddrPro", this.getContAddrPro()).append("contAddrFlagty", this.getContAddrFlagty()).append("contAddrCou", this.getContAddrCou()).append("contAddr", this.getContAddr()).append("contTel", this.getContTel()).append("inWay", this.getInWay()).append("treatType", this.getTreatType()).append("inhosTime", this.getInhosTime()).append("inDeptCd", this.getInDeptCd()).append("inDeptName", this.getInDeptName()).append("inRoomNo", this.getInRoomNo()).append("chDeptNo", this.getChDeptNo()).append("outTime", this.getOutTime()).append("outDeptCd", this.getOutDeptCd()).append("outDeptName", this.getOutDeptName()).append("outRoomNo", this.getOutRoomNo()).append("realInhosDays", this.getRealInhosDays()).append("osChdName", this.getOsChdName()).append("osChdCd", this.getOsChdCd()).append("osWmdName", this.getOsWmdName()).append("osWmdCd", this.getOsWmdCd()).append("cpType", this.getCpType()).append("medPrepFlag", this.getMedPrepFlag()).append("cmEquiFlag", this.getCmEquiFlag()).append("cmTechFlag", this.getCmTechFlag()).append("pleadFlag", this.getPleadFlag()).append("harmOr", this.getHarmOr()).append("harmOrCd", this.getHarmOrCd()).append("patDiaName", this.getPatDiaName()).append("patDiaCd", this.getPatDiaCd()).append("patNo", this.getPatNo()).append("drugAlleFlag", this.getDrugAlleFlag()).append("alleDrug", this.getAlleDrug()).append("testFlag", this.getTestFlag()).append("aboName", this.getAboName()).append("aboCd", this.getAboCd()).append("rhName", this.getRhName()).append("rhCd", this.getRhCd()).append("hdCd", this.getHdCd()).append("hdName", this.getHdName()).append("arcDocCd", this.getArcDocCd()).append("arcDocName", this.getArcDocName()).append("attDocCd", this.getAttDocCd()).append("attDocName", this.getAttDocName()).append("resDocCd", this.getResDocCd()).append("resDocName", this.getResDocName()).append("dutyNurName", this.getDutyNurName()).append("dutyNurCd", this.getDutyNurCd()).append("stuDocCd", this.getStuDocCd()).append("stuDocName", this.getStuDocName()).append("intDocCd", this.getIntDocCd()).append("intDocName", this.getIntDocName()).append("coderCd", this.getCoderCd()).append("coderName", this.getCoderName()).append("mrqCd", this.getMrqCd()).append("qconDocCd", this.getQconDocCd()).append("qconDocName", this.getQconDocName()).append("qconNurCd", this.getQconNurCd()).append("qconNurName", this.getQconNurName()).append("qconDate", this.getQconDate()).append("leaveWayCd", this.getLeaveWayCd()).append("inOrgName", this.getInOrgName()).append("outInFlag", this.getOutInFlag()).append("outInAim", this.getOutInAim()).append("ciBefDay", this.getCiBefDay()).append("ciBefHour", this.getCiBefHour()).append("ciBefMin", this.getCiBefMin()).append("ciAftDay", this.getCiAftDay()).append("ciAftHour", this.getCiAftHour()).append("ciAftMin", this.getCiAftMin()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("mrState", this.getMrState()).append("chDeptName", this.getChDeptName()).toString();
   }
}
