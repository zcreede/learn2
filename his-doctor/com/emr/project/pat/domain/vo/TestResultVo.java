package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.TestResult;
import java.util.List;

public class TestResultVo extends TestResult {
   private String resultDate;
   List testResultlist;
   private String hisItemCd;
   private String appCd;

   public String getAppCd() {
      return this.appCd;
   }

   public void setAppCd(String appCd) {
      this.appCd = appCd;
   }

   public String getHisItemCd() {
      return this.hisItemCd;
   }

   public void setHisItemCd(String hisItemCd) {
      this.hisItemCd = hisItemCd;
   }

   public String getResultDate() {
      return this.resultDate;
   }

   public void setResultDate(String resultDate) {
      this.resultDate = resultDate;
   }

   public List getTestResultlist() {
      return this.testResultlist;
   }

   public void setTestResultVolist(List testResultlist) {
      this.testResultlist = testResultlist;
   }
}
