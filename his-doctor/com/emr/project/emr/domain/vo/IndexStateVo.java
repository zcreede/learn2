package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.Index;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import java.util.List;

public class IndexStateVo {
   private VisitinfoPersonalVo visitinfoPersonalVo;
   private Integer indexSealupState;
   private List indexList;

   public Integer getIndexSealupState() {
      return this.indexSealupState;
   }

   public void setIndexSealupState(Integer indexSealupState) {
      this.indexSealupState = indexSealupState;
   }

   public VisitinfoPersonalVo getVisitinfoPersonalVo() {
      return this.visitinfoPersonalVo;
   }

   public void setVisitinfoPersonalVo(VisitinfoPersonalVo visitinfoPersonalVo) {
      this.visitinfoPersonalVo = visitinfoPersonalVo;
   }

   public List getIndexList() {
      return this.indexList;
   }

   public void setIndexList(List indexList) {
      this.indexList = indexList;
   }
}
