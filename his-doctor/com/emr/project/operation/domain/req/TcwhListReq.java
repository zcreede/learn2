package com.emr.project.operation.domain.req;

import com.emr.project.operation.domain.TmNaTcwh;

public class TcwhListReq extends TmNaTcwh {
   private Integer pageNum;
   private Integer pageSize;

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public Integer getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }
}
