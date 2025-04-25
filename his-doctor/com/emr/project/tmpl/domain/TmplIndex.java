package com.emr.project.tmpl.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmplIndex extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "模板名称"
   )
   private String tempName;
   @Excel(
      name = "显示名称"
   )
   private String showName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "使用专业"
   )
   private String tempMajor;
   @Excel(
      name = "使用病种"
   )
   private String tempDisease;
   @Excel(
      name = "病历分类"
   )
   private String tempClass;
   @Excel(
      name = "模板类型",
      readConverterExp = "入=院记录、出院记录、页眉等"
   )
   private String tempType;
   @Excel(
      name = "模板状态",
      readConverterExp = "1=：录入；2：驳回；3：申请；4：审核通过"
   )
   private String tempState;
   @Excel(
      name = "模板文件"
   )
   private String tempFile;
   @Excel(
      name = "申请科室编码"
   )
   private String appDeptCd;
   @Excel(
      name = "申请科室名称"
   )
   private String appDeptName;
   @Excel(
      name = "申请医生编码"
   )
   private String appDocCd;
   @Excel(
      name = "申请医生名称"
   )
   private String appDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date appTime;
   @Excel(
      name = "审核科室编码"
   )
   private String conDeptCd;
   @Excel(
      name = "审核科室名称"
   )
   private String conDeptName;
   @Excel(
      name = "审核医师编码"
   )
   private String conDocCd;
   @Excel(
      name = "审核医师签名"
   )
   private String conDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "审核时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date conDate;
   @Excel(
      name = "审核意见"
   )
   private String conView;
   @Excel(
      name = "发布状态"
   )
   private String issState;
   @Excel(
      name = "类别",
      readConverterExp = "0=1：医生模板；02：护士模板"
   )
   private String tempSort;
   @Excel(
      name = "适用性别",
      readConverterExp = "1=：通用；2：男性；3：女性"
   )
   private String useSex;
   @Excel(
      name = "适用年龄",
      readConverterExp = "1=：通用；2：成人；3：儿童"
   )
   private String useAge;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
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
   @Excel(
      name = "序号"
   )
   private Integer tempNo;
   private String editType;
   private String tempEditState;
   private String tempEditFile;

   public String getTempEditState() {
      return this.tempEditState;
   }

   public void setTempEditState(String tempEditState) {
      this.tempEditState = tempEditState;
   }

   public String getTempEditFile() {
      return this.tempEditFile;
   }

   public void setTempEditFile(String tempEditFile) {
      this.tempEditFile = tempEditFile;
   }

   public String getEditType() {
      return this.editType;
   }

   public void setEditType(String editType) {
      this.editType = editType;
   }

   public Integer getTempNo() {
      return this.tempNo;
   }

   public void setTempNo(Integer tempNo) {
      this.tempNo = tempNo;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setTempName(String tempName) {
      this.tempName = tempName;
   }

   public String getTempName() {
      return this.tempName;
   }

   public void setShowName(String showName) {
      this.showName = showName;
   }

   public String getShowName() {
      return this.showName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setTempMajor(String tempMajor) {
      this.tempMajor = tempMajor;
   }

   public String getTempMajor() {
      return this.tempMajor;
   }

   public void setTempDisease(String tempDisease) {
      this.tempDisease = tempDisease;
   }

   public String getTempDisease() {
      return this.tempDisease;
   }

   public void setTempClass(String tempClass) {
      this.tempClass = tempClass;
   }

   public String getTempClass() {
      return this.tempClass;
   }

   public void setTempType(String tempType) {
      this.tempType = tempType;
   }

   public String getTempType() {
      return this.tempType;
   }

   public void setTempState(String tempState) {
      this.tempState = tempState;
   }

   public String getTempState() {
      return this.tempState;
   }

   public void setTempFile(String tempFile) {
      this.tempFile = tempFile;
   }

   public String getTempFile() {
      return this.tempFile;
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

   public void setAppTime(Date appTime) {
      this.appTime = appTime;
   }

   public Date getAppTime() {
      return this.appTime;
   }

   public void setConDeptCd(String conDeptCd) {
      this.conDeptCd = conDeptCd;
   }

   public String getConDeptCd() {
      return this.conDeptCd;
   }

   public void setConDeptName(String conDeptName) {
      this.conDeptName = conDeptName;
   }

   public String getConDeptName() {
      return this.conDeptName;
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

   public void setConDate(Date conDate) {
      this.conDate = conDate;
   }

   public Date getConDate() {
      return this.conDate;
   }

   public void setConView(String conView) {
      this.conView = conView;
   }

   public String getConView() {
      return this.conView;
   }

   public void setIssState(String issState) {
      this.issState = issState;
   }

   public String getIssState() {
      return this.issState;
   }

   public void setTempSort(String tempSort) {
      this.tempSort = tempSort;
   }

   public String getTempSort() {
      return this.tempSort;
   }

   public void setUseSex(String useSex) {
      this.useSex = useSex;
   }

   public String getUseSex() {
      return this.useSex;
   }

   public void setUseAge(String useAge) {
      this.useAge = useAge;
   }

   public String getUseAge() {
      return this.useAge;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("tempName", this.getTempName()).append("showName", this.getShowName()).append("inputstrphtc", this.getInputstrphtc()).append("tempMajor", this.getTempMajor()).append("tempDisease", this.getTempDisease()).append("tempClass", this.getTempClass()).append("tempType", this.getTempType()).append("tempState", this.getTempState()).append("tempFile", this.getTempFile()).append("appDeptCd", this.getAppDeptCd()).append("appDeptName", this.getAppDeptName()).append("appDocCd", this.getAppDocCd()).append("appDocName", this.getAppDocName()).append("appTime", this.getAppTime()).append("conDeptCd", this.getConDeptCd()).append("conDeptName", this.getConDeptName()).append("conDocCd", this.getConDocCd()).append("conDocName", this.getConDocName()).append("conDate", this.getConDate()).append("conView", this.getConView()).append("issState", this.getIssState()).append("remark", this.getRemark()).append("tempSort", this.getTempSort()).append("useSex", this.getUseSex()).append("useAge", this.getUseAge()).append("validFlag", this.getValidFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("editType", this.getEditType()).toString();
   }
}
