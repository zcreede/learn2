package com.emr.project.webEditor.zb.vo;

import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.PhysignValueVo;
import com.emr.project.emr.domain.Index;
import com.emr.project.pat.domain.OperatRecord;
import java.util.List;
import java.util.Map;

public class DayVo {
   private String dayDate;
   private Integer dayNum;
   private List physignValueVoList;
   private Map physignValueVoListMap;
   private List indexList;
   private Map map;
   private List operatRecordList;

   public DayVo() {
   }

   public DayVo(String dayDate, Integer dayNum) {
      this.dayDate = dayDate;
      this.dayNum = dayNum;
   }

   public List getOperatRecordList() {
      return this.operatRecordList;
   }

   public void setOperatRecordList(List operatRecordList) {
      this.operatRecordList = operatRecordList;
   }

   public Map getMap() {
      return this.map;
   }

   public void setMap(Map map) {
      this.map = map;
   }

   public List getIndexList() {
      return this.indexList;
   }

   public void setIndexList(List indexList) {
      this.indexList = indexList;
   }

   public String getDayDate() {
      return this.dayDate;
   }

   public void setDayDate(String dayDate) {
      this.dayDate = dayDate;
   }

   public Integer getDayNum() {
      return this.dayNum;
   }

   public void setDayNum(Integer dayNum) {
      this.dayNum = dayNum;
   }

   public List getPhysignValueVoList() {
      return this.physignValueVoList;
   }

   public void setPhysignValueVoList(List physignValueVoList) {
      this.physignValueVoList = physignValueVoList;
   }

   public Map getPhysignValueVoListMap() {
      return this.physignValueVoListMap;
   }

   public void setPhysignValueVoListMap(Map physignValueVoListMap) {
      this.physignValueVoListMap = physignValueVoListMap;
   }

   public String toString() {
      return "DayVo{dayDate='" + this.dayDate + '\'' + ", dayNum=" + this.dayNum + '}';
   }
}
