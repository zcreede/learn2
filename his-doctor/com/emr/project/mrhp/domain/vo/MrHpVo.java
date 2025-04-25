package com.emr.project.mrhp.domain.vo;

import com.emr.common.constant.CommonConstants;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.Transinfo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

public class MrHpVo extends MrHp {
   private List mrHpDiaXYList = new ArrayList();
   private List mrHpDiaZYList = new ArrayList();
   private List mrHpOpeList = new ArrayList();
   private List transinfoList = new ArrayList();
   private List mrHpFeeList = new ArrayList();
   private MrHpAttachVo mrHpAttachVo = new MrHpAttachVo();
   private String signPic;
   private Boolean obsDeptFlag;
   private List errorMsg;
   private List resultVoList;
   private List emrQcListList;
   private List diagInfoList;
   private String inHosYear;
   private String inHosMouth;
   private String inHosDay;
   private String inHosHour;
   private String inHosMin;
   private String outTimeYear;
   private String outTimeMouth;
   private String outTimeDay;
   private String outTimeHour;
   private String outTimeMin;
   private String birDateYear;
   private String birDateMouth;
   private String birDateDay;
   private String prpAddrAll;
   private String contAddrAll;
   private String nowAddrAll;
   private String apAddrAll;
   private String mzDiaCd1;
   private String mzDiaName1;
   private String mzDiaCd2;
   private String mzDiaName2;
   private String inOrgName1;
   private String inOrgName2;
   private String zyMzDiaCd;
   private String zyMzDiaName;
   private String weight;
   private String patientTel;
   private String lis;
   private String ryzdBh;
   private String ryzdBm;
   private String ryzdMc;
   private String altDateEnd;
   private List patientIdList;
   private String patientId;
   private String recordId;
   @ApiModelProperty("新生儿出生体重(g)-首页打印用")
   private String baby1_bir_weightS;
   @ApiModelProperty("新生儿入院体重(g)-首页打印用")
   private String inhos_weightS;
   @ApiModelProperty("年龄-格式化之后的-首页打印用")
   private String personAge;
   @ApiModelProperty("年龄(不足一岁)-格式化之后的-首页打印用")
   private String babyAge;
   @ApiModelProperty("是否使用移动CA签名 1是 0否")
   private String mobileSignFlag;
   @ApiModelProperty("移动CA签名 工号")
   private String mobileSignUserName;

