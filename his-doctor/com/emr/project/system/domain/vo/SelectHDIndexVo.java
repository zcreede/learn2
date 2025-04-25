package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.SysDept;
import java.util.List;

public class SelectHDIndexVo extends SysDept {
   private List children;

   public SelectHDIndexVo() {
   }

   public SelectHDIndexVo(SysDept sysDept) {
      super.setDeptId(sysDept.getDeptId());
      super.setParentId(sysDept.getParentId());
      super.setAncestors(sysDept.getAncestors());
      super.setDeptName(sysDept.getDeptName());
      super.setOrderNum(sysDept.getOrderNum());
      super.setLeader(sysDept.getLeader());
      super.setPhone(sysDept.getPhone());
      super.setEmail(sysDept.getEmail());
      super.setStatus(sysDept.getStatus());
      super.setDelFlag(sysDept.getDelFlag());
      super.setParentName(sysDept.getParentName());
      super.setSearchCode(sysDept.getSearchCode());
      super.setDeptCode(sysDept.getDeptCode());
      super.setDeptType(sysDept.getDeptType());
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }
}
