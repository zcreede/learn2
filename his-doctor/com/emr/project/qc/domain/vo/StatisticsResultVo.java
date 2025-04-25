package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.EmrQcFlowStatis;
import java.util.List;

public class StatisticsResultVo {
   private Integer total;
   private List charData;
   private List series;
   private String qcdoctCd;
   private String qcdoctName;
   private Integer flaws;
   private Integer checks;
   private Integer unChecks;
   private List xAxis;
   private StatementVo totalData;
   private StatementVo lineData;
   private List emrQcFlowStatisList;
   private List data;
   private String state;

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public List getData() {
      return this.data;
   }

   public void setData(List data) {
      this.data = data;
   }

   public List getEmrQcFlowStatisList() {
      return this.emrQcFlowStatisList;
   }

   public void setEmrQcFlowStatisList(List emrQcFlowStatisList) {
      this.emrQcFlowStatisList = emrQcFlowStatisList;
   }

   public StatementVo getTotalData() {
      return this.totalData;
   }

   public void setTotalData(StatementVo totalData) {
      this.totalData = totalData;
   }

   public StatementVo getLineData() {
      return this.lineData;
   }

   public void setLineData(StatementVo lineData) {
      this.lineData = lineData;
   }

   public List getSeries() {
      return this.series;
   }

   public void setSeries(List series) {
      this.series = series;
   }

   public List getxAxis() {
      return this.xAxis;
   }

   public void setxAxis(List xAxis) {
      this.xAxis = xAxis;
   }

   public String getQcdoctCd() {
      return this.qcdoctCd;
   }

   public void setQcdoctCd(String qcdoctCd) {
      this.qcdoctCd = qcdoctCd;
   }

   public String getQcdoctName() {
      return this.qcdoctName;
   }

   public void setQcdoctName(String qcdoctName) {
      this.qcdoctName = qcdoctName;
   }

   public Integer getFlaws() {
      return this.flaws;
   }

   public void setFlaws(Integer flaws) {
      this.flaws = flaws;
   }

   public Integer getChecks() {
      return this.checks;
   }

   public void setChecks(Integer checks) {
      this.checks = checks;
   }

   public Integer getUnChecks() {
      return this.unChecks;
   }

   public void setUnChecks(Integer unChecks) {
      this.unChecks = unChecks;
   }

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public List getCharData() {
      return this.charData;
   }

   public void setCharData(List charData) {
      this.charData = charData;
   }
}
