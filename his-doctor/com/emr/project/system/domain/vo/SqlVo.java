package com.emr.project.system.domain.vo;

import java.util.Date;
import java.util.List;

public class SqlVo {
   private String sqlStr;
   private String deptCode;
   private String beginTime;
   private String endTime;
   private String inpNo;
   private String patientId;
   private String tableName;
   private Date beginDateTime;
   private Date endDateTime;
   private String reportId;
   private String resDocCd;
   private List inpNoList;
   private String id;

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public List getInpNoList() {
      return this.inpNoList;
   }

   public void setInpNoList(List inpNoList) {
      this.inpNoList = inpNoList;
   }

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getReportId() {
      return this.reportId;
   }

   public void setReportId(String reportId) {
      this.reportId = reportId;
   }

   public Date getBeginDateTime() {
      return this.beginDateTime;
   }

   public void setBeginDateTime(Date beginDateTime) {
      this.beginDateTime = beginDateTime;
   }

   public Date getEndDateTime() {
      return this.endDateTime;
   }

   public void setEndDateTime(Date endDateTime) {
      this.endDateTime = endDateTime;
   }

   public String getTableName() {
      return this.tableName;
   }

   public void setTableName(String tableName) {
      this.tableName = tableName;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public SqlVo() {
   }

   public SqlVo(String deptCode, String beginTime, String endTime) {
      this.deptCode = deptCode;
      this.beginTime = beginTime;
      this.endTime = endTime;
   }

   public SqlVo(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public SqlVo(String sqlStr, String deptCode, String beginTime, String endTime) {
      this.sqlStr = sqlStr;
      this.deptCode = deptCode;
      this.beginTime = beginTime;
      this.endTime = endTime;
   }

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(String beginTime) {
      this.beginTime = beginTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }
}
