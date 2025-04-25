package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcScoreScheDept;
import java.util.List;

public class QcScoreScheDeptVo extends QcScoreScheDept {
   List deptList;

   public List getDeptList() {
      return this.deptList;
   }

   public void setDeptList(List deptList) {
      this.deptList = deptList;
   }
}
