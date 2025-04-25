package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ClinItemCollection extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构编码 "
   )
   private String hospitalCode;
   @Excel(
      name = "医师编码"
   )
   private String docCd;
   @Excel(
      name = "医师姓名"
   )
   private String docName;
   @Excel(
      name = "项目类别编码",
      readConverterExp = "药=疗、处置、检查、检验等"
   )
   private String itemClassCd;
   @Excel(
      name = "项目类别名称"
   )
   private String itemClassName;
   @Excel(
      name = "项目编码"
   )
   private String itemCd;
   @Excel(
      name = "临床项目名称"
   )
   private String itemName;
   @Excel(
      name = "规格"
   )
   private String standard;
   @Excel(
      name = "使用次数"
   )
   private Integer usageTimes;
   @Excel(
      name = "是否草药(0：否；1：是)"
   )
   private String herbalFlag;
   @Excel(
      name = "排序"
   )
   private Integer sort;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人项目"
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
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   private List itemCdList;
   private String stockNo;

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public List getItemCdList() {
      return this.itemCdList;
   }

   public void setItemCdList(List itemCdList) {
      this.itemCdList = itemCdList;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setItemClassCd(String itemClassCd) {
      this.itemClassCd = itemClassCd;
   }

   public String getItemClassCd() {
      return this.itemClassCd;
   }

   public void setItemClassName(String itemClassName) {
      this.itemClassName = itemClassName;
   }

   public String getItemClassName() {
      return this.itemClassName;
   }

   public void setItemCd(String itemCd) {
      this.itemCd = itemCd;
   }

   public String getItemCd() {
      return this.itemCd;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setUsageTimes(Integer usageTimes) {
      this.usageTimes = usageTimes;
   }

   public Integer getUsageTimes() {
      return this.usageTimes;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setSort(Integer sort) {
      this.sort = sort;
   }

   public Integer getSort() {
      return this.sort;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("hospitalCode", this.getHospitalCode()).append("docCd", this.getDocCd()).append("docName", this.getDocName()).append("itemClassCd", this.getItemClassCd()).append("itemClassName", this.getItemClassName()).append("itemCd", this.getItemCd()).append("itemName", this.getItemName()).append("standard", this.getStandard()).append("usageTimes", this.getUsageTimes()).append("herbalFlag", this.getHerbalFlag()).append("sort", this.getSort()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("stockNo", this.getStockNo()).toString();
   }
}
