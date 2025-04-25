package com.emr.project.tmpl.domain.vo;

import com.emr.framework.web.domain.TreeSelect;
import com.emr.project.system.domain.BasDictMedicine;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysDictType;
import com.emr.project.system.domain.SysMenu;
import com.emr.project.system.domain.SysOrg;
import java.util.Date;
import java.util.List;

public class TreeVo extends TreeSelect {
   private String tempState;
   private String tempEditState;
   private Boolean btnstate;
   private String base64;
   private int sort;
   private Date creDate;
   private Boolean editFlag;
   private String tempType;
   private List childrenVo;
   private String standardTmpl;

   public String getStandardTmpl() {
      return this.standardTmpl;
   }

   public void setStandardTmpl(String standardTmpl) {
      this.standardTmpl = standardTmpl;
   }

   public String getTempEditState() {
      return this.tempEditState;
   }

   public void setTempEditState(String tempEditState) {
      this.tempEditState = tempEditState;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public int getSort() {
      return this.sort;
   }

   public void setSort(int sort) {
      this.sort = sort;
   }

   public String getBase64() {
      return this.base64;
   }

   public void setBase64(String base64) {
      this.base64 = base64;
   }

   public String getTempState() {
      return this.tempState;
   }

   public void setTempState(String tempState) {
      this.tempState = tempState;
   }

   public List getChildrenVo() {
      return this.childrenVo;
   }

   public void setChildrenVo(List childrenVo) {
      this.childrenVo = childrenVo;
   }

   public Boolean getBtnstate() {
      return this.btnstate;
   }

   public void setBtnstate(Boolean btnstate) {
      this.btnstate = btnstate;
   }

   public TreeVo() {
   }

   public TreeVo(Long id, String code, String label, List children) {
      super(id, code, label, children);
   }

   public TreeVo(String code, String label, List children) {
      super(code, label, children);
   }

   public TreeVo(Long id, String code, String label) {
      super(id, code, label);
   }

   public TreeVo(String code, String label) {
      super(code, label);
   }

   public TreeVo(SysDept dept) {
      super(dept);
   }

   public TreeVo(SysOrg org) {
      super(org);
   }

   public TreeVo(SysMenu menu) {
      super(menu);
   }

   public TreeVo(SysDictType dictType) {
      super(dictType);
   }

   public TreeVo(SysDictData dictData) {
      super(dictData);
   }

   public TreeVo(BasDictMedicine basDictMedicine) {
      super(basDictMedicine);
   }

   public Long getId() {
      return super.getId();
   }

   public void setId(Long id) {
      super.setId(id);
   }

   public String getLabel() {
      return super.getLabel();
   }

   public void setLabel(String label) {
      super.setLabel(label);
   }

   public String getCode() {
      return super.getCode();
   }

   public void setCode(String code) {
      super.setCode(code);
   }

   public List getChildren() {
      return super.getChildren();
   }

   public void setChildren(List children) {
      super.setChildren(children);
   }

   public Boolean getEditFlag() {
      return this.editFlag;
   }

   public void setEditFlag(Boolean editFlag) {
      this.editFlag = editFlag;
   }

   public String getTempType() {
      return this.tempType;
   }

   public void setTempType(String tempType) {
      this.tempType = tempType;
   }
}
