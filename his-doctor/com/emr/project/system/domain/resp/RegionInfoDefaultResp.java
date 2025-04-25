package com.emr.project.system.domain.resp;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class RegionInfoDefaultResp implements Serializable {
   @ApiModelProperty("省")
   private String addressSheng;
   @ApiModelProperty("市")
   private String addressShi;
   @ApiModelProperty("县")
   private String addressXian;
   @ApiModelProperty("乡")
   private String addressXiang;
   @ApiModelProperty("(省)地址code")
   private String addressShengCode;
   @ApiModelProperty("(市)地址code")
   private String addressShiCode;
   @ApiModelProperty("(县)地址code")
   private String addressXianCode;
   @ApiModelProperty("(乡)地址code")
   private String addressXiangCode;

   public String getAddressSheng() {
      return this.addressSheng;
   }

   public void setAddressSheng(String addressSheng) {
      this.addressSheng = addressSheng;
   }

   public String getAddressShi() {
      return this.addressShi;
   }

   public void setAddressShi(String addressShi) {
      this.addressShi = addressShi;
   }

   public String getAddressXian() {
      return this.addressXian;
   }

   public void setAddressXian(String addressXian) {
      this.addressXian = addressXian;
   }

   public String getAddressXiang() {
      return this.addressXiang;
   }

   public void setAddressXiang(String addressXiang) {
      this.addressXiang = addressXiang;
   }

   public String getAddressShengCode() {
      return this.addressShengCode;
   }

   public void setAddressShengCode(String addressShengCode) {
      this.addressShengCode = addressShengCode;
   }

   public String getAddressShiCode() {
      return this.addressShiCode;
   }

   public void setAddressShiCode(String addressShiCode) {
      this.addressShiCode = addressShiCode;
   }

   public String getAddressXianCode() {
      return this.addressXianCode;
   }

   public void setAddressXianCode(String addressXianCode) {
      this.addressXianCode = addressXianCode;
   }

   public String getAddressXiangCode() {
      return this.addressXiangCode;
   }

   public void setAddressXiangCode(String addressXiangCode) {
      this.addressXiangCode = addressXiangCode;
   }
}
