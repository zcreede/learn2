package com.emr.project.mrhp.domain.req;

import com.emr.project.mrhp.domain.MrHpDrawApi;
import com.emr.project.mrhp.domain.MrHpDrawField;
import com.emr.project.mrhp.domain.MrHpDrawMain;
import java.util.List;

public class MrHpDrawMainReq extends MrHpDrawMain {
   private List mrHpDrawApiList;
   private List mrHpDrawFieldList;

   public List getMrHpDrawApiList() {
      return this.mrHpDrawApiList;
   }

   public void setMrHpDrawApiList(List mrHpDrawApiList) {
      this.mrHpDrawApiList = mrHpDrawApiList;
   }

   public List getMrHpDrawFieldList() {
      return this.mrHpDrawFieldList;
   }

   public void setMrHpDrawFieldList(List mrHpDrawFieldList) {
      this.mrHpDrawFieldList = mrHpDrawFieldList;
   }
}
