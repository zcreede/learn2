package com.emr.framework.web.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;

public class TreeSelectElem implements Serializable {
   private static final long serialVersionUID = 1L;
   private String id;
   private String code;
   private String label;
   private String parentCode;
   @JsonInclude(Include.NON_EMPTY)
   private List children;

   public TreeSelectElem() {
   }

   public TreeSelectElem(String id, String code, String label, List children) {
      this.id = id;
      this.code = code;
      this.label = label;
      this.children = children;
   }

   public TreeSelectElem(String code, String label, List children) {
      this.code = code;
      this.label = label;
      this.children = children;
   }

   public TreeSelectElem(String id, String code, String label) {
      this.id = id;
      this.code = code;
      this.label = label;
   }

   public TreeSelectElem(String code, String label) {
      this.code = code;
      this.label = label;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getLabel() {
      return this.label;
   }

   public void setLabel(String label) {
      this.label = label;
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

   public String getParentCode() {
      return this.parentCode;
   }

   public void setParentCode(String parentCode) {
      this.parentCode = parentCode;
   }
}
