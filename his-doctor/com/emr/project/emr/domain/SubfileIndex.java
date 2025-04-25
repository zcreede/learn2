package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SubfileIndex extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "病程主文件索引ID"
   )
   private Long mainId;
   @Excel(
      name = "病历类型"
   )
   private String mrType;
   @Excel(
      name = "病历文件名称"
   )
   private String mrFileName;
   @Excel(
      name = "病历文件服务器存放路径"
   )
   private String mrServPath;
   @Excel(
      name = "病历文件本地存放路径"
   )
   private String mrLocaPath;
   @Excel(
      name = "病历文档标题"
   )
   private String mrFileShowName;
   @Excel(
      name = "病历所属数据集代码"
   )
   private String mrSetCd;
   @Excel(
      name = "病历所属数据集名称"
   )
   private String mrSetName;
   @Excel(
      name = "患者当前所在病区的编码"
   )
   private String areaCode;
   @Excel(
      name = "患者当前所在病区的名称"
   )
   private String wardName;
   @Excel(
      name = "患者当前所在科室代码"
   )
   private String deptCd;
   @Excel(
      name = "患者当前所在科室代码"
   )
   private String deptName;
   @Excel(
      name = "01暂存；03未提交；05提交主治医师；07提交主任医师；08完成；"
   )
   private String mrState;
   @Excel(
      name = "首次提交超时标志",
      readConverterExp = "1=：超时"
   )
   private Long fsotFlag;
   @Excel(
      name = "首次提交超时时长",
      readConverterExp = "单=位：小时"
   )
   private Long fsotHours;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "首次提交签名时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date fsSignTime;
   @Excel(
      name = "提交签名人编号"
   )
   private String subPerCd;
   @Excel(
      name = "提交签名人姓名"
   )
   private String subPerName;
   @Excel(
      name = "模板ID",
      readConverterExp = "医=疗机构编码+YYYYMMDDHH24MISS+三位随机数"
   )
   private Long tempId;
   @Excel(
      name = "密级"
   )
   private String secLevel;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "记录日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date recoDate;
   @Excel(
      name = "实习医师编码"
   )
   private String intDocCd;
   @Excel(
      name = "实习医师姓名"
   )
   private String intDocName;
   @Excel(
      name = "病床号"
   )
   private String bedNo;
   @Excel(
      name = "病历封存状态"
   )
   private Integer lockState;
   private String delFlag;
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
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人所属科室代码"
   )
   private String cpDeptCd;
   @Excel(
      name = "创建人所属科室名称"
   )
   private String cpDeptName;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
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
      name = "上级医师"
   )
   private String uperDoct;
   @Excel(
      name = "上级医师姓名"
   )
   private String uperDoctName;
   @Excel(
      name = "上级医师职称"
   )
   private String uperTitle;
   @Excel(
      name = "上级医师职称名称"
   )
   private String uperTitleName;
   @Excel(
      name = "区域名称"
   )
   private String contElemName;
   @Excel(
      name = "最迟完成时间"
   )
   private Date lastFinishTime;
   @Excel(
      name = "最迟完成时间对应的时效规则ID"
   )
   private Long lastFinishRuleId;
   private Boolean checked;
   private String orderNo;
   private Long changeDepBedId;
   private String paragraphElemName;
   private String emrTypeName;
   private String freeMoveType;
   private String signCertSn;
   private String mergeFlag;

   public String getFreeMoveType() {
      return this.freeMoveType;
   }

   public void setFreeMoveType(String freeMoveType) {
      this.freeMoveType = freeMoveType;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public Long getChangeDepBedId() {
      return this.changeDepBedId;
   }

   public void setChangeDepBedId(Long changeDepBedId) {
      this.changeDepBedId = changeDepBedId;
   }

   public String getParagraphElemName() {
      return this.paragraphElemName;
   }

   public void setParagraphElemName(String paragraphElemName) {
      this.paragraphElemName = paragraphElemName;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public Long getLastFinishRuleId() {
      return this.lastFinishRuleId;
   }

   public void setLastFinishRuleId(Long lastFinishRuleId) {
      this.lastFinishRuleId = lastFinishRuleId;
   }

   public Date getLastFinishTime() {
      return this.lastFinishTime;
   }

   public void setLastFinishTime(Date lastFinishTime) {
      this.lastFinishTime = lastFinishTime;
   }

   public Boolean getChecked() {
      return this.checked;
   }

   public void setChecked(Boolean checked) {
      this.checked = checked;
   }

   public String getContElemName() {
      return this.contElemName;
   }

   public void setContElemName(String contElemName) {
      this.contElemName = contElemName;
   }

   public String getUperDoct() {
      return this.uperDoct;
   }

   public void setUperDoct(String uperDoct) {
      this.uperDoct = uperDoct;
   }

   public String getUperDoctName() {
      return this.uperDoctName;
   }

   public void setUperDoctName(String uperDoctName) {
      this.uperDoctName = uperDoctName;
   }

   public String getUperTitle() {
      return this.uperTitle;
   }

   public void setUperTitle(String uperTitle) {
      this.uperTitle = uperTitle;
   }

   public String getUperTitleName() {
      return this.uperTitleName;
   }

   public void setUperTitleName(String uperTitleName) {
      this.uperTitleName = uperTitleName;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrFileName(String mrFileName) {
      this.mrFileName = mrFileName;
   }

   public String getMrFileName() {
      return this.mrFileName;
   }

   public void setMrServPath(String mrServPath) {
      this.mrServPath = mrServPath;
   }

   public String getMrServPath() {
      return this.mrServPath;
   }

   public void setMrLocaPath(String mrLocaPath) {
      this.mrLocaPath = mrLocaPath;
   }

   public String getMrLocaPath() {
      return this.mrLocaPath;
   }

   public void setMrFileShowName(String mrFileShowName) {
      this.mrFileShowName = mrFileShowName;
   }

   public String getMrFileShowName() {
      return this.mrFileShowName;
   }

   public void setMrSetCd(String mrSetCd) {
      this.mrSetCd = mrSetCd;
   }

   public String getMrSetCd() {
      return this.mrSetCd;
   }

   public void setMrSetName(String mrSetName) {
      this.mrSetName = mrSetName;
   }

   public String getMrSetName() {
      return this.mrSetName;
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

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setFsotFlag(Long fsotFlag) {
      this.fsotFlag = fsotFlag;
   }

   public Long getFsotFlag() {
      return this.fsotFlag;
   }

   public void setFsotHours(Long fsotHours) {
      this.fsotHours = fsotHours;
   }

   public Long getFsotHours() {
      return this.fsotHours;
   }

   public void setFsSignTime(Date fsSignTime) {
      this.fsSignTime = fsSignTime;
   }

   public Date getFsSignTime() {
      return this.fsSignTime;
   }

   public void setSubPerCd(String subPerCd) {
      this.subPerCd = subPerCd;
   }

   public String getSubPerCd() {
      return this.subPerCd;
   }

   public void setSubPerName(String subPerName) {
      this.subPerName = subPerName;
   }

   public String getSubPerName() {
      return this.subPerName;
   }

   public void setTempId(Long tempId) {
      this.tempId = tempId;
   }

   public Long getTempId() {
      return this.tempId;
   }

   public void setSecLevel(String secLevel) {
      this.secLevel = secLevel;
   }

   public String getSecLevel() {
      return this.secLevel;
   }

   public void setRecoDate(Date recoDate) {
      this.recoDate = recoDate;
   }

   public Date getRecoDate() {
      return this.recoDate;
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

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public Integer getLockState() {
      return this.lockState;
   }

   public void setLockState(Integer lockState) {
      this.lockState = lockState;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
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

   public void setCpDeptCd(String cpDeptCd) {
      this.cpDeptCd = cpDeptCd;
   }

   public String getCpDeptCd() {
      return this.cpDeptCd;
   }

   public void setCpDeptName(String cpDeptName) {
      this.cpDeptName = cpDeptName;
   }

   public String getCpDeptName() {
      return this.cpDeptName;
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

   public String getSignCertSn() {
      return this.signCertSn;
   }

   public void setSignCertSn(String signCertSn) {
      this.signCertSn = signCertSn;
   }

   public String getMergeFlag() {
      return this.mergeFlag;
   }

   public void setMergeFlag(String mergeFlag) {
      this.mergeFlag = mergeFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("mainId", this.getMainId()).append("mrType", this.getMrType()).append("mrFileName", this.getMrFileName()).append("mrServPath", this.getMrServPath()).append("mrLocaPath", this.getMrLocaPath()).append("mrFileShowName", this.getMrFileShowName()).append("mrSetCd", this.getMrSetCd()).append("mrSetName", this.getMrSetName()).append("areaCode", this.getAreaCode()).append("wardName", this.getWardName()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("mrState", this.getMrState()).append("fsotFlag", this.getFsotFlag()).append("fsotHours", this.getFsotHours()).append("fsSignTime", this.getFsSignTime()).append("subPerCd", this.getSubPerCd()).append("subPerName", this.getSubPerName()).append("tempId", this.getTempId()).append("secLevel", this.getSecLevel()).append("recoDate", this.getRecoDate()).append("intDocCd", this.getIntDocCd()).append("intDocName", this.getIntDocName()).append("bedNo", this.getBedNo()).append("lockState", this.getLockState()).append("delFlag", this.getDelFlag()).append("creDate", this.getCreDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("cpDeptCd", this.getCpDeptCd()).append("cpDeptName", this.getCpDeptName()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("uperDoct", this.getUperDoct()).append("uperDoctName", this.getUperDoctName()).append("uperTitle", this.getUperTitle()).append("uperTitleName", this.getUperTitleName()).append("orderNo", this.getOrderNo()).toString();
   }
}
