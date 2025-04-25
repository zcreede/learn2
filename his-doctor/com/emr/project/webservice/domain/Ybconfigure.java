package com.emr.project.webservice.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Ybconfigure extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("$column.columnComment")
   private Long id;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   @ApiModelProperty("$column.columnComment")
   private String name;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   @ApiModelProperty("$column.columnComment")
   private String bz;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   @ApiModelProperty("$column.columnComment")
   private String bz1;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   @ApiModelProperty("$column.columnComment")
   private String bz2;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setBz(String bz) {
      this.bz = bz;
   }

   public String getBz() {
      return this.bz;
   }

   public void setBz1(String bz1) {
      this.bz1 = bz1;
   }

   public String getBz1() {
      return this.bz1;
   }

   public void setBz2(String bz2) {
      this.bz2 = bz2;
   }

   public String getBz2() {
      return this.bz2;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("name", this.getName()).append("bz", this.getBz()).append("bz1", this.getBz1()).append("bz2", this.getBz2()).toString();
   }
}
