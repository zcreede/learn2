package com.emr.project.tmpm.domain.vo;

import com.emr.project.tmpm.domain.ClinitemUseLog;
import java.util.List;

public class ClinitemUseLogVo extends ClinitemUseLog {
   private List itemClassCdList;

   public ClinitemUseLogVo(String hospitalCode, String docCd, List itemClassCdList) {
      super.setHospitalCode(hospitalCode);
      super.setDocCd(docCd);
      this.itemClassCdList = itemClassCdList;
   }

   public List getItemClassCdList() {
      return this.itemClassCdList;
   }

   public void setItemClassCdList(List itemClassCdList) {
      this.itemClassCdList = itemClassCdList;
   }
}
