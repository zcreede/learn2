package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestAlertReport extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String resultalertid;
   private String alertman;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "上报时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm",
      sort = 10
   )
   private Date alertdt;
   private String reportid;
   private String resultid;
   private String rptunitid;
   private String rptunitname;
   private String hospitalcode;
   private String patType;
   @Excel(
      name = "住院号",
      sort = 3
   )
   private String patNo;
   private String patId;
   private String patCardno;
   private String inpId;
   @Excel(
      name = "患者姓名",
      sort = 0
   )
   private String patName;
   private String patSex;
   private String patAgestr;
   private String reqWardno;
   @Excel(
      name = "床号",
      sort = 4
   )
   private String reqBedno;
   private String reqDeptno;
   private String reqDocno;
   private String emerFlag;
   private String specimenName;
   private String barcode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date recieveDt;
   private String descriptions;
   private String rptItemid;
   @Excel(
      name = "项目代码",
      sort = 5
   )
   private String rptItemcode;
   private String itemcodeEn;
   @Excel(
      name = "检验项目",
      align = Excel.Align.LEFT,
      sort = 6
   )
   private String rptItemname;
   private String externalcode1;
   private String externalcode2;
   @Excel(
      name = "结果",
      sort = 7
   )
   private String result;
   private String result1;
   private String result2;
   private String result3;
   private String resultFlag;
   @Excel(
      name = "单位",
      sort = 8
   )
   private String resultUnit;
   @Excel(
      name = "参考范围",
      sort = 9
   )
   private String resultRef;
   private String redoFlag;
   private String redoResult;
   private String alertrules;
   private String repDeptCd;
   @Excel(
      name = "报告科室",
      sort = 17
   )
   private String repDeptName;
   private String repDocCd;
   @Excel(
      name = "报告人",
      sort = 18
   )
   private String repDocName;

   public void setResultalertid(String resultalertid) {
      this.resultalertid = resultalertid;
   }

   public String getResultalertid() {
      return this.resultalertid;
   }

   public void setAlertman(String alertman) {
      this.alertman = alertman;
   }

   public String getAlertman() {
      return this.alertman;
   }

   public void setAlertdt(Date alertdt) {
      this.alertdt = alertdt;
   }

   public Date getAlertdt() {
      return this.alertdt;
   }

   public void setReportid(String reportid) {
      this.reportid = reportid;
   }

   public String getReportid() {
      return this.reportid;
   }

   public void setResultid(String resultid) {
      this.resultid = resultid;
   }

   public String getResultid() {
      return this.resultid;
   }

   public void setRptunitid(String rptunitid) {
      this.rptunitid = rptunitid;
   }

   public String getRptunitid() {
      return this.rptunitid;
   }

   public void setRptunitname(String rptunitname) {
      this.rptunitname = rptunitname;
   }

   public String getRptunitname() {
      return this.rptunitname;
   }

   public void setHospitalcode(String hospitalcode) {
      this.hospitalcode = hospitalcode;
   }

   public String getHospitalcode() {
      return this.hospitalcode;
   }

   public void setPatType(String patType) {
      this.patType = patType;
   }

   public String getPatType() {
      return this.patType;
   }

   public void setPatNo(String patNo) {
      this.patNo = patNo;
   }

   public String getPatNo() {
      return this.patNo;
   }

   public void setPatId(String patId) {
      this.patId = patId;
   }

   public String getPatId() {
      return this.patId;
   }

   public void setPatCardno(String patCardno) {
      this.patCardno = patCardno;
   }

   public String getPatCardno() {
      return this.patCardno;
   }

   public void setInpId(String inpId) {
      this.inpId = inpId;
   }

   public String getInpId() {
      return this.inpId;
   }

   public void setPatName(String patName) {
      this.patName = patName;
   }

   public String getPatName() {
      return this.patName;
   }

   public void setPatSex(String patSex) {
      this.patSex = patSex;
   }

   public String getPatSex() {
      return this.patSex;
   }

   public void setPatAgestr(String patAgestr) {
      this.patAgestr = patAgestr;
   }

   public String getPatAgestr() {
      return this.patAgestr;
   }

   public void setReqWardno(String reqWardno) {
      this.reqWardno = reqWardno;
   }

   public String getReqWardno() {
      return this.reqWardno;
   }

   public void setReqBedno(String reqBedno) {
      this.reqBedno = reqBedno;
   }

   public String getReqBedno() {
      return this.reqBedno;
   }

   public void setReqDeptno(String reqDeptno) {
      this.reqDeptno = reqDeptno;
   }

   public String getReqDeptno() {
      return this.reqDeptno;
   }

   public void setReqDocno(String reqDocno) {
      this.reqDocno = reqDocno;
   }

   public String getReqDocno() {
      return this.reqDocno;
   }

   public void setEmerFlag(String emerFlag) {
      this.emerFlag = emerFlag;
   }

   public String getEmerFlag() {
      return this.emerFlag;
   }

   public void setSpecimenName(String specimenName) {
      this.specimenName = specimenName;
   }

   public String getSpecimenName() {
      return this.specimenName;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   public String getBarcode() {
      return this.barcode;
   }

   public void setRecieveDt(Date recieveDt) {
      this.recieveDt = recieveDt;
   }

   public Date getRecieveDt() {
      return this.recieveDt;
   }

   public void setDescriptions(String descriptions) {
      this.descriptions = descriptions;
   }

   public String getDescriptions() {
      return this.descriptions;
   }

   public void setRptItemid(String rptItemid) {
      this.rptItemid = rptItemid;
   }

   public String getRptItemid() {
      return this.rptItemid;
   }

   public void setRptItemcode(String rptItemcode) {
      this.rptItemcode = rptItemcode;
   }

   public String getRptItemcode() {
      return this.rptItemcode;
   }

   public void setItemcodeEn(String itemcodeEn) {
      this.itemcodeEn = itemcodeEn;
   }

   public String getItemcodeEn() {
      return this.itemcodeEn;
   }

   public void setRptItemname(String rptItemname) {
      this.rptItemname = rptItemname;
   }

   public String getRptItemname() {
      return this.rptItemname;
   }

   public void setExternalcode1(String externalcode1) {
      this.externalcode1 = externalcode1;
   }

   public String getExternalcode1() {
      return this.externalcode1;
   }

   public void setExternalcode2(String externalcode2) {
      this.externalcode2 = externalcode2;
   }

   public String getExternalcode2() {
      return this.externalcode2;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public String getResult() {
      return this.result;
   }

   public void setResult1(String result1) {
      this.result1 = result1;
   }

   public String getResult1() {
      return this.result1;
   }

   public void setResult2(String result2) {
      this.result2 = result2;
   }

   public String getResult2() {
      return this.result2;
   }

   public void setResult3(String result3) {
      this.result3 = result3;
   }

   public String getResult3() {
      return this.result3;
   }

   public void setResultFlag(String resultFlag) {
      this.resultFlag = resultFlag;
   }

   public String getResultFlag() {
      return this.resultFlag;
   }

   public void setResultUnit(String resultUnit) {
      this.resultUnit = resultUnit;
   }

   public String getResultUnit() {
      return this.resultUnit;
   }

   public void setResultRef(String resultRef) {
      this.resultRef = resultRef;
   }

   public String getResultRef() {
      return this.resultRef;
   }

   public void setRedoFlag(String redoFlag) {
      this.redoFlag = redoFlag;
   }

   public String getRedoFlag() {
      return this.redoFlag;
   }

   public void setRedoResult(String redoResult) {
      this.redoResult = redoResult;
   }

   public String getRedoResult() {
      return this.redoResult;
   }

   public void setAlertrules(String alertrules) {
      this.alertrules = alertrules;
   }

   public String getAlertrules() {
      return this.alertrules;
   }

   public String getRepDeptCd() {
      return this.repDeptCd;
   }

   public void setRepDeptCd(String repDeptCd) {
      this.repDeptCd = repDeptCd;
   }

   public String getRepDeptName() {
      return this.repDeptName;
   }

   public void setRepDeptName(String repDeptName) {
      this.repDeptName = repDeptName;
   }

   public String getRepDocCd() {
      return this.repDocCd;
   }

   public void setRepDocCd(String repDocCd) {
      this.repDocCd = repDocCd;
   }

   public String getRepDocName() {
      return this.repDocName;
   }

   public void setRepDocName(String repDocName) {
      this.repDocName = repDocName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("resultalertid", this.getResultalertid()).append("alertman", this.getAlertman()).append("alertdt", this.getAlertdt()).append("reportid", this.getReportid()).append("resultid", this.getResultid()).append("rptunitid", this.getRptunitid()).append("rptunitname", this.getRptunitname()).append("hospitalcode", this.getHospitalcode()).append("patType", this.getPatType()).append("patNo", this.getPatNo()).append("patId", this.getPatId()).append("patCardno", this.getPatCardno()).append("inpId", this.getInpId()).append("patName", this.getPatName()).append("patSex", this.getPatSex()).append("patAgestr", this.getPatAgestr()).append("reqWardno", this.getReqWardno()).append("reqBedno", this.getReqBedno()).append("reqDeptno", this.getReqDeptno()).append("reqDocno", this.getReqDocno()).append("emerFlag", this.getEmerFlag()).append("specimenName", this.getSpecimenName()).append("barcode", this.getBarcode()).append("recieveDt", this.getRecieveDt()).append("descriptions", this.getDescriptions()).append("rptItemid", this.getRptItemid()).append("rptItemcode", this.getRptItemcode()).append("itemcodeEn", this.getItemcodeEn()).append("rptItemname", this.getRptItemname()).append("externalcode1", this.getExternalcode1()).append("externalcode2", this.getExternalcode2()).append("result", this.getResult()).append("result1", this.getResult1()).append("result2", this.getResult2()).append("result3", this.getResult3()).append("resultFlag", this.getResultFlag()).append("resultUnit", this.getResultUnit()).append("resultRef", this.getResultRef()).append("redoFlag", this.getRedoFlag()).append("redoResult", this.getRedoResult()).append("alertrules", this.getAlertrules()).append("repDeptCd", this.getRepDeptCd()).append("repDeptName", this.getRepDeptName()).append("repDocCd", this.getRepDocCd()).append("repDocName", this.getRepDocName()).toString();
   }
}
