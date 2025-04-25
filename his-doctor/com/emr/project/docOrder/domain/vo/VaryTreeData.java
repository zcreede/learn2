package com.emr.project.docOrder.domain.vo;

import java.io.Serializable;
import java.util.List;

public class VaryTreeData implements Serializable {
   private String name;
   private String pyName;
   private String code;
   private List children;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPyName() {
      return this.pyName;
   }

   public void setPyName(String pyName) {
      this.pyName = pyName;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }
}
