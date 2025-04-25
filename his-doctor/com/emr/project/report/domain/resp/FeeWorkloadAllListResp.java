package com.emr.project.report.domain.resp;

import com.emr.project.report.domain.vo.TmBsAccountThirdVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FeeWorkloadAllListResp implements Serializable {
   private List list = new ArrayList();
   private String allTotal;
   private List tableHeader = new ArrayList();

   public List getTableHeader() {
      return this.tableHeader;
   }

   public void setTableHeader(List tableHeader) {
      this.tableHeader = tableHeader;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public String getAllTotal() {
      return this.allTotal;
   }

   public void setAllTotal(String allTotal) {
      this.allTotal = allTotal;
   }
}
