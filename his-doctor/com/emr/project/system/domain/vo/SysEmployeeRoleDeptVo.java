package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.SysEmployeeRoleDept;

public class SysEmployeeRoleDeptVo extends SysEmployeeRoleDept {
   private String deptName;

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }
}
