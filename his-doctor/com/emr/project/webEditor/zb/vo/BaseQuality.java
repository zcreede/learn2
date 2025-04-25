package com.emr.project.webEditor.zb.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.emr.common.constant.CommonConstants;

public class BaseQuality {
   private String clasId;
   private String clasName;
   private String unitId;
   private Long elemId;
   private String sourFlag;
   private String elemCd;
   private String elemName;
   private String inputstrphtc;
   private String validFlag;
   private String typeFlag;
   private String contType;
   private String windowName;
   private String consistencyCheck;
   private String specialVerify;
   private String isMacroReplace;
   private String macroField;
   private String macroReplaceType;
   private String elemSex;
   private String isNullPrint;
   @JSONField(
      name = "PlaceHolder"
   )
   private String PlaceHolder;
   @JSONField(
      name = "HelpTip"
   )
   private String HelpTip;
   @JSONField(
      name = "EditProtect"
   )
   private Boolean EditProtect;
   @JSONField(
      name = "DelFlag"
   )
   private String DelFlag;
   @JSONField(
      name = "DeleteProtect"
   )
   private Boolean DeleteProtect;
   @JSONField(
      name = "MustFillContent"
   )
   private Boolean MustFillContent;
   @JSONField(
      name = "ReverseEdit"
   )
   private Boolean ReverseEdit;
   @JSONField(
      name = "IsCtrlHidden"
   )
   private Boolean IsCtrlHidden;
   @JSONField(
      name = "ViewSecret"
   )
   private Boolean ViewSecret;
   @JSONField(
      name = "Printable"
   )
   private Boolean Printable;
   @JSONField(
      name = "BackgroundColor"
   )
   private String BackgroundColor = "dcdcdc";
   @JSONField(
      name = "IsNotRecordInXML"
   )
   private Boolean IsNotRecordInXML;
   private String sourceUnitId;
   private String sourceSetId;
   private String sourceTable;
   private String sourceColu;
   private String medicalformulaCon;
   @JSONField(
      name = "Tag"
   )
   private String Tag;
   @JSONField(
      name = "Type"
   )
   private String Type;
   @JSONField(
      name = "TypeName"
   )
   private String TypeName;
   @JSONField(
      name = "remark"
   )
   private String remark;
   @JSONField(
      name = "PlcHdrColor"
   )
   private String PlcHdrColor;
   private int sort;
   private String tmplId;
   private String remindFlag;
   private String tertiarySign;

   public BaseQuality() {
      this.IsNotRecordInXML = CommonConstants.BOOL_TRUE;
   }

   public String getTertiarySign() {
      return this.tertiarySign;
   }

   public void setTertiarySign(String tertiarySign) {
      this.tertiarySign = tertiarySign;
   }

   public String getPlcHdrColor() {
      return this.PlcHdrColor;
   }

   public void setPlcHdrColor(String plcHdrColor) {
      this.PlcHdrColor = plcHdrColor;
   }

   public String getTmplId() {
      return this.tmplId;
   }

   public void setTmplId(String tmplId) {
      this.tmplId = tmplId;
   }

   public String getRemindFlag() {
      return this.remindFlag;
   }

   public void setRemindFlag(String remindFlag) {
      this.remindFlag = remindFlag;
   }

   public int getSort() {
      return this.sort;
   }

   public void setSort(int sort) {
      this.sort = sort;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public String getClasId() {
      return this.clasId;
   }

   public void setClasId(String clasId) {
      this.clasId = clasId;
   }

   public String getClasName() {
      return this.clasName;
   }

   public void setClasName(String clasName) {
      this.clasName = clasName;
   }

   public String getUnitId() {
      return this.unitId;
   }

   public void setUnitId(String unitId) {
      this.unitId = unitId;
   }

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
   }

   public String getSourFlag() {
      return this.sourFlag;
   }

