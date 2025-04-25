package com.emr.framework.web.domain;

import com.emr.project.system.domain.BasDictMedicine;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysDictType;
import com.emr.project.system.domain.SysMenu;
import com.emr.project.system.domain.SysOrg;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class TreeSelect implements Serializable {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String code;
   private String label;
   private String parentCode;
   @JsonInclude(Include.NON_EMPTY)
   private List children;

   public TreeSelect() {
   }

   public TreeSelect(Long id, String code, String label, List children) {
      this.id = id;
      this.code = code;
      this.label = label;
      this.children = children;
   }

   public TreeSelect(String code, String label, List children) {
      this.code = code;
      this.label = label;
      this.children = children;
   }

   public TreeSelect(Long id, String code, String label) {
      this.id = id;
      this.code = code;
      this.label = label;
   }

   public TreeSelect(String code, String label) {
      this.code = code;
      this.label = label;
   }

   public TreeSelect(SysDept dept) {
      this.id = dept.getDeptId();
      this.code = dept.getDeptCode();
      this.label = dept.getDeptName();
      this.children = (List)dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public TreeSelect(SysOrg org) {
      this.id = org.getId();
      this.code = org.getOrgCode();
      this.label = org.getOrgName();
      this.parentCode = org.getParentCode();
      this.children = (List)org.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public TreeSelect(SysMenu menu) {
      this.id = menu.getMenuId();
      this.label = menu.getMenuName();
      this.children = (List)menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public TreeSelect(SysDictType dictType) {
      this.id = dictType.getDictId();
      this.label = dictType.getDictName();
      this.children = (List)dictType.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public TreeSelect(SysDictData dictData) {
      this.id = dictData.getDictCode();
      this.label = dictData.getDictLabel();
      this.children = (List)dictData.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public TreeSelect(BasDictMedicine basDictMedicine) {
      this.id = basDictMedicine.getId();
      this.label = basDictMedicine.getName();
      this.code = basDictMedicine.getCode();
      this.children = (List)basDictMedicine.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
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
