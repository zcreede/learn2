package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.Personalinfo;
import java.util.ArrayList;
import java.util.List;

public class PersonalinfoVo extends Personalinfo {
   PatFeeVo PatFeeVo = new PatFeeVo();
   List feeList = new ArrayList();
   DrgMrGroupVo drgMrGroupVo = new DrgMrGroupVo();
   VisitinfoVo visitinfoVo;
   private String patTypeCd;
   private String patTypeName;
   private String height;
   private String weight;
   private String inpNo;

   public String getPatTypeCd() {
      return this.patTypeCd;
   }

   public void setPatTypeCd(String patTypeCd) {
      this.patTypeCd = patTypeCd;
   }

   public String getPatTypeName() {
      return this.patTypeName;
   }

   public void setPatTypeName(String patTypeName) {
      this.patTypeName = patTypeName;
   }

   public String getHeight() {
      return this.height;
   }

   public void setHeight(String height) {
      this.height = height;
   }

   public String getWeight() {
      return this.weight;
   }

   public void setWeight(String weight) {
      this.weight = weight;
   }

   public VisitinfoVo getVisitinfoVo() {
      return this.visitinfoVo;
   }

   public void setVisitinfoVo(VisitinfoVo visitinfoVo) {
      this.visitinfoVo = visitinfoVo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public PatFeeVo getPatFeeVo() {
      return this.PatFeeVo;
   }

   public void setPatFeeVo(PatFeeVo patFeeVo) {
      this.PatFeeVo = patFeeVo;
   }

   public List getFeeList() {
      return this.feeList;
   }

   public void setFeeList(List feeList) {
      this.feeList = feeList;
   }

   public DrgMrGroupVo getDrgMrGroupVo() {
      return this.drgMrGroupVo;
   }

   public void setDrgMrGroupVo(DrgMrGroupVo drgMrGroupVo) {
      this.drgMrGroupVo = drgMrGroupVo;
   }
}
