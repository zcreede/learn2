package com.emr.project.esSearch.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.annotation.ESMetaData;
import org.zxp.esclientrhl.enums.Analyzer;
import org.zxp.esclientrhl.enums.DataType;

@ESMetaData(
   indexName = "emrfileelem",
   number_of_shards = 5,
   number_of_replicas = 0,
   printLog = true
)
public class EmrFileElem {
   @ESID
   private Long id;
   private Long mrFileId;
   @Excel(
      name = "病历文件分类名称"
   )
   private String mrTypeName;
   @Excel(
      name = "病历文件分类编号"
   )
   private String mrTypeCd;
   @Excel(
      name = "父文件元素ID"
   )
   private String prioFileElemid;
   @Excel(
      name = "父元素ID"
   )
   private Long prioElemId;
   @Excel(
      name = "父元素编码"
   )
   private String prioElemCd;
   @Excel(
      name = "父元素名称"
   )
   private String prioElemName;
   @Excel(
      name = "文件元素ID"
   )
   private String fileElemId;
   @Excel(
      name = "元素ID"
   )
   private Long elemId;
   @Excel(
      name = "元素编码"
   )
   private String elemCd;
   @Excel(
      name = "元素名称"
   )
   private String elemName;
   @Excel(
      name = "元素内容"
   )
   @ESMapping(
      datatype = DataType.text_type,
      analyzer = Analyzer.ik_max_word,
      search_analyzer = Analyzer.ik_max_word
   )
   private String elemCon;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getMrTypeCd() {
      return this.mrTypeCd;
   }

   public void setMrTypeCd(String mrTypeCd) {
      this.mrTypeCd = mrTypeCd;
   }

   public String getPrioFileElemid() {
      return this.prioFileElemid;
   }

   public void setPrioFileElemid(String prioFileElemid) {
      this.prioFileElemid = prioFileElemid;
   }

   public Long getPrioElemId() {
      return this.prioElemId;
   }

   public void setPrioElemId(Long prioElemId) {
      this.prioElemId = prioElemId;
   }

   public String getPrioElemCd() {
      return this.prioElemCd;
   }

   public void setPrioElemCd(String prioElemCd) {
      this.prioElemCd = prioElemCd;
   }

   public String getPrioElemName() {
      return this.prioElemName;
   }

   public void setPrioElemName(String prioElemName) {
      this.prioElemName = prioElemName;
   }

   public String getFileElemId() {
      return this.fileElemId;
   }

   public void setFileElemId(String fileElemId) {
      this.fileElemId = fileElemId;
   }

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemCon() {
      return this.elemCon;
   }

   public void setElemCon(String elemCon) {
      this.elemCon = elemCon;
   }
}
