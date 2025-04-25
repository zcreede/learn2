package com.emr.project.tmpl.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ElemAttri extends BaseEntity {
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
      name = "控件元素名称   在模板中的控件名称 用于识别在模板中的标识"
   )
   private String contElemName;
   @Excel(
      name = "元素ID   在元素管理表的ID"
   )
   private Long elemId;
   @Excel(
      name = "元素名称  在元素管理表的名称"
   )
   private String elemName;
   @Excel(
      name = "元素编码 在元素管理表的编码"
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
      name = "元素属性 以JSON格式存储"
   )
   private String elemAttri;
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
   private String linkData;
   private String doCode;
   private String triggerWay;
   private String tmplContElemName;

   public ElemAttri(String contElemName, Long elemId, String elemName, String elemCd, String typeFlag, String contType, String elemAttri, String tmplContElemName, String linkData, String doCode, String triggerWay) {
      this.contElemName = contElemName;
      this.elemId = elemId;
      this.elemName = elemName;
      this.elemCd = elemCd;
      this.typeFlag = typeFlag;
      this.contType = contType;
      this.elemAttri = elemAttri;
      this.tmplContElemName = tmplContElemName;
      this.linkData = linkData;
      this.doCode = doCode;
      this.triggerWay = triggerWay;
   }

   public String getTmplContElemName() {
      return this.tmplContElemName;
   }

   public void setTmplContElemName(String tmplContElemName) {
      this.tmplContElemName = tmplContElemName;
   }

   public ElemAttri() {
   }

   public String getDoCode() {
      return this.doCode;
   }

   public void setDoCode(String doCode) {
      this.doCode = doCode;
   }

   public String getTriggerWay() {
      return this.triggerWay;
   }

   public void setTriggerWay(String triggerWay) {
      this.triggerWay = triggerWay;
   }

   public String getLinkData() {
      return this.linkData;
   }

   public void setLinkData(String linkData) {
      this.linkData = linkData;
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

   public void setElemAttri(String elemAttri) {
      this.elemAttri = elemAttri;
   }

   public String getElemAttri() {
      return this.elemAttri;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("tempId", this.getTempId()).append("tempName", this.getTempName()).append("contElemName", this.getContElemName()).append("elemId", this.getElemId()).append("elemName", this.getElemName()).append("elemCd", this.getElemCd()).append("typeFlag", this.getTypeFlag()).append("contType", this.getContType()).append("elemAttri", this.getElemAttri()).append("creDate", this.getCreDate()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerName", this.getAltPerName()).toString();
   }
}
