package com.emr.project.dr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdCaConsApply extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String patientId;
   @Excel(
      name = "住院号",
      sort = 0
   )
   private String inpNo;
   private Integer visitId;
   @Excel(
      name = "患者姓名",
      sort = 1
   )
   private String patientName;
   private String sex;
   private String sexCd;
   private String age;
   @Excel(
      name = "病床号",
      sort = 2
   )
   private String bedNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date inhosTime;
   private String consTypeCd;
   @Excel(
      name = "会诊类型",
      sort = 6
   )
   private String consTypeName;
   private String applyDeptCd;
   @Excel(
      name = "所属科室",
      sort = 3
   )
   private String applyDeptName;
   private String applyDocCd;
   @Excel(
      name = "发起人",
      sort = 4
   )
   private String applyDocName;
   private String applyDocTitleCd;
   private String applyDocTitleName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "发起时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm",
      sort = 5
   )
   private Date applyDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date applySignDate;
   private String consDeptCd;
   private String consDeptName;
   private String invDocCd;
   private String invDocName;
   private String invDocTitleCd;
   private String invDocTitleName;
   private String consDocCd;
   private String consDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "会诊时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm",
      sort = 7
   )
   private Date consDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "会诊记录完成时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm",
      sort = 8
   )
   private Date consSignDate;
   private String patCondSesc;
   private String consPurp;
   private String state;
   private String genReview;
   private String phyExam;
   private String consDiag;
   private String consOpinion;
   private String applySatiCd;
   private String applySatiName;
   private String applyEavlCd;
   private String applyEavlName;
   private String applyEavlDesc;
   private String consSatiCd;
   private String consSatiName;
   private String consEavlCd;
   private String consEavlName;
   private String consEavlDesc;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date altDate;
   private String altPerCode;
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date creDate;
   private String crePerCode;
   private String crePerName;
   private String phone;
   private Long mrFileId;
   private String linkMan;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date delDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date acceptDate;
   private String orderNo;
   private String applyDiag;

   public String getApplyDiag() {
      return this.applyDiag;
   }

   public void setApplyDiag(String applyDiag) {
      this.applyDiag = applyDiag;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public Date getAcceptDate() {
      return this.acceptDate;
   }

   public void setAcceptDate(Date acceptDate) {
      this.acceptDate = acceptDate;
   }

   public Date getDelDate() {
      return this.delDate;
   }

   public void setDelDate(Date delDate) {
      this.delDate = delDate;
   }

   public String getLinkMan() {
      return this.linkMan;
   }

   public void setLinkMan(String linkMan) {
      this.linkMan = linkMan;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
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

   public void setVisitId(Integer visitId) {
      this.visitId = visitId;
   }

   public Integer getVisitId() {
      return this.visitId;
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

   public void setAge(String age) {
      this.age = age;
   }

   public String getAge() {
      return this.age;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setConsTypeCd(String consTypeCd) {
      this.consTypeCd = consTypeCd;
   }

   public String getConsTypeCd() {
      return this.consTypeCd;
   }

   public void setConsTypeName(String consTypeName) {
      this.consTypeName = consTypeName;
   }

   public String getConsTypeName() {
      return this.consTypeName;
   }

   public void setApplyDeptCd(String applyDeptCd) {
      this.applyDeptCd = applyDeptCd;
   }

   public String getApplyDeptCd() {
      return this.applyDeptCd;
   }

   public void setApplyDeptName(String applyDeptName) {
      this.applyDeptName = applyDeptName;
   }

   public String getApplyDeptName() {
      return this.applyDeptName;
   }

   public void setApplyDocCd(String applyDocCd) {
      this.applyDocCd = applyDocCd;
   }

   public String getApplyDocCd() {
      return this.applyDocCd;
   }

   public void setApplyDocName(String applyDocName) {
      this.applyDocName = applyDocName;
   }

   public String getApplyDocName() {
      return this.applyDocName;
   }

   public void setApplyDocTitleCd(String applyDocTitleCd) {
      this.applyDocTitleCd = applyDocTitleCd;
   }

   public String getApplyDocTitleCd() {
      return this.applyDocTitleCd;
   }

   public void setApplyDocTitleName(String applyDocTitleName) {
      this.applyDocTitleName = applyDocTitleName;
   }

   public String getApplyDocTitleName() {
      return this.applyDocTitleName;
   }

   public void setApplyDate(Date applyDate) {
      this.applyDate = applyDate;
   }

   public Date getApplyDate() {
      return this.applyDate;
   }

   public void setApplySignDate(Date applySignDate) {
      this.applySignDate = applySignDate;
   }

   public Date getApplySignDate() {
      return this.applySignDate;
   }

   public void setConsDeptCd(String consDeptCd) {
      this.consDeptCd = consDeptCd;
   }

   public String getConsDeptCd() {
      return this.consDeptCd;
   }

   public void setConsDeptName(String consDeptName) {
      this.consDeptName = consDeptName;
   }

   public String getConsDeptName() {
      return this.consDeptName;
   }

   public void setInvDocCd(String invDocCd) {
      this.invDocCd = invDocCd;
   }

   public String getInvDocCd() {
      return this.invDocCd;
   }

   public void setInvDocName(String invDocName) {
      this.invDocName = invDocName;
   }

   public String getInvDocName() {
      return this.invDocName;
   }

   public void setInvDocTitleCd(String invDocTitleCd) {
      this.invDocTitleCd = invDocTitleCd;
   }

   public String getInvDocTitleCd() {
      return this.invDocTitleCd;
   }

   public void setInvDocTitleName(String invDocTitleName) {
      this.invDocTitleName = invDocTitleName;
   }

   public String getInvDocTitleName() {
      return this.invDocTitleName;
   }

   public void setConsDocCd(String consDocCd) {
      this.consDocCd = consDocCd;
   }

   public String getConsDocCd() {
      return this.consDocCd;
   }

   public void setConsDocName(String consDocName) {
      this.consDocName = consDocName;
   }

   public String getConsDocName() {
      return this.consDocName;
   }

   public void setConsDate(Date consDate) {
      this.consDate = consDate;
   }

   public Date getConsDate() {
      return this.consDate;
   }

   public void setConsSignDate(Date consSignDate) {
      this.consSignDate = consSignDate;
   }

   public Date getConsSignDate() {
      return this.consSignDate;
   }

   public void setPatCondSesc(String patCondSesc) {
      this.patCondSesc = patCondSesc;
   }

   public String getPatCondSesc() {
      return this.patCondSesc;
   }

   public void setConsPurp(String consPurp) {
      this.consPurp = consPurp;
   }

   public String getConsPurp() {
      return this.consPurp;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getState() {
      return this.state;
   }

   public void setGenReview(String genReview) {
      this.genReview = genReview;
   }

   public String getGenReview() {
      return this.genReview;
   }

   public void setPhyExam(String phyExam) {
      this.phyExam = phyExam;
   }

   public String getPhyExam() {
      return this.phyExam;
   }

   public void setConsDiag(String consDiag) {
      this.consDiag = consDiag;
   }

   public String getConsDiag() {
      return this.consDiag;
   }

   public void setConsOpinion(String consOpinion) {
      this.consOpinion = consOpinion;
   }

   public String getConsOpinion() {
      return this.consOpinion;
   }

   public void setApplySatiCd(String applySatiCd) {
      this.applySatiCd = applySatiCd;
   }

   public String getApplySatiCd() {
      return this.applySatiCd;
   }

   public void setApplySatiName(String applySatiName) {
      this.applySatiName = applySatiName;
   }

   public String getApplySatiName() {
      return this.applySatiName;
   }

   public void setApplyEavlCd(String applyEavlCd) {
      this.applyEavlCd = applyEavlCd;
   }

   public String getApplyEavlCd() {
      return this.applyEavlCd;
   }

   public void setApplyEavlName(String applyEavlName) {
      this.applyEavlName = applyEavlName;
   }

   public String getApplyEavlName() {
      return this.applyEavlName;
   }

   public void setApplyEavlDesc(String applyEavlDesc) {
      this.applyEavlDesc = applyEavlDesc;
   }

   public String getApplyEavlDesc() {
      return this.applyEavlDesc;
   }

   public void setConsSatiCd(String consSatiCd) {
      this.consSatiCd = consSatiCd;
   }

   public String getConsSatiCd() {
      return this.consSatiCd;
   }

   public void setConsSatiName(String consSatiName) {
      this.consSatiName = consSatiName;
   }

   public String getConsSatiName() {
      return this.consSatiName;
   }

   public void setConsEavlCd(String consEavlCd) {
      this.consEavlCd = consEavlCd;
   }

   public String getConsEavlCd() {
      return this.consEavlCd;
   }

   public void setConsEavlName(String consEavlName) {
      this.consEavlName = consEavlName;
   }

   public String getConsEavlName() {
      return this.consEavlName;
   }

   public void setConsEavlDesc(String consEavlDesc) {
      this.consEavlDesc = consEavlDesc;
   }

   public String getConsEavlDesc() {
      return this.consEavlDesc;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("patientId", this.getPatientId()).append("inpNo", this.getInpNo()).append("visitId", this.getVisitId()).append("patientName", this.getPatientName()).append("sex", this.getSex()).append("sexCd", this.getSexCd()).append("age", this.getAge()).append("bedNo", this.getBedNo()).append("inhosTime", this.getInhosTime()).append("consTypeCd", this.getConsTypeCd()).append("consTypeName", this.getConsTypeName()).append("applyDeptCd", this.getApplyDeptCd()).append("applyDeptName", this.getApplyDeptName()).append("applyDocCd", this.getApplyDocCd()).append("applyDocName", this.getApplyDocName()).append("applyDocTitleCd", this.getApplyDocTitleCd()).append("applyDocTitleName", this.getApplyDocTitleName()).append("applyDate", this.getApplyDate()).append("applySignDate", this.getApplySignDate()).append("consDeptCd", this.getConsDeptCd()).append("consDeptName", this.getConsDeptName()).append("invDocCd", this.getInvDocCd()).append("invDocName", this.getInvDocName()).append("invDocTitleCd", this.getInvDocTitleCd()).append("invDocTitleName", this.getInvDocTitleName()).append("consDocCd", this.getConsDocCd()).append("consDocName", this.getConsDocName()).append("consDate", this.getConsDate()).append("consSignDate", this.getConsSignDate()).append("patCondSesc", this.getPatCondSesc()).append("consPurp", this.getConsPurp()).append("state", this.getState()).append("genReview", this.getGenReview()).append("phyExam", this.getPhyExam()).append("consDiag", this.getConsDiag()).append("consOpinion", this.getConsOpinion()).append("applySatiCd", this.getApplySatiCd()).append("applySatiName", this.getApplySatiName()).append("applyEavlCd", this.getApplyEavlCd()).append("applyEavlName", this.getApplyEavlName()).append("applyEavlDesc", this.getApplyEavlDesc()).append("consSatiCd", this.getConsSatiCd()).append("consSatiName", this.getConsSatiName()).append("consEavlCd", this.getConsEavlCd()).append("consEavlName", this.getConsEavlName()).append("consEavlDesc", this.getConsEavlDesc()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("phone", this.getPhone()).append("mrFileId", this.getMrFileId()).append("linkMan", this.getLinkMan()).append("delDate", this.getDelDate()).append("acceptDate", this.getAcceptDate()).append("orderNo", this.getOrderNo()).toString();
   }
}
