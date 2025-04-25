package com.emr.project.docOrder.domain.vo;

import java.util.List;
import java.util.Map;

public class OrderListInfoVo {
   private List list;
   private List listMap;
   private OrderListPatientVo patientInfo;
   private Integer pageNum;
   private Integer total;
   List pageDataList;
   private List listAll;
   Map herbalMap;

   public Map getHerbalMap() {
      return this.herbalMap;
   }

   public List getListMap() {
      return this.listMap;
   }

   public void setListMap(List listMap) {
      this.listMap = listMap;
   }

   public void setHerbalMap(Map herbalMap) {
      this.herbalMap = herbalMap;
   }

   public List getPageDataList() {
      return this.pageDataList;
   }

   public void setPageDataList(List pageDataList) {
      this.pageDataList = pageDataList;
   }

   public List getListAll() {
      return this.listAll;
   }

   public void setListAll(List listAll) {
      this.listAll = listAll;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public OrderListPatientVo getPatientInfo() {
      return this.patientInfo;
   }

   public void setPatientInfo(OrderListPatientVo patientInfo) {
      this.patientInfo = patientInfo;
   }

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }
}