   public void setSourFlag(String sourFlag) {
      this.sourFlag = sourFlag;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getValidFlag() {
      return this.validFlag;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getTypeFlag() {
      return this.typeFlag;
   }

   public void setTypeFlag(String typeFlag) {
      this.typeFlag = typeFlag;
   }

   public String getContType() {
      return this.contType;
   }

   public void setContType(String contType) {
      this.contType = contType;
   }

   public String getWindowName() {
      return this.windowName;
   }

   public void setWindowName(String windowName) {
      this.windowName = windowName;
   }

   public String getConsistencyCheck() {
      return this.consistencyCheck;
   }

   public void setConsistencyCheck(String consistencyCheck) {
      this.consistencyCheck = consistencyCheck;
   }

   public String getSpecialVerify() {
      return this.specialVerify;
   }

   public void setSpecialVerify(String specialVerify) {
      this.specialVerify = specialVerify;
   }

   public String getIsMacroReplace() {
      return this.isMacroReplace;
   }

   public void setIsMacroReplace(String macroReplace) {
      this.isMacroReplace = macroReplace;
   }

   public String getMacroField() {
      return this.macroField;
   }

   public void setMacroField(String macroField) {
      this.macroField = macroField;
   }

   public String getMacroReplaceType() {
      return this.macroReplaceType;
   }

   public void setMacroReplaceType(String macroReplaceType) {
      this.macroReplaceType = macroReplaceType;
   }

   public String getElemSex() {
      return this.elemSex;
   }

   public void setElemSex(String elemSex) {
      this.elemSex = elemSex;
   }

   public String getIsNullPrint() {
      return this.isNullPrint;
   }

   public void setIsNullPrint(String nullPrint) {
      this.isNullPrint = nullPrint;
   }

   public String getPlaceHolder() {
      return this.PlaceHolder;
   }

   public void setPlaceHolder(String placeHolder) {
      this.PlaceHolder = placeHolder;
   }

   public String getHelpTip() {
      return this.HelpTip;
   }

   public void setHelpTip(String helpTip) {
      this.HelpTip = helpTip;
   }

   public Boolean getEditProtect() {
      return this.EditProtect;
   }

   public void setEditProtect(Boolean editProtect) {
      this.EditProtect = editProtect;
   }

   public String getDelFlag() {
      return this.DelFlag;
   }

   public void setDelFlag(String delFlag) {
      this.DelFlag = delFlag;
   }

   public Boolean getDeleteProtect() {
      return this.DeleteProtect;
   }

   public void setDeleteProtect(Boolean deleteProtect) {
      this.DeleteProtect = deleteProtect;
   }

   public Boolean getMustFillContent() {
      return this.MustFillContent;
   }

   public void setMustFillContent(Boolean mustFillContent) {
      this.MustFillContent = mustFillContent;
   }

   public Boolean getReverseEdit() {
      return this.ReverseEdit;
   }

   public void setReverseEdit(Boolean reverseEdit) {
      this.ReverseEdit = reverseEdit;
   }

   public Boolean getIsCtrlHidden() {
      return this.IsCtrlHidden;
   }

   public void setIsCtrlHidden(Boolean ctrlHidden) {
      this.IsCtrlHidden = ctrlHidden;
   }

   public Boolean getViewSecret() {
      return this.ViewSecret;
   }

   public void setViewSecret(Boolean viewSecret) {
      this.ViewSecret = viewSecret;
   }

   public Boolean getPrintable() {
      return this.Printable;
   }

   public void setPrintable(Boolean printable) {
      this.Printable = printable;
   }

   public String getBackgroundColor() {
      return this.BackgroundColor;
   }

   public void setBackgroundColor(String backgroundColor) {
      this.BackgroundColor = backgroundColor;
   }

   public Boolean getNotRecordInXML() {
      return this.IsNotRecordInXML;
   }

   public void setNotRecordInXML(Boolean notRecordInXML) {
      this.IsNotRecordInXML = notRecordInXML;
   }

   public String getSourceUnitId() {
      return this.sourceUnitId;
   }

   public void setSourceUnitId(String sourceUnitId) {
      this.sourceUnitId = sourceUnitId;
   }

   public String getSourceSetId() {
      return this.sourceSetId;
   }

   public void setSourceSetId(String sourceSetId) {
      this.sourceSetId = sourceSetId;
   }

   public String getSourceTable() {
      return this.sourceTable;
   }

   public void setSourceTable(String sourceTable) {
      this.sourceTable = sourceTable;
   }

   public String getSourceColu() {
      return this.sourceColu;
   }

   public void setSourceColu(String sourceColu) {
      this.sourceColu = sourceColu;
   }

   public String getMedicalformulaCon() {
      return this.medicalformulaCon;
   }

   public void setMedicalformulaCon(String medicalformulaCon) {
      this.medicalformulaCon = medicalformulaCon;
   }

   public String getTag() {
      return this.Tag;
   }

   public void setTag(String tag) {
      this.Tag = tag;
   }

   public String getType() {
      return this.Type;
   }

   public void setType(String type) {
      this.Type = type;
   }

   public String getTypeName() {
      return this.TypeName;
   }

   public void setTypeName(String typeName) {
      this.TypeName = typeName;
   }
}
