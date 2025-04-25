package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdMsgSendMain implements Serializable {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "发送消息应用"
   )
   private String sendAppId;
   @Excel(
      name = "消息模板ID"
   )
   private String msgId;
   @Excel(
      name = "模板类型 1公告信息 2普通通知 3业务消息"
   )
   private String msgType;
   @Excel(
      name = "就诊类型(mz/zy)"
   )
   private String visitType;
   @Excel(
      name = "门诊号/住院号"
   )
   private String visitNo;
   @Excel(
      name = "业务ID",
      readConverterExp = "医=嘱编码、病历id等"
   )
   private String busId;
   @Excel(
      name = "消息内容"
   )
   private String mesContent;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "有效截至时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date expDate;
   @Excel(
      name = "业务处理链接"
   )
   private String execUrl;
   @Excel(
      name = "接收对象json串"
   )
   private String receiveCode;
   @Excel(
      name = "状态",
      readConverterExp = "状态（-1发送失败 0创建1发送2撤销3已读4已处理"
   )
   private String status;
   @Excel(
      name = "消息创建科室编码"
   )
   private String creDeptCd;
   @Excel(
      name = "消息创建科室名称"
   )
   private String creDeptName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人姓名"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "撤销时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date cancelDate;
   @Excel(
      name = "撤销人编码"
   )
   private String cancelPerCode;
   @Excel(
      name = "撤销人姓名"
   )
   private String cancelPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "发送时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date sendDate;
   @Excel(
      name = "发送人编码"
   )
   private String sendUserCd;
   @Excel(
      name = "发送人姓名"
   )
   private String sendUserName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "处理时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date execDate;
   @Excel(
      name = "处理人编码"
   )
   private String execUserCd;
   @Excel(
      name = "处理人姓名"
   )
   private String execUserName;

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

   public void setSendAppId(String sendAppId) {
      this.sendAppId = sendAppId;
   }

   public String getSendAppId() {
      return this.sendAppId;
   }

   public void setMsgId(String msgId) {
      this.msgId = msgId;
   }

   public String getMsgId() {
      return this.msgId;
   }

   public void setMsgType(String msgType) {
      this.msgType = msgType;
   }

   public String getMsgType() {
      return this.msgType;
   }

   public void setVisitType(String visitType) {
      this.visitType = visitType;
   }

   public String getVisitType() {
      return this.visitType;
   }

   public void setVisitNo(String visitNo) {
      this.visitNo = visitNo;
   }

   public String getVisitNo() {
      return this.visitNo;
   }

   public void setBusId(String busId) {
      this.busId = busId;
   }

   public String getBusId() {
      return this.busId;
   }

   public void setMesContent(String mesContent) {
      this.mesContent = mesContent;
   }

   public String getMesContent() {
      return this.mesContent;
   }

   public void setExpDate(Date expDate) {
      this.expDate = expDate;
   }

   public Date getExpDate() {
      return this.expDate;
   }

   public void setExecUrl(String execUrl) {
      this.execUrl = execUrl;
   }

   public String getExecUrl() {
      return this.execUrl;
   }

   public void setReceiveCode(String receiveCode) {
      this.receiveCode = receiveCode;
   }

   public String getReceiveCode() {
      return this.receiveCode;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setCreDeptCd(String creDeptCd) {
      this.creDeptCd = creDeptCd;
   }

   public String getCreDeptCd() {
      return this.creDeptCd;
   }

   public void setCreDeptName(String creDeptName) {
      this.creDeptName = creDeptName;
   }

   public String getCreDeptName() {
      return this.creDeptName;
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

   public void setCancelDate(Date cancelDate) {
      this.cancelDate = cancelDate;
   }

   public Date getCancelDate() {
      return this.cancelDate;
   }

   public void setCancelPerCode(String cancelPerCode) {
      this.cancelPerCode = cancelPerCode;
   }

   public String getCancelPerCode() {
      return this.cancelPerCode;
   }

   public void setCancelPerName(String cancelPerName) {
      this.cancelPerName = cancelPerName;
   }

   public String getCancelPerName() {
      return this.cancelPerName;
   }

   public void setSendDate(Date sendDate) {
      this.sendDate = sendDate;
   }

   public Date getSendDate() {
      return this.sendDate;
   }

   public void setSendUserCd(String sendUserCd) {
      this.sendUserCd = sendUserCd;
   }

   public String getSendUserCd() {
      return this.sendUserCd;
   }

   public void setSendUserName(String sendUserName) {
      this.sendUserName = sendUserName;
   }

   public String getSendUserName() {
      return this.sendUserName;
   }

   public void setExecDate(Date execDate) {
      this.execDate = execDate;
   }

   public Date getExecDate() {
      return this.execDate;
   }

   public void setExecUserCd(String execUserCd) {
      this.execUserCd = execUserCd;
   }

   public String getExecUserCd() {
      return this.execUserCd;
   }

   public void setExecUserName(String execUserName) {
      this.execUserName = execUserName;
   }

   public String getExecUserName() {
      return this.execUserName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("sendAppId", this.getSendAppId()).append("msgId", this.getMsgId()).append("msgType", this.getMsgType()).append("visitType", this.getVisitType()).append("visitNo", this.getVisitNo()).append("busId", this.getBusId()).append("mesContent", this.getMesContent()).append("expDate", this.getExpDate()).append("execUrl", this.getExecUrl()).append("receiveCode", this.getReceiveCode()).append("status", this.getStatus()).append("creDeptCd", this.getCreDeptCd()).append("creDeptName", this.getCreDeptName()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("cancelDate", this.getCancelDate()).append("cancelPerCode", this.getCancelPerCode()).append("cancelPerName", this.getCancelPerName()).append("sendDate", this.getSendDate()).append("sendUserCd", this.getSendUserCd()).append("sendUserName", this.getSendUserName()).append("execDate", this.getExecDate()).append("execUserCd", this.getExecUserCd()).append("execUserName", this.getExecUserName()).toString();
   }
}
