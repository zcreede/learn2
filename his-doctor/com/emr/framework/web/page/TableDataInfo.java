package com.emr.framework.web.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableDataInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   private long total;
   private List rows;
   private int code;
   private String msg;
   private String result;
   private Object object;

   public TableDataInfo() {
      this.code = 200;
      this.msg = "查询成功";
      this.rows = new ArrayList();
      this.total = 0L;
   }

   public TableDataInfo(List list, int total) {
      this.rows = list;
      this.total = (long)total;
   }

   public TableDataInfo(int code, String msg) {
      this.code = code;
      this.msg = msg;
   }

   public String getResult() {
      return this.result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public long getTotal() {
      return this.total;
   }

   public void setTotal(long total) {
      this.total = total;
   }

   public List getRows() {
      return this.rows;
   }

   public void setRows(List rows) {
      this.rows = rows;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public Object getObject() {
      return this.object;
   }

   public void setObject(Object object) {
      this.object = object;
   }
}
