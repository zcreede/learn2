package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdMsgSendReceiver implements Serializable {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "消息ID",
      readConverterExp = "消=息主表ID"
   )
   private Long sendId;
   @Excel(
      name = "接收消息应用"
   )
   private String recAppId;
   @Excel(
      name = "接收对象类型",
      readConverterExp = "科=室人员、科室医师、科室护士、人员工号"
   )
   private String recStaffType;
   @Excel(
      name = "接收科室编码"
   )
   private String recDeptCd;
   @Excel(
      name = "接收用户编码"
   )
   private String recUserCd;
   @Excel(
      name = "状态(0未读1已读2已处理）"
   )
   private String status;
   @Excel(
      name = "阅读时间"
   )
   private String readDate;
   @Excel(
      name = "阅读用户编码"
   )
   private String readUserCd;
   @Excel(
      name = "阅读用户姓名"
   )
   private String readUserName;
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

   public void setSendId(Long sendId) {
      this.sendId = sendId;
   }

   public Long getSendId() {
      return this.sendId;
   }

   public void setRecAppId(String recAppId) {
      this.recAppId = recAppId;
   }

   public String getRecAppId() {
      return this.recAppId;
   }

   public void setRecStaffType(String recStaffType) {
      this.recStaffType = recStaffType;
   }

   public String getRecStaffType() {
      return this.recStaffType;
   }

   public void setRecDeptCd(String recDeptCd) {
      this.recDeptCd = recDeptCd;
   }

   public String getRecDeptCd() {
      return this.recDeptCd;
   }

   public void setRecUserCd(String recUserCd) {
      this.recUserCd = recUserCd;
   }

   public String getRecUserCd() {
      return this.recUserCd;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setReadDate(String readDate) {
      this.readDate = readDate;
   }

   public String getReadDate() {
      return this.readDate;
   }

   public void setReadUserCd(String readUserCd) {
      this.readUserCd = readUserCd;
   }

   public String getReadUserCd() {
      return this.readUserCd;
   }

   public void setReadUserName(String readUserName) {
      this.readUserName = readUserName;
   }

   public String getReadUserName() {
      return this.readUserName;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("sendId", this.getSendId()).append("recAppId", this.getRecAppId()).append("recStaffType", this.getRecStaffType()).append("recDeptCd", this.getRecDeptCd()).append("recUserCd", this.getRecUserCd()).append("status", this.getStatus()).append("readDate", this.getReadDate()).append("readUserCd", this.getReadUserCd()).append("readUserName", this.getReadUserName()).append("execDate", this.getExecDate()).append("execUserCd", this.getExecUserCd()).append("execUserName", this.getExecUserName()).toString();
   }
}
