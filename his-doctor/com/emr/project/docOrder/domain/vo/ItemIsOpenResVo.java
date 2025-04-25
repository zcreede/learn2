package com.emr.project.docOrder.domain.vo;

public class ItemIsOpenResVo {
   private String resCode;
   private String msgFlag;
   private String windowFlag;
   private String windowCode;
   private String resMsg;
   private String drugClassCode;
   private TdPaApplyFormVo tdPaApplyFormVo;
   private String purposeExamination;
   private String tempOpenFlag;
   private String drugGradeCd;
   private String drugGradeName;
   private String drugLevelCd;
   private String recipType;
   private String orderSortNumber;
   private String cpNo;
   private String orderGroupSortNumber;
   private Integer orderGroupNo;

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getRecipType() {
      return this.recipType;
   }

   public void setRecipType(String recipType) {
      this.recipType = recipType;
   }

   public String getDrugLevelCd() {
      return this.drugLevelCd;
   }

   public void setDrugLevelCd(String drugLevelCd) {
      this.drugLevelCd = drugLevelCd;
   }

   public String getDrugGradeCd() {
      return this.drugGradeCd;
   }

   public void setDrugGradeCd(String drugGradeCd) {
      this.drugGradeCd = drugGradeCd;
   }

   public String getDrugGradeName() {
      return this.drugGradeName;
   }

   public void setDrugGradeName(String drugGradeName) {
      this.drugGradeName = drugGradeName;
   }

   public String getTempOpenFlag() {
      return this.tempOpenFlag;
   }

   public void setTempOpenFlag(String tempOpenFlag) {
      this.tempOpenFlag = tempOpenFlag;
   }

   public ItemIsOpenResVo() {
      this.resCode = "0";
   }

   public ItemIsOpenResVo(String resCode, String msgFlag, String resMsg, String windowFlag, String windowCode, String drugClassCode) {
      this.resCode = resCode;
      this.msgFlag = msgFlag;
      this.resMsg = resMsg;
      this.windowFlag = windowFlag;
      this.windowCode = windowCode;
      this.drugClassCode = drugClassCode;
   }

   public ItemIsOpenResVo(String resCode, String msgFlag, String resMsg, String windowFlag, String windowCode, String drugClassCode, String drugGradeCd, String drugGradeName, String drugLevelCd) {
      this.resCode = resCode;
      this.msgFlag = msgFlag;
      this.resMsg = resMsg;
      this.windowFlag = windowFlag;
      this.windowCode = windowCode;
      this.drugClassCode = drugClassCode;
      this.drugGradeCd = drugGradeCd;
      this.drugGradeName = drugGradeName;
      this.drugLevelCd = drugLevelCd;
   }

   public ItemIsOpenResVo(String resCode, String msgFlag, String resMsg, String windowFlag, String windowCode, String drugClassCode, String drugGradeCd, String drugGradeName, String drugLevelCd, String cpNo) {
      this.resCode = resCode;
      this.msgFlag = msgFlag;
      this.resMsg = resMsg;
      this.windowFlag = windowFlag;
      this.windowCode = windowCode;
      this.drugClassCode = drugClassCode;
      this.drugGradeCd = drugGradeCd;
      this.drugGradeName = drugGradeName;
      this.drugLevelCd = drugLevelCd;
      this.cpNo = cpNo;
   }

   public TdPaApplyFormVo getTdPaApplyFormVo() {
      return this.tdPaApplyFormVo;
   }

   public void setTdPaApplyFormVo(TdPaApplyFormVo tdPaApplyFormVo) {
      this.tdPaApplyFormVo = tdPaApplyFormVo;
   }

   public String getPurposeExamination() {
      return this.purposeExamination;
   }

   public void setPurposeExamination(String purposeExamination) {
      this.purposeExamination = purposeExamination;
   }

   public String getResCode() {
      return this.resCode;
   }

   public void setResCode(String resCode) {
      this.resCode = resCode;
   }

   public String getResMsg() {
      return this.resMsg;
   }

   public void setResMsg(String resMsg) {
      this.resMsg = resMsg;
   }

   public String getMsgFlag() {
      return this.msgFlag;
   }

   public void setMsgFlag(String msgFlag) {
      this.msgFlag = msgFlag;
   }

   public String getWindowFlag() {
      return this.windowFlag;
   }

   public void setWindowFlag(String windowFlag) {
      this.windowFlag = windowFlag;
   }

   public String getWindowCode() {
      return this.windowCode;
   }

   public void setWindowCode(String windowCode) {
      this.windowCode = windowCode;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }
}
