package com.emr.project.mrhp.domain.req;

import com.emr.project.mrhp.domain.MrHpDrawApi;
import com.emr.project.mrhp.domain.TmDsPreserveOut;
import java.util.List;

public class TmDsPreserveOutReq extends TmDsPreserveOut {
   private String sqlStr;
   private List mrHpDrawApiList;

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public List getMrHpDrawApiList() {
      return this.mrHpDrawApiList;
   }

   public void setMrHpDrawApiList(List mrHpDrawApiList) {
      this.mrHpDrawApiList = mrHpDrawApiList;
   }
}
