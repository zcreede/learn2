package com.emr.project.tmpm.domain.vo;

import com.emr.project.tmpm.domain.TmPmCipherDetail;
import com.emr.project.tmpm.domain.TmPmCipherMain;
import java.util.List;

public class TmPmCipherMainVo extends TmPmCipherMain {
   private String deptCode;
   private String docCode;
   private List detailList;
   private List insertList;
   private Boolean collectFlag;
   private TmPmCipherMain saveAsInfo;

   public TmPmCipherMain getSaveAsInfo() {
      return this.saveAsInfo;
   }

   public void setSaveAsInfo(TmPmCipherMain saveAsInfo) {
      this.saveAsInfo = saveAsInfo;
   }

   public List getInsertList() {
      return this.insertList;
   }

   public void setInsertList(List insertList) {
      this.insertList = insertList;
   }

   public Boolean getCollectFlag() {
      return this.collectFlag;
   }

   public void setCollectFlag(Boolean collectFlag) {
      this.collectFlag = collectFlag;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDocCode() {
      return this.docCode;
   }

   public void setDocCode(String docCode) {
      this.docCode = docCode;
   }
}
