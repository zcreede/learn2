package com.emr.project.mrhp.domain.req;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class DictTypePageReq implements Serializable {
   @ApiModelProperty("搜索内容")
   private String searchValue;

   public String getSearchValue() {
      return this.searchValue;
   }

   public void setSearchValue(String searchValue) {
      this.searchValue = searchValue;
   }
}
