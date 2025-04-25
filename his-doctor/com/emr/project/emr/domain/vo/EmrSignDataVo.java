package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrSignData;
import java.util.List;

public class EmrSignDataVo extends EmrSignData {
   private List idList;
   private List resDocCdList;

   public List getResDocCdList() {
      return this.resDocCdList;
   }

   public void setResDocCdList(List resDocCdList) {
      this.resDocCdList = resDocCdList;
   }

   public List getIdList() {
      return this.idList;
   }

   public void setIdList(List idList) {
      this.idList = idList;
   }
}
