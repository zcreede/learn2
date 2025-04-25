package com.emr.project.pat.domain.vo;

import com.emr.project.qc.domain.vo.EmrMessageVo;
import java.util.List;

public class TestExamAlertMsgResVo {
   private List msgList;
   private List testAlertList;
   private List examAlertList;
   private List alertMsgVoList;

   public List getMsgList() {
      return this.msgList;
   }

   public void setMsgList(List msgList) {
      this.msgList = msgList;
   }

   public List getTestAlertList() {
      return this.testAlertList;
   }

   public void setTestAlertList(List testAlertList) {
      this.testAlertList = testAlertList;
   }

   public List getExamAlertList() {
      return this.examAlertList;
   }

   public void setExamAlertList(List examAlertList) {
      this.examAlertList = examAlertList;
   }

   public List getAlertMsgVoList() {
      return this.alertMsgVoList;
   }

   public void setAlertMsgVoList(List alertMsgVoList) {
      this.alertMsgVoList = alertMsgVoList;
   }
}
