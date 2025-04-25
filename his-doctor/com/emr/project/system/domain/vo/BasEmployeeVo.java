package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.BasEmployee;
import java.util.List;

public class BasEmployeeVo extends BasEmployee {
   private String hosName;
   private String nationName;
   private String mariStatusName;
   private String employeeId;
   private String authorLevel;
   private String highaAuthor;
   private List userRoleDepts;
   private Long[] postIds;
   private String certSn;
   private String roleType;
   private String consType;
   private String staffNo;
   private String healthCode;

   public String getHealthCode() {
      return this.healthCode;
   }

   public void setHealthCode(String healthCode) {
      this.healthCode = healthCode;
   }

   public String getStaffNo() {
      return this.staffNo;
   }

   public void setStaffNo(String staffNo) {
      this.staffNo = staffNo;
   }

   public String getRoleType() {
      return this.roleType;
   }

   public void setRoleType(String roleType) {
      this.roleType = roleType;
   }

   public String getConsType() {
      return this.consType;
   }

   public void setConsType(String consType) {
      this.consType = consType;
   }

   public String getCertSn() {
      return this.certSn;
   }

   public void setCertSn(String certSn) {
      this.certSn = certSn;
   }

   public Long[] getPostIds() {
      return this.postIds;
   }

   public void setPostIds(Long[] postIds) {
      this.postIds = postIds;
   }

   public String getEmployeeId() {
      return this.employeeId;
   }

   public void setEmployeeId(String employeeId) {
      this.employeeId = employeeId;
   }

   public List getUserRoleDepts() {
      return this.userRoleDepts;
   }

   public void setUserRoleDepts(List userRoleDepts) {
      this.userRoleDepts = userRoleDepts;
   }

   public String getHighaAuthor() {
      return this.highaAuthor;
   }

   public void setHighaAuthor(String highaAuthor) {
      this.highaAuthor = highaAuthor;
   }

   public String getAuthorLevel() {
      return this.authorLevel;
   }

   public void setAuthorLevel(String authorLevel) {
      this.authorLevel = authorLevel;
   }

   public String getHosName() {
      return this.hosName;
   }

   public void setHosName(String hosName) {
      this.hosName = hosName;
   }

   public String getNationName() {
      return this.nationName;
   }

   public void setNationName(String nationName) {
      this.nationName = nationName;
   }

   public String getMariStatusName() {
      return this.mariStatusName;
   }

   public void setMariStatusName(String mariStatusName) {
      this.mariStatusName = mariStatusName;
   }
}
