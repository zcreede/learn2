package com.emr.project.tmpl.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ElemMacro extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "模板ID"
   )
   private Long tempId;
   @Excel(
      name = "模板名称"
   )
   private String tempName;
   @Excel(
      name = "病历类型"
   )
   private String tempType;
   @Excel(
      name = "控件元素名称  控件在模板中识别名称"
   )
   private String contElemName;
   @Excel(
      name = "元素ID   元素管理表中的ID"
   )
   private Long elemId;
   @Excel(
      name = "元素名称  元素管理表中的名称"
   )
   private String elemName;
   @Excel(
      name = "元素编码  元素管理表中的编码"
   )
   private String elemCd;
   @Excel(
      name = "元素类型标识(1: 数据元控件   2;数据组  3：区域   A：医学公式   "
   )
   private String typeFlag;
   @Excel(
      name = "控件类型：1：文体框，2：数字输入框，3：单选下拉，4：多选下拉，5：单选组合下拉框( 可编辑 )  6 多选组合下拉框( 可编辑 )   7：日期输入，8：复选框   9 弹出选择框  10 有无选择框 11  患者签名 12 患者签名    A1:牙齿医学公式  A2:视野医学公式  A3:月经 A4: 文本 A5:瞳孔 A6: 光定位 A7: 胎动"
   )
   private String contType;
   @Excel(
      name = "替换方式"
   )
   private String replMeth;
   @Excel(
      name = "取值表"
   )
   private String sourTable;
   @Excel(
      name = "取值字段"
   )
   private String sourColu;
   @Excel(
      name = "数据格式"
   )
   private String dataForm;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   private String replType;
   private String changeAlertFlag;

   public String getChangeAlertFlag() {
      return this.changeAlertFlag;
   }

   public void setChangeAlertFlag(String changeAlertFlag) {
      this.changeAlertFlag = changeAlertFlag;
   }

   public String getReplType() {
      return this.replType;
   }

   public void setReplType(String replType) {
      this.replType = replType;
   }

   public ElemMacro() {
   }

   public ElemMacro(String contElemName, Long elemId, String elemName, String elemCd, String typeFlag, String contType, String replMeth, String sourTable, String sourColu) {
      this.contElemName = contElemName;
      this.elemId = elemId;
      this.elemName = elemName;
      this.elemCd = elemCd;
      this.typeFlag = typeFlag;
      this.contType = contType;
      this.replMeth = replMeth;
      this.sourTable = sourTable;
      this.sourColu = sourColu;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setTempId(Long tempId) {
      this.tempId = tempId;
   }

   public Long getTempId() {
      return this.tempId;
   }

   public void setTempName(String tempName) {
      this.tempName = tempName;
   }

   public String getTempName() {
      return this.tempName;
   }

   public void setTempType(String tempType) {
      this.tempType = tempType;
   }

   public String getTempType() {
      return this.tempType;
   }

   public void setContElemName(String contElemName) {
      this.contElemName = contElemName;
   }

   public String getContElemName() {
      return this.contElemName;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
   }

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public void setTypeFlag(String typeFlag) {
      this.typeFlag = typeFlag;
   }

   public String getTypeFlag() {
      return this.typeFlag;
   }

   public void setContType(String contType) {
      this.contType = contType;
   }

   public String getContType() {
      return this.contType;
   }

   public void setReplMeth(String replMeth) {
      this.replMeth = replMeth;
   }

   public String getReplMeth() {
      return this.replMeth;
   }

   public void setSourTable(String sourTable) {
      this.sourTable = sourTable;
   }

   public String getSourTable() {
      return this.sourTable;
   }

   public void setSourColu(String sourColu) {
      this.sourColu = sourColu;
   }

   public String getSourColu() {
      return this.sourColu;
   }

   public void setDataForm(String dataForm) {
      this.dataForm = dataForm;
   }

   public String getDataForm() {
      return this.dataForm;
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

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("tempId", this.getTempId()).append("tempName", this.getTempName()).append("tempType", this.getTempType()).append("contElemName", this.getContElemName()).append("elemId", this.getElemId()).append("elemName", this.getElemName()).append("elemCd", this.getElemCd()).append("typeFlag", this.getTypeFlag()).append("contType", this.getContType()).append("replMeth", this.getReplMeth()).append("sourTable", this.getSourTable()).append("sourColu", this.getSourColu()).append("dataForm", this.getDataForm()).append("creDate", this.getCreDate()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerName", this.getAltPerName()).append("replType", this.getReplType()).toString();
   }
}