   public MrHpVo() {
      this.obsDeptFlag = CommonConstants.BOOL_FALSE;
      this.errorMsg = new ArrayList(1);
      this.resultVoList = new ArrayList(1);
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public List getPatientIdList() {
      return this.patientIdList;
   }

   public void setPatientIdList(List patientIdList) {
      this.patientIdList = patientIdList;
   }

   public String getBaby1_bir_weightS() {
      return this.baby1_bir_weightS;
   }

   public void setBaby1_bir_weightS(String baby1_bir_weightS) {
      this.baby1_bir_weightS = baby1_bir_weightS;
   }

   public String getPersonAge() {
      return this.personAge;
   }

   public void setPersonAge(String personAge) {
      this.personAge = personAge;
   }

   public String getBabyAge() {
      return this.babyAge;
   }

   public void setBabyAge(String babyAge) {
      this.babyAge = babyAge;
   }

   public String getInhos_weightS() {
      return this.inhos_weightS;
   }

   public void setInhos_weightS(String inhos_weightS) {
      this.inhos_weightS = inhos_weightS;
   }

   public String getAltDateEnd() {
      return this.altDateEnd;
   }

   public void setAltDateEnd(String altDateEnd) {
      this.altDateEnd = altDateEnd;
   }

   public String getRyzdBh() {
      return this.ryzdBh;
   }

   public void setRyzdBh(String ryzdBh) {
      this.ryzdBh = ryzdBh;
   }

   public String getRyzdBm() {
      return this.ryzdBm;
   }

   public void setRyzdBm(String ryzdBm) {
      this.ryzdBm = ryzdBm;
   }

   public String getRyzdMc() {
      return this.ryzdMc;
   }

   public void setRyzdMc(String ryzdMc) {
      this.ryzdMc = ryzdMc;
   }

   public List getDiagInfoList() {
      return this.diagInfoList;
   }

   public void setDiagInfoList(List diagInfoList) {
      this.diagInfoList = diagInfoList;
   }

   public String getLis() {
      return this.lis;
   }

   public void setLis(String lis) {
      this.lis = lis;
   }

   public String getWeight() {
      return this.weight;
   }

   public void setWeight(String weight) {
      this.weight = weight;
   }

   public String getOutTimeYear() {
      return this.outTimeYear;
   }

   public void setOutTimeYear(String outTimeYear) {
      this.outTimeYear = outTimeYear;
   }

   public String getOutTimeMouth() {
      return this.outTimeMouth;
   }

   public void setOutTimeMouth(String outTimeMouth) {
      this.outTimeMouth = outTimeMouth;
   }

   public String getOutTimeDay() {
      return this.outTimeDay;
   }

   public void setOutTimeDay(String outTimeDay) {
      this.outTimeDay = outTimeDay;
   }

   public String getOutTimeHour() {
      return this.outTimeHour;
   }

   public void setOutTimeHour(String outTimeHour) {
      this.outTimeHour = outTimeHour;
   }

   public String getPatientTel() {
      return this.patientTel;
   }

   public void setPatientTel(String patientTel) {
      this.patientTel = patientTel;
   }

   public String getOutTimeMin() {
      return this.outTimeMin;
   }

   public void setOutTimeMin(String outTimeMin) {
      this.outTimeMin = outTimeMin;
   }

   public String getInHosYear() {
      return this.inHosYear;
   }

   public void setInHosYear(String inHosYear) {
      this.inHosYear = inHosYear;
   }

   public String getInHosMouth() {
      return this.inHosMouth;
   }

   public void setInHosMouth(String inHosMouth) {
      this.inHosMouth = inHosMouth;
   }

   public String getInHosDay() {
      return this.inHosDay;
   }

   public void setInHosDay(String inHosDay) {
      this.inHosDay = inHosDay;
   }

   public String getInHosHour() {
      return this.inHosHour;
   }

   public void setInHosHour(String inHosHour) {
      this.inHosHour = inHosHour;
   }

   public String getInHosMin() {
      return this.inHosMin;
   }

   public void setInHosMin(String inHosMin) {
      this.inHosMin = inHosMin;
   }

   public List getEmrQcListList() {
      return this.emrQcListList;
   }

   public void setEmrQcListList(List emrQcListList) {
      this.emrQcListList = emrQcListList;
   }

   public List getErrorMsg() {
      return this.errorMsg;
   }

   public void setErrorMsg(List errorMsg) {
      this.errorMsg = errorMsg;
   }

   public Boolean getObsDeptFlag() {
      return this.obsDeptFlag;
   }

   public void setObsDeptFlag(Boolean obsDeptFlag) {
      this.obsDeptFlag = obsDeptFlag;
   }

   public String getSignPic() {
      return this.signPic;
   }

   public void setSignPic(String signPic) {
      this.signPic = signPic;
   }

   public MrHpAttachVo getMrHpAttachVo() {
      return this.mrHpAttachVo;
   }

   public void setMrHpAttachVo(MrHpAttachVo mrHpAttachVo) {
      this.mrHpAttachVo = mrHpAttachVo;
   }

   public List getMrHpDiaZYList() {
      return this.mrHpDiaZYList;
   }

   public void setMrHpDiaZYList(List mrHpDiaZYList) {
      this.mrHpDiaZYList = mrHpDiaZYList;
   }

   public List getMrHpFeeList() {
      return this.mrHpFeeList;
   }

   public void setMrHpFeeList(List mrHpFeeList) {
      this.mrHpFeeList = mrHpFeeList;
   }

   public List getTransinfoList() {
      return this.transinfoList;
   }

   public void setTransinfoList(List transinfoList) {
      this.transinfoList = transinfoList;
   }

   public List getMrHpOpeList() {
      return this.mrHpOpeList;
   }

   public void setMrHpOpeList(List mrHpOpeList) {
      this.mrHpOpeList = mrHpOpeList;
   }

   public List getMrHpDiaXYList() {
      return this.mrHpDiaXYList;
   }

   public void setMrHpDiaXYList(List mrHpDiaXYList) {
      this.mrHpDiaXYList = mrHpDiaXYList;
   }

   public List getResultVoList() {
      return this.resultVoList;
   }

   public void setResultVoList(List resultVoList) {
      this.resultVoList = resultVoList;
   }

   public String getBirDateYear() {
      return this.birDateYear;
   }

   public void setBirDateYear(String birDateYear) {
      this.birDateYear = birDateYear;
   }

   public String getBirDateMouth() {
      return this.birDateMouth;
   }

   public void setBirDateMouth(String birDateMouth) {
      this.birDateMouth = birDateMouth;
   }

   public String getBirDateDay() {
      return this.birDateDay;
   }

   public void setBirDateDay(String birDateDay) {
      this.birDateDay = birDateDay;
   }

   public String getPrpAddrAll() {
      return this.prpAddrAll;
   }

   public void setPrpAddrAll(String prpAddrAll) {
      this.prpAddrAll = prpAddrAll;
   }

   public String getContAddrAll() {
      return this.contAddrAll;
   }

   public void setContAddrAll(String contAddrAll) {
      this.contAddrAll = contAddrAll;
   }

   public String getNowAddrAll() {
      return this.nowAddrAll;
   }

   public void setNowAddrAll(String nowAddrAll) {
      this.nowAddrAll = nowAddrAll;
   }

   public String getApAddrAll() {
      return this.apAddrAll;
   }

   public void setApAddrAll(String apAddrAll) {
      this.apAddrAll = apAddrAll;
   }

   public String getMzDiaCd1() {
      return this.mzDiaCd1;
   }

   public void setMzDiaCd1(String mzDiaCd1) {
      this.mzDiaCd1 = mzDiaCd1;
   }

   public String getMzDiaName1() {
      return this.mzDiaName1;
   }

   public void setMzDiaName1(String mzDiaName1) {
      this.mzDiaName1 = mzDiaName1;
   }

   public String getMzDiaCd2() {
      return this.mzDiaCd2;
   }

   public void setMzDiaCd2(String mzDiaCd2) {
      this.mzDiaCd2 = mzDiaCd2;
   }

   public String getMzDiaName2() {
      return this.mzDiaName2;
   }

   public void setMzDiaName2(String mzDiaName2) {
      this.mzDiaName2 = mzDiaName2;
   }

   public String getInOrgName1() {
      return this.inOrgName1;
   }

   public void setInOrgName1(String inOrgName1) {
      this.inOrgName1 = inOrgName1;
   }

   public String getInOrgName2() {
      return this.inOrgName2;
   }

   public void setInOrgName2(String inOrgName2) {
      this.inOrgName2 = inOrgName2;
   }

   public String getZyMzDiaCd() {
      return this.zyMzDiaCd;
   }

   public void setZyMzDiaCd(String zyMzDiaCd) {
      this.zyMzDiaCd = zyMzDiaCd;
   }

   public String getZyMzDiaName() {
      return this.zyMzDiaName;
   }

   public void setZyMzDiaName(String zyMzDiaName) {
      this.zyMzDiaName = zyMzDiaName;
   }

   public String getMobileSignFlag() {
      return this.mobileSignFlag;
   }

   public void setMobileSignFlag(String mobileSignFlag) {
      this.mobileSignFlag = mobileSignFlag;
   }

   public String getMobileSignUserName() {
      return this.mobileSignUserName;
   }

   public void setMobileSignUserName(String mobileSignUserName) {
      this.mobileSignUserName = mobileSignUserName;
   }
}
