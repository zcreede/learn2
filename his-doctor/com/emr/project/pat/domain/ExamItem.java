package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ExamItem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String id;
   private String orgCd;
   @Excel(
      name = "住院号",
      sort = 2
   )
   private String patientId;
   private String examRepNo;
   private String appCd;
   private String examAppOrg;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date appTime;
   private String appDeptCd;
   @Excel(
      name = "科室",
      sort = 1
   )
   private String appDeptName;
   private String appDocCd;
   private String appDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date examTime;
   @Excel(
      name = "检查部位",
      sort = 5
   )
   private String examPos;
   private String examItemCd;
   private String examItemName;
   @Excel(
      name = "危急值结果",
      align = Excel.Align.LEFT,
      sort = 4
   )
   private String examResObj;
   private String examResSub;
   private Long examResQua;
   private String examResUnit;
   private String examResCd;
   private String examRemark;
   private String imageWay;
   private String examRepOrg;
   private String examRepDeptCd;
   @Excel(
      name = "报告科室",
      sort = 7
   )
   private String examRepDeptName;
   private String examDocCd;
   @Excel(
      name = "检查人",
      sort = 8
   )
   private String examDocName;
   private String examTecCd;
   private String examTecName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "报告日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm",
      sort = 6
   )
   private Date examRepDate;
   private String repDocCd;
   private String repDocName;
   private String examWayCd;
   private String examWayName;
   private String examSpecNo;
   private String examSpecName;
   private String examType;
   private String conDocCd;
   private String conDocName;
   private String source;
   @Excel(
      name = "标识",
      readConverterExp = "1=门诊,2=急诊,3=住院,4=体检",
      sort = 21
   )
   private String identify;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date creDate;
   private String crePerCode;
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date altDate;
   private String altPerCode;
   private String altPerName;
   private String alterFlag;
   private String accnum;
   private String diagClicName;
   private String recDocCd;
   private Date recDate;

   public String getRecDocCd() {
      return this.recDocCd;
   }

   public void setRecDocCd(String recDocCd) {
      this.recDocCd = recDocCd;
   }

   public Date getRecDate() {
      return this.recDate;
   }

   public void setRecDate(Date recDate) {
      this.recDate = recDate;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return this.id;
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

   public void setExamRepNo(String examRepNo) {
      this.examRepNo = examRepNo;
   }

   public String getExamRepNo() {
      return this.examRepNo;
   }

   public void setAppCd(String appCd) {
      this.appCd = appCd;
   }

   public String getAppCd() {
      return this.appCd;
   }

   public void setExamAppOrg(String examAppOrg) {
      this.examAppOrg = examAppOrg;
   }

   public String getExamAppOrg() {
      return this.examAppOrg;
   }

   public void setAppTime(Date appTime) {
      this.appTime = appTime;
   }

   public Date getAppTime() {
      return this.appTime;
   }

   public void setAppDeptCd(String appDeptCd) {
      this.appDeptCd = appDeptCd;
   }

   public String getAppDeptCd() {
      return this.appDeptCd;
   }

   public void setAppDeptName(String appDeptName) {
      this.appDeptName = appDeptName;
   }

   public String getAppDeptName() {
      return this.appDeptName;
   }

   public void setAppDocCd(String appDocCd) {
      this.appDocCd = appDocCd;
   }

   public String getAppDocCd() {
      return this.appDocCd;
   }

   public void setAppDocName(String appDocName) {
      this.appDocName = appDocName;
   }

   public String getAppDocName() {
      return this.appDocName;
   }

   public void setExamTime(Date examTime) {
      this.examTime = examTime;
   }

   public Date getExamTime() {
      return this.examTime;
   }

   public void setExamPos(String examPos) {
      this.examPos = examPos;
   }

   public String getExamPos() {
      return this.examPos;
   }

   public void setExamItemCd(String examItemCd) {
      this.examItemCd = examItemCd;
   }

   public String getExamItemCd() {
      return this.examItemCd;
   }

   public void setExamItemName(String examItemName) {
      this.examItemName = examItemName;
   }

   public String getExamItemName() {
      return this.examItemName;
   }

   public void setExamResObj(String examResObj) {
      this.examResObj = examResObj;
   }

   public String getExamResObj() {
      return this.examResObj;
   }

   public void setExamResSub(String examResSub) {
      this.examResSub = examResSub;
   }

   public String getExamResSub() {
      return this.examResSub;
   }

   public void setExamResQua(Long examResQua) {
      this.examResQua = examResQua;
   }

   public Long getExamResQua() {
      return this.examResQua;
   }

   public void setExamResUnit(String examResUnit) {
      this.examResUnit = examResUnit;
   }

   public String getExamResUnit() {
      return this.examResUnit;
   }

   public void setExamResCd(String examResCd) {
      this.examResCd = examResCd;
   }

   public String getExamResCd() {
      return this.examResCd;
   }

   public void setExamRemark(String examRemark) {
      this.examRemark = examRemark;
   }

   public String getExamRemark() {
      return this.examRemark;
   }

   public void setImageWay(String imageWay) {
      this.imageWay = imageWay;
   }

   public String getImageWay() {
      return this.imageWay;
   }

   public void setExamRepOrg(String examRepOrg) {
      this.examRepOrg = examRepOrg;
   }

   public String getExamRepOrg() {
      return this.examRepOrg;
   }

   public void setExamRepDeptCd(String examRepDeptCd) {
      this.examRepDeptCd = examRepDeptCd;
   }

   public String getExamRepDeptCd() {
      return this.examRepDeptCd;
   }

   public void setExamRepDeptName(String examRepDeptName) {
      this.examRepDeptName = examRepDeptName;
   }

   public String getExamRepDeptName() {
      return this.examRepDeptName;
   }

   public void setExamDocCd(String examDocCd) {
      this.examDocCd = examDocCd;
   }

   public String getExamDocCd() {
      return this.examDocCd;
   }

   public void setExamDocName(String examDocName) {
      this.examDocName = examDocName;
   }

   public String getExamDocName() {
      return this.examDocName;
   }

   public void setExamTecCd(String examTecCd) {
      this.examTecCd = examTecCd;
   }

   public String getExamTecCd() {
      return this.examTecCd;
   }

   public void setExamTecName(String examTecName) {
      this.examTecName = examTecName;
   }

   public String getExamTecName() {
      return this.examTecName;
   }

   public void setExamRepDate(Date examRepDate) {
      this.examRepDate = examRepDate;
   }

   public Date getExamRepDate() {
      return this.examRepDate;
   }

   public void setRepDocCd(String repDocCd) {
      this.repDocCd = repDocCd;
   }

   public String getRepDocCd() {
      return this.repDocCd;
   }

   public void setRepDocName(String repDocName) {
      this.repDocName = repDocName;
   }

   public String getRepDocName() {
      return this.repDocName;
   }

   public void setExamWayCd(String examWayCd) {
      this.examWayCd = examWayCd;
   }

   public String getExamWayCd() {
      return this.examWayCd;
   }

   public void setExamWayName(String examWayName) {
      this.examWayName = examWayName;
   }

   public String getExamWayName() {
      return this.examWayName;
   }

   public void setExamSpecNo(String examSpecNo) {
      this.examSpecNo = examSpecNo;
   }

   public String getExamSpecNo() {
      return this.examSpecNo;
   }

   public void setExamSpecName(String examSpecName) {
      this.examSpecName = examSpecName;
   }

   public String getExamSpecName() {
      return this.examSpecName;
   }

   public void setExamType(String examType) {
      this.examType = examType;
   }

   public String getExamType() {
      return this.examType;
   }

   public void setConDocCd(String conDocCd) {
      this.conDocCd = conDocCd;
   }

   public String getConDocCd() {
      return this.conDocCd;
   }

   public void setConDocName(String conDocName) {
      this.conDocName = conDocName;
   }

   public String getConDocName() {
      return this.conDocName;
   }

   public void setSource(String source) {
      this.source = source;
   }

   public String getSource() {
      return this.source;
   }

   public void setIdentify(String identify) {
      this.identify = identify;
   }

   public String getIdentify() {
      return this.identify;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
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

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
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

   public void setAlterFlag(String alterFlag) {
      this.alterFlag = alterFlag;
   }

   public String getAlterFlag() {
      return this.alterFlag;
   }

   public void setAccnum(String accnum) {
      this.accnum = accnum;
   }

   public String getAccnum() {
      return this.accnum;
   }

   public void setDiagClicName(String diagClicName) {
      this.diagClicName = diagClicName;
   }

   public String getDiagClicName() {
      return this.diagClicName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("patientId", this.getPatientId()).append("examRepNo", this.getExamRepNo()).append("appCd", this.getAppCd()).append("examAppOrg", this.getExamAppOrg()).append("appTime", this.getAppTime()).append("appDeptCd", this.getAppDeptCd()).append("appDeptName", this.getAppDeptName()).append("appDocCd", this.getAppDocCd()).append("appDocName", this.getAppDocName()).append("examTime", this.getExamTime()).append("examPos", this.getExamPos()).append("examItemCd", this.getExamItemCd()).append("examItemName", this.getExamItemName()).append("examResObj", this.getExamResObj()).append("examResSub", this.getExamResSub()).append("examResQua", this.getExamResQua()).append("examResUnit", this.getExamResUnit()).append("examResCd", this.getExamResCd()).append("examRemark", this.getExamRemark()).append("imageWay", this.getImageWay()).append("examRepOrg", this.getExamRepOrg()).append("examRepDeptCd", this.getExamRepDeptCd()).append("examRepDeptName", this.getExamRepDeptName()).append("examDocCd", this.getExamDocCd()).append("examDocName", this.getExamDocName()).append("examTecCd", this.getExamTecCd()).append("examTecName", this.getExamTecName()).append("examRepDate", this.getExamRepDate()).append("repDocCd", this.getRepDocCd()).append("repDocName", this.getRepDocName()).append("examWayCd", this.getExamWayCd()).append("examWayName", this.getExamWayName()).append("examSpecNo", this.getExamSpecNo()).append("examSpecName", this.getExamSpecName()).append("examType", this.getExamType()).append("conDocCd", this.getConDocCd()).append("conDocName", this.getConDocName()).append("source", this.getSource()).append("identify", this.getIdentify()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("alterFlag", this.getAlterFlag()).append("accnum", this.getAccnum()).append("diagClicName", this.getDiagClicName()).toString();
   }
}
