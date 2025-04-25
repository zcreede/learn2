package com.emr.project.mrhp.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MrHpPrintVo implements Serializable {
   private List diagPrintXyList = new ArrayList();
   private List medicalInfor = new ArrayList();
   private List diagPrintZyList = new ArrayList();
   private List printOperInfoList = new ArrayList();
   private List picInfoVoList = new ArrayList();

   public List getPicInfoVoList() {
      return this.picInfoVoList;
   }

   public void setPicInfoVoList(List picInfoVoList) {
      this.picInfoVoList = picInfoVoList;
   }

   public List getDiagPrintXyList() {
      return this.diagPrintXyList;
   }

   public void setDiagPrintXyList(List diagPrintXyList) {
      this.diagPrintXyList = diagPrintXyList;
   }

   public List getMedicalInfor() {
      return this.medicalInfor;
   }

   public void setMedicalInfor(List medicalInfor) {
      this.medicalInfor = medicalInfor;
   }

   public List getDiagPrintZyList() {
      return this.diagPrintZyList;
   }

   public void setDiagPrintZyList(List diagPrintZyList) {
      this.diagPrintZyList = diagPrintZyList;
   }

   public List getPrintOperInfoList() {
      return this.printOperInfoList;
   }

   public void setPrintOperInfoList(List printOperInfoList) {
      this.printOperInfoList = printOperInfoList;
   }
}
