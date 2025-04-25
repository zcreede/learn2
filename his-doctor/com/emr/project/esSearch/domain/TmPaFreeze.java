package com.emr.project.esSearch.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TmPaFreeze {
   private static final long serialVersionUID = 1L;
   private Long id;
   private Long freezeId;
   private String orgCd;
   private String freezeSerial;
   private String wareCode;
   private String drugCd;
   private List drugCdList;
   private String drugName;
   private Integer freezeNum;
   private Integer freezeNumNew;
   private String minUnit;
   private String standard;
   private String manufacturer;
   private String packUnits;
   private Integer minUnitRatio;
   private BigDecimal purPrice;
   private BigDecimal retailPrice;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date freezeDate;
   private String freezeSourceCd;
   private String freezeSourceName;
   private String freezeDept;
   private String freezeOperCd;
   private String freezeOperName;
   private String stockNo;
   private String remark;
   private String ip;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date thawDate;
   private String thawOperCd;
   private String thawOperName;
   private String thawWayCd;
   private String thawWayName;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getFreezeId() {
      return this.freezeId;
   }

   public void setFreezeId(Long freezeId) {
      this.freezeId = freezeId;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getFreezeSerial() {
      return this.freezeSerial;
   }

   public void setFreezeSerial(String freezeSerial) {
      this.freezeSerial = freezeSerial;
   }

   public String getWareCode() {
      return this.wareCode;
   }

   public void setWareCode(String wareCode) {
      this.wareCode = wareCode;
   }

   public String getDrugCd() {
      return this.drugCd;
   }

   public void setDrugCd(String drugCd) {
      this.drugCd = drugCd;
   }

   public List getDrugCdList() {
      return this.drugCdList;
   }

   public void setDrugCdList(List drugCdList) {
      this.drugCdList = drugCdList;
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }

   public Integer getFreezeNum() {
      return this.freezeNum;
   }

   public void setFreezeNum(Integer freezeNum) {
      this.freezeNum = freezeNum;
   }

   public Integer getFreezeNumNew() {
      return this.freezeNumNew;
   }

   public void setFreezeNumNew(Integer freezeNumNew) {
      this.freezeNumNew = freezeNumNew;
   }

   public String getMinUnit() {
      return this.minUnit;
   }

   public void setMinUnit(String minUnit) {
      this.minUnit = minUnit;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getManufacturer() {
      return this.manufacturer;
   }

   public void setManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
   }

   public String getPackUnits() {
      return this.packUnits;
   }

   public void setPackUnits(String packUnits) {
      this.packUnits = packUnits;
   }

   public Integer getMinUnitRatio() {
      return this.minUnitRatio;
   }

   public void setMinUnitRatio(Integer minUnitRatio) {
      this.minUnitRatio = minUnitRatio;
   }

   public BigDecimal getPurPrice() {
      return this.purPrice;
   }

   public void setPurPrice(BigDecimal purPrice) {
      this.purPrice = purPrice;
   }

   public BigDecimal getRetailPrice() {
      return this.retailPrice;
   }

   public void setRetailPrice(BigDecimal retailPrice) {
      this.retailPrice = retailPrice;
   }

   public Date getFreezeDate() {
      return this.freezeDate;
   }

   public void setFreezeDate(Date freezeDate) {
      this.freezeDate = freezeDate;
   }

   public String getFreezeSourceCd() {
      return this.freezeSourceCd;
   }

   public void setFreezeSourceCd(String freezeSourceCd) {
      this.freezeSourceCd = freezeSourceCd;
   }

   public String getFreezeSourceName() {
      return this.freezeSourceName;
   }

   public void setFreezeSourceName(String freezeSourceName) {
      this.freezeSourceName = freezeSourceName;
   }

   public String getFreezeDept() {
      return this.freezeDept;
   }

   public void setFreezeDept(String freezeDept) {
      this.freezeDept = freezeDept;
   }

   public String getFreezeOperCd() {
      return this.freezeOperCd;
   }

   public void setFreezeOperCd(String freezeOperCd) {
      this.freezeOperCd = freezeOperCd;
   }

   public String getFreezeOperName() {
      return this.freezeOperName;
   }

   public void setFreezeOperName(String freezeOperName) {
      this.freezeOperName = freezeOperName;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public Date getThawDate() {
      return this.thawDate;
   }

   public void setThawDate(Date thawDate) {
      this.thawDate = thawDate;
   }

   public String getThawOperCd() {
      return this.thawOperCd;
   }

   public void setThawOperCd(String thawOperCd) {
      this.thawOperCd = thawOperCd;
   }

   public String getThawOperName() {
      return this.thawOperName;
   }

   public void setThawOperName(String thawOperName) {
      this.thawOperName = thawOperName;
   }

   public String getThawWayCd() {
      return this.thawWayCd;
   }

   public void setThawWayCd(String thawWayCd) {
      this.thawWayCd = thawWayCd;
   }

   public String getThawWayName() {
      return this.thawWayName;
   }

   public void setThawWayName(String thawWayName) {
      this.thawWayName = thawWayName;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public String getIp() {
      return this.ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }
}
