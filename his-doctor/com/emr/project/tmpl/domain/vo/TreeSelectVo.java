package com.emr.project.tmpl.domain.vo;

import com.emr.framework.web.domain.TreeSelect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

public class TreeSelectVo {
   private TreeSelect treeSelect;
   private String tempState;
   @JsonInclude(Include.NON_EMPTY)
   private List children;

   public String getTempState() {
      return this.tempState;
   }

   public void setTempState(String tempState) {
      this.tempState = tempState;
   }

   public TreeSelect getTreeSelect() {
      return this.treeSelect;
   }

   public void setTreeSelect(TreeSelect treeSelect) {
      this.treeSelect = treeSelect;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }
}
