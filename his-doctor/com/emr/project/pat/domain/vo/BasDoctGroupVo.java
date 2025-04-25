package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.BasDoctGroup;
import com.emr.project.pat.domain.BasDoctGroupMember;
import java.util.List;

public class BasDoctGroupVo extends BasDoctGroup {
   private List list;
   private Integer nums;

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public Integer getNums() {
      return this.nums;
   }

   public void setNums(Integer nums) {
      this.nums = nums;
   }
}
