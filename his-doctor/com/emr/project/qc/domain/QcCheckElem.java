package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcCheckElem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long checkId;
   private String elemId;
   @Excel(
      name = "元素名称"
   )
   private String elemName;
   private Integer ruleType;
   private List checkIdList;

   public QcCheckElem() {
   }

   public QcCheckElem(Long checkId, String elemId, String elemName, Integer ruleType) {
      this.checkId = checkId;
      this.elemId = elemId;
      this.elemName = elemName;
      this.ruleType = ruleType;
   }

   public Integer getRuleType() {
      return this.ruleType;
   }

   public void setRuleType(Integer ruleType) {
      this.ruleType = ruleType;
   }

   public List getCheckIdList() {
      return this.checkIdList;
   }

   public void setCheckIdList(List checkIdList) {
      this.checkIdList = checkIdList;
   }

   public void setCheckId(Long checkId) {
      this.checkId = checkId;
   }

   public Long getCheckId() {
      return this.checkId;
   }

   public void setElemId(String elemId) {
      this.elemId = elemId;
   }

   public String getElemId() {
      return this.elemId;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemName() {
      return this.elemName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("checkId", this.getCheckId()).append("elemId", this.getElemId()).append("elemName", this.getElemName()).append("ruleType", this.getRuleType()).toString();
   }
}
