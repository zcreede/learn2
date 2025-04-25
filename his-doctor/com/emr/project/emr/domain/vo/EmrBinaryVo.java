package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.SubfileIndex;
import java.util.List;

public class EmrBinaryVo extends EmrBinary {
   private List subfileIndexList;

   public List getSubfileIndexList() {
      return this.subfileIndexList;
   }

   public void setSubfileIndexList(List subfileIndexList) {
      this.subfileIndexList = subfileIndexList;
   }
}
