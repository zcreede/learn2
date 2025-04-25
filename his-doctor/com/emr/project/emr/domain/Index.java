package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel("病历索引对象")
public class Index extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "医疗机构名称"
   )
   private String orgName;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "作用病历类型",
      readConverterExp = "作用病历类型（ID)"
   )
   private String mrType;
   @Excel(
      name = "病历文件序号"
   )
   private Long mrFileNo;
   @Excel(
      name = "病历文件名称"
   )
   @ApiModelProperty("病历文档名")
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
      name = "病历文件显示名称"
   )
   private String mrFileShowName;
   @Excel(
      name = "病历文件分类编号"
   )
   private String mrTypeCd;
   @Excel(
      name = "病历文件分类名称"
   )
   @ApiModelProperty("病历类型")
   private String mrTypeName;
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
      name = "01暂存；03未提交；05待二级审签；07待三级审签；08完成；"
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
   @Excel(
      name = "病历提交修改次数"
   )
   private Long mrAlterTimes;
   @Excel(
      name = "打印标志"
   )
   private String printFlag;
   @Excel(
      name = "病历打印次数"
   )
   private Long mrPrintTimes;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "病历修改申请期限",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date mraaTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "首次提交签名时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
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
   @ApiModelProperty("科室")
   private String cpDeptName;
   @Excel(
      name = "病历大类",
      readConverterExp = "1=：住院病历；2：护理病历；3.门（急"
   )
   private String mrClass;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "记录日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date recoDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "质控抽查时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date contChecTime;
   @Excel(
      name = "质控抽查状态",
      readConverterExp = "0=：未抽查；1：已抽查"
   )
   private String contChecState;
   @Excel(
      name = "质控抽查人编码"
   )
   private String contChecCd;
   @Excel(
      name = "质控抽查人姓名"
   )
   private String contChecName;
   @Excel(
      name = "实习医师编码"
   )
   private String intDocCd;
   @Excel(
      name = "实习医师姓名"
   )
   private String intDocName;
   @Excel(
      name = "复印标志"
   )
   private String copyFlag;
   @Excel(
      name = "EMR PDF HTML，用于区分病历文件打开方式，主要解决其他系统病历浏览的问题"
   )
   private String fileType;
   @Excel(
      name = "病床号"
   )
   private String bedNo;
   @Excel(
      name = "模板文件是否支持多医生修改"
   )
   private String moreDoc;
   @Excel(
      name = "婴儿ID"
   )
   private String babyId;
   @Excel(
      name = "病历封锁状态"
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
      name = "修改人姓名"
   )
   @ApiModelProperty("操作员")
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   @ApiModelProperty("操作员工号")
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
   @ApiModelProperty("删除时间")
   private Date altDate;
   @ApiModelProperty("住院号")
   private String inpNo;
   @ApiModelProperty("患者姓名")
   private String patientName;
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
      name = "最迟完成时间"
   )
   private Date lastFinishTime;
   @Excel(
      name = "最迟完成时间对应的时效规则ID"
   )
   private Long lastFinishRuleId;
   @Excel(
      name = "病历内容(txt)"
   )
   private String mrContent;
   private String freeMoveType;
   private String mrStates;
   private List mrStateList;
   private List secLevelList;
   private String orderNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date firstPrintTime;

   public String getFreeMoveType() {
      return this.freeMoveType;
   }

   public void setFreeMoveType(String freeMoveType) {
      this.freeMoveType = freeMoveType;
   }

   public String getMrContent() {
      return this.mrContent;
   }

   public void setMrContent(String mrContent) {
      this.mrContent = mrContent;
   }

   public Date getFirstPrintTime() {
      return this.firstPrintTime;
   }

   public void setFirstPrintTime(Date firstPrintTime) {
      this.firstPrintTime = firstPrintTime;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public Date getLastFinishTime() {
      return this.lastFinishTime;
   }

   public void setLastFinishTime(Date lastFinishTime) {
      this.lastFinishTime = lastFinishTime;
   }

   public List getSecLevelList() {
      return this.secLevelList;
   }

   public void setSecLevelList(List secLevelList) {
      this.secLevelList = secLevelList;
   }

   public List getMrStateList() {
      return this.mrStateList;
   }

   public void setMrStateList(List mrStateList) {
      this.mrStateList = mrStateList;
   }

   public String getMrStates() {
      return this.mrStates;
   }

   public void setMrStates(String mrStates) {
      this.mrStates = mrStates;
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

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
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

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrFileNo(Long mrFileNo) {
      this.mrFileNo = mrFileNo;
   }

   public Long getMrFileNo() {
      return this.mrFileNo;
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

   public void setMrTypeCd(String mrTypeCd) {
      this.mrTypeCd = mrTypeCd;
   }

   public String getMrTypeCd() {
      return this.mrTypeCd;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
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

   public void setMrAlterTimes(Long mrAlterTimes) {
      this.mrAlterTimes = mrAlterTimes;
   }

   public Long getMrAlterTimes() {
      return this.mrAlterTimes;
   }

   public void setPrintFlag(String printFlag) {
      this.printFlag = printFlag;
   }

   public String getPrintFlag() {
      return this.printFlag;
   }

   public void setMrPrintTimes(Long mrPrintTimes) {
      this.mrPrintTimes = mrPrintTimes;
   }

   public Long getMrPrintTimes() {
      return this.mrPrintTimes;
   }

   public void setMraaTime(Date mraaTime) {
      this.mraaTime = mraaTime;
   }

   public Date getMraaTime() {
      return this.mraaTime;
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

   public void setMrClass(String mrClass) {
      this.mrClass = mrClass;
   }

   public String getMrClass() {
      return this.mrClass;
   }

   public void setRecoDate(Date recoDate) {
      this.recoDate = recoDate;
   }

   public Date getRecoDate() {
      return this.recoDate;
   }

   public void setContChecTime(Date contChecTime) {
      this.contChecTime = contChecTime;
   }

   public Date getContChecTime() {
      return this.contChecTime;
   }

   public void setContChecState(String contChecState) {
      this.contChecState = contChecState;
   }

   public String getContChecState() {
      return this.contChecState;
   }

   public void setContChecCd(String contChecCd) {
      this.contChecCd = contChecCd;
   }

   public String getContChecCd() {
      return this.contChecCd;
   }

   public void setContChecName(String contChecName) {
      this.contChecName = contChecName;
   }

   public String getContChecName() {
      return this.contChecName;
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

   public void setCopyFlag(String copyFlag) {
      this.copyFlag = copyFlag;
   }

   public String getCopyFlag() {
      return this.copyFlag;
   }

   public void setFileType(String fileType) {
      this.fileType = fileType;
   }

   public String getFileType() {
      return this.fileType;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setMoreDoc(String moreDoc) {
      this.moreDoc = moreDoc;
   }

   public String getMoreDoc() {
      return this.moreDoc;
   }

   public void setBabyId(String babyId) {
      this.babyId = babyId;
   }

   public String getBabyId() {
      return this.babyId;
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

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public Long getLastFinishRuleId() {
      return this.lastFinishRuleId;
   }

   public void setLastFinishRuleId(Long lastFinishRuleId) {
      this.lastFinishRuleId = lastFinishRuleId;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("orgName", this.getOrgName()).append("patientId", this.getPatientId()).append("mrType", this.getMrType()).append("mrFileNo", this.getMrFileNo()).append("mrFileName", this.getMrFileName()).append("mrServPath", this.getMrServPath()).append("mrLocaPath", this.getMrLocaPath()).append("mrFileShowName", this.getMrFileShowName()).append("mrTypeCd", this.getMrTypeCd()).append("mrTypeName", this.getMrTypeName()).append("mrSetCd", this.getMrSetCd()).append("mrSetName", this.getMrSetName()).append("areaCode", this.getAreaCode()).append("wardName", this.getWardName()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("mrState", this.getMrState()).append("fsotFlag", this.getFsotFlag()).append("fsotHours", this.getFsotHours()).append("mrAlterTimes", this.getMrAlterTimes()).append("printFlag", this.getPrintFlag()).append("mrPrintTimes", this.getMrPrintTimes()).append("mraaTime", this.getMraaTime()).append("fsSignTime", this.getFsSignTime()).append("subPerCd", this.getSubPerCd()).append("subPerName", this.getSubPerName()).append("tempId", this.getTempId()).append("secLevel", this.getSecLevel()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("cpDeptCd", this.getCpDeptCd()).append("cpDeptName", this.getCpDeptName()).append("mrClass", this.getMrClass()).append("recoDate", this.getRecoDate()).append("contChecTime", this.getContChecTime()).append("contChecState", this.getContChecState()).append("contChecCd", this.getContChecCd()).append("contChecName", this.getContChecName()).append("intDocCd", this.getIntDocCd()).append("intDocName", this.getIntDocName()).append("copyFlag", this.getCopyFlag()).append("fileType", this.getFileType()).append("bedNo", this.getBedNo()).append("moreDoc", this.getMoreDoc()).append("babyId", this.getBabyId()).append("lockState", this.getLockState()).append("delFlag", this.getDelFlag()).append("creDate", this.getCreDate()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("uperDoct", this.getUperDoct()).append("uperDoctName", this.getUperDoctName()).append("uperTitle", this.getUperTitle()).append("uperTitleName", this.getUperTitleName()).append("orderNo", this.getOrderNo()).toString();
   }
}
