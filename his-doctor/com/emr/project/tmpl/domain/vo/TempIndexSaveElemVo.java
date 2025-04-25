package com.emr.project.tmpl.domain.vo;

import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.ElemDate;
import com.emr.project.tmpl.domain.ElemGender;
import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.ElemPhysign;
import com.emr.project.tmpl.domain.ElemSign;
import java.util.List;

public class TempIndexSaveElemVo {
   private List elemAttriList;
   private List elemDateList;
   private List elemGenderList;
   private List elemMacroList;
   private List elemPhysignList;
   private List elemSignList;

   public TempIndexSaveElemVo() {
   }

   public TempIndexSaveElemVo(List elemAttriList, List elemDateList, List elemGenderList, List elemMacroList, List elemPhysignList, List elemSignList) {
      this.elemAttriList = elemAttriList;
      this.elemDateList = elemDateList;
      this.elemGenderList = elemGenderList;
      this.elemMacroList = elemMacroList;
      this.elemPhysignList = elemPhysignList;
      this.elemSignList = elemSignList;
   }

   public List getElemAttriList() {
      return this.elemAttriList;
   }

   public void setElemAttriList(List elemAttriList) {
      this.elemAttriList = elemAttriList;
   }

   public List getElemDateList() {
      return this.elemDateList;
   }

   public void setElemDateList(List elemDateList) {
      this.elemDateList = elemDateList;
   }

   public List getElemGenderList() {
      return this.elemGenderList;
   }

   public void setElemGenderList(List elemGenderList) {
      this.elemGenderList = elemGenderList;
   }

   public List getElemMacroList() {
      return this.elemMacroList;
   }

   public void setElemMacroList(List elemMacroList) {
      this.elemMacroList = elemMacroList;
   }

   public List getElemPhysignList() {
      return this.elemPhysignList;
   }

   public void setElemPhysignList(List elemPhysignList) {
      this.elemPhysignList = elemPhysignList;
   }

   public List getElemSignList() {
      return this.elemSignList;
   }

   public void setElemSignList(List elemSignList) {
      this.elemSignList = elemSignList;
   }
}
