package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.SysEmrLog;
import java.util.Date;

public class SysEmrLogVo extends SysEmrLog {
   private Date creBeginDate;
   private Date creEndDate;
   private String[] optTypeList;

   public String[] getOptTypeList() {
      return this.optTypeList;
   }

   public void setOptTypeList(String[] optTypeList) {
      this.optTypeList = optTypeList;
   }

   public Date getCreBeginDate() {
      return this.creBeginDate;
   }

   public void setCreBeginDate(Date creBeginDate) {
      this.creBeginDate = creBeginDate;
   }

   public Date getCreEndDate() {
      return this.creEndDate;
   }

   public void setCreEndDate(Date creEndDate) {
      this.creEndDate = creEndDate;
   }
}
