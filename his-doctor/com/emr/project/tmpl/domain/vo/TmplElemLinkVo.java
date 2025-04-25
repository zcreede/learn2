package com.emr.project.tmpl.domain.vo;

import com.emr.project.tmpl.domain.TmplElemLink;
import com.emr.project.tmpl.domain.TmplElemLinkElem;
import java.util.List;

public class TmplElemLinkVo extends TmplElemLink {
   private List linkElemList;
   private String linkType;
   private String tmplContElemName;
   private List linkElemType1List;
   private List linkElemType2List;
   private List linkElemType3List;
   private List linkElemVoList;

   public String getTmplContElemName() {
      return this.tmplContElemName;
   }

   public void setTmplContElemName(String tmplContElemName) {
      this.tmplContElemName = tmplContElemName;
   }

   public List getLinkElemList() {
      return this.linkElemList;
   }

   public void setLinkElemList(List linkElemList) {
      this.linkElemList = linkElemList;
   }

   public String getLinkType() {
      return this.linkType;
   }

   public void setLinkType(String linkType) {
      this.linkType = linkType;
   }

   public List getLinkElemType1List() {
      return this.linkElemType1List;
   }

   public void setLinkElemType1List(List linkElemType1List) {
      this.linkElemType1List = linkElemType1List;
   }

   public List getLinkElemType2List() {
      return this.linkElemType2List;
   }

   public void setLinkElemType2List(List linkElemType2List) {
      this.linkElemType2List = linkElemType2List;
   }

   public List getLinkElemType3List() {
      return this.linkElemType3List;
   }

   public void setLinkElemType3List(List linkElemType3List) {
      this.linkElemType3List = linkElemType3List;
   }

   public List getLinkElemVoList() {
      return this.linkElemVoList;
   }

   public void setLinkElemVoList(List linkElemVoList) {
      this.linkElemVoList = linkElemVoList;
   }
}
