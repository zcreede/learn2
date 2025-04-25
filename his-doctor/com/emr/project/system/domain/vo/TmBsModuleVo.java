package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.TmBsModule;
import java.util.List;

public class TmBsModuleVo extends TmBsModule {
   private List selectHDIndexVoList;

   public List getSelectHDIndexVoList() {
      return this.selectHDIndexVoList;
   }

   public void setSelectHDIndexVoList(List selectHDIndexVoList) {
      this.selectHDIndexVoList = selectHDIndexVoList;
   }
}
