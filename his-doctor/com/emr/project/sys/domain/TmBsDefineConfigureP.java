package com.emr.project.sys.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmBsDefineConfigureP extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String hisVer;
   private String systemCode;
   private String code;
   @Excel(
      name = "配置项名称"
   )
   private String name;
   @Excel(
      name = "配置项简称"
   )
   private String nameShort;
   @Excel(
      name = "配置项类型(1:输入,3:多选一) "
   )
   private String type;
   @Excel(
      name = "是否取值"
   )
   private String sfqz;
   @Excel(
      name = "缺省值"
   )
   private String defaultValue;
   @Excel(
      name = "是否限定范围"
   )
   private String range;
   @Excel(
      name = "最小值"
   )
   private String min;
   @Excel(
      name = "最大值"
   )
   private String max;
   @Excel(
      name = "取值方式{1—逻辑型[0,1], 2—整数[0…9]，3—实数[0…9,.],4—字符型,5—日期型}"
   )
   private String qzfs;
   @Excel(
      name = "配置项级数"
   )
   private Long jsInt;
   @Excel(
      name = "末级{0-否，1-是}"
   )
   private String mjChr;
   @Excel(
      name = "是否依赖上级"
   )
   private String sfylsj;
   @Excel(
      name = "依赖值"
   )
   private String ylz;
   @Excel(
      name = "是否依赖其它配置项"
   )
   private String sfylqt;
   @Excel(
      name = "其它配置项编码"
   )
   private String qtbm;
   @Excel(
      name = "其它配置项的依赖值"
   )
   private String qtylz;
   @Excel(
      name = "备注"
   )
   private String remarks;
   @Excel(
      name = "拼音码"
   )
   private String zjm;
   @Excel(
      name = "分类"
   )
   private String classf;
   @Excel(
      name = "父编码",
      readConverterExp = "空=值代表末级"
   )
   private String codeP;

   public void setHisVer(String hisVer) {
      this.hisVer = hisVer;
   }

   public String getHisVer() {
      return this.hisVer;
   }

   public void setSystemCode(String systemCode) {
      this.systemCode = systemCode;
   }

   public String getSystemCode() {
      return this.systemCode;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getCode() {
      return this.code;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setNameShort(String nameShort) {
      this.nameShort = nameShort;
   }

   public String getNameShort() {
      return this.nameShort;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return this.type;
   }

   public void setSfqz(String sfqz) {
      this.sfqz = sfqz;
   }

   public String getSfqz() {
      return this.sfqz;
   }

   public void setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public void setRange(String range) {
      this.range = range;
   }

   public String getRange() {
      return this.range;
   }

   public void setMin(String min) {
      this.min = min;
   }

   public String getMin() {
      return this.min;
   }

   public void setMax(String max) {
      this.max = max;
   }

   public String getMax() {
      return this.max;
   }

   public void setQzfs(String qzfs) {
      this.qzfs = qzfs;
   }

   public String getQzfs() {
      return this.qzfs;
   }

   public void setJsInt(Long jsInt) {
      this.jsInt = jsInt;
   }

   public Long getJsInt() {
      return this.jsInt;
   }

   public void setMjChr(String mjChr) {
      this.mjChr = mjChr;
   }

   public String getMjChr() {
      return this.mjChr;
   }

   public void setSfylsj(String sfylsj) {
      this.sfylsj = sfylsj;
   }

   public String getSfylsj() {
      return this.sfylsj;
   }

   public void setYlz(String ylz) {
      this.ylz = ylz;
   }

   public String getYlz() {
      return this.ylz;
   }

   public void setSfylqt(String sfylqt) {
      this.sfylqt = sfylqt;
   }

   public String getSfylqt() {
      return this.sfylqt;
   }

   public void setQtbm(String qtbm) {
      this.qtbm = qtbm;
   }

   public String getQtbm() {
      return this.qtbm;
   }

   public void setQtylz(String qtylz) {
      this.qtylz = qtylz;
   }

   public String getQtylz() {
      return this.qtylz;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
   }

   public String getRemarks() {
      return this.remarks;
   }

   public void setZjm(String zjm) {
      this.zjm = zjm;
   }

   public String getZjm() {
      return this.zjm;
   }

   public void setClassf(String classf) {
      this.classf = classf;
   }

   public String getClassf() {
      return this.classf;
   }

   public void setCodeP(String codeP) {
      this.codeP = codeP;
   }

   public String getCodeP() {
      return this.codeP;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hisVer", this.getHisVer()).append("systemCode", this.getSystemCode()).append("code", this.getCode()).append("name", this.getName()).append("nameShort", this.getNameShort()).append("type", this.getType()).append("sfqz", this.getSfqz()).append("defaultValue", this.getDefaultValue()).append("range", this.getRange()).append("min", this.getMin()).append("max", this.getMax()).append("qzfs", this.getQzfs()).append("jsInt", this.getJsInt()).append("mjChr", this.getMjChr()).append("sfylsj", this.getSfylsj()).append("ylz", this.getYlz()).append("sfylqt", this.getSfylqt()).append("qtbm", this.getQtbm()).append("qtylz", this.getQtylz()).append("remarks", this.getRemarks()).append("zjm", this.getZjm()).append("classf", this.getClassf()).append("codeP", this.getCodeP()).append("createTime", this.getCreateTime()).append("updateTime", this.getUpdateTime()).toString();
   }
}
