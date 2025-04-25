package com.emr.project.sys.domain.vo;

import com.emr.project.sys.domain.SysOpeIcd;

public class SysOpeIcdVo extends SysOpeIcd {
   private String docCd;
   private Boolean collectFlag;
   private String levelName;
   private String opeTypeName;

   public String getOpeTypeName() {
      return this.opeTypeName;
   }

   public void setOpeTypeName(String opeTypeName) {
      this.opeTypeName = opeTypeName;
   }

   public String getLevelName() {
      return this.levelName;
   }

   public void setLevelName(String levelName) {
      this.levelName = levelName;
   }

   public Boolean getCollectFlag() {
      return this.collectFlag;
   }

   public void setCollectFlag(Boolean collectFlag) {
      this.collectFlag = collectFlag;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }
}
