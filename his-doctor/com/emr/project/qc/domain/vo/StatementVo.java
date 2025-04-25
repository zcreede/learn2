package com.emr.project.qc.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatementVo {
   private String name;
   private Integer value;
   private Double ratio;
   private List data = new ArrayList(1);
   private List lineData = new ArrayList(1);
   private List ratioData = new ArrayList(1);
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date beginTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date endTime;

   public StatementVo() {
   }

   public StatementVo(String name, Integer value, Double ratio, List data) {
      this.name = name;
      this.value = value;
      this.ratio = ratio;
      this.data.addAll(data);
   }

   public StatementVo(String name, Integer value, Double ratio) {
      this.name = name;
      this.value = value;
      this.ratio = ratio;
   }

   public StatementVo(String name, List data) {
      this.name = name;
      this.data.addAll(data);
   }

   public StatementVo(String name, List lineData, Integer value) {
      this.name = name;
      this.lineData.addAll(lineData);
   }

   public StatementVo(String name, List lineData, List ratioData) {
      this.name = name;
      this.getRatioData().addAll(ratioData);
      this.getLineData().addAll(lineData);
   }

   public List getRatioData() {
      return this.ratioData;
   }

   public void setRatioData(List ratioData) {
      this.ratioData = ratioData;
   }

   public List getLineData() {
      return this.lineData;
   }

   public void setLineData(List lineData) {
      this.lineData = lineData;
   }

   public StatementVo(String name) {
      this.name = name;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getValue() {
      return this.value;
   }

   public void setValue(Integer value) {
      this.value = value;
   }

   public Double getRatio() {
      return this.ratio;
   }

   public void setRatio(Double ratio) {
      this.ratio = ratio;
   }

   public List getData() {
      return this.data;
   }

   public void setData(List data) {
      this.data = data;
   }
}
