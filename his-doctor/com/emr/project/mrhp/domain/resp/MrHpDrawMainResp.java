package com.emr.project.mrhp.domain.resp;

import com.emr.project.mrhp.domain.MrHpDrawApi;
import com.emr.project.mrhp.domain.MrHpDrawField;
import com.emr.project.mrhp.domain.MrHpDrawMain;
import java.util.List;

public class MrHpDrawMainResp extends MrHpDrawMain {
   private List mrHpDrawFieldList;
   private List mrHpDrawApiList;

   public List getMrHpDrawFieldList() {
      return this.mrHpDrawFieldList;
   }

   public void setMrHpDrawFieldList(List mrHpDrawFieldList) {
      this.mrHpDrawFieldList = mrHpDrawFieldList;
   }

   public List getMrHpDrawApiList() {
      return this.mrHpDrawApiList;
   }

   public void setMrHpDrawApiList(List mrHpDrawApiList) {
      this.mrHpDrawApiList = mrHpDrawApiList;
   }
}
