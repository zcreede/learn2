package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcScoreSche;
import com.emr.project.qc.domain.QcScoreScheDept;
import com.emr.project.system.domain.SysDept;
import java.util.List;

public class QcScoreScheVo extends QcScoreSche {
   private List deptList;
   private String dedNum;
   private String deptNum;
   private String appSegName;
   private List qcScoreScheDeptList;
   private List sysDeptList;
   private String deptFlag;
   private List qcScoreScheItemClassList;

   public String getDeptFlag() {
      return this.deptFlag;
   }

   public void setDeptFlag(String deptFlag) {
      this.deptFlag = deptFlag;
   }

   public List getSysDeptList() {
      return this.sysDeptList;
   }

   public void setSysDeptList(List sysDeptList) {
      this.sysDeptList = sysDeptList;
   }

   public String getAppSegName() {
      return this.appSegName;
   }

   public void setAppSegName(String appSegName) {
      this.appSegName = appSegName;
   }

   public List getQcScoreScheItemClassList() {
      return this.qcScoreScheItemClassList;
   }

   public void setQcScoreScheItemClassList(List qcScoreScheItemClassList) {
      this.qcScoreScheItemClassList = qcScoreScheItemClassList;
   }

   public List getQcScoreScheDeptList() {
      return this.qcScoreScheDeptList;
   }

   public void setQcScoreScheDeptList(List qcScoreScheDeptList) {
      this.qcScoreScheDeptList = qcScoreScheDeptList;
   }

   public String getDedNum() {
      return this.dedNum;
   }

   public void setDedNum(String dedNum) {
      this.dedNum = dedNum;
   }

   public String getDeptNum() {
      return this.deptNum;
   }

   public void setDeptNum(String deptNum) {
      this.deptNum = deptNum;
   }

   public List getDeptList() {
      return this.deptList;
   }

   public void setDeptList(List deptList) {
      this.deptList = deptList;
   }
}
