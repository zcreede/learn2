package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.Transinfo;
import java.util.Date;

public class TransinfoVo extends Transinfo {
   private Date date;

   public Date getDate() {
      return this.date;
   }

   public void setDate(Date date) {
      this.date = date;
   }
}
