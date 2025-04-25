package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.BasEmployee;
import java.util.List;

public class BasEmployeeSearchVo extends BasEmployee {
   private List titleCodeList;
   private String groupId;

   public String getGroupId() {
      return this.groupId;
   }

   public void setGroupId(String groupId) {
      this.groupId = groupId;
   }

   public List getTitleCodeList() {
      return this.titleCodeList;
   }

   public void setTitleCodeList(List titleCodeList) {
      this.titleCodeList = titleCodeList;
   }
}
