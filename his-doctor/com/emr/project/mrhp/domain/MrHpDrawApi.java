package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpDrawApi extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "提取配置主键ID"
   )
   private Long mainId;
   @Excel(
      name = "请求参数"
   )
   private String parameter;
   @Excel(
      name = "请求参数类型"
   )
   private String parameterType;
   @Excel(
      name = "三方参数名"
   )
   private String parameterThird;
   @Excel(
      name = "对应his替换字符"
   )
   private String hisParameter;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setParameter(String parameter) {
      this.parameter = parameter;
   }

   public String getParameter() {
      return this.parameter;
   }

   public void setHisParameter(String hisParameter) {
      this.hisParameter = hisParameter;
   }

   public String getHisParameter() {
      return this.hisParameter;
   }

   public String getParameterType() {
      return this.parameterType;
   }

   public void setParameterType(String parameterType) {
      this.parameterType = parameterType;
   }

   public String getParameterThird() {
      return this.parameterThird;
   }

   public void setParameterThird(String parameterThird) {
      this.parameterThird = parameterThird;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("mainId", this.getMainId()).append("parameter", this.getParameter()).append("hisParameter", this.getHisParameter()).toString();
   }
}
