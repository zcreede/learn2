package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.BasDoctGroupMember;
import java.util.List;

public class BasDoctGroupMemberVo extends BasDoctGroupMember {
   private List list;
   private String deptCd;

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }
}
