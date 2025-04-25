package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.sys.domain.vo.BusIntegMenuTreeVo;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;

public class IndexSaveReturnVo {
   private Index index;
   private String mrState;
   private List subfileIndexList;
   private List currMenuTreeList;
   private Date updateTime;
   private SysUser updateUser;
   private SubfileIndex subfileIndex;

   public Index getIndex() {
      return this.index;
   }

   public void setIndex(Index index) {
      this.index = index;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public List getSubfileIndexList() {
      return this.subfileIndexList;
   }

   public void setSubfileIndexList(List subfileIndexList) {
      this.subfileIndexList = subfileIndexList;
   }

   public List getCurrMenuTreeList() {
      return this.currMenuTreeList;
   }

   public void setCurrMenuTreeList(List currMenuTreeList) {
      this.currMenuTreeList = currMenuTreeList;
   }

   public Date getUpdateTime() {
      return this.updateTime;
   }

   public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
   }

   public SysUser getUpdateUser() {
      return this.updateUser;
   }

   public void setUpdateUser(SysUser updateUser) {
      this.updateUser = updateUser;
   }

   public SubfileIndex getSubfileIndex() {
      return this.subfileIndex;
   }

   public void setSubfileIndex(SubfileIndex subfileIndex) {
      this.subfileIndex = subfileIndex;
   }
}
