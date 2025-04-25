package com.emr.project.system.domain.vo;

import com.emr.common.constant.CommonConstants;
import com.emr.project.system.domain.SysStaElem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SysStaElemVo extends SysStaElem {
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
   @JsonProperty("PlaceHolder")
   private String PlaceHolder;
   @JsonProperty("PlcHdrColor")
   private String PlcHdrColor;
   @JsonProperty("HelpTip")
   private String HelpTip;
   @JsonProperty("EditProtect")
   private Boolean EditProtect;
   @JsonProperty("DelFlag")
   private String DelFlag;
   @JsonProperty("DeleteProtect")
   private Boolean DeleteProtect;
   @JsonProperty("MustFillContent")
   private Boolean MustFillContent;
   @JsonProperty("ReverseEdit")
   private Boolean ReverseEdit;
   @JsonProperty("IsCtrlHidden")
   private Boolean IsCtrlHidden;
   @JsonProperty("ViewSecret")
   private Boolean ViewSecret;
   @JsonProperty("Printable")
   private Boolean Printable;
   @JsonProperty("BackgroundColor")
   private String BackgroundColor;
   @JsonProperty("IsNotRecordInXML")
   private Boolean IsNotRecordInXML;
   @JsonProperty("TextBoxRes")
   private String TextBoxRes;
   @JsonProperty("TextBoxMinLen")
   private Integer TextBoxMinLen;
   @JsonProperty("TextBoxMaxLen")
   private Integer TextBoxMaxLen;
   @JsonProperty("MaxValue")
   private Integer MaxValue;
   @JsonProperty("MinValue")
   private Integer MinValue;
   @JsonProperty("Precision")
   private Integer Precision;
   @JsonProperty("Unit")
   private String Unit;
   @JsonProperty("ContentException")
   private String ContentException;
   @JsonProperty("OutRangeInfo")
   private String OutRangeInfo;
   private String currentTime;
   private String inoutHosTimeRange;
   @JsonProperty("Style")
   private String Style;
   @JsonProperty("StyleFormat")
   private String StyleFormat;
   @JsonProperty("CheckOption")
   private String CheckOption;
   @JsonProperty("IsChecked")
   private Boolean IsChecked;
   @JsonProperty("CheckPos")
   private String CheckPos;
   private String listDataSource;
   private Long setId;
   private String listDataArrStr;
   @JsonProperty("FrontString")
   private String FrontString;
   @JsonProperty("UnSelectedFrontString")
   private String UnSelectedFrontString;
   @JsonProperty("Separator")
   private String Separator;
   @JsonProperty("GroupSeparator")
   private String GroupSeparator;
   @JsonProperty("doCode")
   private String doCode;
   @JsonProperty("triggerWay")
   private String triggerWay;
   private String contTypeName;
   private String typeFlagName;
   private Long elemId;
   private String sourceUnitId;
   private String sourceSetId;
   private String sourceTable;
   private String sourceColu;
   @JsonProperty("Title")
   private String Title;
   @JsonProperty("TitleVisible")
   private Boolean TitleVisible;
   @JsonProperty("FrontIndent")
   private Double FrontIndent;
   @JsonProperty("BackIndent")
   private Double BackIndent;
   @JsonProperty("Visible")
   private String Visible;
   private String signFrontString;
   private String signPlaceHolder;
   private String define;
   private String setName;
   private String medicalformulaCon;
   @JsonProperty("Tag")
   private String Tag;
   @JsonProperty("ShowValue")
   private Boolean ShowValue;
   @JsonProperty("linkData")
   private String linkData;
   private String formulaRemark;
   private String newStr;
   private String oldStr;
   private int sort;
   private String tmplId;
   private String remindFlag;
   private String tertiarySign;

   public SysStaElemVo() {
      this.IsNotRecordInXML = CommonConstants.BOOL_TRUE;
   }

   public String getTertiarySign() {
      return this.tertiarySign;
   }

   public void setTertiarySign(String tertiarySign) {
      this.tertiarySign = tertiarySign;
   }

   public String getTmplId() {
      return this.tmplId;
   }

   public String getPlcHdrColor() {
      return this.PlcHdrColor;
   }

   public String getLinkData() {
      return this.linkData;
   }

   public void setLinkData(String linkData) {
      this.linkData = linkData;
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

   public void setPlcHdrColor(String plcHdrColor) {
      this.PlcHdrColor = plcHdrColor;
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

   public String getNewStr() {
      return this.newStr;
   }

   public void setNewStr(String newStr) {
      this.newStr = newStr;
   }

   public String getOldStr() {
      return this.oldStr;
   }

   public void setOldStr(String oldStr) {
      this.oldStr = oldStr;
   }

   public String getFormulaRemark() {
      return this.formulaRemark;
   }

   public void setFormulaRemark(String formulaRemark) {
      this.formulaRemark = formulaRemark;
   }

   public Boolean getShowValue() {
      return this.ShowValue;
   }

   public void setShowValue(Boolean showValue) {
      this.ShowValue = showValue;
   }

   public Boolean getDeleteProtect() {
      return this.DeleteProtect;
   }

   public void setDeleteProtect(Boolean deleteProtect) {
      this.DeleteProtect = deleteProtect;
   }

   public String getTag() {
      return this.Tag;
   }

   public void setTag(String tag) {
      this.Tag = tag;
   }

   public String getMedicalformulaCon() {
      return this.medicalformulaCon;
   }

   public void setMedicalformulaCon(String medicalformulaCon) {
      this.medicalformulaCon = medicalformulaCon;
   }

   public String getSetName() {
      return this.setName;
   }

   public void setSetName(String setName) {
      this.setName = setName;
   }

   public String getDefine() {
      return this.define;
   }

   public void setDefine(String define) {
      this.define = define;
   }

   public Long getSetId() {
      return this.setId;
   }

   public void setSetId(Long setId) {
      this.setId = setId;
   }

   public String getTitle() {
      return this.Title;
   }

   public void setTitle(String title) {
      this.Title = title;
   }

   public Boolean getTitleVisible() {
      return this.TitleVisible;
   }

   public void setTitleVisible(Boolean titleVisible) {
      this.TitleVisible = titleVisible;
   }

   public Double getFrontIndent() {
      return this.FrontIndent;
   }

   public void setFrontIndent(Double frontIndent) {
      this.FrontIndent = frontIndent;
   }

   public Double getBackIndent() {
      return this.BackIndent;
   }

   public void setBackIndent(Double backIndent) {
      this.BackIndent = backIndent;
   }

   public String getVisible() {
      return this.Visible;
   }

   public void setVisible(String visible) {
      this.Visible = visible;
   }

   public String getSignFrontString() {
      return this.signFrontString;
   }

   public void setSignFrontString(String signFrontString) {
      this.signFrontString = signFrontString;
   }

   public String getSignPlaceHolder() {
      return this.signPlaceHolder;
   }

   public void setSignPlaceHolder(String signPlaceHolder) {
      this.signPlaceHolder = signPlaceHolder;
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

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
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

   public void setIsMacroReplace(String isMacroReplace) {
      this.isMacroReplace = isMacroReplace;
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

   public void setIsNullPrint(String isNullPrint) {
      this.isNullPrint = isNullPrint;
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

   public void setIsCtrlHidden(Boolean isCtrlHidden) {
      this.IsCtrlHidden = isCtrlHidden;
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

   public Boolean getIsNotRecordInXML() {
      return this.IsNotRecordInXML;
   }

   public void setIsNotRecordInXML(Boolean isNotRecordInXML) {
      this.IsNotRecordInXML = isNotRecordInXML;
   }

   public String getTextBoxRes() {
      return this.TextBoxRes;
   }

   public void setTextBoxRes(String textBoxRes) {
      this.TextBoxRes = textBoxRes;
   }

   public Integer getTextBoxMinLen() {
      return this.TextBoxMinLen;
   }

   public void setTextBoxMinLen(Integer textBoxMinLen) {
      this.TextBoxMinLen = textBoxMinLen;
   }

   public Integer getTextBoxMaxLen() {
      return this.TextBoxMaxLen;
   }

   public void setTextBoxMaxLen(Integer textBoxMaxLen) {
      this.TextBoxMaxLen = textBoxMaxLen;
   }

   public Integer getMaxValue() {
      return this.MaxValue;
   }

   public void setMaxValue(Integer maxValue) {
      this.MaxValue = maxValue;
   }

   public Integer getMinValue() {
      return this.MinValue;
   }

   public void setMinValue(Integer minValue) {
      this.MinValue = minValue;
   }

   public Integer getPrecision() {
      return this.Precision;
   }

   public void setPrecision(Integer precision) {
      this.Precision = precision;
   }

   public String getUnit() {
      return this.Unit;
   }

   public void setUnit(String unit) {
      this.Unit = unit;
   }

   public String getContentException() {
      return this.ContentException;
   }

   public void setContentException(String contentException) {
      this.ContentException = contentException;
   }

   public String getOutRangeInfo() {
      return this.OutRangeInfo;
   }

   public void setOutRangeInfo(String outRangeInfo) {
      this.OutRangeInfo = outRangeInfo;
   }

   public String getCurrentTime() {
      return this.currentTime;
   }

   public void setCurrentTime(String currentTime) {
      this.currentTime = currentTime;
   }

   public String getInoutHosTimeRange() {
      return this.inoutHosTimeRange;
   }

   public void setInoutHosTimeRange(String inoutHosTimeRange) {
      this.inoutHosTimeRange = inoutHosTimeRange;
   }

   public String getStyle() {
      return this.Style;
   }

   public void setStyle(String style) {
      this.Style = style;
   }

   public String getStyleFormat() {
      return this.StyleFormat;
   }

   public void setStyleFormat(String styleFormat) {
      this.StyleFormat = styleFormat;
   }

   public String getCheckOption() {
      return this.CheckOption;
   }

   public void setCheckOption(String checkOption) {
      this.CheckOption = checkOption;
   }

   public Boolean getIsChecked() {
      return this.IsChecked;
   }

   public void setIsChecked(Boolean isChecked) {
      this.IsChecked = isChecked;
   }

   public String getCheckPos() {
      return this.CheckPos;
   }

   public void setCheckPos(String checkPos) {
      this.CheckPos = checkPos;
   }

   public String getListDataSource() {
      return this.listDataSource;
   }

   public void setListDataSource(String listDataSource) {
      this.listDataSource = listDataSource;
   }

   public String getListDataArrStr() {
      return this.listDataArrStr;
   }

   public void setListDataArrStr(String listDataArrStr) {
      this.listDataArrStr = listDataArrStr;
   }

   public String getFrontString() {
      return this.FrontString;
   }

   public void setFrontString(String frontString) {
      this.FrontString = frontString;
   }

   public String getUnSelectedFrontString() {
      return this.UnSelectedFrontString;
   }

   public void setUnSelectedFrontString(String unSelectedFrontString) {
      this.UnSelectedFrontString = unSelectedFrontString;
   }

   public String getSeparator() {
      return this.Separator;
   }

   public void setSeparator(String separator) {
      this.Separator = separator;
   }

   public String getGroupSeparator() {
      return this.GroupSeparator;
   }

   public void setGroupSeparator(String groupSeparator) {
      this.GroupSeparator = groupSeparator;
   }

   public String getContTypeName() {
      return this.contTypeName;
   }

   public void setContTypeName(String contTypeName) {
      this.contTypeName = contTypeName;
   }

   public String getTypeFlagName() {
      return this.typeFlagName;
   }

   public void setTypeFlagName(String typeFlagName) {
      this.typeFlagName = typeFlagName;
   }
}
