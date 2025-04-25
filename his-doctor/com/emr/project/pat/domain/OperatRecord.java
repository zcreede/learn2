package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OperatRecord extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String oprId;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "手术申请单编号"
   )
   private String oprApplNo;
   @Excel(
      name = "病区代码"
   )
   private String areaCode;
   @Excel(
      name = "病区名称"
   )
   private String wardName;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @Excel(
      name = "科室代码"
   )
   private String deptCd;
   @Excel(
      name = "手术间编号"
   )
   private String oprRomNo;
   @Excel(
      name = "手术间名称"
   )
   private String oprRomName;
   @Excel(
      name = "术前诊断编码"
   )
   private String oprPreDigIcd;
   @Excel(
      name = "术前诊断名称"
   )
   private String oprPreDigName;
   @Excel(
      name = "术后诊断名称"
   )
   private String oprOpstDigName;
   @Excel(
      name = "术后诊断编码"
   )
   private String oprOpstDigIcd;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "手术及操作开始日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date oprBeginDatetime;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "手术结束日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date oprEndDatetime;
   @Excel(
      name = "手术全程时间(min)"
   )
   private Long oprDuraMins;
   @Excel(
      name = "手术及操作编码"
   )
   private String oprIcd;
   @Excel(
      name = "手术及操作名称"
   )
   private String oprName;
   @Excel(
      name = "手术级别代码"
   )
   private String oprLevelCode;
   @Excel(
      name = "手术级别"
   )
   private String oprLevel;
   @Excel(
      name = "手术及操作目标部位名称"
   )
   private String oprPart;
   @Excel(
      name = "手术体位代码"
   )
   private String oprPosiCode;
   @Excel(
      name = "手术切口描述"
   )
   private String oprInciName;
   @Excel(
      name = "手术切口类别代码"
   )
   private String oprInciCode;
   @Excel(
      name = "术后出血量(ml)"
   )
   private Long bleeVolu;
   @Excel(
      name = "输液量(ml)"
   )
   private Long transQuan;
   @Excel(
      name = "输血量(ml)"
   )
   private Long blooQuan;
   @Excel(
      name = "术前用药"
   )
   private String oprPreDrug;
   @Excel(
      name = "术中用药"
   )
   private String oprDrug;
   @Excel(
      name = "引流材料名称"
   )
   private String draiMate;
   @Excel(
      name = "引流材料数目"
   )
   private String draiMateNum;
   @Excel(
      name = "引流标志"
   )
   private Long draiFlag;
   @Excel(
      name = "放置部位"
   )
   private String draiMatePosi;
   @Excel(
      name = "标输血反应标志"
   )
   private Long transReacFlag;
   @Excel(
      name = "输血反应类型代码"
   )
   private String transReacTypeCode;
   @Excel(
      name = "手术医师编号"
   )
   private String oprDoctCode;
   @Excel(
      name = "手术医生姓名"
   )
   private String oprDoctName;
   @Excel(
      name = "手术助手签名"
   )
   private String oprAssiName;
   @Excel(
      name = "Ⅰ助姓名"
   )
   private String aidName1;
   @Excel(
      name = "Ⅱ助姓名"
   )
   private String aidName2;
   @Excel(
      name = "器械护士编号"
   )
   private String instNurseCode;
   @Excel(
      name = "器械护士姓名"
   )
   private String instNurseName;
   @Excel(
      name = "巡台护士编号"
   )
   private String patrNurseCode;
   @Excel(
      name = "巡台护士姓名"
   )
   private String patrNurseName;
   @Excel(
      name = "麻醉医师编号"
   )
   private String anestCode;
   @Excel(
      name = "麻醉医师姓名"
   )
   private String anestName;
   @Excel(
      name = "麻醉方法代码"
   )
   private String anestMethCode;
   @Excel(
      name = "麻醉方法名称"
   )
   private String anestMethName;
   @Excel(
      name = "手术台号"
   )
   private String oprSameDayNo;
   @Excel(
      name = "HBV"
   )
   private String hbv;
   @Excel(
      name = "HCB"
   )
   private String hcb;
   @Excel(
      name = "HIV"
   )
   private String hiv;
   @Excel(
      name = "手术申请单状态"
   )
   private String oprApplState;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
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
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
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

   public void setOprId(String oprId) {
      this.oprId = oprId;
   }

   public String getOprId() {
      return this.oprId;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setOprApplNo(String oprApplNo) {
      this.oprApplNo = oprApplNo;
   }

   public String getOprApplNo() {
      return this.oprApplNo;
   }

   public void setAreaCode(String areaCode) {
      this.areaCode = areaCode;
   }

   public String getAreaCode() {
      return this.areaCode;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName;
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setOprRomNo(String oprRomNo) {
      this.oprRomNo = oprRomNo;
   }

   public String getOprRomNo() {
      return this.oprRomNo;
   }

   public void setOprRomName(String oprRomName) {
      this.oprRomName = oprRomName;
   }

   public String getOprRomName() {
      return this.oprRomName;
   }

   public void setOprPreDigIcd(String oprPreDigIcd) {
      this.oprPreDigIcd = oprPreDigIcd;
   }

   public String getOprPreDigIcd() {
      return this.oprPreDigIcd;
   }

   public void setOprPreDigName(String oprPreDigName) {
      this.oprPreDigName = oprPreDigName;
   }

   public String getOprPreDigName() {
      return this.oprPreDigName;
   }

   public void setOprOpstDigName(String oprOpstDigName) {
      this.oprOpstDigName = oprOpstDigName;
   }

   public String getOprOpstDigName() {
      return this.oprOpstDigName;
   }

   public void setOprOpstDigIcd(String oprOpstDigIcd) {
      this.oprOpstDigIcd = oprOpstDigIcd;
   }

   public String getOprOpstDigIcd() {
      return this.oprOpstDigIcd;
   }

   public void setOprBeginDatetime(Date oprBeginDatetime) {
      this.oprBeginDatetime = oprBeginDatetime;
   }

   public Date getOprBeginDatetime() {
      return this.oprBeginDatetime;
   }

   public void setOprEndDatetime(Date oprEndDatetime) {
      this.oprEndDatetime = oprEndDatetime;
   }

   public Date getOprEndDatetime() {
      return this.oprEndDatetime;
   }

   public void setOprDuraMins(Long oprDuraMins) {
      this.oprDuraMins = oprDuraMins;
   }

   public Long getOprDuraMins() {
      return this.oprDuraMins;
   }

   public void setOprIcd(String oprIcd) {
      this.oprIcd = oprIcd;
   }

   public String getOprIcd() {
      return this.oprIcd;
   }

   public void setOprName(String oprName) {
      this.oprName = oprName;
   }

   public String getOprName() {
      return this.oprName;
   }

   public void setOprLevelCode(String oprLevelCode) {
      this.oprLevelCode = oprLevelCode;
   }

   public String getOprLevelCode() {
      return this.oprLevelCode;
   }

   public void setOprLevel(String oprLevel) {
      this.oprLevel = oprLevel;
   }

   public String getOprLevel() {
      return this.oprLevel;
   }

   public void setOprPart(String oprPart) {
      this.oprPart = oprPart;
   }

   public String getOprPart() {
      return this.oprPart;
   }

   public void setOprPosiCode(String oprPosiCode) {
      this.oprPosiCode = oprPosiCode;
   }

   public String getOprPosiCode() {
      return this.oprPosiCode;
   }

   public void setOprInciName(String oprInciName) {
      this.oprInciName = oprInciName;
   }

   public String getOprInciName() {
      return this.oprInciName;
   }

   public void setOprInciCode(String oprInciCode) {
      this.oprInciCode = oprInciCode;
   }

   public String getOprInciCode() {
      return this.oprInciCode;
   }

   public void setBleeVolu(Long bleeVolu) {
      this.bleeVolu = bleeVolu;
   }

   public Long getBleeVolu() {
      return this.bleeVolu;
   }

   public void setTransQuan(Long transQuan) {
      this.transQuan = transQuan;
   }

   public Long getTransQuan() {
      return this.transQuan;
   }

   public void setBlooQuan(Long blooQuan) {
      this.blooQuan = blooQuan;
   }

   public Long getBlooQuan() {
      return this.blooQuan;
   }

   public void setOprPreDrug(String oprPreDrug) {
      this.oprPreDrug = oprPreDrug;
   }

   public String getOprPreDrug() {
      return this.oprPreDrug;
   }

   public void setOprDrug(String oprDrug) {
      this.oprDrug = oprDrug;
   }

   public String getOprDrug() {
      return this.oprDrug;
   }

   public void setDraiMate(String draiMate) {
      this.draiMate = draiMate;
   }

   public String getDraiMate() {
      return this.draiMate;
   }

   public void setDraiMateNum(String draiMateNum) {
      this.draiMateNum = draiMateNum;
   }

   public String getDraiMateNum() {
      return this.draiMateNum;
   }

   public void setDraiFlag(Long draiFlag) {
      this.draiFlag = draiFlag;
   }

   public Long getDraiFlag() {
      return this.draiFlag;
   }

   public void setDraiMatePosi(String draiMatePosi) {
      this.draiMatePosi = draiMatePosi;
   }

   public String getDraiMatePosi() {
      return this.draiMatePosi;
   }

   public void setTransReacFlag(Long transReacFlag) {
      this.transReacFlag = transReacFlag;
   }

   public Long getTransReacFlag() {
      return this.transReacFlag;
   }

   public void setTransReacTypeCode(String transReacTypeCode) {
      this.transReacTypeCode = transReacTypeCode;
   }

   public String getTransReacTypeCode() {
      return this.transReacTypeCode;
   }

   public void setOprDoctCode(String oprDoctCode) {
      this.oprDoctCode = oprDoctCode;
   }

   public String getOprDoctCode() {
      return this.oprDoctCode;
   }

   public void setOprDoctName(String oprDoctName) {
      this.oprDoctName = oprDoctName;
   }

   public String getOprDoctName() {
      return this.oprDoctName;
   }

   public void setOprAssiName(String oprAssiName) {
      this.oprAssiName = oprAssiName;
   }

   public String getOprAssiName() {
      return this.oprAssiName;
   }

   public void setAidName1(String aidName1) {
      this.aidName1 = aidName1;
   }

   public String getAidName1() {
      return this.aidName1;
   }

   public void setAidName2(String aidName2) {
      this.aidName2 = aidName2;
   }

   public String getAidName2() {
      return this.aidName2;
   }

   public void setInstNurseCode(String instNurseCode) {
      this.instNurseCode = instNurseCode;
   }

   public String getInstNurseCode() {
      return this.instNurseCode;
   }

   public void setInstNurseName(String instNurseName) {
      this.instNurseName = instNurseName;
   }

   public String getInstNurseName() {
      return this.instNurseName;
   }

   public void setPatrNurseCode(String patrNurseCode) {
      this.patrNurseCode = patrNurseCode;
   }

   public String getPatrNurseCode() {
      return this.patrNurseCode;
   }

   public void setPatrNurseName(String patrNurseName) {
      this.patrNurseName = patrNurseName;
   }

   public String getPatrNurseName() {
      return this.patrNurseName;
   }

   public void setAnestCode(String anestCode) {
      this.anestCode = anestCode;
   }

   public String getAnestCode() {
      return this.anestCode;
   }

   public void setAnestName(String anestName) {
      this.anestName = anestName;
   }

   public String getAnestName() {
      return this.anestName;
   }

   public void setAnestMethCode(String anestMethCode) {
      this.anestMethCode = anestMethCode;
   }

   public String getAnestMethCode() {
      return this.anestMethCode;
   }

   public void setAnestMethName(String anestMethName) {
      this.anestMethName = anestMethName;
   }

   public String getAnestMethName() {
      return this.anestMethName;
   }

   public void setOprSameDayNo(String oprSameDayNo) {
      this.oprSameDayNo = oprSameDayNo;
   }

   public String getOprSameDayNo() {
      return this.oprSameDayNo;
   }

   public void setHbv(String hbv) {
      this.hbv = hbv;
   }

   public String getHbv() {
      return this.hbv;
   }

   public void setHcb(String hcb) {
      this.hcb = hcb;
   }

   public String getHcb() {
      return this.hcb;
   }

   public void setHiv(String hiv) {
      this.hiv = hiv;
   }

   public String getHiv() {
      return this.hiv;
   }

   public void setOprApplState(String oprApplState) {
      this.oprApplState = oprApplState;
   }

   public String getOprApplState() {
      return this.oprApplState;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("oprId", this.getOprId()).append("orgCd", this.getOrgCd()).append("patientId", this.getPatientId()).append("oprApplNo", this.getOprApplNo()).append("areaCode", this.getAreaCode()).append("wardName", this.getWardName()).append("deptName", this.getDeptName()).append("deptCd", this.getDeptCd()).append("oprRomNo", this.getOprRomNo()).append("oprRomName", this.getOprRomName()).append("oprPreDigIcd", this.getOprPreDigIcd()).append("oprPreDigName", this.getOprPreDigName()).append("oprOpstDigName", this.getOprOpstDigName()).append("oprOpstDigIcd", this.getOprOpstDigIcd()).append("oprBeginDatetime", this.getOprBeginDatetime()).append("oprEndDatetime", this.getOprEndDatetime()).append("oprDuraMins", this.getOprDuraMins()).append("oprIcd", this.getOprIcd()).append("oprName", this.getOprName()).append("oprLevelCode", this.getOprLevelCode()).append("oprLevel", this.getOprLevel()).append("oprPart", this.getOprPart()).append("oprPosiCode", this.getOprPosiCode()).append("oprInciName", this.getOprInciName()).append("oprInciCode", this.getOprInciCode()).append("bleeVolu", this.getBleeVolu()).append("transQuan", this.getTransQuan()).append("blooQuan", this.getBlooQuan()).append("oprPreDrug", this.getOprPreDrug()).append("oprDrug", this.getOprDrug()).append("draiMate", this.getDraiMate()).append("draiMateNum", this.getDraiMateNum()).append("draiFlag", this.getDraiFlag()).append("draiMatePosi", this.getDraiMatePosi()).append("transReacFlag", this.getTransReacFlag()).append("transReacTypeCode", this.getTransReacTypeCode()).append("oprDoctCode", this.getOprDoctCode()).append("oprDoctName", this.getOprDoctName()).append("oprAssiName", this.getOprAssiName()).append("aidName1", this.getAidName1()).append("aidName2", this.getAidName2()).append("instNurseCode", this.getInstNurseCode()).append("instNurseName", this.getInstNurseName()).append("patrNurseCode", this.getPatrNurseCode()).append("patrNurseName", this.getPatrNurseName()).append("anestCode", this.getAnestCode()).append("anestName", this.getAnestName()).append("anestMethCode", this.getAnestMethCode()).append("anestMethName", this.getAnestMethName()).append("oprSameDayNo", this.getOprSameDayNo()).append("hbv", this.getHbv()).append("hcb", this.getHcb()).append("hiv", this.getHiv()).append("oprApplState", this.getOprApplState()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
