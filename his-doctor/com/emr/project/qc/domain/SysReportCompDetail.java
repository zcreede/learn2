package com.emr.project.qc.domain;

import com.emr.framework.web.domain.BaseEntity;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysReportCompDetail extends BaseEntity {
   private Long id;
   private Long repId;
   private Long comId;
   private String comDetailCode;
   private String comDetailName;
   private String dataSource;
   private String staticData;
   private String sqlStr;
   private String classMethod;
   private Integer returnDataType;
   private Integer valid;
   private Date creDate;
   private String crePer;
   private String crePerName;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getRepId() {
      return this.repId;
   }

   public void setRepId(Long repId) {
      this.repId = repId;
   }

   public Long getComId() {
      return this.comId;
   }

   public void setComId(Long comId) {
      this.comId = comId;
   }

   public String getComDetailCode() {
      return this.comDetailCode;
   }

   public void setComDetailCode(String comDetailCode) {
      this.comDetailCode = comDetailCode;
   }

   public String getComDetailName() {
      return this.comDetailName;
   }

   public void setComDetailName(String comDetailName) {
      this.comDetailName = comDetailName;
   }

   public String getDataSource() {
      return this.dataSource;
   }

   public void setDataSource(String dataSource) {
      this.dataSource = dataSource;
   }

   public String getStaticData() {
      return this.staticData;
   }

   public void setStaticData(String staticData) {
      this.staticData = staticData;
   }

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public String getClassMethod() {
      return this.classMethod;
   }

   public void setClassMethod(String classMethod) {
      this.classMethod = classMethod;
   }

   public Integer getReturnDataType() {
      return this.returnDataType;
   }

   public void setReturnDataType(Integer returnDataType) {
      this.returnDataType = returnDataType;
   }

   public Integer getValid() {
      return this.valid;
   }

   public void setValid(Integer valid) {
      this.valid = valid;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public String getCrePer() {
      return this.crePer;
   }

   public void setCrePer(String crePer) {
      this.crePer = crePer;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("repId", this.getRepId()).append("comId", this.getComId()).append("comDetailCode", this.getComDetailCode()).append("comDetailName", this.getComDetailName()).append("dataSource", this.getDataSource()).append("staticData", this.getStaticData()).append("sqlStr", this.getSqlStr()).append("classMethod", this.getClassMethod()).append("returnDataType", this.getReturnDataType()).append("valid", this.getValid()).append("creDate", this.getCreDate()).append("crePer", this.getCrePer()).append("crePerName", this.getCrePerName()).toString();
   }
}
