package com.emr.project.tmpl.domain.vo;

import com.emr.project.tmpl.domain.ElemAttri;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ElemAttriVo extends ElemAttri {
   private String elemCon;
   private String elemTypeCd;
   private String elemConText;
   private String parentContElemName;
   private Long parentElemId;
   private String parentElemName;
   private String parentElemCd;
   private Long unitId;
   private Double elemConTextInt;
   private Date inhosTime;
   private Date outTime;
   private Date nowDate;
   private Boolean bool;
   private String elemContTextCode;
   private String linkContElemName;

   public ElemAttriVo() {
   }

   public ElemAttriVo(ElemAttri elemAttri) {
      super.setId(elemAttri.getId());
      super.setTempId(elemAttri.getTempId());
      super.setTempName(elemAttri.getTempName());
      super.setContElemName(elemAttri.getContElemName());
      super.setElemId(elemAttri.getElemId());
      super.setElemName(elemAttri.getElemName());
      super.setElemCd(elemAttri.getElemCd());
      super.setTypeFlag(elemAttri.getTypeFlag());
      super.setContType(elemAttri.getContType());
      super.setElemAttri(elemAttri.getElemAttri());
      super.setCreDate(elemAttri.getCreDate());
      super.setCrePerName(elemAttri.getCrePerName());
      super.setAltDate(elemAttri.getAltDate());
      super.setAltPerName(elemAttri.getAltPerName());
      super.setTmplContElemName(elemAttri.getTmplContElemName());
      super.setLinkData(elemAttri.getLinkData());
      super.setDoCode(elemAttri.getDoCode());
      super.setTriggerWay(elemAttri.getTriggerWay());
   }

   public String getLinkContElemName() {
      return this.linkContElemName;
   }

   public void setLinkContElemName(String linkContElemName) {
      this.linkContElemName = linkContElemName;
   }

   public String getElemContTextCode() {
      return this.elemContTextCode;
   }

   public void setElemContTextCode(String elemContTextCode) {
      this.elemContTextCode = elemContTextCode;
   }

   public Boolean getBool() {
      return this.bool;
   }

   public void setBool(Boolean bool) {
      this.bool = bool;
   }

   public Double getElemConTextInt() {
      return this.elemConTextInt;
   }

   public void setElemConTextInt(Double elemConTextInt) {
      this.elemConTextInt = elemConTextInt;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public Date getNowDate() {
      return this.nowDate;
   }

   public void setNowDate(Date nowDate) {
      this.nowDate = nowDate;
   }

   public String getElemCon() {
      return this.elemCon;
   }

   public void setElemCon(String elemCon) {
      this.elemCon = elemCon;
   }

   public String getElemTypeCd() {
      return this.elemTypeCd;
   }

   public void setElemTypeCd(String elemTypeCd) {
      this.elemTypeCd = elemTypeCd;
   }

   public String getElemConText() {
      return this.elemConText;
   }

   public void setElemConText(String elemConText) {
      this.elemConText = elemConText;
   }

   public String getParentContElemName() {
      return this.parentContElemName;
   }

   public void setParentContElemName(String parentContElemName) {
      this.parentContElemName = parentContElemName;
   }

   public Long getParentElemId() {
      return this.parentElemId;
   }

   public void setParentElemId(Long parentElemId) {
      this.parentElemId = parentElemId;
   }

   public String getParentElemName() {
      return this.parentElemName;
   }

   public void setParentElemName(String parentElemName) {
      this.parentElemName = parentElemName;
   }

   public String getParentElemCd() {
      return this.parentElemCd;
   }

   public void setParentElemCd(String parentElemCd) {
      this.parentElemCd = parentElemCd;
   }

   public Long getUnitId() {
      return this.unitId;
   }

   public void setUnitId(Long unitId) {
      this.unitId = unitId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("tempId", this.getTempId()).append("tempName", this.getTempName()).append("contElemName", this.getContElemName()).append("elemConText", this.getElemConText()).append("elemId", this.getElemId()).append("elemName", this.getElemName()).append("elemCd", this.getElemCd()).append("typeFlag", this.getTypeFlag()).append("contType", this.getContType()).append("elemAttri", this.getElemAttri()).append("creDate", this.getCreDate()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerName", this.getAltPerName()).toString();
   }
}
