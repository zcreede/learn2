package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import java.util.List;

public class IndexListVo {
   private List indexList;
   private List subfileIndexList;

   public List getIndexList() {
      return this.indexList;
   }

   public void setIndexList(List indexList) {
      this.indexList = indexList;
   }

   public List getSubfileIndexList() {
      return this.subfileIndexList;
   }

   public void setSubfileIndexList(List subfileIndexList) {
      this.subfileIndexList = subfileIndexList;
   }
}
