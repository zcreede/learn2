package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Personalinfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "患者主索引ID"
   )
   private String patientMainId;
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "患者性别"
   )
   private String sex;
   @Excel(
      name = "患者性别代码"
   )
   private String sexCd;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出生日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
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
      name = "国籍"
   )
   private String citizenship;
   @Excel(
      name = "国籍代码"
   )
   private String citizenshipCd;
   @Excel(
      name = "婚姻状况代码"
   )
   private String marStaCd;
   @Excel(
      name = "婚姻状况"
   )
   private String marSta;
   @Excel(
      name = "身份证件类型编号"
   )
   private String cardTypeCd;
   @Excel(
      name = "身份证件类型名称"
   )
   private String cardType;
   @Excel(
      name = "患者身份证件号码"
   )
   private String idCard;
   @Excel(
      name = "民族"
   )
   private String nationCd;
   @Excel(
      name = "医疗保障卡号"
   )
   private String healCardNo;
   @Excel(
      name = "城乡居民个人健康档案的编号"
   )
   private String healFileNo;
   @Excel(
      name = "退伍军人标志"
   )
   private String exsFlag;
   @Excel(
      name = "学历代码"
   )
   private String eduCd;
   @Excel(
      name = "学历名称"
   )
   private String eduName;
   @Excel(
      name = "医院就诊卡号"
   )
   private String sdCardId;
   @Excel(
      name = "职业类别代码"
   )
   private String proTypeCd;
   @Excel(
      name = "职业类别名称"
   )
   private String proTypeName;
   @Excel(
      name = "现住址-门牌号码"
   )
   private String nowAddrHouseNo;
   @Excel(
      name = "现住址-邮政编码"
   )
   private String nowAddrPost;
   @Excel(
      name = "现住址-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String nowAddrPro;
   @Excel(
      name = "现住址-村",
      readConverterExp = "街=、路、弄等"
   )
   private String nowAddrVil;
   @Excel(
      name = "现住址-市",
      readConverterExp = "地=区、州"
   )
   private String nowAddrFlagty;
   @Excel(
      name = "现住址-县",
      readConverterExp = "区="
   )
   private String nowAddrCou;
   @Excel(
      name = "现住址-乡",
      readConverterExp = "镇=、街道办事处"
   )
   private String nowAddrTown;
   @Excel(
      name = "现住址电话"
   )
   private String nowAddrTel;
   @Excel(
      name = "籍贯地址-门牌编号"
   )
   private String citAddrHouseNo;
   @Excel(
      name = "籍贯地址-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String citAddrPro;
   @Excel(
      name = "籍贯地址-村",
      readConverterExp = "街=、路、弄"
   )
   private String citAddrVil;
   @Excel(
      name = "籍贯地址-市",
      readConverterExp = "地=区、州"
   )
   private String citAddrFlagty;
   @Excel(
      name = "籍贯地址-县",
      readConverterExp = "区="
   )
   private String citAddrCou;
   @Excel(
      name = "籍贯地址-乡",
      readConverterExp = "镇=、街道办事处"
   )
   private String citAddrTown;
   @Excel(
      name = "户口地址-门牌号码"
   )
   private String rprAddrHouseNo;
   @Excel(
      name = "户口地址-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String rprAddrPro;
   @Excel(
      name = "户口地址-村",
      readConverterExp = "街=、路、弄等"
   )
   private String rprAddrVil;
   @Excel(
      name = "户口地址-市",
      readConverterExp = "地=区、州"
   )
   private String rprAddrFlagty;
   @Excel(
      name = "患者户籍登记所在地址的县",
      readConverterExp = "区="
   )
   private String rprAddrCou;
   @Excel(
      name = "户口地址-县",
      readConverterExp = "区="
   )
   private String rprAddrTown;
   @Excel(
      name = "户口地址-邮政编码"
   )
   private String prpAddrPost;
   @Excel(
      name = "患者电话号码"
   )
   private String patientTel;
   @Excel(
      name = "工作单位名称"
   )
   private String workName;
   @Excel(
      name = "工作单位地址"
   )
   private String workAddr;
   @Excel(
      name = "工作单位电话"
   )
   private String workTel;
   @Excel(
      name = "工作单位邮编"
   )
   private String workPost;
   @Excel(
      name = "出生地-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String birAddrPro;
   @Excel(
      name = "出生地-市",
      readConverterExp = "地=区、州"
   )
   private String birAddrPlagty;
   @Excel(
      name = "出生地-县",
      readConverterExp = "区="
   )
   private String birAddrCou;
   @Excel(
      name = "联系人电话号码"
   )
   private String contTel;
   @Excel(
      name = "联系人姓名"
   )
   private String contName;
   @Excel(
      name = "联系人地址-邮编"
   )
   private String contAddrPost;
   @Excel(
      name = "联系人地址-省",
      readConverterExp = "自=治区、直辖市"
   )
   private String contAddrPro;
   @Excel(
      name = "联系人地址-村",
      readConverterExp = "街=、路、弄等"
   )
   private String contAddrVil;
   @Excel(
      name = "联系人地址-市",
      readConverterExp = "地=区、州"
   )
   private String contAddrFlagty;
   @Excel(
      name = "联系人地址-县",
      readConverterExp = "区="
   )
   private String contAddrCou;
   @Excel(
      name = "联系人地址-乡",
      readConverterExp = "镇=、街道办事处"
   )
   private String contAddrTown;
   @Excel(
      name = "联系人与患者之间的关系类别代码"
   )
   private String cpRela;
   @Excel(
      name = "密级"
   )
   private String secLevel;
   @Excel(
      name = "ABO血型"
   )
   private String aboName;
   @Excel(
      name = "ABO血型代码"
   )
   private String aboCd;
   @Excel(
      name = "RH血型"
   )
   private String rhName;
   @Excel(
      name = "Rh血型代码"
   )
   private String rhCd;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "参加工作日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date workDate;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "民族名称"
   )
   private String nation;
   @Excel(
      name = "政治面貌编码"
   )
   private String polStatCd;
   @Excel(
      name = "政治面貌名称"
   )
   private String polStatName;
   @Excel(
      name = "基本信息修改标志，默认0，当为1时不再从HIS同步此条数据"
   )
   private String modiFlag;
   @Excel(
      name = "联系人关系名称"
   )
   private String cpRelaName;
   @Excel(
      name = "联系人关系名称"
   )
   private String bqdmGl;
   private String proTypeDesc;
   private String workAddrPro;
   private String workAddrFlagty;
   private String workAddrCou;
   private String workAddrTown;
   private String workAddrVil;
   private String workAddrHouseNo;
   private String cpRelaRem;
   private String contAddrHouseNo;
   private String payTypeCd;
   private String payTypeName;
   private String inpNo;

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPayTypeCd() {
      return this.payTypeCd;
   }

   public void setPayTypeCd(String payTypeCd) {
      this.payTypeCd = payTypeCd;
   }

   public String getPayTypeName() {
      return this.payTypeName;
   }

   public void setPayTypeName(String payTypeName) {
      this.payTypeName = payTypeName;
   }

   public String getProTypeDesc() {
      return this.proTypeDesc;
   }

   public void setProTypeDesc(String proTypeDesc) {
      this.proTypeDesc = proTypeDesc;
   }

   public String getWorkAddrPro() {
      return this.workAddrPro;
   }

   public void setWorkAddrPro(String workAddrPro) {
      this.workAddrPro = workAddrPro;
   }

   public String getWorkAddrFlagty() {
      return this.workAddrFlagty;
   }

   public void setWorkAddrFlagty(String workAddrFlagty) {
      this.workAddrFlagty = workAddrFlagty;
   }

   public String getWorkAddrCou() {
      return this.workAddrCou;
   }

   public void setWorkAddrCou(String workAddrCou) {
      this.workAddrCou = workAddrCou;
   }

   public String getWorkAddrTown() {
      return this.workAddrTown;
   }

   public void setWorkAddrTown(String workAddrTown) {
      this.workAddrTown = workAddrTown;
   }

   public String getWorkAddrVil() {
      return this.workAddrVil;
   }

   public void setWorkAddrVil(String workAddrVil) {
      this.workAddrVil = workAddrVil;
   }

   public String getWorkAddrHouseNo() {
      return this.workAddrHouseNo;
   }

   public void setWorkAddrHouseNo(String workAddrHouseNo) {
      this.workAddrHouseNo = workAddrHouseNo;
   }

   public String getCpRelaRem() {
      return this.cpRelaRem;
   }

   public void setCpRelaRem(String cpRelaRem) {
      this.cpRelaRem = cpRelaRem;
   }

   public String getContAddrHouseNo() {
      return this.contAddrHouseNo;
   }

   public void setContAddrHouseNo(String contAddrHouseNo) {
      this.contAddrHouseNo = contAddrHouseNo;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
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

   public void setMarStaCd(String marStaCd) {
      this.marStaCd = marStaCd;
   }

   public String getMarStaCd() {
      return this.marStaCd;
   }

   public void setMarSta(String marSta) {
      this.marSta = marSta;
   }

   public String getMarSta() {
      return this.marSta;
   }

   public void setCardTypeCd(String cardTypeCd) {
      this.cardTypeCd = cardTypeCd;
   }

   public String getCardTypeCd() {
      return this.cardTypeCd;
   }

   public void setCardType(String cardType) {
      this.cardType = cardType;
   }

   public String getCardType() {
      return this.cardType;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setNationCd(String nationCd) {
      this.nationCd = nationCd;
   }

   public String getNationCd() {
      return this.nationCd;
   }

   public void setHealCardNo(String healCardNo) {
      this.healCardNo = healCardNo;
   }

   public String getHealCardNo() {
      return this.healCardNo;
   }

   public void setHealFileNo(String healFileNo) {
      this.healFileNo = healFileNo;
   }

   public String getHealFileNo() {
      return this.healFileNo;
   }

   public void setExsFlag(String exsFlag) {
      this.exsFlag = exsFlag;
   }

   public String getExsFlag() {
      return this.exsFlag;
   }

   public void setEduCd(String eduCd) {
      this.eduCd = eduCd;
   }

   public String getEduCd() {
      return this.eduCd;
   }

   public void setEduName(String eduName) {
      this.eduName = eduName;
   }

   public String getEduName() {
      return this.eduName;
   }

   public void setSdCardId(String sdCardId) {
      this.sdCardId = sdCardId;
   }

   public String getSdCardId() {
      return this.sdCardId;
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

   public void setNowAddrHouseNo(String nowAddrHouseNo) {
      this.nowAddrHouseNo = nowAddrHouseNo;
   }

   public String getNowAddrHouseNo() {
      return this.nowAddrHouseNo;
   }

   public void setNowAddrPost(String nowAddrPost) {
      this.nowAddrPost = nowAddrPost;
   }

   public String getNowAddrPost() {
      return this.nowAddrPost;
   }

   public void setNowAddrPro(String nowAddrPro) {
      this.nowAddrPro = nowAddrPro;
   }

   public String getNowAddrPro() {
      return this.nowAddrPro;
   }

   public void setNowAddrVil(String nowAddrVil) {
      this.nowAddrVil = nowAddrVil;
   }

   public String getNowAddrVil() {
      return this.nowAddrVil;
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

   public void setNowAddrTown(String nowAddrTown) {
      this.nowAddrTown = nowAddrTown;
   }

   public String getNowAddrTown() {
      return this.nowAddrTown;
   }

   public void setNowAddrTel(String nowAddrTel) {
      this.nowAddrTel = nowAddrTel;
   }

   public String getNowAddrTel() {
      return this.nowAddrTel;
   }

   public void setCitAddrHouseNo(String citAddrHouseNo) {
      this.citAddrHouseNo = citAddrHouseNo;
   }

   public String getCitAddrHouseNo() {
      return this.citAddrHouseNo;
   }

   public void setCitAddrPro(String citAddrPro) {
      this.citAddrPro = citAddrPro;
   }

   public String getCitAddrPro() {
      return this.citAddrPro;
   }

   public void setCitAddrVil(String citAddrVil) {
      this.citAddrVil = citAddrVil;
   }

   public String getCitAddrVil() {
      return this.citAddrVil;
   }

   public void setCitAddrFlagty(String citAddrFlagty) {
      this.citAddrFlagty = citAddrFlagty;
   }

   public String getCitAddrFlagty() {
      return this.citAddrFlagty;
   }

   public void setCitAddrCou(String citAddrCou) {
      this.citAddrCou = citAddrCou;
   }

   public String getCitAddrCou() {
      return this.citAddrCou;
   }

   public void setCitAddrTown(String citAddrTown) {
      this.citAddrTown = citAddrTown;
   }

   public String getCitAddrTown() {
      return this.citAddrTown;
   }

   public void setRprAddrHouseNo(String rprAddrHouseNo) {
      this.rprAddrHouseNo = rprAddrHouseNo;
   }

   public String getRprAddrHouseNo() {
      return this.rprAddrHouseNo;
   }

   public void setRprAddrPro(String rprAddrPro) {
      this.rprAddrPro = rprAddrPro;
   }

   public String getRprAddrPro() {
      return this.rprAddrPro;
   }

   public void setRprAddrVil(String rprAddrVil) {
      this.rprAddrVil = rprAddrVil;
   }

   public String getRprAddrVil() {
      return this.rprAddrVil;
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

   public void setRprAddrTown(String rprAddrTown) {
      this.rprAddrTown = rprAddrTown;
   }

   public String getRprAddrTown() {
      return this.rprAddrTown;
   }

   public void setPrpAddrPost(String prpAddrPost) {
      this.prpAddrPost = prpAddrPost;
   }

   public String getPrpAddrPost() {
      return this.prpAddrPost;
   }

   public void setPatientTel(String patientTel) {
      this.patientTel = patientTel;
   }

   public String getPatientTel() {
      return this.patientTel;
   }

   public void setWorkName(String workName) {
      this.workName = workName;
   }

   public String getWorkName() {
      return this.workName;
   }

   public void setWorkAddr(String workAddr) {
      this.workAddr = workAddr;
   }

   public String getWorkAddr() {
      return this.workAddr;
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

   public void setContTel(String contTel) {
      this.contTel = contTel;
   }

   public String getContTel() {
      return this.contTel;
   }

   public void setContName(String contName) {
      this.contName = contName;
   }

   public String getContName() {
      return this.contName;
   }

   public void setContAddrPost(String contAddrPost) {
      this.contAddrPost = contAddrPost;
   }

   public String getContAddrPost() {
      return this.contAddrPost;
   }

   public void setContAddrPro(String contAddrPro) {
      this.contAddrPro = contAddrPro;
   }

   public String getContAddrPro() {
      return this.contAddrPro;
   }

   public void setContAddrVil(String contAddrVil) {
      this.contAddrVil = contAddrVil;
   }

   public String getContAddrVil() {
      return this.contAddrVil;
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

   public void setContAddrTown(String contAddrTown) {
      this.contAddrTown = contAddrTown;
   }

   public String getContAddrTown() {
      return this.contAddrTown;
   }

   public void setCpRela(String cpRela) {
      this.cpRela = cpRela;
   }

   public String getCpRela() {
      return this.cpRela;
   }

   public void setSecLevel(String secLevel) {
      this.secLevel = secLevel;
   }

   public String getSecLevel() {
      return this.secLevel;
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

   public void setWorkDate(Date workDate) {
      this.workDate = workDate;
   }

   public Date getWorkDate() {
      return this.workDate;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   public String getNation() {
      return this.nation;
   }

   public void setPolStatCd(String polStatCd) {
      this.polStatCd = polStatCd;
   }

   public String getPolStatCd() {
      return this.polStatCd;
   }

   public void setPolStatName(String polStatName) {
      this.polStatName = polStatName;
   }

   public String getPolStatName() {
      return this.polStatName;
   }

   public void setModiFlag(String modiFlag) {
      this.modiFlag = modiFlag;
   }

   public String getModiFlag() {
      return this.modiFlag;
   }

   public void setCpRelaName(String cpRelaName) {
      this.cpRelaName = cpRelaName;
   }

   public String getCpRelaName() {
      return this.cpRelaName;
   }

   public void setBqdmGl(String bqdmGl) {
      this.bqdmGl = bqdmGl;
   }

   public String getBqdmGl() {
      return this.bqdmGl;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("patientMainId", this.getPatientMainId()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("inputstrphtc", this.getInputstrphtc()).append("sex", this.getSex()).append("sexCd", this.getSexCd()).append("birDate", this.getBirDate()).append("ageY", this.getAgeY()).append("ageM", this.getAgeM()).append("ageD", this.getAgeD()).append("ageH", this.getAgeH()).append("ageMi", this.getAgeMi()).append("citizenship", this.getCitizenship()).append("citizenshipCd", this.getCitizenshipCd()).append("marStaCd", this.getMarStaCd()).append("marSta", this.getMarSta()).append("cardTypeCd", this.getCardTypeCd()).append("cardType", this.getCardType()).append("idCard", this.getIdCard()).append("nationCd", this.getNationCd()).append("healCardNo", this.getHealCardNo()).append("healFileNo", this.getHealFileNo()).append("exsFlag", this.getExsFlag()).append("eduCd", this.getEduCd()).append("eduName", this.getEduName()).append("sdCardId", this.getSdCardId()).append("proTypeCd", this.getProTypeCd()).append("proTypeName", this.getProTypeName()).append("nowAddrHouseNo", this.getNowAddrHouseNo()).append("nowAddrPost", this.getNowAddrPost()).append("nowAddrPro", this.getNowAddrPro()).append("nowAddrVil", this.getNowAddrVil()).append("nowAddrFlagty", this.getNowAddrFlagty()).append("nowAddrCou", this.getNowAddrCou()).append("nowAddrTown", this.getNowAddrTown()).append("nowAddrTel", this.getNowAddrTel()).append("citAddrHouseNo", this.getCitAddrHouseNo()).append("citAddrPro", this.getCitAddrPro()).append("citAddrVil", this.getCitAddrVil()).append("citAddrFlagty", this.getCitAddrFlagty()).append("citAddrCou", this.getCitAddrCou()).append("citAddrTown", this.getCitAddrTown()).append("rprAddrHouseNo", this.getRprAddrHouseNo()).append("rprAddrPro", this.getRprAddrPro()).append("rprAddrVil", this.getRprAddrVil()).append("rprAddrFlagty", this.getRprAddrFlagty()).append("rprAddrCou", this.getRprAddrCou()).append("rprAddrTown", this.getRprAddrTown()).append("prpAddrPost", this.getPrpAddrPost()).append("patientTel", this.getPatientTel()).append("workName", this.getWorkName()).append("workAddr", this.getWorkAddr()).append("workTel", this.getWorkTel()).append("workPost", this.getWorkPost()).append("birAddrPro", this.getBirAddrPro()).append("birAddrPlagty", this.getBirAddrPlagty()).append("birAddrCou", this.getBirAddrCou()).append("contTel", this.getContTel()).append("contName", this.getContName()).append("contAddrPost", this.getContAddrPost()).append("contAddrPro", this.getContAddrPro()).append("contAddrVil", this.getContAddrVil()).append("contAddrFlagty", this.getContAddrFlagty()).append("contAddrCou", this.getContAddrCou()).append("contAddrTown", this.getContAddrTown()).append("cpRela", this.getCpRela()).append("secLevel", this.getSecLevel()).append("aboName", this.getAboName()).append("aboCd", this.getAboCd()).append("rhName", this.getRhName()).append("rhCd", this.getRhCd()).append("workDate", this.getWorkDate()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("nation", this.getNation()).append("polStatCd", this.getPolStatCd()).append("polStatName", this.getPolStatName()).append("modiFlag", this.getModiFlag()).append("cpRelaName", this.getCpRelaName()).append("bqdmGl", this.getBqdmGl()).append("proTypeDesc", this.getProTypeDesc()).append("workAddrPro", this.getWorkAddrPro()).append("workAddrFlagty", this.getContAddrFlagty()).append("workAddrCou", this.getWorkAddrCou()).append("workAddrTown", this.getWorkAddrTown()).append("workAddrVil", this.getWorkAddrVil()).append("workAddrHouseNo", this.getWorkAddrHouseNo()).append("cpRelaRem", this.getCpRelaRem()).append("contAddrHouseNo", this.getContAddrHouseNo()).toString();
   }
}
