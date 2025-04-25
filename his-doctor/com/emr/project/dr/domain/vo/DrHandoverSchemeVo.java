package com.emr.project.dr.domain.vo;

import com.emr.framework.web.domain.TreeSelect;
import com.emr.project.dr.domain.DrHandoverScheme;
import com.emr.project.dr.domain.DrHandoverSchemeDept;
import java.util.List;

public class DrHandoverSchemeVo extends DrHandoverScheme {
   private String deptCd;
   private String deptNames;
   private List deptCdList;
   private List deptList;
   public List depts;

   public List getDepts() {
      return this.depts;
   }

   public void setDepts(List depts) {
      this.depts = depts;
   }

   public String getDeptNames() {
      return this.deptNames;
   }

   public void setDeptNames(String deptNames) {
      this.deptNames = deptNames;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public List getDeptCdList() {
      return this.deptCdList;
   }

   public void setDeptCdList(List deptCdList) {
      this.deptCdList = deptCdList;
   }

   public List getDeptList() {
      return this.deptList;
   }

   public void setDeptList(List deptList) {
      this.deptList = deptList;
   }
}
