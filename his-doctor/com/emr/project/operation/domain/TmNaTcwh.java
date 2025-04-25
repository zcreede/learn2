package com.emr.project.operation.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmNaTcwh extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("ID")
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   @ApiModelProperty("医疗机构代码")
   private String hospitalCode;
   @Excel(
      name = "病区编码"
   )
   @ApiModelProperty("病区编码")
   private String wardNo;
   @Excel(
      name = "套餐编码"
   )
   @ApiModelProperty("套餐编码")
   private String packageNo;
   @Excel(
      name = "套餐名称"
   )
   @ApiModelProperty("套餐名称")
   private String packageName;
   @Excel(
      name = "拼音码"
   )
   @ApiModelProperty("拼音码")
   private String packagePym;
   @Excel(
      name = "共享类别"
   )
   @ApiModelProperty("共享类别")
   private String shareClass;
   @Excel(
      name = "注销标志"
   )
   @ApiModelProperty("注销标志")
   private String flag;
   @Excel(
      name = "创建人内码"
   )
   @ApiModelProperty("创建人内码")
   private String operatorNo;

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

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setPackageNo(String packageNo) {
      this.packageNo = packageNo;
   }

   public String getPackageNo() {
      return this.packageNo;
   }

   public void setPackageName(String packageName) {
      this.packageName = packageName;
   }

   public String getPackageName() {
      return this.packageName;
   }

   public void setPackagePym(String packagePym) {
      this.packagePym = packagePym;
   }

   public String getPackagePym() {
      return this.packagePym;
   }

   public void setShareClass(String shareClass) {
      this.shareClass = shareClass;
   }

   public String getShareClass() {
      return this.shareClass;
   }

   public void setFlag(String flag) {
      this.flag = flag;
   }

   public String getFlag() {
      return this.flag;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo;
   }

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("hospitalCode", this.getHospitalCode()).append("wardNo", this.getWardNo()).append("packageNo", this.getPackageNo()).append("packageName", this.getPackageName()).append("packagePym", this.getPackagePym()).append("shareClass", this.getShareClass()).append("flag", this.getFlag()).append("operatorNo", this.getOperatorNo()).toString();
   }
}
