package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.BasDictMedicine;
import java.util.List;

public class BasDictMedicineVo extends BasDictMedicine {
   private List deptCodeList;
   private List soleDeptCodeList;

   public List getSoleDeptCodeList() {
      return this.soleDeptCodeList;
   }

   public void setSoleDeptCodeList(List soleDeptCodeList) {
      this.soleDeptCodeList = soleDeptCodeList;
   }

   public List getDeptCodeList() {
      return this.deptCodeList;
   }

   public void setDeptCodeList(List deptCodeList) {
      this.deptCodeList = deptCodeList;
   }
}
