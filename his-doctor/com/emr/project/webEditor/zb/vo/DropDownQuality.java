package com.emr.project.webEditor.zb.vo;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class DropDownQuality extends BaseQuality {
   private String listDataSource;
   private String dictType;
   private String listDataArrStr;
   @JSONField(
      name = "FrontString"
   )
   private String FrontString;
   @JSONField(
      name = "UnSelectedFrontString"
   )
   private String UnSelectedFrontString;
   @JSONField(
      name = "Separator"
   )
   private String Separator;
   @JSONField(
      name = "GroupSeparator"
   )
   private String GroupSeparator;
   @JSONField(
      name = "CodeArray"
   )
   private List CodeArray;
   @JSONField(
      name = "ValueArray"
   )
   private List ValueArray;
   @JSONField(
      name = "MutexString"
   )
   private String MutexString;
   private String setName;

   public String getSetName() {
      return this.setName;
   }

   public void setSetName(String setName) {
      this.setName = setName;
   }

   public List getCodeArray() {
      return this.CodeArray;
   }

   public void setCodeArray(List codeArray) {
      this.CodeArray = codeArray;
   }

   public List getValueArray() {
      return this.ValueArray;
   }

   public void setValueArray(List valueArray) {
      this.ValueArray = valueArray;
   }

   public String getMutexString() {
      return this.MutexString;
   }

   public void setMutexString(String mutexString) {
      this.MutexString = mutexString;
   }

   public String getListDataSource() {
      return this.listDataSource;
   }

   public void setListDataSource(String listDataSource) {
      this.listDataSource = listDataSource;
   }

   public String getDictType() {
      return this.dictType;
   }

   public void setDictType(String dictType) {
      this.dictType = dictType;
   }

   public String getListDataArrStr() {
      return this.listDataArrStr;
   }

   public void setListDataArrStr(String listDataArrStr) {
      this.listDataArrStr = listDataArrStr;
   }

   public String getFrontString() {
      return this.FrontString;
   }

   public void setFrontString(String frontString) {
      this.FrontString = frontString;
   }

   public String getUnSelectedFrontString() {
      return this.UnSelectedFrontString;
   }

   public void setUnSelectedFrontString(String unSelectedFrontString) {
      this.UnSelectedFrontString = unSelectedFrontString;
   }

   public String getSeparator() {
      return this.Separator;
   }

   public void setSeparator(String separator) {
      this.Separator = separator;
   }

   public String getGroupSeparator() {
      return this.GroupSeparator;
   }

   public void setGroupSeparator(String groupSeparator) {
      this.GroupSeparator = groupSeparator;
   }
}
