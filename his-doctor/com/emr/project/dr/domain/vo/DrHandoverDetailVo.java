package com.emr.project.dr.domain.vo;

import com.emr.project.dr.domain.DrHandoverDetail;
import java.util.List;

public class DrHandoverDetailVo extends DrHandoverDetail {
   private String saveFlag;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private List detailList;
   private String groupId;

   public String getGroupId() {
      return this.groupId;
   }

   public void setGroupId(String groupId) {
      this.groupId = groupId;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }

   public Long getAgeY() {
      return this.ageY;
   }

   public void setAgeY(Long ageY) {
      this.ageY = ageY;
   }

   public Long getAgeM() {
      return this.ageM;
   }

   public void setAgeM(Long ageM) {
      this.ageM = ageM;
   }

   public Long getAgeD() {
      return this.ageD;
   }

   public void setAgeD(Long ageD) {
      this.ageD = ageD;
   }

   public Long getAgeH() {
      return this.ageH;
   }

   public void setAgeH(Long ageH) {
      this.ageH = ageH;
   }

   public Long getAgeMi() {
      return this.ageMi;
   }

   public void setAgeMi(Long ageMi) {
      this.ageMi = ageMi;
   }

   public String getSaveFlag() {
      return this.saveFlag;
   }

   public void setSaveFlag(String saveFlag) {
      this.saveFlag = saveFlag;
   }
}
